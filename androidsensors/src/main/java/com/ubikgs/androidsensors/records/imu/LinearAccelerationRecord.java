package com.ubikgs.androidsensors.records.imu;

import android.hardware.SensorEvent;

import com.ubikgs.androidsensors.records.RecordInfo;

/**
 * Created by alberto on 18/9/17.
 */

public class LinearAccelerationRecord extends TriAxisRecord {

    public LinearAccelerationRecord() {
        super();
    }

    public LinearAccelerationRecord(RecordInfo recordInfo, float x, float y, float z) {
        super(recordInfo, x, y, z);
    }

    public LinearAccelerationRecord(SensorEvent event) {
        super(event);
    }

    @Override
    public String toString() {
        return "LinearAccelerationRecord{} " + super.toString();
    }

}
