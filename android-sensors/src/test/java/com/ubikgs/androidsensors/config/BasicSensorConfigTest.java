package com.ubikgs.androidsensors.config;

import com.ubikgs.androidsensors.SensorType;
import com.ubikgs.androidsensors.utils.MillisecondsToMicroseconds;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
public class BasicSensorConfigTest {

    private final long MIN_DELAY_MS = 100;

    @Mock MillisecondsToMicroseconds millisecondsToMicroseconds;

    private BasicSensorConfig basicSensorConfig;

    @Before
    public void setUp() throws Exception {
        basicSensorConfig = new BasicSensorConfig(MIN_DELAY_MS, millisecondsToMicroseconds);
    }

    @Test
    public void getMinSensorDelay_forAllIMUSensor_returnsInUs() throws Exception {
        HashSet<SensorType> imuSensors = new HashSet<>(Arrays.asList(
                SensorType.ACCELEROMETER,
                SensorType.GRAVITY,
                SensorType.GYROSCOPE,
                SensorType.LINEAR_ACCELERATION,
                SensorType.MAGNETIC_FIELD,
                SensorType.ROTATION_VECTOR
        ));

        Observable.fromIterable(imuSensors)
                .map(new Function<SensorType, Long>() {
                    @Override
                    public Long apply(SensorType sensorType) throws Exception {
                        return basicSensorConfig.getMinSensorDelay(sensorType);
                    }
                })
                .blockingSubscribe();

        verify(millisecondsToMicroseconds, times(imuSensors.size())).convert(MIN_DELAY_MS);
    }

    @Test
    public void getMinSensorDelay_forAllNonIMUSensor_returnsInMs() throws Exception {
        HashSet<SensorType> nonIMUSensors = new HashSet<>(Arrays.asList(
                SensorType.LOCATION,
                SensorType.RAW_GPS_MEASUREMENTS,
                SensorType.RAW_GPS_NAVIGATION,
                SensorType.RAW_GPS_STATUS
        ));

        Long count = Observable.fromIterable(nonIMUSensors)
                .map(new Function<SensorType, Long>() {
                    @Override
                    public Long apply(SensorType sensorType) throws Exception {
                        return basicSensorConfig.getMinSensorDelay(sensorType);
                    }
                })
                .filter(new Predicate<Long>() {
                    @Override
                    public boolean test(Long delay) throws Exception {
                        return delay.equals(MIN_DELAY_MS);
                    }
                })
                .count()
                .blockingGet();

        assertThat(count.intValue(), equalTo(nonIMUSensors.size()));
    }

    @Test
    public void getBackpressureStrategy_forAllSensors_returnsBufferStrategy() throws Exception {
        HashSet<SensorType> allSensors = new HashSet<>(Arrays.asList(SensorType.values()));

        Long count = Observable.fromIterable(allSensors)
                .map(new Function<SensorType, BackpressureStrategy>() {
                    @Override
                    public BackpressureStrategy apply(SensorType sensorType) throws Exception {
                        return basicSensorConfig.getBackpressureStrategy(sensorType);
                    }
                })
                .filter(new Predicate<BackpressureStrategy>() {
                    @Override
                    public boolean test(BackpressureStrategy backpressureStrategy) throws Exception {
                        return backpressureStrategy.equals(BackpressureStrategy.BUFFER);
                    }
                })
                .count()
                .blockingGet();

        assertThat(count.intValue(), equalTo(allSensors.size()));
    }
}