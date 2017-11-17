package com.ubikgs.androidsensors.checkers.internal;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.pm.PackageManager;

import com.ubikgs.androidsensors.SensorType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

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
@RunWith(MockitoJUnitRunner.class)
public class BLESensorCheckerTest {
    private final static int SDK_THRESHOLD = 21;

    @Mock BluetoothManager bluetoothManager;
    @Mock BluetoothAdapter bluetoothAdapter;

    @Mock PackageManager packageManager;

    private BLESensorChecker bleSensorChecker;

    @Before
    public void setUp() throws Exception {
        bleSensorChecker = new BLESensorChecker(bluetoothManager, packageManager, SDK_THRESHOLD);

        when(packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)).thenReturn(true);
        when(bluetoothManager.getAdapter()).thenReturn(bluetoothAdapter);
        when(bluetoothAdapter.isEnabled()).thenReturn(true);
    }

    @Test
    public void is_whenDeviceHasBLEFeatureMatchesRequiredVersionAndAdapterIsEnabled_returnsTrue() throws Exception {
        assertThatSensorStatusIs(true);
    }

    @Test
    public void is_whenDeviceNotHasBLEFeature_returnsFalse() throws Exception {
        when(packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)).thenReturn(false);

        assertThatSensorStatusIs(false);
    }

    @Test
    public void is_whenDeviceHasBLEFeatureMatchesRequiredVersionButHasNoAdapter_returnsFalse() throws Exception {
        when(bluetoothManager.getAdapter()).thenReturn(null);

        assertThatSensorStatusIs(false);
    }

    @Test
    public void is_whenDeviceHasBLEFeatureMatchesRequiredVersionAndAdapterIsNotEnabled_returnsFalse() throws Exception {
        when(bluetoothAdapter.isEnabled()).thenReturn(false);

        assertThatSensorStatusIs(false);
    }

    @Test
    public void isReady_whenBelowSDKVersion_returnsFalse() throws Exception {
        bleSensorChecker = new BLESensorChecker(bluetoothManager, packageManager,
                SDK_THRESHOLD - 1);

        assertThatSensorStatusIs(false);
    }

    private void assertThatSensorStatusIs(boolean expected) {
        boolean ready = bleSensorChecker.isReady(SensorType.BLE_MEASUREMENTS);

        assertThat(ready, is(expected));
    }

}