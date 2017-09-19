package com.ubikgs.androidsensors.gatherers.gps;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.ubikgs.androidsensors.SensorType;
import com.ubikgs.androidsensors.checkers.applevel.SensorRequirementChecker;
import com.ubikgs.androidsensors.checkers.internal.SensorChecker;
import com.ubikgs.androidsensors.checkers.permissions.PermissionChecker;
import com.ubikgs.androidsensors.config.SensorConfig;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;
import com.ubikgs.androidsensors.records.SensorRecord;
import com.ubikgs.androidsensors.records.gps.LocationRecord;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Completable;
import io.reactivex.FlowableEmitter;
import io.reactivex.android.schedulers.AndroidSchedulers;

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

public class LocationGatherer extends GPSGatherer {

    protected static final int MIN_DISTANCE = 0;

    @Inject
    public LocationGatherer(SensorConfig sensorConfig,
                            LocationManager locationManager,
                            @Named("gpsSensorEnableRequester") SensorEnableRequester sensorEnableRequester,
                            @Named("fineLocationPermissionChecker") PermissionChecker permissionChecker,
                            @Named("gpsSensorChecker") SensorChecker sensorChecker,
                            SensorRequirementChecker sensorRequirementChecker) {
        super(sensorConfig, locationManager,
                sensorEnableRequester, permissionChecker, sensorChecker, sensorRequirementChecker);
    }

    @Override
    protected void configureSensorSubscribeAndUnsubscribeBehaviors(FlowableEmitter<SensorRecord> subscriber) {
        final LocationListener locationListener = initializeLocationListenerFor(subscriber);

        startListeningLocationChanges(locationListener);
        addUnsubscribeCallbackFor(subscriber, locationListener);
    }

    private LocationListener initializeLocationListenerFor(FlowableEmitter<SensorRecord> subscriber) {
        return new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                subscriber.onNext(new LocationRecord(location));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                // TODO: 13/9/17 Check if something else should be done here
            }

            @Override
            public void onProviderEnabled(String provider) {
                // TODO: 13/9/17 Check if something else should be done here
            }

            @Override
            public void onProviderDisabled(String provider) {
                // TODO: 13/9/17 Check if something else should be done here
            }
        };
    }

    @SuppressWarnings("MissingPermission")
    private void startListeningLocationChanges(LocationListener locationListener) {
        // This is needed because location manager location updates need a looper
        Completable.create(e ->
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        sensorConfig.getMinSensorDelay(getSensorType()),
                        MIN_DISTANCE,
                        locationListener))
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private void addUnsubscribeCallbackFor(FlowableEmitter<SensorRecord> subscriber, LocationListener locationListener) {
        subscriber.setCancellable(() -> locationManager.removeUpdates(locationListener));
    }

    @Override
    public SensorType getSensorType() {
        return SensorType.LOCATION;
    }
}
