package com.ubikgs.androidsensors.gatherers.bluetooth;

import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.ubikgs.androidsensors.SensorType;
import com.ubikgs.androidsensors.checkers.applevel.SensorRequirementChecker;
import com.ubikgs.androidsensors.checkers.internal.SensorChecker;
import com.ubikgs.androidsensors.checkers.permissions.PermissionChecker;
import com.ubikgs.androidsensors.config.SensorConfig;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;
import com.ubikgs.androidsensors.gatherers.AbstractSensorGatherer;
import com.ubikgs.androidsensors.records.SensorRecord;
import com.ubikgs.androidsensors.records.bluetooth.BLEMeasurementsRecord;

import java.util.Collections;
import java.util.List;

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

public class BLEMeasurementsGatherer extends AbstractSensorGatherer {

    private BluetoothManager bluetoothManager;

    @Inject
    public BLEMeasurementsGatherer(SensorConfig sensorConfig,
                                   BluetoothManager bluetoothManager,
                                   @Named("bluetoothSensorEnableRequester")SensorEnableRequester sensorEnableRequester,
                                   @Named("fineLocationPermissionChecker") PermissionChecker permissionChecker,
                                   @Named("bleSensorChecker")SensorChecker sensorChecker,
                                   SensorRequirementChecker sensorRequirementChecker){
        super(sensorConfig, sensorEnableRequester, permissionChecker,sensorChecker,sensorRequirementChecker);
        this.bluetoothManager = bluetoothManager;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void configureSensorSubscribeAndUnsubscribeBehaviors(FlowableEmitter<SensorRecord> subscriber) {
        List<ScanFilter> scanFilters = initializeScanFilters();
        ScanSettings scanSettings = initializeScanSettings();
        final ScanCallback scanCallback = initializeScanCallbackFor(subscriber);

        startListeningBluetoothMeasurements(scanFilters, scanSettings, scanCallback);
        addUnsuscribeCallbackFor(subscriber, scanCallback);
    }

    private List<ScanFilter> initializeScanFilters() {
        return Collections.emptyList();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private ScanSettings initializeScanSettings() {
        return new ScanSettings.Builder()
                .setReportDelay(sensorConfig.getMinSensorDelay(SensorType.BLE_MEASUREMENTS))
                .build();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private ScanCallback initializeScanCallbackFor(final FlowableEmitter<SensorRecord> subscriber){
        return new ScanCallback() {
            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                subscriber.onNext(new BLEMeasurementsRecord(results));
            }
        };
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startListeningBluetoothMeasurements(List<ScanFilter> scanFilters,
                                                     ScanSettings scanSettings,
                                                     ScanCallback scanCallback){
        BluetoothLeScanner scanner = bluetoothManager.getAdapter().getBluetoothLeScanner();
        scanner.startScan(scanFilters, scanSettings, scanCallback);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void addUnsuscribeCallbackFor(FlowableEmitter<SensorRecord> subscriber,
                                          final ScanCallback scanCallback){
        final BluetoothLeScanner scanner = bluetoothManager.getAdapter().getBluetoothLeScanner();
        subscriber.setCancellable(new Cancellable() {
            @Override
            public void cancel() throws Exception {
                scanner.flushPendingScanResults(scanCallback);
                scanner.stopScan(scanCallback);
            }
        });
    }

    @Override
    public SensorType getSensorType() {
        return SensorType.BLE_MEASUREMENTS;
    }
}
