package com.ubikgs.androidsensors.enablers;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

import com.ubikgs.androidsensors.SensorType;

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
