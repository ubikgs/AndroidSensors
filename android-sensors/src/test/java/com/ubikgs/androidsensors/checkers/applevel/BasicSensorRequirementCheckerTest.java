package com.ubikgs.androidsensors.checkers.applevel;

import com.ubikgs.androidsensors.SensorType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 *  Copyright 2017 Alberto González Pérez
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     <p>http://www.apache.org/licenses/LICENSE-2.0</p>
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
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