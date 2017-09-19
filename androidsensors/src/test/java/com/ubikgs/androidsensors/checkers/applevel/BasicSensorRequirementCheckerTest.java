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
public class BasicSensorRequirementCheckerTest {

    private BasicSensorRequirementChecker basicSensorRequirementChecker;

    @Before
    public void setUp() throws Exception {
        basicSensorRequirementChecker = new BasicSensorRequirementChecker();
    }

    @Test
    public void isRequired_locationGatherer_isRequired() throws Exception {
        boolean required = basicSensorRequirementChecker.isRequired(SensorType.LOCATION);
        assertThat(required, is(true));
    }

    @Test
    public void isRequired_rawGPSMeasurementsGatherer_isNotRequired() throws Exception {
        boolean required = basicSensorRequirementChecker.isRequired(SensorType.RAW_GPS_MEASUREMENTS);
        assertThat(required, is(false));
    }

    @Test
    public void isRequired_rawGPSNavigationGatherer_isNotRequired() throws Exception {
        boolean required = basicSensorRequirementChecker.isRequired(SensorType.RAW_GPS_NAVIGATION);
        assertThat(required, is(false));
    }

    @Test
    public void isRequired_rawGPSStatusGatherer_isNotRequired() throws Exception {
        boolean required = basicSensorRequirementChecker.isRequired(SensorType.RAW_GPS_STATUS);
        assertThat(required, is(false));
    }
}