package com.ubikgs.androidsensors.enablers;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

import com.ubikgs.androidsensors.SensorType;

import javax.inject.Inject;

/**
 * Created by alberto on 13/9/17.
 */

public class GPSSensorEnableRequester implements SensorEnableRequester {

    private final Context context;

    @Inject
    public GPSSensorEnableRequester(Context context) {
        this.context = context;
    }


    @Override
    public void performEnableRequestFor(SensorType sensorType) {
        showAnInfoToast();
        navigateToGPSSettings();
    }

    private void showAnInfoToast() {
        Toast.makeText(context, "Please, enable GPS", Toast.LENGTH_LONG).show();
    }

    private void navigateToGPSSettings() {
        Intent gpsSettingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(gpsSettingsIntent);
    }

}
