package com.ubikgs.androidsensors.gatherers.bluetooth;

import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
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

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.FlowableEmitter;
import io.reactivex.functions.Cancellable;

/**
 * Created by geotec-laptop01 on 08/11/2017.
 */

public class BLEMeasurementsGatherer extends AbstractSensorGatherer {

    private BluetoothManager bluetoothManager;

    @Inject
    public BLEMeasurementsGatherer(SensorConfig sensorConfig,
                                   BluetoothManager bluetoothManager,
                                   @Named("bluetoothSensorEnableRequester")SensorEnableRequester sensorEnableRequester,
                                   PermissionChecker permissionChecker,
                                   @Named("bleSensorChecker")SensorChecker sensorChecker,
                                   SensorRequirementChecker sensorRequirementChecker){
        super(sensorConfig, sensorEnableRequester, permissionChecker,sensorChecker,sensorRequirementChecker);
        this.bluetoothManager = bluetoothManager;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void configureSensorSubscribeAndUnsubscribeBehaviors(FlowableEmitter<SensorRecord> subscriber) {
        final ScanCallback scanCallback = initializeScanCallbackFor(subscriber);

        startListeningBluetoothMeasurements(scanCallback);
        addUnsuscribeCallbackFor(subscriber, scanCallback);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private ScanCallback initializeScanCallbackFor(final FlowableEmitter<SensorRecord> subscriber){
        return new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                subscriber.onNext(new BLEMeasurementsRecord(result));
            }
        };
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startListeningBluetoothMeasurements(ScanCallback scanCallback){
        BluetoothLeScanner scanner = bluetoothManager.getAdapter().getBluetoothLeScanner();
        scanner.startScan(scanCallback);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void addUnsuscribeCallbackFor(FlowableEmitter<SensorRecord> subscriber,
                                          final ScanCallback scanCallback){
        final BluetoothLeScanner scanner = bluetoothManager.getAdapter().getBluetoothLeScanner();
        subscriber.setCancellable(new Cancellable() {
            @Override
            public void cancel() throws Exception {
                scanner.stopScan(scanCallback);
            }
        });
    }

    @Override
    public SensorType getSensorType() {
        return SensorType.BLE_MEASUREMENTS;
    }
}
