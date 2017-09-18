package com.ubikgs.androidsensors.checkers.internal;

import android.hardware.SensorManager;

import com.ubikgs.androidsensors.SensorType;
import com.ubikgs.androidsensors.utils.SensorTypeToAndroidSensor;

import javax.inject.Inject;

/**
 * Created by alberto on 11/9/17.
 */

public class IMUSensorChecker implements SensorChecker {

    private final SensorTypeToAndroidSensor sensorTypeToAndroidSensor;
    private final SensorManager sensorManager;

    @Inject
    public IMUSensorChecker(SensorTypeToAndroidSensor sensorTypeToAndroidSensor, SensorManager sensorManager) {
        this.sensorTypeToAndroidSensor = sensorTypeToAndroidSensor;
        this.sensorManager = sensorManager;
    }

    @Override
    public boolean isReady(SensorType sensorType) {
        int androidSensorType = sensorTypeToAndroidSensor.getAndroidSensorType(sensorType);

        return sensorManager.getDefaultSensor(androidSensorType) != null;
    }
}
