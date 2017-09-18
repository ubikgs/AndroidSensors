package com.ubikgs.androidsensors.checkers.internal;

import com.ubikgs.androidsensors.SensorType;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alberto on 13/9/17.
 */
public class RawGPSSensorCheckerTest extends GPSSensorCheckerTest {

    private final static int SDK_THRESHOLD = 24;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        gpsSensorChecker = new RawGPSSensorChecker(locationManager, SDK_THRESHOLD);
    }

    @Test
    public void isReady_whenBelowSDKVersion_returnsFalse() throws Exception {
        gpsSensorChecker = new RawGPSSensorChecker(locationManager, SDK_THRESHOLD - 1);

        boolean ready = gpsSensorChecker.isReady(SensorType.LOCATION);

        assertThat(ready, is(false));
    }
}