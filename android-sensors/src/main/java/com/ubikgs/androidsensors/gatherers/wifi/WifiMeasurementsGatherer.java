package com.ubikgs.androidsensors.gatherers.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

import java.util.Date;
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
    private Context context;

    @Inject
    public WifiMeasurementsGatherer(SensorConfig sensorConfig,
                                    WifiManager wifiManager,
                                    @Named("wifiSensorEnableRequester")SensorEnableRequester sensorEnableRequester,
                                    @Named("fineLocationPermissionChecker") PermissionChecker permissionChecker,
                                    @Named("wifiSensorChecker")SensorChecker sensorChecker,
                                    SensorRequirementChecker sensorRequirementChecker,
                                    Context context){
        super(sensorConfig, sensorEnableRequester, permissionChecker, sensorChecker, sensorRequirementChecker);
        this.wifiManager = wifiManager;
        this.context = context;
    }

    @Override
    protected void configureSensorSubscribeAndUnsubscribeBehaviors(FlowableEmitter<SensorRecord> subscriber) {
        BroadcastReceiver broadcastReceiver = defineBroadcastReceiverFor(subscriber);
        registerBroadcastReceiver(broadcastReceiver);
        addUnsubscribeCallbackFor(subscriber, broadcastReceiver);
    }

    private BroadcastReceiver defineBroadcastReceiverFor(final FlowableEmitter<SensorRecord> subscriber){
        return new BroadcastReceiver() {
            Timer timer = new Timer();
            long prevCallTime = new Date().getTime();

            @Override
            public void onReceive(Context context, Intent intent) {
                long actualTime = new Date().getTime();
                long delay = calculateDiffDelay(prevCallTime, actualTime);
                prevCallTime = actualTime;

                subscriber.onNext(new WifiMeasurementsRecord(wifiManager.getScanResults()));

                if (delay > 0)
                    timer.schedule(createScanTask(), delay);
                else
                    createScanTask().run();
            }
        };
    }

    protected long calculateDiffDelay(long prevCallTime, long actualTime) {
        long diff = actualTime - prevCallTime;
        return sensorConfig.getMinSensorDelay(SensorType.WIFI_MEASUREMENTS) - diff;
    }

    private void registerBroadcastReceiver(BroadcastReceiver broadcastReceiver){
        createScanTask().run();
        context.registerReceiver(broadcastReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    private TimerTask createScanTask(){
        return new TimerTask() {
            @Override
            public void run() {
                wifiManager.startScan();
            }
        };
    }

    private void addUnsubscribeCallbackFor(FlowableEmitter<SensorRecord> subscriber,
                                           final BroadcastReceiver broadcastReceiver){
        subscriber.setCancellable(new Cancellable() {
            @Override
            public void cancel() throws Exception {
                context.unregisterReceiver(broadcastReceiver);
            }
        });
    }

    @Override
    public SensorType getSensorType() {
        return SensorType.WIFI_MEASUREMENTS;
    }

}
