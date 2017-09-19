package com.ubikgs.androidsensors.gatherers.imu;

import android.hardware.SensorManager;

import com.ubikgs.androidsensors.SensorType;
import com.ubikgs.androidsensors.gatherers.SensorGathererTest;
import com.ubikgs.androidsensors.utils.SensorTypeToAndroidSensor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

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
public abstract class IMUSensorGathererTest extends SensorGathererTest {

    @Mock protected SensorManager sensorManager;
    @Mock protected SensorTypeToAndroidSensor sensorTypeToAndroidSensor;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        when(sensorConfig.getMinSensorDelay(any(SensorType.class))).thenReturn(100L);
    }

    @Test
    public void getAndroidSensorType_callsSensorTypeToAndroidSensor() throws Exception {
        ((IMUSensorGatherer) sensorGatherer).getAndroidSensorType();

        verify(sensorTypeToAndroidSensor, times(1))
                .getAndroidSensorType(getSensorType());
    }

}
