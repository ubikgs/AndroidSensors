package com.ubikgs.androidsensors.config;

import com.ubikgs.androidsensors.SensorType;

import io.reactivex.BackpressureStrategy;

/**
 * Created by alberto on 15/9/17.
 */

public interface SensorConfig {
    long getMinSensorDelay(SensorType sensorType);

    BackpressureStrategy getBackpressureStrategy(SensorType sensorType);
}
