package com.ubikgs.androidsensors.checkers.applevel;

import com.ubikgs.androidsensors.SensorType;

/**
 * Created by alberto on 15/9/17.
 */

public interface CriticalityChecker {
    boolean isCritical(SensorType sensorType);
}
