package com.ubikgs.androidsensors;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;

import com.ubikgs.androidsensors.checkers.applevel.SensorRequirementChecker;
import com.ubikgs.androidsensors.config.SensorConfig;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;
import com.ubikgs.androidsensors.gatherers.SensorGatherer;
import com.ubikgs.androidsensors.gatherers.gps.LocationGatherer;
import com.ubikgs.androidsensors.gatherers.imu.AccelerometerGatherer;
import com.ubikgs.androidsensors.gatherers.wifi.WifiMeasurementsGatherer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

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
@RunWith(AndroidJUnit4.class)
public class AndroidSensorsIntegrationTest {

    private static final int GATHERER_NUMBER = 11;

    @Inject Context context;
    private Helper helper;

    private AndroidSensors defaultAndroidSensors;
    private AndroidSensors customAndroidSensors;


    @Before
    public void setUp() throws Exception {
        DaggerTestBedComponent.create().inject(this);

        helper = new Helper();

        defaultAndroidSensors = AndroidSensors
                .builder()
                .build(context);

        customAndroidSensors = AndroidSensors
                .builder()
                .customDefaultEnableRequester(helper.defaultSensorEnableRequester)
                .customGPSEnableRequester(helper.gpsSensorEnableRequester)
                .customWifiEnableRequester(helper.wifiSensorEnableRequester)
                .customSensorRequirementChecker(helper.sensorRequirementChecker)
                .customSensorConfig(helper.sensorConfig)
                .build(context);
    }

    @Test
    public void sensorGatherers_withDefaultConfigIsEmpty_isFalse() throws Exception {
        boolean empty = defaultAndroidSensors.allSensorGatherers().isEmpty();
        assertThat(empty, is(false));
    }

    @Test
    public void sensorGatherers_withDefaultConfigEqualsToGathererNumber_isTrue() throws Exception {
        int empty = defaultAndroidSensors.allSensorGatherers().size();
        assertThat(empty, equalTo(GATHERER_NUMBER));
    }

    @Test
    public void sensorGathererFor_withDefaultConfig_returnsAccelerometerGatherer() throws Exception {
        SensorGatherer sensorGatherer =
                defaultAndroidSensors.sensorGathererBy(SensorType.ACCELEROMETER);

        assertThat(sensorGatherer, not(equalTo(null)));
    }

    @Test
    public void sensorGatherer_withDefaultConfig_returnsAccelerometerGatherer() throws Exception {
        AccelerometerGatherer accelerometerGatherer =
                defaultAndroidSensors.sensorGatherer(AccelerometerGatherer.class);

        assertThat(accelerometerGatherer, not(equalTo(null)));
    }

    @Test
    public void sensorGatherer_withDefaultConfig_returnsLocationGatherer() throws Exception {
        LocationGatherer locationGatherer =
                defaultAndroidSensors.sensorGatherer(LocationGatherer.class);

        assertThat(locationGatherer, not(equalTo(null)));
    }

    @Test
    public void sensorGatherer_withCustomConfig_returnsWithCustomDefaultEnableRequester() throws Exception {
        AccelerometerGatherer accelerometerGatherer =
                customAndroidSensors.sensorGatherer(AccelerometerGatherer.class);

        accelerometerGatherer.askForEnabling();
        assertThat(helper.defaultSensorEnableRequesterCalled, is(true));
    }

    @Test
    public void sensorGatherer_withCustomConfig_returnsWithCustomGPSEnableRequester() throws Exception {
        LocationGatherer locationGatherer =
                customAndroidSensors.sensorGatherer(LocationGatherer.class);

        locationGatherer.askForEnabling();
        assertThat(helper.gpsEnableRequesterCalled, is(true));
    }

    @Test
    public void sensorGatherer_withCustomConfig_returnsWithCustomWifiEnableRequester() throws Exception {
        WifiMeasurementsGatherer wifiMeasurementsGatherer =
                customAndroidSensors.sensorGatherer(WifiMeasurementsGatherer.class);

        wifiMeasurementsGatherer.askForEnabling();
        assertThat(helper.wifiEnableRequesterCalled, is(true));
    }

    @Test
    public void sensorGatherer_withCustomConfig_returnsWithCustomSensorRequirementChecker() throws Exception {
        AccelerometerGatherer accelerometerGatherer =
                customAndroidSensors.sensorGatherer(AccelerometerGatherer.class);

        accelerometerGatherer.isRequired();
        assertThat(helper.sensorRequirementCheckerCalled, is(true));
    }

    @Test
    public void sensorGatherer_withCustomConfig_returnsWithCustomSensorConfig() throws Exception {
        AccelerometerGatherer accelerometerGatherer =
                customAndroidSensors.sensorGatherer(AccelerometerGatherer.class);

        accelerometerGatherer.recordStream().subscribe().dispose();
        assertThat(helper.sensorConfigCalled, is(true));
    }

    /*
    * Helper objects
    * */

    private class Helper {
        boolean defaultSensorEnableRequesterCalled = false;
        boolean gpsEnableRequesterCalled = false;
        boolean wifiEnableRequesterCalled = false;
        boolean sensorRequirementCheckerCalled = false;
        boolean sensorConfigCalled = false;

        SensorEnableRequester defaultSensorEnableRequester = new SensorEnableRequester() {
            @Override
            public void performEnableRequestFor(SensorType sensorType) {
                defaultSensorEnableRequesterCalled = true;
            }
        };

        SensorEnableRequester gpsSensorEnableRequester = new SensorEnableRequester() {
            @Override
            public void performEnableRequestFor(SensorType sensorType) {
                gpsEnableRequesterCalled = true;
            }
        };

        SensorEnableRequester wifiSensorEnableRequester = new SensorEnableRequester() {
            @Override
            public void performEnableRequestFor(SensorType sensorType) {
                wifiEnableRequesterCalled = true;
            }
        };

        SensorRequirementChecker sensorRequirementChecker = new SensorRequirementChecker() {
            @Override
            public boolean isRequired(SensorType sensorType) {
                sensorRequirementCheckerCalled = true;
                return true;
            }
        };

        SensorConfig sensorConfig = new SensorConfig() {
            @Override
            public long getMinSensorDelay(SensorType sensorType) {
                sensorConfigCalled = true;
                return 0;
            }

            @Override
            public BackpressureStrategy getBackpressureStrategy(SensorType sensorType) {
                sensorConfigCalled = true;
                return BackpressureStrategy.BUFFER;
            }
        };
    }
}