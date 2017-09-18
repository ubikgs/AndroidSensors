package com.ubikgs.androidsensors.gatherers;

import com.ubikgs.androidsensors.records.SensorRecord;

import io.reactivex.Flowable;

/**
 * Created by alberto on 12/9/17.
 */

public interface SensorGatherer {

    Flowable<SensorRecord> recordStream();

    String getNeededPermission();

    boolean hasPermissionGranted();

    boolean isReady();

    void askForEnabling();

    boolean isCritical();

}
