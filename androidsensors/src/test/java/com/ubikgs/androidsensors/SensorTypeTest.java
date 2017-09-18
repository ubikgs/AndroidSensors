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
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by alberto on 23/3/17.
 */
public class SensorTypeTest {
    @Test
    public void accelerometer() throws Exception {
        assertThat(ACCELEROMETER.getRecordClass(), equalTo(AccelerometerRecord.class));
    }

    @Test
    public void gravity() throws Exception {
        assertThat(GRAVITY.getRecordClass(), equalTo(GravityRecord.class));
    }

    @Test
    public void gyroscope() throws Exception {
        assertThat(GYROSCOPE.getRecordClass(), equalTo(GyroscopeRecord.class));
    }

    @Test
    public void linearAcceleration() throws Exception {
        assertThat(LINEAR_ACCELERATION.getRecordClass(), equalTo(LinearAccelerationRecord.class));
    }

    @Test
    public void locationProducer() throws Exception {
        assertThat(LOCATION.getRecordClass(), equalTo(LocationRecord.class));
    }

    @Test
    public void magneticField() throws Exception {
        assertThat(MAGNETIC_FIELD.getRecordClass(), equalTo(MagneticFieldRecord.class));
    }

    @Test
    public void rawGPSMeasurements() throws Exception {
        assertThat(RAW_GPS_MEASUREMENTS.getRecordClass(), equalTo(RawGPSMeasurementsRecord.class));
    }

    @Test
    public void rawGPSNavigation() throws Exception {
        assertThat(RAW_GPS_NAVIGATION.getRecordClass(), equalTo(RawGPSNavigationRecord.class));
    }

    @Test
    public void rawGPSStatus() throws Exception {
        assertThat(RAW_GPS_STATUS.getRecordClass(), equalTo(RawGPSStatusRecord.class));
    }

    @Test
    public void rotationVector() throws Exception {
        assertThat(ROTATION_VECTOR.getRecordClass(), equalTo(RotationVectorRecord.class));
    }
}