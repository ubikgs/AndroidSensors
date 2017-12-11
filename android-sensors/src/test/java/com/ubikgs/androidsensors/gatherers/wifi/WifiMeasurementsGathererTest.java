package com.ubikgs.androidsensors.gatherers.wifi;

import android.content.Context;
import android.net.wifi.WifiManager;

import com.ubikgs.androidsensors.SensorType;
import com.ubikgs.androidsensors.gatherers.SensorGathererTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Date;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

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
public class WifiMeasurementsGathererTest extends SensorGathererTest {

    @Mock WifiManager wifiManager;
    @Mock Context context;

    private WifiMeasurementsGatherer wifiMeasurementsGatherer;

    @Before
    public void setUp() throws Exception {
        wifiMeasurementsGatherer = new WifiMeasurementsGatherer(sensorConfig, wifiManager,
                sensorEnableRequester, permissionChecker, sensorChecker, sensorRequirementChecker, context);
        sensorGatherer = wifiMeasurementsGatherer;
        super.setUp();
    }

    @Override
    protected SensorType getSensorType() {
        return SensorType.WIFI_MEASUREMENTS;
    }

    @Test
    public void calculateDelay_noDiff_returnsFullDelay() throws Exception {
        long actualTime = new Date().getTime();
        long delay = wifiMeasurementsGatherer.calculateDiffDelay(actualTime, actualTime);
        assertThat(delay, equalTo(sensorConfig.getMinSensorDelay(SensorType.WIFI_MEASUREMENTS)));
    }

    @Test
    public void calculateDelay_halfDiff_returnsHalfDelay() throws Exception {
        long actualTime = new Date().getTime();
        long halfDelay = sensorConfig.getMinSensorDelay(SensorType.WIFI_MEASUREMENTS) / 2;
        long delay = wifiMeasurementsGatherer.calculateDiffDelay(actualTime - halfDelay, actualTime);
        assertThat(delay, equalTo(halfDelay));
    }

    @Test
    public void calculateDelay_fullDiff_returnsNoDelay() throws Exception {
        long actualTime = new Date().getTime();
        long sensorDelay = sensorConfig.getMinSensorDelay(SensorType.WIFI_MEASUREMENTS);
        long delay = wifiMeasurementsGatherer.calculateDiffDelay(actualTime - sensorDelay, actualTime);
        assertThat(delay, equalTo(0L));
    }
}