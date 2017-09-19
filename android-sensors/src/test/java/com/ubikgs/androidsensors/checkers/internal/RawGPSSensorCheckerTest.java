package com.ubikgs.androidsensors.checkers.internal;

import com.ubikgs.androidsensors.SensorType;

import org.junit.Before;
import org.junit.Test;

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