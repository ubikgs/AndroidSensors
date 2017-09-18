package com.ubikgs.androidsensors.factories;

import android.hardware.SensorEvent;
import android.util.Log;

import com.ubikgs.androidsensors.SensorType;
import com.ubikgs.androidsensors.records.SensorRecord;
import com.ubikgs.androidsensors.records.imu.AccelerometerRecord;
import com.ubikgs.androidsensors.records.imu.GravityRecord;
import com.ubikgs.androidsensors.records.imu.GyroscopeRecord;
import com.ubikgs.androidsensors.records.imu.LinearAccelerationRecord;
import com.ubikgs.androidsensors.records.imu.MagneticFieldRecord;
import com.ubikgs.androidsensors.records.imu.RotationVectorRecord;

/**
 * Created by alberto on 18/9/17.
 */

public class SensorRecordFactory {

    public static SensorRecord createFrom(SensorType type, SensorEvent event) {
        switch (type) {
            case ACCELEROMETER:
                return new AccelerometerRecord(event);
            case GRAVITY:
                return new GravityRecord(event);
            case GYROSCOPE:
                return new GyroscopeRecord(event);
            case LINEAR_ACCELERATION:
                return new LinearAccelerationRecord(event);
            case MAGNETIC_FIELD:
                return new MagneticFieldRecord(event);
            case ROTATION_VECTOR:
                return new RotationVectorRecord(event);
            default:
                Log.e(SensorRecordFactory.class.getName(), "createFrom() called with unimplemented type: " + type);
                throw new Error("Unimplemented");
        }
    }
}
