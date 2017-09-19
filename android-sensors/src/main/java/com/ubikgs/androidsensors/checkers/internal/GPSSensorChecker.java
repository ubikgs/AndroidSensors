package com.ubikgs.androidsensors.checkers.internal;

import android.location.LocationManager;

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

public class GPSSensorChecker implements SensorChecker {

    private final LocationManager locationManager;

    @Inject
    public GPSSensorChecker(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    @Override
    public boolean isReady(SensorType sensorType) {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
