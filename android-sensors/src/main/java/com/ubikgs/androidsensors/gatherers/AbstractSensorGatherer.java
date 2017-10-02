package com.ubikgs.androidsensors.gatherers;

import com.ubikgs.androidsensors.SensorType;
import com.ubikgs.androidsensors.checkers.applevel.SensorRequirementChecker;
import com.ubikgs.androidsensors.checkers.internal.SensorChecker;
import com.ubikgs.androidsensors.checkers.permissions.PermissionChecker;
import com.ubikgs.androidsensors.config.SensorConfig;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;
import com.ubikgs.androidsensors.records.SensorRecord;

import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

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

public abstract class AbstractSensorGatherer implements SensorGatherer {

    protected final SensorConfig sensorConfig;
    private final SensorEnableRequester sensorEnableRequester;
    private final PermissionChecker permissionChecker;
    private final SensorChecker sensorChecker;
    private final SensorRequirementChecker sensorRequirementChecker;

    public AbstractSensorGatherer(SensorConfig sensorConfig,
                                  SensorEnableRequester sensorEnableRequester,
                                  PermissionChecker permissionChecker,
                                  SensorChecker sensorChecker,
                                  SensorRequirementChecker sensorRequirementChecker) {

        this.sensorConfig = sensorConfig;
        this.sensorEnableRequester = sensorEnableRequester;
        this.permissionChecker = permissionChecker;
        this.sensorChecker = sensorChecker;
        this.sensorRequirementChecker = sensorRequirementChecker;
    }

    public Flowable<SensorRecord> recordStream() {

        if (!isReady() || !hasPermissionGranted())
            return Flowable.error(new Error("Sensor status was not checked before asking for a data stream. " +
                    "Please, ensure that you're calling isReady() and hasPermissionGranted()."));


        return Flowable.create(new FlowableOnSubscribe<SensorRecord>() {
                                   @Override
                                   public void subscribe(FlowableEmitter<SensorRecord> e) throws Exception {
                                       configureSensorSubscribeAndUnsubscribeBehaviors(e);
                                   }
                               },
                sensorConfig.getBackpressureStrategy(getSensorType()));
    }

    protected abstract void configureSensorSubscribeAndUnsubscribeBehaviors(FlowableEmitter<SensorRecord> subscriber);

    public String getNeededPermission() {
        return permissionChecker.getNeededPermission();
    }

    public boolean hasPermissionGranted() {
        return permissionChecker.isPermissionGranted();
    }

    public boolean isReady() {
        return sensorChecker.isReady(getSensorType());
    }

    public void askForEnabling() {
        sensorEnableRequester.performEnableRequestFor(getSensorType());
    }

    @Override
    public boolean isRequired() {
        return sensorRequirementChecker.isRequired(getSensorType());
    }

    public abstract SensorType getSensorType();
}
