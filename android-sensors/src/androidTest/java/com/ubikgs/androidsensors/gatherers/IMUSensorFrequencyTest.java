package com.ubikgs.androidsensors.gatherers;

import android.hardware.SensorManager;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.ubikgs.androidsensors.DaggerTestBedComponent;
import com.ubikgs.androidsensors.checkers.applevel.SensorRequirementChecker;
import com.ubikgs.androidsensors.checkers.internal.SensorChecker;
import com.ubikgs.androidsensors.checkers.permissions.PermissionChecker;
import com.ubikgs.androidsensors.config.BasicSensorConfig;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;
import com.ubikgs.androidsensors.gatherers.imu.AccelerometerGatherer;
import com.ubikgs.androidsensors.gatherers.imu.GravityGatherer;
import com.ubikgs.androidsensors.gatherers.imu.GyroscopeGatherer;
import com.ubikgs.androidsensors.gatherers.imu.IMUSensorGatherer;
import com.ubikgs.androidsensors.gatherers.imu.LinearAccelerationGatherer;
import com.ubikgs.androidsensors.gatherers.imu.MagneticFieldGatherer;
import com.ubikgs.androidsensors.gatherers.imu.RotationVectorGatherer;
import com.ubikgs.androidsensors.utils.MillisecondsToMicroseconds;
import com.ubikgs.androidsensors.utils.SensorTypeToAndroidSensor;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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
@RunWith(AndroidJUnit4.class)
public class IMUSensorFrequencyTest {

    @Inject SensorManager sensorManager;
    @Inject SensorEnableRequester sensorEnableRequester;
    @Inject PermissionChecker permissionChecker;
    @Inject @Named("imuSensorChecker") SensorChecker imuSensorChecker;
    @Inject
    SensorRequirementChecker sensorRequirementChecker;
    @Inject SensorTypeToAndroidSensor sensorTypeToAndroidSensor;
    @Inject MillisecondsToMicroseconds millisecondsToMicroseconds;

    @Before
    public void setUp() throws Exception {
        DaggerTestBedComponent.create().inject(this);
    }

    @Ignore
    @Test
    public void testAccelerometerSensorDelay() throws Exception {
        testSensorDelay(new GathererCreator() {
            @Override
            public IMUSensorGatherer createWithDelay(long i) {
                return new AccelerometerGatherer(
                        new BasicSensorConfig(i, millisecondsToMicroseconds), sensorManager,
                        sensorEnableRequester, permissionChecker, imuSensorChecker, sensorRequirementChecker,
                        sensorTypeToAndroidSensor);
            }
        });
    }

    @Ignore
    @Test
    public void testGravitySensorDelay() throws Exception {
        testSensorDelay(new GathererCreator() {
            @Override
            public IMUSensorGatherer createWithDelay(long i) {
                return new GravityGatherer(
                        new BasicSensorConfig(i, millisecondsToMicroseconds), sensorManager,
                        sensorEnableRequester, permissionChecker, imuSensorChecker, sensorRequirementChecker,
                        sensorTypeToAndroidSensor);
            }
        });
    }

    @Ignore
    @Test
    public void testGyroscopeSensorDelay() throws Exception {
        testSensorDelay(new GathererCreator() {
            @Override
            public IMUSensorGatherer createWithDelay(long i) {
                return new GyroscopeGatherer(
                        new BasicSensorConfig(i, millisecondsToMicroseconds), sensorManager,
                        sensorEnableRequester, permissionChecker, imuSensorChecker, sensorRequirementChecker,
                        sensorTypeToAndroidSensor);
            }
        });
    }

    @Ignore
    @Test
    public void testLinearAccelerationSensorDelay() throws Exception {
        testSensorDelay(new GathererCreator() {
            @Override
            public IMUSensorGatherer createWithDelay(long i) {
                return new LinearAccelerationGatherer(
                        new BasicSensorConfig(i, millisecondsToMicroseconds), sensorManager,
                        sensorEnableRequester, permissionChecker, imuSensorChecker, sensorRequirementChecker,
                        sensorTypeToAndroidSensor);
            }
        });
    }

