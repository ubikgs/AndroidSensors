package com.ubikgs.androidsensors.gatherers.imu;

import com.ubikgs.androidsensors.SensorType;

import org.junit.Before;

/**
 * Created by alberto on 12/9/17.
 */

public class GyroscopeGathererTest extends IMUSensorGathererTest {

    @Override
    @Before
    public void setUp() throws Exception {
        sensorGatherer = new GyroscopeGatherer(sensorConfig, sensorManager,
                sensorEnableRequester, permissionChecker, sensorChecker, sensorRequirementChecker,
                sensorTypeToAndroidSensor);
        super.setUp();
    }

    @Override
    protected SensorType getSensorType() {
        return SensorType.GYROSCOPE;
    }
}