package com.ubikgs.androidsensors.checkers.applevel;

import com.ubikgs.androidsensors.SensorType;

/**
 * Created by alberto on 15/9/17.
 */

public interface SensorRequirementChecker {
    boolean isRequired(SensorType sensorType);
}
