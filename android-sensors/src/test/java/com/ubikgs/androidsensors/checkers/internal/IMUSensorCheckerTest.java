package com.ubikgs.androidsensors.checkers.internal;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import com.ubikgs.androidsensors.SensorType;
import com.ubikgs.androidsensors.utils.SensorTypeToAndroidSensor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

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
@RunWith(MockitoJUnitRunner.class)
public class IMUSensorCheckerTest {

    private static final SensorType SENSOR_TYPE = SensorType.ACCELEROMETER;
    private static final int ANDROID_SENSOR = 0;

    @Mock SensorManager sensorManager;
    @Mock SensorTypeToAndroidSensor sensorTypeToAndroidSensor;
    @Mock Sensor sensor;

    private IMUSensorChecker imuSensorChecker;

    @Before
    public void setUp() throws Exception {
        imuSensorChecker = new IMUSensorChecker(sensorTypeToAndroidSensor, sensorManager);

        when(sensorTypeToAndroidSensor.getAndroidSensorType(SENSOR_TYPE)).thenReturn(ANDROID_SENSOR);
        when(sensorManager.getDefaultSensor(ANDROID_SENSOR)).thenReturn(sensor);
    }

    @Test
    public void isReady_ifThereIsADefaultSensor() throws Exception {
        boolean ready = checkIfSensorIsReady();

        assertThat(ready, is(true));
    }

    @Test
    public void isReady_ifThereIsNoDefaultSensor_returnsFalse() throws Exception {
        when(sensorManager.getDefaultSensor(ANDROID_SENSOR)).thenReturn(null);

        boolean ready = checkIfSensorIsReady();

        assertThat(ready, is(false));
    }

    private boolean checkIfSensorIsReady() {
        return imuSensorChecker.isReady(SENSOR_TYPE);
    }
}