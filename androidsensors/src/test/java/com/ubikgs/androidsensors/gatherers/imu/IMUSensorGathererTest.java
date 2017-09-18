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
 * Created by alberto on 13/9/17.
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
