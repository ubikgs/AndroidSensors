package com.ubikgs.androidsensors.checkers.applevel;

import com.ubikgs.androidsensors.SensorType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by alberto on 15/9/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class BasicCriticalityCheckerTest {

    private BasicCriticalityChecker basicCriticalityChecker;

    @Before
    public void setUp() throws Exception {
        basicCriticalityChecker = new BasicCriticalityChecker();
    }

    @Test
    public void isCritical_locationGatherer_isCritical() throws Exception {
        boolean critical = basicCriticalityChecker.isCritical(SensorType.LOCATION);
        assertThat(critical, is(true));
    }

    @Test
    public void isCritical_rawGPSMeasurementsGatherer_isNotCritical() throws Exception {
        boolean critical = basicCriticalityChecker.isCritical(SensorType.RAW_GPS_MEASUREMENTS);
        assertThat(critical, is(false));
    }

    @Test
    public void isCritical_rawGPSNavigationGatherer_isNotCritical() throws Exception {
        boolean critical = basicCriticalityChecker.isCritical(SensorType.RAW_GPS_NAVIGATION);
        assertThat(critical, is(false));
    }

    @Test
    public void isCritical_rawGPSStatusGatherer_isNotCritical() throws Exception {
        boolean critical = basicCriticalityChecker.isCritical(SensorType.RAW_GPS_STATUS);
        assertThat(critical, is(false));
    }
}