package com.ubikgs.androidsensors.checkers.permissions;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
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
public class FineLocationPermissionCheckerTest {

    private static final int SDK_THRESHOLD = 23;
    @Mock Context context;
    private FineLocationPermissionChecker.CheckPermission checkPermission;

    private FineLocationPermissionChecker fineLocationPermissionChecker;

    @Before
    public void setUp() throws Exception {
        checkPermission = new FineLocationPermissionChecker.CheckPermission() {
            @Override
            public int check(Context context, String permission) {
                return context.checkSelfPermission(permission);
            }
        };

        fineLocationPermissionChecker = new FineLocationPermissionChecker(SDK_THRESHOLD,
                context, checkPermission);

        when(context.checkSelfPermission(fineLocationPermissionChecker.getNeededPermission()))
                .thenReturn(PackageManager.PERMISSION_GRANTED);
    }

    @Test
    public void getNeededPermission_matchesFineLocationPermission() throws Exception {
        String permission = fineLocationPermissionChecker.getNeededPermission();

        assertThat(permission, equalTo(Manifest.permission.ACCESS_FINE_LOCATION));
    }

    @Test
    public void hasPermission_aboveSDKThresholdAndWitPermission_checksOnContextAndReturnsTrue() throws Exception {
        verifyChecksOnContextAndReturns(true);
    }

    @Test
    public void hasPermission_aboveSDKThresholdAndWithNoPermission_checksOnContextAndReturnsFalse() throws Exception {
        when(context.checkSelfPermission(fineLocationPermissionChecker.getNeededPermission()))
                .thenReturn(PackageManager.PERMISSION_DENIED);

        verifyChecksOnContextAndReturns(false);
    }

    private void verifyChecksOnContextAndReturns(boolean bool) {
        boolean hasPermission = fineLocationPermissionChecker.isPermissionGranted();

        verify(context, times(1))
                .checkSelfPermission(fineLocationPermissionChecker.getNeededPermission());

        assertThat(hasPermission, is(bool));
    }

    @Test
    public void hasPermission_belowThreshold_returnsTrue() throws Exception {
        fineLocationPermissionChecker = new FineLocationPermissionChecker(SDK_THRESHOLD - 1,
                context, checkPermission);

        boolean hasPermission = fineLocationPermissionChecker.isPermissionGranted();

        assertThat(hasPermission, is(true));
    }
}