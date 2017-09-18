package com.ubikgs.androidsensors.config;

import com.ubikgs.androidsensors.SensorType;
import com.ubikgs.androidsensors.utils.MillisecondsToMicroseconds;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;

/**
 * Created by alberto on 15/9/17.
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
                SensorType.ACCELEROMETER,
                SensorType.GRAVITY,
                SensorType.GYROSCOPE,
                SensorType.LINEAR_ACCELERATION,
                SensorType.MAGNETIC_FIELD,
                SensorType.ROTATION_VECTOR
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
