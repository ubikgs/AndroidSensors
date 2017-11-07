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
import com.ubikgs.androidsensors.records.wifi.WifiMeasurementsRecord;

import org.junit.Test;

import static com.ubikgs.androidsensors.SensorType.ACCELEROMETER;
import static com.ubikgs.androidsensors.SensorType.GRAVITY;
import static com.ubikgs.androidsensors.SensorType.GYROSCOPE;
import static com.ubikgs.androidsensors.SensorType.LINEAR_ACCELERATION;
import static com.ubikgs.androidsensors.SensorType.LOCATION;
import static com.ubikgs.androidsensors.SensorType.MAGNETIC_FIELD;
import static com.ubikgs.androidsensors.SensorType.RAW_GPS_MEASUREMENTS;
import static com.ubikgs.androidsensors.SensorType.RAW_GPS_NAVIGATION;
import static com.ubikgs.androidsensors.SensorType.RAW_GPS_STATUS;
import static com.ubikgs.androidsensors.SensorType.ROTATION_VECTOR;
import static com.ubikgs.androidsensors.SensorType.WIFI_MEASUREMENTS;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

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
public class SensorTypeTest {
    @Test
    public void accelerometer() throws Exception {
        assertThat(ACCELEROMETER.getRecordClass(), equalTo((Class) AccelerometerRecord.class));
    }

    @Test
    public void gravity() throws Exception {
        assertThat(GRAVITY.getRecordClass(), equalTo((Class) GravityRecord.class));
    }

    @Test
    public void gyroscope() throws Exception {
        assertThat(GYROSCOPE.getRecordClass(), equalTo((Class) GyroscopeRecord.class));
    }

    @Test
    public void linearAcceleration() throws Exception {
        assertThat(LINEAR_ACCELERATION.getRecordClass(), equalTo((Class) LinearAccelerationRecord.class));
    }

    @Test
    public void locationProducer() throws Exception {
        assertThat(LOCATION.getRecordClass(), equalTo((Class) LocationRecord.class));
    }

    @Test
    public void magneticField() throws Exception {
        assertThat(MAGNETIC_FIELD.getRecordClass(), equalTo((Class) MagneticFieldRecord.class));
    }

    @Test
    public void rawGPSMeasurements() throws Exception {
        assertThat(RAW_GPS_MEASUREMENTS.getRecordClass(), equalTo((Class) RawGPSMeasurementsRecord.class));
    }

    @Test
    public void rawGPSNavigation() throws Exception {
        assertThat(RAW_GPS_NAVIGATION.getRecordClass(), equalTo((Class) RawGPSNavigationRecord.class));
    }

    @Test
    public void rawGPSStatus() throws Exception {
        assertThat(RAW_GPS_STATUS.getRecordClass(), equalTo((Class) RawGPSStatusRecord.class));
    }

    @Test
    public void rotationVector() throws Exception {
        assertThat(ROTATION_VECTOR.getRecordClass(), equalTo((Class) RotationVectorRecord.class));
    }

    @Test
    public void wifiMeasurements() throws Exception {
        assertThat(WIFI_MEASUREMENTS.getRecordClass(), equalTo((Class) WifiMeasurementsRecord.class));
    }

    @Test
    public void imuValues() throws Exception {
        SensorType[] imuValues = new SensorType[] {
                ACCELEROMETER,
                GRAVITY,
                GYROSCOPE,
                LINEAR_ACCELERATION,
                MAGNETIC_FIELD,
                ROTATION_VECTOR
        };

        assertThat(SensorType.imuValues(), equalTo(imuValues));
    }

    @Test
    public void gpsValues() throws Exception {
        SensorType[] gpsValues = new SensorType[] {
                LOCATION,
                RAW_GPS_MEASUREMENTS,
                RAW_GPS_NAVIGATION,
                RAW_GPS_STATUS
        };
        
        assertThat(SensorType.gpsValues(), equalTo(gpsValues));
    }

    @Test
    public void rawGPSValues() throws Exception {
        SensorType[] rawGPSValues = new SensorType[] {
                RAW_GPS_MEASUREMENTS,
                RAW_GPS_NAVIGATION,
                RAW_GPS_STATUS
        };

        assertThat(SensorType.rawGPSValues(), equalTo(rawGPSValues));
    }

    @Test
    public void wifiValues() throws Exception {
        SensorType[] wifiValues = new SensorType[] {
                WIFI_MEASUREMENTS
        };

        assertThat(SensorType.wifiValues(), equalTo(wifiValues));
    }
}