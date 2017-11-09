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

    private final BluetoothManager bluetoothManager;

    @Inject
    public BluetoothSensorChecker(BluetoothManager bluetoothManager){
        this.bluetoothManager = bluetoothManager;
    }

    @Override
    public boolean isReady(SensorType sensorType) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
            return bluetoothManager.getAdapter().isEnabled();
        }
        Log.w(this.getClass().getName(), "This functionality requires at least Android Jelly Bean MR2");
        return false;
    }
}
