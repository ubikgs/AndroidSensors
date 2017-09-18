package com.ubikgs.androidsensors.gatherers.imu;

import android.hardware.SensorManager;

import com.ubikgs.androidsensors.SensorType;
import com.ubikgs.androidsensors.checkers.applevel.CriticalityChecker;
import com.ubikgs.androidsensors.checkers.internal.SensorChecker;
import com.ubikgs.androidsensors.checkers.permissions.PermissionChecker;
import com.ubikgs.androidsensors.config.SensorConfig;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;
import com.ubikgs.androidsensors.utils.SensorTypeToAndroidSensor;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by alberto on 12/9/17.
 */

public class RotationVectorGatherer extends IMUSensorGatherer {

    @Inject
    public RotationVectorGatherer(SensorConfig sensorConfig,
                                  SensorManager sensorManager,
                                  SensorEnableRequester sensorEnableRequester,
                                  PermissionChecker permissionChecker,
                                  @Named("imuSensorChecker") SensorChecker sensorChecker,
                                  CriticalityChecker criticalityChecker,
                                  SensorTypeToAndroidSensor sensorTypeToAndroidSensor) {
        super(sensorConfig, sensorManager,
                sensorEnableRequester, permissionChecker, sensorChecker, criticalityChecker,
                sensorTypeToAndroidSensor);
    }

    @Override
    protected SensorType getSensorType() {
        return SensorType.ROTATION_VECTOR;
    }
}
