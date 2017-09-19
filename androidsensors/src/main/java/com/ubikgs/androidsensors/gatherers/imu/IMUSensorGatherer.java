package com.ubikgs.androidsensors.gatherers.imu;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.ubikgs.androidsensors.checkers.applevel.SensorRequirementChecker;
import com.ubikgs.androidsensors.checkers.internal.SensorChecker;
import com.ubikgs.androidsensors.checkers.permissions.PermissionChecker;
import com.ubikgs.androidsensors.config.SensorConfig;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;
import com.ubikgs.androidsensors.factories.SensorRecordFactory;
import com.ubikgs.androidsensors.gatherers.AbstractSensorGatherer;
import com.ubikgs.androidsensors.records.SensorRecord;
import com.ubikgs.androidsensors.utils.SensorTypeToAndroidSensor;

import io.reactivex.FlowableEmitter;

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

public abstract class IMUSensorGatherer extends AbstractSensorGatherer {

    private final SensorManager sensorManager;
    private final SensorTypeToAndroidSensor sensorTypeToAndroidSensor;

    public IMUSensorGatherer(SensorConfig sensorConfig,
                             SensorManager sensorManager,
                             SensorEnableRequester sensorEnableRequester,
                             PermissionChecker permissionChecker,
                             SensorChecker sensorChecker,
                             SensorRequirementChecker sensorRequirementChecker,
                             SensorTypeToAndroidSensor sensorTypeToAndroidSensor) {

        super(sensorConfig,
                sensorEnableRequester, permissionChecker, sensorChecker, sensorRequirementChecker);
        this.sensorManager = sensorManager;
        this.sensorTypeToAndroidSensor = sensorTypeToAndroidSensor;

    }

    @Override
    protected void configureSensorSubscribeAndUnsubscribeBehaviors(FlowableEmitter<SensorRecord> subscriber) {
        final SensorEventListener sensorEventListener = initializeSensorEventListenerFor(subscriber);

        startListeningSensorChanges(sensorEventListener);
        addUnsubscribeCallbackFor(subscriber, sensorEventListener);
    }

    private SensorEventListener initializeSensorEventListenerFor(FlowableEmitter<SensorRecord> subscriber) {
        return new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent event) {
                subscriber.onNext(createSensorRecordFrom(event));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        };
    }

    private void startListeningSensorChanges(SensorEventListener sensorEventListener) {
        final Sensor sensor = getDefaultSensor();
        sensorManager.registerListener(sensorEventListener,
                sensor,
                (int) sensorConfig.getMinSensorDelay(getSensorType()));
    }

    private void addUnsubscribeCallbackFor(FlowableEmitter<SensorRecord> subscriber, SensorEventListener sensorEventListener) {
        subscriber.setCancellable(() -> sensorManager.unregisterListener(sensorEventListener));
    }

    private Sensor getDefaultSensor() {
        final Sensor sensor = sensorManager.getDefaultSensor(getAndroidSensorType());
        if (sensor != null) {
            Log.i(this.getClass().getName(), "Default sensor: " + sensor.toString());
        }
        return sensor;
    }

    protected int getAndroidSensorType() {
        return sensorTypeToAndroidSensor.getAndroidSensorType(getSensorType());
    }

    protected SensorRecord createSensorRecordFrom(SensorEvent event) {
        return SensorRecordFactory.createFrom(getSensorType(), event);
    }
}
