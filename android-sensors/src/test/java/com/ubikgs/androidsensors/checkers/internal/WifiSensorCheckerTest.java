package com.ubikgs.androidsensors.checkers.internal;

import android.net.wifi.WifiManager;

import com.ubikgs.androidsensors.SensorType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by alberto on 7/11/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class WifiSensorCheckerTest {

    @Mock WifiManager wifiManager;

    private WifiSensorChecker wifiSensorChecker;

    @Before
    public void setUp() throws Exception {
        wifiSensorChecker = new WifiSensorChecker(wifiManager);
    }

    @Test
    public void isReady_withWifiEnabled_returnsTrue() throws Exception {
        when(wifiManager.isWifiEnabled()).thenReturn(true);

        boolean ready = checkIfWifiIsReady();

        assertThat(ready, is(true));
    }

    @Test
    public void isReady_withWifiDisabled_returnsFalse() throws Exception {
        when(wifiManager.isWifiEnabled()).thenReturn(false);

        boolean ready = checkIfWifiIsReady();

        assertThat(ready, is(false));
    }

    private boolean checkIfWifiIsReady() {
        return wifiSensorChecker.isReady(SensorType.WIFI_MEASUREMENTS);
    }
}