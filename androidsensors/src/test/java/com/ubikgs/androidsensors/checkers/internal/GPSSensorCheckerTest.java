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
 * Created by alberto on 13/9/17.
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