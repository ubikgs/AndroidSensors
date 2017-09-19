package com.ubikgs.androidsensors.gatherers.gps;

import android.location.GnssNavigationMessage;
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
import com.ubikgs.androidsensors.records.gps.RawGPSNavigationRecord;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Completable;
import io.reactivex.FlowableEmitter;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by alberto on 13/9/17.
 */

public class RawGPSNavigationGatherer extends RawGPSGatherer {

    @Inject
    public RawGPSNavigationGatherer(SensorConfig sensorConfig,
                                    LocationManager locationManager,
                                    @Named("gpsSensorEnableRequester") SensorEnableRequester sensorEnableRequester,
                                    @Named("fineLocationPermissionChecker") PermissionChecker permissionChecker,
                                    @Named("rawGPSSensorChecker") SensorChecker sensorChecker,
                                    SensorRequirementChecker sensorRequirementChecker) {
        super(sensorConfig, locationManager,
                sensorEnableRequester, permissionChecker, sensorChecker, sensorRequirementChecker);
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @Override
    protected void configureSensorSubscribeAndUnsubscribeBehaviors(FlowableEmitter<SensorRecord> subscriber) {
        final GnssNavigationMessage.Callback callback = initializeGnssNavigationCallbackFor(subscriber);

        startListeningGnssNavigationMessages(callback);
        addUnsubscribeCallbackFor(subscriber, callback);
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private GnssNavigationMessage.Callback initializeGnssNavigationCallbackFor(FlowableEmitter<SensorRecord> subscriber) {
        return new GnssNavigationMessage.Callback() {
            @Override
            public void onGnssNavigationMessageReceived(GnssNavigationMessage event) {
                subscriber.onNext(new RawGPSNavigationRecord(event));
            }
        };
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private void startListeningGnssNavigationMessages(GnssNavigationMessage.Callback callback) {
        // This is needed because location manager location updates need a looper
        Completable.create(e ->
                checkRegistrationSuccess(locationManager.registerGnssNavigationMessageCallback(callback)))
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private void addUnsubscribeCallbackFor(FlowableEmitter<SensorRecord> subscriber, GnssNavigationMessage.Callback callback) {
        subscriber.setCancellable(() -> locationManager.unregisterGnssNavigationMessageCallback(callback));
    }

    @Override
    public SensorType getSensorType() {
        return SensorType.RAW_GPS_NAVIGATION;
    }
}
