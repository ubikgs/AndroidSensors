package com.ubikgs.androidsensors.checkers.internal;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;

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
public class BluetoothSensorCheckerTest {
    private final static int SDK_THRESHOLD = 18;

    @Mock BluetoothManager bluetoothManager;
    @Mock BluetoothAdapter bluetoothAdapter;

    private BluetoothSensorChecker bluetoothSensorChecker;

    @Before
    public void setUp() throws Exception {
        bluetoothSensorChecker = new BluetoothSensorChecker(bluetoothManager, SDK_THRESHOLD);
        when(bluetoothManager.getAdapter()).thenReturn(bluetoothAdapter);
        when(bluetoothAdapter.isEnabled()).thenReturn(true);
    }

    @Test
    public void is_whenMatchesRequiredVersionAndAdapterIsEnabled_returnsTrue() throws Exception {
        assertThatSensorStatusIs(true);
    }

    @Test
    public void is_whenMatchesRequiredVersionAndAdapterIsNotEnabled_returnsFalse() throws Exception {
        when(bluetoothAdapter.isEnabled()).thenReturn(false);

        assertThatSensorStatusIs(false);
    }

    @Test
    public void isReady_whenBelowSDKVersion_returnsFalse() throws Exception {
        bluetoothSensorChecker = new BluetoothSensorChecker(bluetoothManager, SDK_THRESHOLD - 1);

        assertThatSensorStatusIs(false);
    }

    private void assertThatSensorStatusIs(boolean expected) {
        boolean ready = bluetoothSensorChecker.isReady(SensorType.BLE_MEASUREMENTS);

        assertThat(ready, is(expected));
    }

}