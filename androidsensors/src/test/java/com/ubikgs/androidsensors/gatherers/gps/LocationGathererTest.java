package com.ubikgs.androidsensors.gatherers.gps;

import com.ubikgs.androidsensors.SensorType;

import org.junit.Before;

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
public class LocationGathererTest extends GPSGathererTest {

    @Override
    @Before
    public void setUp() throws Exception {
        sensorGatherer = new LocationGatherer(sensorConfig, locationManager,
                sensorEnableRequester, permissionChecker, sensorChecker, sensorRequirementChecker);
        super.setUp();
    }

    @Override
    protected SensorType getSensorType() {
        return SensorType.LOCATION;
    }
}