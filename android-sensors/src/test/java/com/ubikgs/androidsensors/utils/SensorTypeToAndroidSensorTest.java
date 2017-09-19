package com.ubikgs.androidsensors.utils;

import android.hardware.Sensor;

import com.ubikgs.androidsensors.SensorType;

import org.junit.Before;
import org.junit.Test;

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