package com.ubikgs.androidsensors.gatherers.wifi;

import android.content.BroadcastReceiver;
import android.net.wifi.WifiManager;

import com.ubikgs.androidsensors.SensorType;
import com.ubikgs.androidsensors.checkers.applevel.SensorRequirementChecker;
import com.ubikgs.androidsensors.checkers.internal.SensorChecker;
import com.ubikgs.androidsensors.checkers.permissions.PermissionChecker;
import com.ubikgs.androidsensors.config.SensorConfig;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;
import com.ubikgs.androidsensors.enablers.WifiSensorEnableRequester;
import com.ubikgs.androidsensors.gatherers.AbstractSensorGatherer;
import com.ubikgs.androidsensors.records.SensorRecord;
import com.ubikgs.androidsensors.records.wifi.WifiMeasurementsRecord;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import io.reactivex.FlowableEmitter;
import io.reactivex.functions.Cancellable;

/**
 * Created by geotec-laptop01 on 06/11/2017.
 */

public class WifiMeasurementsGatherer extends AbstractSensorGatherer {

    private WifiManager wifiManager;

    @Inject
    public WifiMeasurementsGatherer(SensorConfig sensorConfig,
                                     WifiManager wifiManager,
                                     SensorEnableRequester sensorEnableRequester,
                                     PermissionChecker permissionChecker,
                                     SensorChecker sensorChecker,
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
