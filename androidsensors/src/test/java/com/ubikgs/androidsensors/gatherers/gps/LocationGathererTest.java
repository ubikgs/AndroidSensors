package com.ubikgs.androidsensors.gatherers.gps;

import com.ubikgs.androidsensors.SensorType;

import org.junit.Before;

/**
 * Created by alberto on 13/9/17.
 */
public class LocationGathererTest extends GPSGathererTest {

    @Override
    @Before
    public void setUp() throws Exception {
        sensorGatherer = new LocationGatherer(sensorConfig, locationManager,
                sensorEnableRequester, permissionChecker, sensorChecker, criticalityChecker);
        super.setUp();
    }

    @Override
    protected SensorType getSensorType() {
        return SensorType.LOCATION;
    }
}