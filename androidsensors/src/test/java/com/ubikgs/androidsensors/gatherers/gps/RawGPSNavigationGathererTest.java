package com.ubikgs.androidsensors.gatherers.gps;

import com.ubikgs.androidsensors.SensorType;

import org.junit.Before;

/**
 * Created by alberto on 13/9/17.
 */
public class RawGPSNavigationGathererTest extends GPSGathererTest {

    @Override
    @Before
    public void setUp() throws Exception {
        sensorGatherer = new RawGPSNavigationGatherer(sensorConfig, locationManager,
                sensorEnableRequester, permissionChecker, sensorChecker, sensorRequirementChecker);
        super.setUp();
    }

    @Override
    protected SensorType getSensorType() {
        return SensorType.RAW_GPS_NAVIGATION;
    }
}