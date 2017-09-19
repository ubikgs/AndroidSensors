package com.ubikgs.androidsensors.gatherers.gps;

import android.location.LocationManager;

import com.ubikgs.androidsensors.checkers.applevel.SensorRequirementChecker;
import com.ubikgs.androidsensors.checkers.internal.SensorChecker;
import com.ubikgs.androidsensors.checkers.permissions.PermissionChecker;
import com.ubikgs.androidsensors.config.SensorConfig;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;
import com.ubikgs.androidsensors.gatherers.AbstractSensorGatherer;

/**
 * Created by alberto on 13/9/17.
 */

public abstract class GPSGatherer extends AbstractSensorGatherer {

    protected final LocationManager locationManager;

    public GPSGatherer(SensorConfig sensorConfig,
                       LocationManager locationManager,
                       SensorEnableRequester sensorEnableRequester,
                       PermissionChecker permissionChecker,
                       SensorChecker sensorChecker,
                       SensorRequirementChecker sensorRequirementChecker) {

        super(sensorConfig, sensorEnableRequester, permissionChecker, sensorChecker, sensorRequirementChecker);
        this.locationManager = locationManager;
    }
}
