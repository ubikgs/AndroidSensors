package com.ubikgs.androidsensors.records.imu;

import android.hardware.SensorEvent;

import com.ubikgs.androidsensors.records.RecordInfo;

/**
 * Created by alberto on 18/9/17.
 */

public class AccelerometerRecord extends TriAxisRecord {

    public AccelerometerRecord() {
        super();
    }

    public AccelerometerRecord(RecordInfo recordInfo, float x, float y, float z) {
        super(recordInfo, x, y, z);
    }

    public AccelerometerRecord(SensorEvent event) {
        super(event);
    }

    @Override
    public String toString() {
        return "AccelerometerRecord{} " + super.toString();
    }

}
