package com.ubikgs.androidsensors.records.imu;

import android.hardware.SensorEvent;

import com.ubikgs.androidsensors.records.RecordInfo;

/**
 * Created by alberto on 18/9/17.
 */

public class GyroscopeRecord extends TriAxisRecord {

    public GyroscopeRecord() {
        super();
    }

    public GyroscopeRecord(RecordInfo recordInfo, float x, float y, float z) {
        super(recordInfo, x, y, z);
    }

    public GyroscopeRecord(SensorEvent event) {
        super(event);
    }

    @Override
    public String toString() {
        return "GyroscopeRecord{} " + super.toString();
    }

}
