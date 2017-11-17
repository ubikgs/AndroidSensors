package com.ubikgs.androidsensors.checkers.applevel;

import com.ubikgs.androidsensors.SensorType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 *  Copyright 2017 Alberto González Pérez
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     <p>http://www.apache.org/licenses/LICENSE-2.0</p>
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
@RunWith(MockitoJUnitRunner.class)
public class BasicSensorRequirementCheckerTest {

    private BasicSensorRequirementChecker basicSensorRequirementChecker;

    @Before
    public void setUp() throws Exception {
        basicSensorRequirementChecker = new BasicSensorRequirementChecker();
    }

    @Test
    public void isRequired_IMULocationWifiAndBluetooth_areRequired() throws Exception {
        HashSet<SensorType> sensorTypes = new HashSet<>();
        sensorTypes.addAll(Arrays.asList(SensorType.imuValues()));
        sensorTypes.add(SensorType.LOCATION);
        sensorTypes.addAll(Arrays.asList(SensorType.wifiValues()));
        sensorTypes.addAll(Arrays.asList(SensorType.bluetoothValues()));

        Long count = Observable.fromIterable(sensorTypes)
                .map(new Function<SensorType, Boolean>() {
                    @Override
                    public Boolean apply(SensorType sensorType) throws Exception {
                        return basicSensorRequirementChecker.isRequired(sensorType);
                    }
                }).filter(new Predicate<Boolean>() {
                    @Override
                    public boolean test(Boolean required) throws Exception {
                        return required;
                    }
                }).count()
                .blockingGet();

        assertThat(count.intValue(), equalTo(sensorTypes.size()));
    }

    @Test
    public void isRequired_rawGPS_areNotRequired() throws Exception {
        HashSet<SensorType> sensorTypes =
                new HashSet<>(Arrays.asList(SensorType.rawGPSValues()));

        Long count = Observable.fromIterable(sensorTypes)
                .map(new Function<SensorType, Boolean>() {
                    @Override
                    public Boolean apply(SensorType sensorType) throws Exception {
                        return basicSensorRequirementChecker.isRequired(sensorType);
                    }
                }).filter(new Predicate<Boolean>() {
                    @Override
                    public boolean test(Boolean required) throws Exception {
                        return !required;
                    }
                }).count()
                .blockingGet();

        assertThat(count.intValue(), equalTo(sensorTypes.size()));
    }
}