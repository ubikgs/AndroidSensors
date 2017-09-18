package com.ubikgs.androidsensors.gatherers;

import com.ubikgs.androidsensors.SensorType;
import com.ubikgs.androidsensors.checkers.applevel.CriticalityChecker;
import com.ubikgs.androidsensors.checkers.internal.SensorChecker;
import com.ubikgs.androidsensors.checkers.permissions.PermissionChecker;
import com.ubikgs.androidsensors.config.SensorConfig;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;
import com.ubikgs.androidsensors.records.SensorRecord;

import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

/**
 * Created by alberto on 15/9/17.
 */

public abstract class AbstractSensorGatherer implements SensorGatherer {

    protected final SensorConfig sensorConfig;
    private final SensorEnableRequester sensorEnableRequester;
    private final PermissionChecker permissionChecker;
    private final SensorChecker sensorChecker;
    private final CriticalityChecker criticalityChecker;

    public AbstractSensorGatherer(SensorConfig sensorConfig,
                                  SensorEnableRequester sensorEnableRequester,
                                  PermissionChecker permissionChecker,
                                  SensorChecker sensorChecker,
                                  CriticalityChecker criticalityChecker) {

        this.sensorConfig = sensorConfig;
        this.sensorEnableRequester = sensorEnableRequester;
        this.permissionChecker = permissionChecker;
        this.sensorChecker = sensorChecker;
        this.criticalityChecker = criticalityChecker;
    }

    public Flowable<SensorRecord> recordStream() {

        if (!isReady() || !hasPermissionGranted())
            return Flowable.error(new Error("Sensor status was not checked before asking for a data stream. " +
                    "Please, ensure that you're calling isReady() and hasPermissionGranted()."));


        return Flowable.create(this::configureSensorSubscribeAndUnsubscribeBehaviors,
                sensorConfig.getBackpressureStrategy(getSensorType()));
    }

    protected abstract void configureSensorSubscribeAndUnsubscribeBehaviors(FlowableEmitter<SensorRecord> subscriber);

    public String getNeededPermission() {
        return permissionChecker.getNeededPermission();
    }

    public boolean hasPermissionGranted() {
        return permissionChecker.isPermissionGranted();
    }

    public boolean isReady() {
        return sensorChecker.isReady(getSensorType());
    }

    public void askForEnabling() {
        sensorEnableRequester.performEnableRequestFor(getSensorType());
    }

    @Override
    public boolean isCritical() {
        return criticalityChecker.isCritical(getSensorType());
    }

    public abstract SensorType getSensorType();
}
