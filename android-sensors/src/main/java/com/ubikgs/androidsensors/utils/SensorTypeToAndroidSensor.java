package com.ubikgs.androidsensors.utils;

import android.hardware.Sensor;

import com.ubikgs.androidsensors.SensorType;

import java.util.HashMap;
import java.util.Map;

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
