package com.ubikgs.androidsensors.checkers.internal;

import android.net.wifi.WifiManager;

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
