package com.ubikgs.androidsensors.gatherers.imu;

import com.ubikgs.androidsensors.SensorType;

import org.junit.Before;

/**
 * Created by alberto on 12/9/17.
 */
public class MagneticFieldGathererTest extends IMUSensorGathererTest {

    @Override
    @Before
    public void setUp() throws Exception {
        sensorGatherer = new MagneticFieldGatherer(sensorConfig, sensorManager,
                sensorEnableRequester, permissionChecker, sensorChecker, criticalityChecker,
                sensorTypeToAndroidSensor);
        super.setUp();
    }

    @Override
    protected SensorType getSensorType() {
        return SensorType.MAGNETIC_FIELD;
    }
}