    @Ignore
    @Test
    public void testMagneticFieldSensorDelay() throws Exception {
        testSensorDelay(new GathererCreator() {
            @Override
            public IMUSensorGatherer createWithDelay(long i) {
                return new MagneticFieldGatherer(
                        new BasicSensorConfig(i, millisecondsToMicroseconds), sensorManager,
                        sensorEnableRequester, permissionChecker, imuSensorChecker, sensorRequirementChecker,
                        sensorTypeToAndroidSensor);
            }
        });
    }

    @Ignore
    @Test
    public void testRotationVectorSensorDelay() throws Exception {
        testSensorDelay(new GathererCreator() {
            @Override
            public IMUSensorGatherer createWithDelay(long i) {
                return new RotationVectorGatherer(
                        new BasicSensorConfig(i, millisecondsToMicroseconds), sensorManager,
                        sensorEnableRequester, permissionChecker, imuSensorChecker, sensorRequirementChecker,
                        sensorTypeToAndroidSensor);
            }
        });
    }

    private void testSensorDelay(final GathererCreator gathererCreator) {

        final String sensor = gathererCreator.createWithDelay(0).getSensorType().name().toUpperCase();

        Log.d(sensor + " BENCHMARK", "TBR\treal\texpected");

        Observable.rangeLong(0, 101)
                .map(new Function<Long, Single<long[]>>() {
                    @Override
                    public Single<long[]> apply(final Long i) throws Exception {
                        return grab1SecGatheringSamplesAndAverage(i, gathererCreator)
                                .map(new Function<Long, long[]>() {
                                    @Override
                                    public long[] apply(Long count) throws Exception {
                                        return new long[]{i, count};
                                    }
                                });
                    }
                }).map(new Function<Single<long[]>, long[]>() {
                    @Override
                    public long[] apply(Single<long[]> single) throws Exception {
                        return single.blockingGet();
                    }
                })
                .subscribe(new Consumer<long[]>() {
                    @Override
                    public void accept(long[] result) throws Exception {
                        Log.d(sensor + " BENCHMARK",
                                String.format("%dms\t%dHz\t%dHz", result[0], result[1], result[0] == 0 ? 1000 : 1000 / result[0]));
                    }
                });
    }

    private Single<Long> grab1SecGatheringSamplesAndAverage(final Long i, final GathererCreator gathererCreator) {
        return Observable.range(0, 10)
                .map(new Function<Integer, Single<Long>>() {
                    @Override
                    public Single<Long> apply(Integer __) throws Exception {
                        return gatherDuring1SecWithSpecifiedDelay(i, gathererCreator);
                    }
                })
                .map(new Function<Single<Long>, Long>() {
                    @Override
                    public Long apply(Single<Long> longSingle) throws Exception {
                        return longSingle.blockingGet();
                    }
                })
                .reduce(new long[2], new BiFunction<long[], Long, long[]>() {
                    @Override
                    public long[] apply(long[] aggregate, Long result) throws Exception {
                        return new long[]{aggregate[0]+1, aggregate[1] + result};
                    }
                })
                .map(new Function<long[], Long>() {
                    @Override
                    public Long apply(long[] result) throws Exception {
                        return result[0] == 0 ? 0 : result[1] / result[0];
                    }
                });
    }

    private Single<Long> gatherDuring1SecWithSpecifiedDelay(Long i, GathererCreator gathererCreator) {
        return gathererCreator.createWithDelay(i).recordStream()
                .subscribeOn(Schedulers.newThread())
                .take(1, TimeUnit.SECONDS)
                .toObservable()
                .count();
    }

    /*
    ------------------------
    Gatherer creators
    ------------------------
     */

    private interface GathererCreator {
        IMUSensorGatherer createWithDelay(long i);
    }
}