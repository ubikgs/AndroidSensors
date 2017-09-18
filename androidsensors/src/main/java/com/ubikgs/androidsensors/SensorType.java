package com.ubikgs.androidsensors;

import com.ubikgs.androidsensors.records.gps.LocationRecord;
import com.ubikgs.androidsensors.records.gps.RawGPSMeasurementsRecord;
import com.ubikgs.androidsensors.records.gps.RawGPSNavigationRecord;
import com.ubikgs.androidsensors.records.gps.RawGPSStatusRecord;
import com.ubikgs.androidsensors.records.imu.AccelerometerRecord;
import com.ubikgs.androidsensors.records.imu.GravityRecord;
import com.ubikgs.androidsensors.records.imu.GyroscopeRecord;
import com.ubikgs.androidsensors.records.imu.LinearAccelerationRecord;
import com.ubikgs.androidsensors.records.imu.MagneticFieldRecord;
import com.ubikgs.androidsensors.records.imu.RotationVectorRecord;

import java.util.HashMap;

/**
 * Created by alberto on 22/2/17.
 */

public enum SensorType {
    ACCELEROMETER, GRAVITY, GYROSCOPE, LINEAR_ACCELERATION,
    LOCATION, MAGNETIC_FIELD,
    RAW_GPS_MEASUREMENTS, RAW_GPS_NAVIGATION, RAW_GPS_STATUS,
    ROTATION_VECTOR;

    private static HashMap<SensorType, Class> recordClasses = new HashMap<>();

    static {
        recordClasses.put(ACCELEROMETER, AccelerometerRecord.class);
        recordClasses.put(GRAVITY, GravityRecord.class);
        recordClasses.put(GYROSCOPE, GyroscopeRecord.class);
        recordClasses.put(LINEAR_ACCELERATION, LinearAccelerationRecord.class);
        recordClasses.put(LOCATION, LocationRecord.class);
        recordClasses.put(MAGNETIC_FIELD, MagneticFieldRecord.class);
        recordClasses.put(RAW_GPS_MEASUREMENTS, RawGPSMeasurementsRecord.class);
        recordClasses.put(RAW_GPS_NAVIGATION, RawGPSNavigationRecord.class);
        recordClasses.put(RAW_GPS_STATUS, RawGPSStatusRecord.class);
        recordClasses.put(ROTATION_VECTOR, RotationVectorRecord.class);
    }

    public Class getRecordClass() {
        return recordClasses.get(this);
    }
}
