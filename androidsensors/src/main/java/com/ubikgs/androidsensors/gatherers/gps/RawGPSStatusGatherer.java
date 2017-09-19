package com.ubikgs.androidsensors.gatherers.gps;

import android.location.GnssStatus;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.ubikgs.androidsensors.SensorType;
import com.ubikgs.androidsensors.checkers.applevel.SensorRequirementChecker;
import com.ubikgs.androidsensors.checkers.internal.SensorChecker;
import com.ubikgs.androidsensors.checkers.permissions.PermissionChecker;
import com.ubikgs.androidsensors.config.SensorConfig;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;
import com.ubikgs.androidsensors.records.SensorRecord;
import com.ubikgs.androidsensors.records.gps.RawGPSStatusRecord;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Completable;
import io.reactivex.FlowableEmitter;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by alberto on 13/9/17.
 */

public class RawGPSStatusGatherer extends RawGPSGatherer {

    @Inject
    public RawGPSStatusGatherer(SensorConfig sensorConfig,
                                LocationManager locationManager,
                                @Named("gpsSensorEnableRequester") SensorEnableRequester sensorEnableRequester,
                                @Named("fineLocationPermissionChecker") PermissionChecker permissionChecker,
                                @Named("rawGPSSensorChecker") SensorChecker sensorChecker,
                                SensorRequirementChecker sensorRequirementChecker) {
        super(sensorConfig,locationManager,
                sensorEnableRequester, permissionChecker, sensorChecker, sensorRequirementChecker);
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @Override
    protected void configureSensorSubscribeAndUnsubscribeBehaviors(FlowableEmitter<SensorRecord> subscriber) {
        final GnssStatus.Callback callback = initializeGnssStatusCallbackFor(subscriber);

        startListeningGnssStatusChanges(callback);
        addUnsubscribeCallbackFor(subscriber, callback);
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private GnssStatus.Callback initializeGnssStatusCallbackFor(FlowableEmitter<SensorRecord> subscriber) {
        return new GnssStatus.Callback() {
            @Override
            public void onSatelliteStatusChanged(GnssStatus status) {
                subscriber.onNext(new RawGPSStatusRecord(status));
            }
        };
    }

    @SuppressWarnings("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.N)
    private void startListeningGnssStatusChanges(GnssStatus.Callback callback) {
        // This is needed because location manager location updates need a looper
        Completable.create(e ->
                checkRegistrationSuccess(locationManager.registerGnssStatusCallback(callback)))
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private void addUnsubscribeCallbackFor(FlowableEmitter<SensorRecord> subscriber, GnssStatus.Callback callback) {
        subscriber.setCancellable(() ->
                locationManager.unregisterGnssStatusCallback(callback));
    }

    @Override
    public SensorType getSensorType() {
        return SensorType.RAW_GPS_STATUS;
    }
}
