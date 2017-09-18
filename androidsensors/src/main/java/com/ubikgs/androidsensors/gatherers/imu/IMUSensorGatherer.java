package com.ubikgs.androidsensors.gatherers.imu;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.ubikgs.androidsensors.checkers.applevel.CriticalityChecker;
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
 * Created by alberto on 13/9/17.
 */

public abstract class IMUSensorGatherer extends AbstractSensorGatherer {

    private final SensorManager sensorManager;
    private final SensorTypeToAndroidSensor sensorTypeToAndroidSensor;

    public IMUSensorGatherer(SensorConfig sensorConfig,
                             SensorManager sensorManager,
                             SensorEnableRequester sensorEnableRequester,
                             PermissionChecker permissionChecker,
                             SensorChecker sensorChecker,
                             CriticalityChecker criticalityChecker,
                             SensorTypeToAndroidSensor sensorTypeToAndroidSensor) {

        super(sensorConfig,
                sensorEnableRequester, permissionChecker, sensorChecker, criticalityChecker);
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
