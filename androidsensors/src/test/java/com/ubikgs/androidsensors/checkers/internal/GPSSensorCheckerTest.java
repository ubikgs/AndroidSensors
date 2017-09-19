package com.ubikgs.androidsensors.checkers.internal;

import android.location.LocationManager;

import com.ubikgs.androidsensors.SensorType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
public class GPSSensorCheckerTest {

    private static final String gpsProvider = LocationManager.GPS_PROVIDER;


    @Mock protected LocationManager locationManager;

    protected GPSSensorChecker gpsSensorChecker;

    @Before
    public void setUp() throws Exception {
        gpsSensorChecker = new GPSSensorChecker(locationManager);

        when(locationManager.isProviderEnabled(gpsProvider)).thenReturn(true);
    }

    @Test
    public void isReady_callsLocationManagerWithProviderEnabled_returnsTrue() throws Exception {
        verifyLocationProviderIsCalledAndReturns(true);
    }

    @Test
    public void isReady_callsLocationManagerWithProviderNotEnabled_returnsTrue() throws Exception {
        when(locationManager.isProviderEnabled(gpsProvider)).thenReturn(false);

        verifyLocationProviderIsCalledAndReturns(false);
    }

    private void verifyLocationProviderIsCalledAndReturns(boolean bool) {
        boolean ready = gpsSensorChecker.isReady(SensorType.LOCATION);

        verify(locationManager, times(1)).isProviderEnabled(gpsProvider);

        assertThat(ready, is(bool));
    }

}