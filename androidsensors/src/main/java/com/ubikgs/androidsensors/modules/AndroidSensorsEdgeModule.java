package com.ubikgs.androidsensors.modules;

import android.content.Context;

import com.ubikgs.androidsensors.checkers.applevel.BasicSensorRequirementChecker;
import com.ubikgs.androidsensors.checkers.applevel.SensorRequirementChecker;
import com.ubikgs.androidsensors.config.BasicSensorConfig;
import com.ubikgs.androidsensors.config.SensorConfig;
import com.ubikgs.androidsensors.enablers.BasicSensorEnableRequester;
import com.ubikgs.androidsensors.enablers.GPSSensorEnableRequester;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;
import com.ubikgs.androidsensors.utils.MillisecondsToMicroseconds;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alberto on 18/9/17.
 */

@Module
public class AndroidSensorsEdgeModule {

    private static final long DEFAULT_MIN_SENSOR_DELAY = 20L;

    private SensorEnableRequester defaultSensorEnableRequester;
    private SensorEnableRequester gpsSensorEnableRequester;
    private SensorRequirementChecker sensorRequirementChecker;
    private SensorConfig sensorConfig;

    public AndroidSensorsEdgeModule() {
    }

    public AndroidSensorsEdgeModule(SensorEnableRequester defaultSensorEnableRequester,
                                    SensorEnableRequester gpsSensorEnableRequester,
                                    SensorRequirementChecker sensorRequirementChecker,
                                    SensorConfig sensorConfig) {

        this.defaultSensorEnableRequester = defaultSensorEnableRequester;
        this.gpsSensorEnableRequester = gpsSensorEnableRequester;
        this.sensorRequirementChecker = sensorRequirementChecker;
        this.sensorConfig = sensorConfig;
    }

    /*
    * SensorEnableRequesters
    * */

    @Provides
    SensorEnableRequester provideBasicSensorEnableRequester(Context context) {
        return defaultSensorEnableRequester != null ?
                defaultSensorEnableRequester : new BasicSensorEnableRequester(context);
    }

    @Provides
    @Named("gpsSensorEnableRequester")
    SensorEnableRequester provideGPSSensorEnableRequester(Context context) {
        return gpsSensorEnableRequester != null ?
                gpsSensorEnableRequester : new GPSSensorEnableRequester(context);
    }

    /*
    * SensorRequirementCheckers
    * */

    @Provides
    SensorRequirementChecker provideBasicCriticalityChecker() {
        return sensorRequirementChecker != null ?
                sensorRequirementChecker : new BasicSensorRequirementChecker();
    }

    /*
    * SensorConfigurers
    * */

    @Provides
    SensorConfig provideBasicSensorConfig(MillisecondsToMicroseconds millisecondsToMicroseconds) {
        return sensorConfig != null ?
                sensorConfig : new BasicSensorConfig(DEFAULT_MIN_SENSOR_DELAY, millisecondsToMicroseconds);
    }
}
