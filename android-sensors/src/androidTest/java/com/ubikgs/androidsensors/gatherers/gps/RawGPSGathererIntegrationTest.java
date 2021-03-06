package com.ubikgs.androidsensors.gatherers.gps;

import org.junit.Before;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
public abstract class RawGPSGathererIntegrationTest extends GPSGathererIntegrationTest {

    protected LocationGatherer locationGatherer;

    @Override
    @Before
    public void setUp() throws Exception {
        if (locationGatherer == null) {
            throw new Error("Fill in attribute locationGatherer before calling super.setUp()");
        }
        super.setUp();
    }

    @Override
    public void recordStream_emitsAtLeastOneRecord_whenSensorIsAvailable() throws Exception {
        Disposable subscribe = createLocationGathererSubscription();
        super.recordStream_emitsAtLeastOneRecord_whenSensorIsAvailable();
        subscribe.dispose();
    }

    @Override
    public void recordStream_emitsAtLeastXRecords_duringPeriod_whenSensorIsAvailable() throws Exception {
        Disposable subscribe = createLocationGathererSubscription();
        super.recordStream_emitsAtLeastXRecords_duringPeriod_whenSensorIsAvailable();
        subscribe.dispose();
    }

    private Disposable createLocationGathererSubscription() {
        return locationGatherer.recordStream().subscribeOn(Schedulers.newThread()).subscribe();
    }
}
