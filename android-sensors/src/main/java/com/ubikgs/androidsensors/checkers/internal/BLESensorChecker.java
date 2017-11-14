package com.ubikgs.androidsensors.checkers.internal;


import android.bluetooth.BluetoothManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.ubikgs.androidsensors.SensorType;

import javax.inject.Inject;

/**
 * Copyright 2017 Alberto González Pérez
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * <p>http://www.apache.org/licenses/LICENSE-2.0</p>
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class BLESensorChecker implements SensorChecker {

    private static final int MIN_SDK_THRESHOLD = Build.VERSION_CODES.LOLLIPOP;

    private final BluetoothManager bluetoothManager;
    private final PackageManager packageManager;
    private final int androidVersion;

    @Inject
    public BLESensorChecker(BluetoothManager bluetoothManager, PackageManager packageManager){
        this(bluetoothManager, packageManager, Build.VERSION.SDK_INT);
    }

    public BLESensorChecker(BluetoothManager bluetoothManager, PackageManager packageManager, int androidVersion) {
        this.bluetoothManager = bluetoothManager;
        this.packageManager = packageManager;
        this.androidVersion = androidVersion;
    }

    @Override
    public boolean isReady(SensorType sensorType) {

        if (!packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Log.w(this.getClass().getName(), "Bluetooth Low Energy is not supported by this device");
            return false;
        }

        if (androidVersion < MIN_SDK_THRESHOLD) {
            Log.w(this.getClass().getName(), "This functionality requires at least Android Jelly Bean MR2 (API 18)");
            return false;
        }

        if (bluetoothManager.getAdapter() == null) {
            Log.w(this.getClass().getName(), "There is no bluetooth adapter in this device");
            return false;
        }

        return bluetoothManager.getAdapter().isEnabled();
    }
}