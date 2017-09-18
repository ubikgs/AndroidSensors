package com.ubikgs.androidsensors.records.imu;

import android.hardware.SensorEvent;

import com.ubikgs.androidsensors.records.RecordInfo;

/**
 * Created by alberto on 18/9/17.
 */

public class MagneticFieldRecord extends TriAxisRecord {

    public MagneticFieldRecord() {
        super();
    }

    public MagneticFieldRecord(RecordInfo recordInfo, float x, float y, float z) {
        super(recordInfo, x, y, z);
    }

    public MagneticFieldRecord(SensorEvent event) {
        super(event);
    }

    @Override
    public String toString() {
        return "MagneticFieldRecord{} " + super.toString();
    }
}
