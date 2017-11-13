package com.ubikgs.androidsensors.enablers;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.ubikgs.androidsensors.SensorType;

import javax.inject.Inject;


/**
 * Created by geotec-laptop01 on 08/11/2017.
 */

public class BluetoothSensorEnableRequester implements SensorEnableRequester {

    private final Context context;

    @Inject
    public BluetoothSensorEnableRequester(Context context){
        this.context = context;
    }

    @Override
    public void performEnableRequestFor(SensorType sensorType) {
        showAnInfoToast();
        navigateToBluetoothSettings();
    }

    private void showAnInfoToast(){
        Toast.makeText(context, "Please, enable Bluetooth", Toast.LENGTH_LONG).show();
    }

    private void navigateToBluetoothSettings(){
        Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        context.startActivity(enableBluetoothIntent);
    }
}
