package com.ubikgs.androidsensors.gatherers.gps;

import android.location.GnssMeasurementsEvent;
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
import com.ubikgs.androidsensors.records.gps.RawGPSMeasurementsRecord;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.FlowableEmitter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Cancellable;

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

public class RawGPSMeasurementsGatherer extends RawGPSGatherer {

    @Inject
    public RawGPSMeasurementsGatherer(SensorConfig sensorConfig,
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
        final GnssMeasurementsEvent.Callback callback = initializeGnssMeasurementsCallbackFor(subscriber);

        startListeningGnssMeasurementsEventsChanges(callback);
        addUnsubscribeCallbackFor(subscriber, callback);
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private GnssMeasurementsEvent.Callback initializeGnssMeasurementsCallbackFor(final FlowableEmitter<SensorRecord> subscriber) {
        return new GnssMeasurementsEvent.Callback() {
            @Override
            public void onGnssMeasurementsReceived(GnssMeasurementsEvent event) {
                subscriber.onNext(new RawGPSMeasurementsRecord(event));
            }
        };
    }

    @SuppressWarnings("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.N)
    private void startListeningGnssMeasurementsEventsChanges(final GnssMeasurementsEvent.Callback callback) {
        // This is needed because location manager location updates need a looper
        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter e) throws Exception {
                checkRegistrationSuccess(locationManager.registerGnssMeasurementsCallback(callback));
            }
        })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private void addUnsubscribeCallbackFor(FlowableEmitter<SensorRecord> subscriber,
                                           final GnssMeasurementsEvent.Callback callback) {
        subscriber.setCancellable(new Cancellable() {
            @Override
            public void cancel() throws Exception {
                locationManager.unregisterGnssMeasurementsCallback(callback);
            }
        });
    }

    @Override
    public SensorType getSensorType() {
        return SensorType.RAW_GPS_MEASUREMENTS;
    }
}
