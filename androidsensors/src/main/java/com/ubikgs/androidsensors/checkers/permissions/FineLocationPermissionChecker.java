package com.ubikgs.androidsensors.checkers.permissions;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import javax.inject.Inject;

/**
 *  Copyright 2017 Alberto González Pérez
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     <p>http://www.apache.org/licenses/LICENSE-2.0</p>
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
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
