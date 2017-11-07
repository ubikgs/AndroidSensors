package com.ubikgs.androidsensors.enablers;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

import com.ubikgs.androidsensors.SensorType;

import javax.inject.Inject;

/**
 * Created by geotec-laptop01 on 03/11/2017.
 */

public class WifiSensorEnableRequester implements SensorEnableRequester {

    private final Context context;

    @Inject
    public WifiSensorEnableRequester(Context context) {
        this.context = context;
    }

    @Override
    public void performEnableRequestFor(SensorType sensorType) {
        showAnInfoToast();
        navigateToWifiSettings();
    }

    private void showAnInfoToast(){
        Toast.makeText(context, "Please, enable WiFi", Toast.LENGTH_LONG).show();
    }

    private void navigateToWifiSettings(){
        Intent wifiSettingsIntent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        context.startActivity(wifiSettingsIntent);
    }
}
