package com.ubikgs.androidsensors.modules;

import android.content.Context;
import android.hardware.SensorManager;
import android.location.LocationManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alberto on 18/9/17.
 */

@Module
public class SystemServicesModule {

    @Provides
    SensorManager provideSensorManager(Context context) {
        return (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    @Provides
    LocationManager provideLocationManager(Context context) {
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }
}
