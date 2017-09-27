package com.ubikgs.androidsensors;

import com.ubikgs.androidsensors.records.SensorRecord;
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
 *  Copyright 2017 Alberto González Pérez
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     <p>http://www.apache.org/licenses/LICENSE-2.0</p>
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

public enum SensorType {
    ACCELEROMETER, GRAVITY, GYROSCOPE, LINEAR_ACCELERATION,
    LOCATION, MAGNETIC_FIELD,
    RAW_GPS_MEASUREMENTS, RAW_GPS_NAVIGATION, RAW_GPS_STATUS,
    ROTATION_VECTOR;

    private static HashMap<SensorType, Class<? extends SensorRecord>> recordClasses = new HashMap<>();

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

    public Class<? extends SensorRecord> getRecordClass() {
        return recordClasses.get(this);
    }

    public static SensorType[] imuValues() {
        return new SensorType[] {
                ACCELEROMETER,
                GRAVITY,
                GYROSCOPE,
                LINEAR_ACCELERATION,
                MAGNETIC_FIELD,
                ROTATION_VECTOR
        };
    }

    public static SensorType[] gpsValues() {
        return new SensorType[] {
                LOCATION,
                RAW_GPS_MEASUREMENTS,
                RAW_GPS_NAVIGATION,
                RAW_GPS_STATUS
        };
    }

    public static SensorType[] rawGPSValues() {
        return new SensorType[] {
                RAW_GPS_MEASUREMENTS,
                RAW_GPS_NAVIGATION,
                RAW_GPS_STATUS
        };
    }
}
