package com.ubikgs.androidsensors.checkers.applevel;


import com.ubikgs.androidsensors.SensorType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

/**
 * Created by alberto on 15/9/17.
 */

public class BasicSensorRequirementChecker implements SensorRequirementChecker {

    private final Set<SensorType> criticalSensors;

    @Inject
    public BasicSensorRequirementChecker() {
        criticalSensors = new HashSet<>(Arrays.asList(SensorType.values()));

        criticalSensors.removeAll(Arrays.asList(
                SensorType.RAW_GPS_MEASUREMENTS,
                SensorType.RAW_GPS_NAVIGATION,
                SensorType.RAW_GPS_STATUS
        ));
    }

    @Override
    public boolean isRequired(SensorType sensorType) {
        return criticalSensors.contains(sensorType);
    }
}
