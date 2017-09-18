package com.ubikgs.androidsensors.records.imu;

import android.hardware.SensorEvent;

import com.ubikgs.androidsensors.records.RecordInfo;

/**
 * Created by alberto on 18/9/17.
 */

public class GravityRecord extends TriAxisRecord {

    public GravityRecord() {
        super();
    }

    public GravityRecord(RecordInfo recordInfo, float x, float y, float z) {
        super(recordInfo, x, y, z);
    }

    public GravityRecord(SensorEvent event) {
        super(event);
    }

    @Override
    public String toString() {
        return "GravityRecord{} " + super.toString();
    }

}
