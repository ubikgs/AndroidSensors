package com.ubikgs.androidsensors.utils;

import android.hardware.Sensor;

import com.ubikgs.androidsensors.SensorType;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by alberto on 11/9/17.
 */

public class SensorTypeToAndroidSensor {

    private final Map<SensorType, Integer> androidSensorTypes = new HashMap<>();

    @Inject
    public SensorTypeToAndroidSensor() {
        androidSensorTypes.put(SensorType.ACCELEROMETER, Sensor.TYPE_ACCELEROMETER);
        androidSensorTypes.put(SensorType.GRAVITY, Sensor.TYPE_GRAVITY);
        androidSensorTypes.put(SensorType.GYROSCOPE, Sensor.TYPE_GYROSCOPE);
        androidSensorTypes.put(SensorType.LINEAR_ACCELERATION, Sensor.TYPE_LINEAR_ACCELERATION);
        androidSensorTypes.put(SensorType.MAGNETIC_FIELD, Sensor.TYPE_MAGNETIC_FIELD);
        androidSensorTypes.put(SensorType.ROTATION_VECTOR, Sensor.TYPE_ROTATION_VECTOR);
    }

    public int getAndroidSensorType(SensorType sensorType) {
        if (!androidSensorTypes.containsKey(sensorType))
            throw new Error(String.format("%s is not available on the map, please add it.", sensorType));

        return androidSensorTypes.get(sensorType);
    }
}
