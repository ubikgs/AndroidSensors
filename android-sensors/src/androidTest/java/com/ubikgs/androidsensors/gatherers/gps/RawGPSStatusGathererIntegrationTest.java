package com.ubikgs.androidsensors.gatherers.gps;

import com.ubikgs.androidsensors.DaggerTestBedComponent;
import com.ubikgs.androidsensors.records.SensorRecord;
import com.ubikgs.androidsensors.records.gps.RawGPSStatusRecord;

import org.junit.Before;

import javax.inject.Inject;

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
public class RawGPSStatusGathererIntegrationTest extends RawGPSGathererIntegrationTest {

    @Inject RawGPSStatusGatherer rawGPSStatusGatherer;
    @Inject LocationGatherer locationGatherer;

    @Override
    @Before
    public void setUp() throws Exception {
        DaggerTestBedComponent.create().inject(this);
        sensorGatherer = rawGPSStatusGatherer;
        super.locationGatherer = locationGatherer;
        super.setUp();
    }

    @Override
    protected Class<? extends SensorRecord> getSensorRecordClass() {
        return RawGPSStatusRecord.class;
    }
}