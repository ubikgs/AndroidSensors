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
