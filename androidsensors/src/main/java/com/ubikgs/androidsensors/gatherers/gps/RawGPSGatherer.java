package com.ubikgs.androidsensors.gatherers.gps;

import android.location.LocationManager;

import com.ubikgs.androidsensors.checkers.applevel.CriticalityChecker;
import com.ubikgs.androidsensors.checkers.internal.SensorChecker;
import com.ubikgs.androidsensors.checkers.permissions.PermissionChecker;
import com.ubikgs.androidsensors.config.SensorConfig;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;

/**
 * Created by alberto on 13/9/17.
 */

public abstract class RawGPSGatherer extends GPSGatherer {

    public RawGPSGatherer(SensorConfig sensorConfig,
                          LocationManager locationManager,
                          SensorEnableRequester sensorEnableRequester,
                          PermissionChecker permissionChecker,
                          SensorChecker sensorChecker,
                          CriticalityChecker criticalityChecker) {
        super(sensorConfig, locationManager,
                sensorEnableRequester, permissionChecker, sensorChecker, criticalityChecker);
    }

    protected void checkRegistrationSuccess(boolean success) {
        if (!success) {
            throw new Error("GNSS Callback couldn't be registered");
        }
    }

}
