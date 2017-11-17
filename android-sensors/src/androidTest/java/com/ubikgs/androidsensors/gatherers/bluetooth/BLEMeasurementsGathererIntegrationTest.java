package com.ubikgs.androidsensors.gatherers.bluetooth;

import android.support.test.rule.GrantPermissionRule;

import com.ubikgs.androidsensors.DaggerTestBedComponent;
import com.ubikgs.androidsensors.gatherers.SensorGathererIntegrationTest;
import com.ubikgs.androidsensors.records.SensorRecord;
import com.ubikgs.androidsensors.records.bluetooth.BLEMeasurementsRecord;

import org.junit.Before;
import org.junit.Rule;

import javax.inject.Inject;

/**
 * Copyright 2017 Alberto González Pérez
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * <p>http://www.apache.org/licenses/LICENSE-2.0</p>
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class BLEMeasurementsGathererIntegrationTest extends SensorGathererIntegrationTest {

    @Rule public GrantPermissionRule mRuntimePermissionRule =
            GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);

    @Inject BLEMeasurementsGatherer bleMeasurementsGatherer;

    @Before
    public void setUp() throws Exception {
        DaggerTestBedComponent.create().inject(this);
        sensorGatherer = bleMeasurementsGatherer;

        gatheringPeriod = 10;
        expectedRecordsDuringPeriod = 2;
    }

    @Override
    protected Class<? extends SensorRecord> getSensorRecordClass() {
        return BLEMeasurementsRecord.class;
    }
}