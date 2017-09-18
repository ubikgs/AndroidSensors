package com.ubikgs.androidsensors.enablers;

import android.content.Context;
import android.widget.Toast;

import com.ubikgs.androidsensors.SensorType;

import javax.inject.Inject;

/**
 * Created by alberto on 11/9/17.
 */

public class BasicSensorEnableRequester implements SensorEnableRequester {

    private final Context context;

    @Inject
    public BasicSensorEnableRequester(Context context) {
        this.context = context;
    }

    @Override
    public void performEnableRequestFor(SensorType sensorType) {
        Toast.makeText(context, String.format("Sorry, %s is not available in this device",
                sensorType.name().toLowerCase()), Toast.LENGTH_LONG).show();
    }
}
