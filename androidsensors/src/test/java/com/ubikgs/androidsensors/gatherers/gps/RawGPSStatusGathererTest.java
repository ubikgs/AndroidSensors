package com.ubikgs.androidsensors.gatherers.gps;

import com.ubikgs.androidsensors.SensorType;

import org.junit.Before;

/**
 * Created by alberto on 13/9/17.
 */
public class RawGPSStatusGathererTest extends GPSGathererTest {

    @Override
    @Before
    public void setUp() throws Exception {
        sensorGatherer = new RawGPSStatusGatherer(sensorConfig, locationManager,
                sensorEnableRequester, permissionChecker, sensorChecker, sensorRequirementChecker);
        super.setUp();
    }

    @Override
    protected SensorType getSensorType() {
        return SensorType.RAW_GPS_STATUS;
    }
}