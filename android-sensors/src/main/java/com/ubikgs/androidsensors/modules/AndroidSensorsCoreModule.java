package com.ubikgs.androidsensors.modules;

import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.net.wifi.WifiManager;

import com.ubikgs.androidsensors.checkers.internal.BLESensorChecker;
import com.ubikgs.androidsensors.checkers.internal.GPSSensorChecker;
import com.ubikgs.androidsensors.checkers.internal.IMUSensorChecker;
import com.ubikgs.androidsensors.checkers.internal.RawGPSSensorChecker;
import com.ubikgs.androidsensors.checkers.internal.SensorChecker;
import com.ubikgs.androidsensors.checkers.internal.WifiSensorChecker;
import com.ubikgs.androidsensors.checkers.permissions.FineLocationPermissionChecker;
import com.ubikgs.androidsensors.checkers.permissions.NoPermissionChecker;
import com.ubikgs.androidsensors.checkers.permissions.PermissionChecker;
import com.ubikgs.androidsensors.gatherers.SensorGatherer;
import com.ubikgs.androidsensors.gatherers.bluetooth.BluetoothMeasurementsGatherer;
import com.ubikgs.androidsensors.gatherers.gps.LocationGatherer;
import com.ubikgs.androidsensors.gatherers.gps.RawGPSMeasurementsGatherer;
import com.ubikgs.androidsensors.gatherers.gps.RawGPSNavigationGatherer;
import com.ubikgs.androidsensors.gatherers.gps.RawGPSStatusGatherer;
import com.ubikgs.androidsensors.gatherers.imu.AccelerometerGatherer;
import com.ubikgs.androidsensors.gatherers.imu.GravityGatherer;
import com.ubikgs.androidsensors.gatherers.imu.GyroscopeGatherer;
import com.ubikgs.androidsensors.gatherers.imu.LinearAccelerationGatherer;
import com.ubikgs.androidsensors.gatherers.imu.MagneticFieldGatherer;
import com.ubikgs.androidsensors.gatherers.imu.RotationVectorGatherer;
import com.ubikgs.androidsensors.gatherers.wifi.WifiMeasurementsGatherer;
import com.ubikgs.androidsensors.utils.SensorTypeToAndroidSensor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

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

@Module
public class AndroidSensorsCoreModule {

    /*
    * SensorGatherers
    * */

    @Provides
    @ElementsIntoSet
    Set<SensorGatherer> provideAllSensorGatherers(
            AccelerometerGatherer accelerometerGatherer,
            GravityGatherer gravityGatherer,
            GyroscopeGatherer gyroscopeGatherer,
            LinearAccelerationGatherer linearAccelerationGatherer,
            MagneticFieldGatherer magneticFieldGatherer,
            RotationVectorGatherer rotationVectorGatherer,
            LocationGatherer locationGatherer,
            RawGPSMeasurementsGatherer rawGPSMeasurementsGatherer,
            RawGPSNavigationGatherer rawGPSNavigationGatherer,
            RawGPSStatusGatherer rawGPSStatusGatherer,
            WifiMeasurementsGatherer wifiMeasurementsGatherer,
            BluetoothMeasurementsGatherer bluetoothMeasurementsGatherer) {

        return new HashSet<SensorGatherer>(Arrays.asList(
                accelerometerGatherer,
                gravityGatherer,
                gyroscopeGatherer,
                linearAccelerationGatherer,
                magneticFieldGatherer,
                rotationVectorGatherer,
                locationGatherer,
                rawGPSMeasurementsGatherer,
                rawGPSNavigationGatherer,
                rawGPSStatusGatherer,
                wifiMeasurementsGatherer,
                bluetoothMeasurementsGatherer));
    }

    /*
    * PermissionCheckers
    * */

    @Provides
    PermissionChecker provideNoPermissionChecker() {
        return new NoPermissionChecker();
    }

    @Provides
    @Named("fineLocationPermissionChecker")
    PermissionChecker provideFineLocationPemissionChecker(Context context) {
        return new FineLocationPermissionChecker(context);
    }

    /*
    * SensorCheckers
    * */

    @Provides
    @Named("imuSensorChecker")
    SensorChecker provideImuSensorChecker(SensorManager sensorManager, SensorTypeToAndroidSensor sensorTypeToAndroidSensor) {
        return new IMUSensorChecker(sensorTypeToAndroidSensor, sensorManager);
    }

    @Provides
    @Named("gpsSensorChecker")
    SensorChecker provideGPSSensorChecker(LocationManager locationManager) {
        return new GPSSensorChecker(locationManager);
    }

    @Provides
    @Named("rawGPSSensorChecker")
    SensorChecker provideRawGPSSensorChecker(LocationManager locationManager) {
        return new RawGPSSensorChecker(locationManager);
    }

    @Provides
    @Named("wifiSensorChecker")
    SensorChecker provideWifiSensorChecker (WifiManager wifiManager){
        return new WifiSensorChecker(wifiManager);
    }

    @Provides
    @Named("bleSensorChecker")
    SensorChecker provideBLESensorChecker(BluetoothManager bluetoothManager){
        return new BLESensorChecker(bluetoothManager);
    }
}
