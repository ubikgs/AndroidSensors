package com.ubikgs.androidsensors.gatherers.imu;

import android.hardware.SensorManager;

import com.ubikgs.androidsensors.SensorType;
import com.ubikgs.androidsensors.checkers.applevel.SensorRequirementChecker;
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

public class MagneticFieldGatherer extends IMUSensorGatherer {

    @Inject
    public MagneticFieldGatherer(SensorConfig sensorConfig,
                                 SensorManager sensorManager,
                                 SensorEnableRequester sensorEnableRequester,
                                 PermissionChecker permissionChecker,
                                 @Named("imuSensorChecker") SensorChecker sensorChecker,
                                 SensorRequirementChecker sensorRequirementChecker,
                                 SensorTypeToAndroidSensor sensorTypeToAndroidSensor) {

        super(sensorConfig, sensorManager,
                sensorEnableRequester, permissionChecker, sensorChecker, sensorRequirementChecker,
                sensorTypeToAndroidSensor);
    }

    @Override
    public SensorType getSensorType() {
        return SensorType.MAGNETIC_FIELD;
    }
}
