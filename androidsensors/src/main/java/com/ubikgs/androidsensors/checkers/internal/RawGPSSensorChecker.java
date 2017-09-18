package com.ubikgs.androidsensors.checkers.internal;

import android.location.LocationManager;
import android.os.Build;
import android.util.Log;

import com.ubikgs.androidsensors.SensorType;

import javax.inject.Inject;

/**
 * Created by alberto on 13/9/17.
 */

public class RawGPSSensorChecker extends GPSSensorChecker {

    private static final int MIN_SDK_THRESHOLD = 24;

    private final int androidVersion;

    @Inject
    public RawGPSSensorChecker(LocationManager locationManager) {
        this(locationManager, Build.VERSION.SDK_INT);
    }

    protected RawGPSSensorChecker(LocationManager locationManager, int androidVersion) {
        super(locationManager);
        this.androidVersion = androidVersion;
    }

    @Override
    public boolean isReady(SensorType sensorType) {
        if (androidVersion < MIN_SDK_THRESHOLD) {
            Log.w(this.getClass().getName(), "This functionality requires at least Android N");
            return false;
        }
        return super.isReady(sensorType);
    }
}
