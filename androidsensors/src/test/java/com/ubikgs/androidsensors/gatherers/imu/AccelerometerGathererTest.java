package com.ubikgs.androidsensors.gatherers.imu;

import com.ubikgs.androidsensors.SensorType;

import org.junit.Before;

/**
 * Created by alberto on 11/9/17.
 */

public class AccelerometerGathererTest extends IMUSensorGathererTest {

    @Override
    @Before
    public void setUp() throws Exception {
        sensorGatherer = new AccelerometerGatherer(sensorConfig, sensorManager,
                sensorEnableRequester, permissionChecker, sensorChecker, sensorRequirementChecker,
                sensorTypeToAndroidSensor);
        super.setUp();
    }

    @Override
    protected SensorType getSensorType() {
        return SensorType.ACCELEROMETER;
    }
}