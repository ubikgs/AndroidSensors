package com.ubikgs.androidsensors.checkers.internal;

import android.net.wifi.WifiManager;

import com.ubikgs.androidsensors.SensorType;

import javax.inject.Inject;

/**
 * Created by geotec-laptop01 on 03/11/2017.
 */

public class WifiSensorChecker implements SensorChecker {

    private final WifiManager wifiManager;

    @Inject
    public WifiSensorChecker(WifiManager wifiManager){
        this.wifiManager = wifiManager;
    }

    @Override
    public boolean isReady(SensorType sensorType) {
        return wifiManager.isWifiEnabled();
    }
}
