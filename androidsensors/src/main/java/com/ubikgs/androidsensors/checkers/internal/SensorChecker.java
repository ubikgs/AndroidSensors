package com.ubikgs.androidsensors.checkers.internal;

import com.ubikgs.androidsensors.SensorType;

/**
 * Created by alberto on 11/9/17.
 */

public interface SensorChecker {
    boolean isReady(SensorType sensorType);
}
