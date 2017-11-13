package com.ubikgs.androidsensors.modules;

import android.content.Context;

import com.ubikgs.androidsensors.checkers.applevel.BasicSensorRequirementChecker;
import com.ubikgs.androidsensors.checkers.applevel.SensorRequirementChecker;
import com.ubikgs.androidsensors.config.BasicSensorConfig;
import com.ubikgs.androidsensors.config.SensorConfig;
import com.ubikgs.androidsensors.enablers.BasicSensorEnableRequester;
import com.ubikgs.androidsensors.enablers.BluetoothSensorEnableRequester;
import com.ubikgs.androidsensors.enablers.GPSSensorEnableRequester;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;
import com.ubikgs.androidsensors.enablers.WifiSensorEnableRequester;
import com.ubikgs.androidsensors.utils.MillisecondsToMicroseconds;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
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
@RunWith(MockitoJUnitRunner.class)
public class AndroidSensorsEdgeModuleTest {

    @Mock Context context;
    @Mock MillisecondsToMicroseconds millisecondsToMicroseconds;

    @Mock SensorEnableRequester defaultSensorEnableRequester;
    @Mock SensorEnableRequester gpsSensorEnableRequester;
    @Mock SensorEnableRequester wifiSensorEnableRequester;
    @Mock SensorEnableRequester bluetoothSensorEnableRequester;
    @Mock SensorRequirementChecker sensorRequirementChecker;
    @Mock SensorConfig sensorConfig;

    private AndroidSensorsEdgeModule defaultModule;
    private AndroidSensorsEdgeModule customModule;

    @Before
    public void setUp() throws Exception {
        defaultModule = new AndroidSensorsEdgeModule();
        customModule = new AndroidSensorsEdgeModule(defaultSensorEnableRequester,
                gpsSensorEnableRequester, wifiSensorEnableRequester, bluetoothSensorEnableRequester,
                sensorRequirementChecker, sensorConfig);
    }

    @Test
    public void provideBasicSensorEnableRequester_withDefaultModule_returnsBasicSensorEnableRequester()
            throws Exception {
        assertThat(defaultModule.provideBasicSensorEnableRequester(context).getClass(),
                equalTo((Class) BasicSensorEnableRequester.class));
    }

    @Test
    public void provideGPSSensorEnableRequester_withDefaultModule_returnsGPSSensorEnableRequester()
            throws Exception {
        assertThat(defaultModule.provideGPSSensorEnableRequester(context).getClass(),
                equalTo((Class) GPSSensorEnableRequester.class));
    }

    @Test
    public void provideWifiSensorEnableRequester_withDefaultModule_returnsWifiSensorEnableRequester()
            throws Exception {
        assertThat(defaultModule.provideWiFiSensorEnableRequester(context).getClass(),
                equalTo((Class) WifiSensorEnableRequester.class));
    }

    @Test
    public void provideBluetoothSensorEnableRequester_withDefaultModule_returnsWifiSensorEnableRequester()
            throws Exception {
        assertThat(defaultModule.provideBluetoothSensorEnableRequester(context).getClass(),
                equalTo((Class) BluetoothSensorEnableRequester.class));
    }

    @Test
    public void provideBasicSensorRequirementChecker_withDefaultModule_returnsBasicCriticalityChecker()
            throws Exception {
        assertThat(defaultModule.provideSensorRequirementChecker().getClass(),
                equalTo((Class) BasicSensorRequirementChecker.class));
    }

    @Test
    public void provideBasicSensorConfig_withDefaultModule_returnsBasicSensorConfig()
            throws Exception {
        assertThat(defaultModule.provideBasicSensorConfig(millisecondsToMicroseconds).getClass(),
                equalTo((Class) BasicSensorConfig.class));
    }

    @Test
    public void provideBasicSensorEnableRequester_withCustomModule_returnsDefaultSensorEnableRequester()
            throws Exception {
        assertThat(customModule.provideBasicSensorEnableRequester(context),
                is(defaultSensorEnableRequester));
    }

    @Test
    public void provideGPSSensorEnableRequester_withCustomModule_returnsGPSSensorEnableRequester()
            throws Exception {
        assertThat(customModule.provideGPSSensorEnableRequester(context),
                is(gpsSensorEnableRequester));
    }

    @Test
    public void provideWifiSensorEnableRequester_withCustomModule_returnsWifiSensorEnableRequester()
            throws Exception {
        assertThat(customModule.provideWiFiSensorEnableRequester(context),
                is(wifiSensorEnableRequester));
    }

    @Test
    public void provideBluetoothSensorEnableRequester_withCustomModule_returnsWifiSensorEnableRequester()
            throws Exception {
        assertThat(customModule.provideBluetoothSensorEnableRequester(context),
                is(bluetoothSensorEnableRequester));
    }

    @Test
    public void provideBasicSensorRequirementChecker_withCustomModule_returnsCriticalityChecker()
            throws Exception {
        assertThat(customModule.provideSensorRequirementChecker(),
                is(sensorRequirementChecker));
    }

    @Test
    public void provideBasicSensorConfig_withCustomModule_returnsBasicSensorConfig()
            throws Exception {
        assertThat(customModule.provideBasicSensorConfig(millisecondsToMicroseconds),
                is(sensorConfig));
    }

}