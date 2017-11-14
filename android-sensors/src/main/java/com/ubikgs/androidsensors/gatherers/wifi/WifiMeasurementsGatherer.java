package com.ubikgs.androidsensors.gatherers.wifi;

import android.net.wifi.WifiManager;

import com.ubikgs.androidsensors.SensorType;
import com.ubikgs.androidsensors.checkers.applevel.SensorRequirementChecker;
import com.ubikgs.androidsensors.checkers.internal.SensorChecker;
import com.ubikgs.androidsensors.checkers.permissions.PermissionChecker;
import com.ubikgs.androidsensors.config.SensorConfig;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;
import com.ubikgs.androidsensors.gatherers.AbstractSensorGatherer;
import com.ubikgs.androidsensors.records.SensorRecord;
import com.ubikgs.androidsensors.records.wifi.WifiMeasurementsRecord;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.FlowableEmitter;
import io.reactivex.functions.Cancellable;

/**
 * Copyright 2017 Alberto González Pérez
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * <p>http://www.apache.org/licenses/LICENSE-2.0</p>
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class WifiMeasurementsGatherer extends AbstractSensorGatherer {

    private WifiManager wifiManager;

    @Inject
    public WifiMeasurementsGatherer(SensorConfig sensorConfig,
                                    WifiManager wifiManager,
                                    @Named("wifiSensorEnableRequester")SensorEnableRequester sensorEnableRequester,
                                    PermissionChecker permissionChecker,
                                    @Named("wifiSensorChecker")SensorChecker sensorChecker,
                                    SensorRequirementChecker sensorRequirementChecker){
        super(sensorConfig, sensorEnableRequester, permissionChecker, sensorChecker, sensorRequirementChecker);
        this.wifiManager = wifiManager;
    }

    @Override
    protected void configureSensorSubscribeAndUnsubscribeBehaviors(FlowableEmitter<SensorRecord> subscriber) {
        final TimerTask timerTask = initializeTimerTaskFor(subscriber);

        startScanning(timerTask);
        cancelScanning(subscriber, timerTask);
    }

    private TimerTask initializeTimerTaskFor(final FlowableEmitter<SensorRecord> subscriber){
        return new TimerTask() {
            @Override
            public void run() {
                subscriber.onNext(new WifiMeasurementsRecord(wifiManager.getScanResults()));
            }
        };
    }

    private void startScanning(TimerTask timerTask){
        Timer timer = new Timer();

        timer.schedule(timerTask, 0, sensorConfig.getMinSensorDelay(SensorType.WIFI_MEASUREMENTS));
    }

    private void cancelScanning(FlowableEmitter<SensorRecord> subscriber, final TimerTask timerTask){
        subscriber.setCancellable(new Cancellable() {
            @Override
            public void cancel() throws Exception {
                timerTask.cancel();
            }
        });
    }

    @Override
    public SensorType getSensorType() {
        return SensorType.WIFI_MEASUREMENTS;
    }
}
