package com.ubikgs.androidsensors.checkers.internal;

import android.location.LocationManager;

import com.ubikgs.androidsensors.SensorType;

import javax.inject.Inject;

/**
 * Created by alberto on 13/9/17.
 */

public class GPSSensorChecker implements SensorChecker {

    private final LocationManager locationManager;

    @Inject
    public GPSSensorChecker(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    @Override
    public boolean isReady(SensorType sensorType) {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
