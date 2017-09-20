package com.ubikgs.androidsensors.config;

import com.ubikgs.androidsensors.SensorType;
import com.ubikgs.androidsensors.utils.MillisecondsToMicroseconds;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;

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

public class BasicSensorConfig implements SensorConfig {

    private final long minSensorDelayMs;
    private final MillisecondsToMicroseconds millisecondsToMicroseconds;
    private final Set<SensorType> sensorsRunningInMicroseconds;

    @Inject
    public BasicSensorConfig(long minSensorDelayMs,
                             MillisecondsToMicroseconds millisecondsToMicroseconds) {
        this.minSensorDelayMs = minSensorDelayMs;
        this.millisecondsToMicroseconds = millisecondsToMicroseconds;

        this.sensorsRunningInMicroseconds = new HashSet<>(Arrays.asList(
                SensorType.imuValues()
        ));
    }

    @Override
    public long getMinSensorDelay(SensorType sensorType) {
        if (sensorsRunningInMicroseconds.contains(sensorType))
            return millisecondsToMicroseconds.convert(minSensorDelayMs);
        return minSensorDelayMs;
    }

    @Override
    public BackpressureStrategy getBackpressureStrategy(SensorType sensorType) {
        return BackpressureStrategy.BUFFER;
    }
}
