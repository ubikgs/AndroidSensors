package com.ubikgs.androidsensors;

import android.content.Context;

import com.ubikgs.androidsensors.checkers.applevel.SensorRequirementChecker;
import com.ubikgs.androidsensors.config.SensorConfig;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;
import com.ubikgs.androidsensors.gatherers.SensorGatherer;
import com.ubikgs.androidsensors.modules.AndroidSensorsEdgeModule;
import com.ubikgs.androidsensors.modules.AndroidSystemModule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

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

public class AndroidSensors {

    @Inject Set<SensorGatherer> sensorGatherers;

    private final Map<Class<? extends SensorGatherer>, SensorGatherer> mappedGatherersByClass;
    private final Map<SensorType, SensorGatherer> mappedGatherersByType;

    private AndroidSensors(Context applicationContext,
                           SensorEnableRequester defaultSensorEnableRequester,
                           SensorEnableRequester gpsSensorEnableRequester,
                           SensorEnableRequester wifiSensorEnableRequester,
                           SensorRequirementChecker sensorRequirementChecker,
                           SensorConfig sensorConfig) {

        this.mappedGatherersByClass = new HashMap<>();
        this.mappedGatherersByType = new HashMap<>();

        DaggerAndroidSensorsComponent.builder()
                .androidSystemModule(new AndroidSystemModule(applicationContext))
                .androidSensorsEdgeModule(new AndroidSensorsEdgeModule(
                        defaultSensorEnableRequester,
                        gpsSensorEnableRequester,
                        wifiSensorEnableRequester,
                        sensorRequirementChecker,
                        sensorConfig
                )).build()
                .inject(this);

        createGatherersMap();
    }

    private void createGatherersMap() {
        for (SensorGatherer sensorGatherer : sensorGatherers) {
            mappedGatherersByClass.put(sensorGatherer.getClass(), sensorGatherer);
            mappedGatherersByType.put(sensorGatherer.getSensorType(), sensorGatherer);
        }
    }

    public Set<SensorGatherer> allSensorGatherers() {
        return new HashSet<>(sensorGatherers);
    }

    public <T extends SensorGatherer> T sensorGatherer(Class<T> type) {

        if (!mappedGatherersByClass.containsKey(type))
            throw new GathererNotAvailableException(type);

        return (T) mappedGatherersByClass.get(type);
    }

    public SensorGatherer sensorGathererBy(SensorType sensorType) {

        if (!mappedGatherersByType.containsKey(sensorType))
            throw new GathererNotAvailableException(sensorType);

        return mappedGatherersByType.get(sensorType);
    }

    /*
    * Builder
    * */

    public interface Builder {
        Builder customDefaultEnableRequester(SensorEnableRequester defaultEnableRequester);
        Builder customGPSEnableRequester(SensorEnableRequester gpsEnableRequester);
        Builder customWifiEnableRequester(SensorEnableRequester wifiSensorEnableRequester);
        Builder customSensorRequirementChecker(SensorRequirementChecker sensorRequirementChecker);
        Builder customSensorConfig(SensorConfig sensorConfig);
        AndroidSensors build(Context context);

    }

    public static Builder builder() {

        return new Builder() {

            private SensorEnableRequester defaultSensorEnableRequester;
            private SensorEnableRequester gpsSensorEnableRequester;
            private SensorEnableRequester wifiSensorEnableRequester;
            private SensorRequirementChecker sensorRequirementChecker;
            private SensorConfig sensorConfig;

            @Override
            public Builder customDefaultEnableRequester(SensorEnableRequester defaultEnableRequester) {
                this.defaultSensorEnableRequester = defaultEnableRequester;
                return this;
            }

            @Override
            public Builder customGPSEnableRequester(SensorEnableRequester gpsEnableRequester) {
                this.gpsSensorEnableRequester = gpsEnableRequester;
                return this;
            }

            @Override
            public Builder customWifiEnableRequester(SensorEnableRequester wifiSensorEnableRequester) {
                this.wifiSensorEnableRequester = wifiSensorEnableRequester;
                return this;
            }

            @Override
            public Builder customSensorRequirementChecker(SensorRequirementChecker sensorRequirementChecker) {
                this.sensorRequirementChecker = sensorRequirementChecker;
                return this;
            }

            @Override
            public Builder customSensorConfig(SensorConfig sensorConfig) {
                this.sensorConfig = sensorConfig;
                return this;
            }

            @Override
            public AndroidSensors build(Context applicationContext) {
                return new AndroidSensors(applicationContext,
                        defaultSensorEnableRequester,
                        gpsSensorEnableRequester,
                        wifiSensorEnableRequester,
                        sensorRequirementChecker,
                        sensorConfig);
            }
        };
    }
}
