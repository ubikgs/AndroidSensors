package com.ubikgs.androidsensors.modules;

import android.content.Context;
import android.hardware.SensorManager;
import android.location.LocationManager;

import com.ubikgs.androidsensors.checkers.applevel.BasicCriticalityChecker;
import com.ubikgs.androidsensors.checkers.applevel.CriticalityChecker;
import com.ubikgs.androidsensors.checkers.internal.GPSSensorChecker;
import com.ubikgs.androidsensors.checkers.internal.IMUSensorChecker;
import com.ubikgs.androidsensors.checkers.internal.RawGPSSensorChecker;
import com.ubikgs.androidsensors.checkers.internal.SensorChecker;
import com.ubikgs.androidsensors.checkers.permissions.FineLocationPermissionChecker;
import com.ubikgs.androidsensors.checkers.permissions.NoPermissionChecker;
import com.ubikgs.androidsensors.checkers.permissions.PermissionChecker;
import com.ubikgs.androidsensors.config.BasicSensorConfig;
import com.ubikgs.androidsensors.config.SensorConfig;
import com.ubikgs.androidsensors.enablers.BasicSensorEnableRequester;
import com.ubikgs.androidsensors.enablers.GPSSensorEnableRequester;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;
import com.ubikgs.androidsensors.gatherers.SensorGatherer;
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
import com.ubikgs.androidsensors.utils.MillisecondsToMicroseconds;
import com.ubikgs.androidsensors.utils.SensorTypeToAndroidSensor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

/**
 * Created by alberto on 11/9/17.
 */

@Module
public class SensorsModule {

    private final long DEFAULT_MIN_SENSOR_DELAY = 20L;

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
            RawGPSStatusGatherer rawGPSStatusGatherer) {

        return new HashSet<>(Arrays.asList(
                accelerometerGatherer,
                gravityGatherer,
                gyroscopeGatherer,
                linearAccelerationGatherer,
                magneticFieldGatherer,
                rotationVectorGatherer,
                locationGatherer,
                rawGPSMeasurementsGatherer,
                rawGPSNavigationGatherer,
                rawGPSStatusGatherer));
    }

    /*
    * SensorEnableRequesters
    * */

    @Provides
    SensorEnableRequester provideBasicSensorEnableRequester(Context context) {
        return new BasicSensorEnableRequester(context);
    }

    @Provides
    @Named("gpsSensorEnableRequester")
    SensorEnableRequester provideGPSSensorEnableRequester(Context context) {
        return new GPSSensorEnableRequester(context);
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

    /*
    * CriticalityCheckers
    * */

    @Provides
    CriticalityChecker provideBasicCritiCalityChecker() {
        return new BasicCriticalityChecker();
    }

    /*
    * SensorConfigurers
    * */

    @Provides
    SensorConfig provideBasicSensorConfig(MillisecondsToMicroseconds millisecondsToMicroseconds) {
        return new BasicSensorConfig(DEFAULT_MIN_SENSOR_DELAY, millisecondsToMicroseconds);
    }
}
