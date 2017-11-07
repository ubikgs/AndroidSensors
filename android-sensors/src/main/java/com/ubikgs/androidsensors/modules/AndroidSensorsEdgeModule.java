package com.ubikgs.androidsensors.modules;

import android.content.Context;

import com.ubikgs.androidsensors.checkers.applevel.BasicSensorRequirementChecker;
import com.ubikgs.androidsensors.checkers.applevel.SensorRequirementChecker;
import com.ubikgs.androidsensors.config.BasicSensorConfig;
import com.ubikgs.androidsensors.config.SensorConfig;
import com.ubikgs.androidsensors.enablers.BasicSensorEnableRequester;
import com.ubikgs.androidsensors.enablers.GPSSensorEnableRequester;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;
import com.ubikgs.androidsensors.enablers.WifiSensorEnableRequester;
import com.ubikgs.androidsensors.utils.MillisecondsToMicroseconds;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

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

@Module
public class AndroidSensorsEdgeModule {

    private static final long DEFAULT_MIN_SENSOR_DELAY = 20L;

    private SensorEnableRequester defaultSensorEnableRequester;
    private SensorEnableRequester gpsSensorEnableRequester;
    private SensorEnableRequester wifiSensorEnableRequester;
    private SensorRequirementChecker sensorRequirementChecker;
    private SensorConfig sensorConfig;

    public AndroidSensorsEdgeModule() {
    }

    public AndroidSensorsEdgeModule(SensorEnableRequester defaultSensorEnableRequester,
                                    SensorEnableRequester gpsSensorEnableRequester,
                                    SensorEnableRequester wifiSensorEnableRequester,
                                    SensorRequirementChecker sensorRequirementChecker,
                                    SensorConfig sensorConfig) {

        this.defaultSensorEnableRequester = defaultSensorEnableRequester;
        this.gpsSensorEnableRequester = gpsSensorEnableRequester;
        this.wifiSensorEnableRequester = wifiSensorEnableRequester;
        this.sensorRequirementChecker = sensorRequirementChecker;
        this.sensorConfig = sensorConfig;
    }

    /*
    * SensorEnableRequesters
    * */

    @Provides
    SensorEnableRequester provideBasicSensorEnableRequester(Context context) {
        return defaultSensorEnableRequester != null ?
                defaultSensorEnableRequester : new BasicSensorEnableRequester(context);
    }

    @Provides
    @Named("gpsSensorEnableRequester")
    SensorEnableRequester provideGPSSensorEnableRequester(Context context) {
        return gpsSensorEnableRequester != null ?
                gpsSensorEnableRequester : new GPSSensorEnableRequester(context);
    }

    @Provides
    @Named("wifiSensorEnableRequester")
    SensorEnableRequester provideWiFiSensorEnableRequester(Context context){
        return wifiSensorEnableRequester != null ?
                wifiSensorEnableRequester : new WifiSensorEnableRequester(context);
    }

    /*
    * SensorRequirementCheckers
    * */

    @Provides
    SensorRequirementChecker provideBasicCriticalityChecker() {
        return sensorRequirementChecker != null ?
                sensorRequirementChecker : new BasicSensorRequirementChecker();
    }

    /*
    * SensorConfigurers
    * */

    @Provides
    SensorConfig provideBasicSensorConfig(MillisecondsToMicroseconds millisecondsToMicroseconds) {
        return sensorConfig != null ?
                sensorConfig : new BasicSensorConfig(DEFAULT_MIN_SENSOR_DELAY, millisecondsToMicroseconds);
    }
}
