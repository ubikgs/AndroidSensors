package com.ubikgs.androidsensors.gatherers;

import android.support.test.runner.AndroidJUnit4;

import com.ubikgs.androidsensors.records.SensorRecord;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
@RunWith(AndroidJUnit4.class)
public abstract class SensorGathererIntegrationTest {
    protected SensorGatherer sensorGatherer;

    protected int gatheringPeriod = 1;
    protected int expectedRecordsDuringPeriod = 10;

    @Before
    public void setUp() throws Exception {
        if (sensorGatherer == null) {
            throw new Error("Inject the corresponding gatherer class and assign it to the sensorGatherer property");
        }
    }

    @Test(timeout = 20000)
    public void dataStream_emitsAtLeastOneRecord_whenSensorIsAvailable() throws Exception {
        SensorRecord sensorRecord = sensorGatherer.recordStream()
                .subscribeOn(Schedulers.newThread())
                .take(1)
                .blockingFirst();

        assertThat(sensorRecord.getClass(), equalTo(getSensorRecordClass()));
    }

    @Test
    public void dataStream_emitsAtLeastXRecords_duringPeriod_whenSensorIsAvailable() throws Exception {
        List<SensorRecord> sensorRecords = sensorGatherer.recordStream()
                .subscribeOn(Schedulers.newThread())
                .take(gatheringPeriod, TimeUnit.SECONDS)
                .toList().blockingGet();

        assertThat(sensorRecords.size() >= expectedRecordsDuringPeriod, is(true));
    }

    protected abstract Class<? extends SensorRecord> getSensorRecordClass();
}
