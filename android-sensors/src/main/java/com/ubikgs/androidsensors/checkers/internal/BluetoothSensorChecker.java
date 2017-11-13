package com.ubikgs.androidsensors.checkers.internal;


import android.bluetooth.BluetoothManager;
import android.os.Build;
import android.util.Log;

import com.ubikgs.androidsensors.SensorType;

import javax.inject.Inject;

/**
 * Created by geotec-laptop01 on 08/11/2017.
 */

public class BluetoothSensorChecker implements SensorChecker {

    private static final int MIN_SDK_THRESHOLD = Build.VERSION_CODES.JELLY_BEAN_MR2;

    private final BluetoothManager bluetoothManager;
    private final int androidVersion;

    @Inject
    public BluetoothSensorChecker(BluetoothManager bluetoothManager){
        this(bluetoothManager, Build.VERSION.SDK_INT);
    }

    public BluetoothSensorChecker(BluetoothManager bluetoothManager, int androidVersion) {
        this.bluetoothManager = bluetoothManager;
        this.androidVersion = androidVersion;
    }

    @Override
    public boolean isReady(SensorType sensorType) {
        if (androidVersion < MIN_SDK_THRESHOLD){
            Log.w(this.getClass().getName(), "This functionality requires at least Android Jelly Bean MR2 (API 18)");
            return false;
        }
        return bluetoothManager.getAdapter().isEnabled();
    }
}