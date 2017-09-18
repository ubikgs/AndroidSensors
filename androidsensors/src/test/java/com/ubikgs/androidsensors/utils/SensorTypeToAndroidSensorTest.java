package com.ubikgs.androidsensors.utils;

import android.hardware.Sensor;

import com.ubikgs.androidsensors.SensorType;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by alberto on 11/9/17.
 */
public class SensorTypeToAndroidSensorTest {

    private SensorTypeToAndroidSensor sensorTypeToAndroidSensor;

    @Before
    public void setUp() throws Exception {
        sensorTypeToAndroidSensor = new SensorTypeToAndroidSensor();
    }

    @Test
    public void given_accelerometer_returnsTypeAccelerometer() throws Exception {
        assertThatReturnsCorrectType(SensorType.ACCELEROMETER, Sensor.TYPE_ACCELEROMETER);
    }

    @Test
    public void given_gravity_returnsGravityType() throws Exception {
        assertThatReturnsCorrectType(SensorType.GRAVITY, Sensor.TYPE_GRAVITY);
    }

    @Test
    public void given_gyroscope_returnsGyroscopeType() throws Exception {
        assertThatReturnsCorrectType(SensorType.GYROSCOPE, Sensor.TYPE_GYROSCOPE);
    }

    @Test
    public void given_linearAcceleration_returnsLinearAccelerationType() throws Exception {
        assertThatReturnsCorrectType(SensorType.LINEAR_ACCELERATION, Sensor.TYPE_LINEAR_ACCELERATION);
    }

    @Test
    public void given_linearAcceleration_returnsMagneticFieldType() throws Exception {
        assertThatReturnsCorrectType(SensorType.MAGNETIC_FIELD, Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Test
    public void given_rotationVector_returnsMagneticFieldType() throws Exception {
        assertThatReturnsCorrectType(SensorType.ROTATION_VECTOR, Sensor.TYPE_ROTATION_VECTOR);
    }

    private void assertThatReturnsCorrectType(SensorType sensorType, int androidType) {
        int accelerometer = sensorTypeToAndroidSensor.getAndroidSensorType(sensorType);
        assertThat(accelerometer, equalTo(androidType));
    }
}