package com.ubikgs.androidsensors.checkers.permissions;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import javax.inject.Inject;

/**
 * Created by alberto on 13/9/17.
 */

public class FineLocationPermissionChecker implements PermissionChecker {

    private static final int SDK_THRESHOLD = 23;

    private final int androidSdkVersion;
    private final Context context;
    private final CheckPermission checkPermission;

    @Inject
    public FineLocationPermissionChecker(Context context) {
        this(Build.VERSION.SDK_INT, context, ContextCompat::checkSelfPermission);
    }

    protected FineLocationPermissionChecker(int androidSdkVersion, Context context, CheckPermission checkPermission) {
        this.androidSdkVersion = androidSdkVersion;
        this.context = context;
        this.checkPermission = checkPermission;
    }

    @Override
    public String getNeededPermission() {
        return Manifest.permission.ACCESS_FINE_LOCATION;
    }

    @Override
    public boolean isPermissionGranted() {
        if (androidSdkVersion < SDK_THRESHOLD) return true;
        return checkPermission.check(context, getNeededPermission())
                == PackageManager.PERMISSION_GRANTED;
    }

    protected interface CheckPermission {
        int check(Context context, String string);
    }
}
