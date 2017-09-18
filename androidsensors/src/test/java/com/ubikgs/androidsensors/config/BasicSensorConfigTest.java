package com.ubikgs.androidsensors.config;

import com.ubikgs.androidsensors.SensorType;
import com.ubikgs.androidsensors.utils.MillisecondsToMicroseconds;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by alberto on 15/9/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class BasicSensorConfigTest {

    private final long MIN_DELAY_MS = 100;

    @Mock MillisecondsToMicroseconds millisecondsToMicroseconds;

    private BasicSensorConfig basicSensorConfig;

    @Before
    public void setUp() throws Exception {
        basicSensorConfig = new BasicSensorConfig(MIN_DELAY_MS, millisecondsToMicroseconds);
    }

    @Test
    public void getMinSensorDelay_forAllIMUSensor_returnsInUs() throws Exception {
        HashSet<SensorType> imuSensors = new HashSet<>(Arrays.asList(
                SensorType.ACCELEROMETER,
                SensorType.GRAVITY,
                SensorType.GYROSCOPE,
                SensorType.LINEAR_ACCELERATION,
                SensorType.MAGNETIC_FIELD,
                SensorType.ROTATION_VECTOR
        ));

        Observable.fromIterable(imuSensors)
                .map(basicSensorConfig::getMinSensorDelay)
                .blockingSubscribe();

        verify(millisecondsToMicroseconds, times(imuSensors.size())).convert(MIN_DELAY_MS);
    }

    @Test
    public void getMinSensorDelay_forAllNonIMUSensor_returnsInMs() throws Exception {
        HashSet<SensorType> nonIMUSensors = new HashSet<>(Arrays.asList(
                SensorType.LOCATION,
                SensorType.RAW_GPS_MEASUREMENTS,
                SensorType.RAW_GPS_NAVIGATION,
                SensorType.RAW_GPS_STATUS
        ));

        Long count = Observable.fromIterable(nonIMUSensors)
                .map(basicSensorConfig::getMinSensorDelay)
                .filter(delay -> delay.equals(MIN_DELAY_MS))
                .count()
                .blockingGet();

        assertThat(count.intValue(), equalTo(nonIMUSensors.size()));
    }

    @Test
    public void getBackpressureStrategy_forAllSensors_returnsBufferStrategy() throws Exception {
        HashSet<SensorType> allSensors = new HashSet<>(Arrays.asList(SensorType.values()));

        Long count = Observable.fromIterable(allSensors)
                .map(basicSensorConfig::getBackpressureStrategy)
                .filter(backpressureStrategy ->
                        backpressureStrategy.equals(BackpressureStrategy.BUFFER))
                .count()
                .blockingGet();

        assertThat(count.intValue(), equalTo(allSensors.size()));
    }
}