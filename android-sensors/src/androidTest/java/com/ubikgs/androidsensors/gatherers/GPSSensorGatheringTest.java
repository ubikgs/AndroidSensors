package com.ubikgs.androidsensors.gatherers;

import android.location.LocationManager;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.ubikgs.androidsensors.DaggerTestBedComponent;
import com.ubikgs.androidsensors.checkers.applevel.SensorRequirementChecker;
import com.ubikgs.androidsensors.checkers.internal.SensorChecker;
import com.ubikgs.androidsensors.checkers.permissions.PermissionChecker;
import com.ubikgs.androidsensors.config.SensorConfig;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;
import com.ubikgs.androidsensors.gatherers.gps.GPSGatherer;
import com.ubikgs.androidsensors.gatherers.gps.LocationGatherer;
import com.ubikgs.androidsensors.gatherers.gps.RawGPSMeasurementsGatherer;
import com.ubikgs.androidsensors.gatherers.gps.RawGPSNavigationGatherer;
import com.ubikgs.androidsensors.gatherers.gps.RawGPSStatusGatherer;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
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
public class GPSSensorGatheringTest {

    @Rule public GrantPermissionRule mRuntimePermissionRule =
            GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);

    @Inject SensorConfig sensorConfig;
    @Inject LocationManager locationManager;
    @Inject SensorEnableRequester sensorEnableRequester;
    @Inject @Named("fineLocationPermissionChecker") PermissionChecker permissionChecker;
    @Inject @Named("gpsSensorChecker") SensorChecker gpsSensorChecker;
    @Inject @Named("rawGPSSensorChecker") SensorChecker rawGPSSensorChecker;
    @Inject
    SensorRequirementChecker sensorRequirementChecker;

    @Inject LocationGatherer locationGatherer;

    @Before
    public void setUp() throws Exception {
        DaggerTestBedComponent.create().inject(this);
    }

    @Ignore
    @Test
    public void testLocationGatherer() throws Exception {
        testSensor(new GathererCreator() {
            @Override
            public GPSGatherer create() {
                return new LocationGatherer(sensorConfig, locationManager,
                        sensorEnableRequester, permissionChecker, gpsSensorChecker, sensorRequirementChecker);
            }
        });
    }

    @Ignore
    @Test
    public void testRawGPSMeasurementsGatherer() throws Exception {
        testSensor(new GathererCreator() {
            @Override
            public GPSGatherer create() {
                return new RawGPSMeasurementsGatherer(sensorConfig, locationManager,
                        sensorEnableRequester, permissionChecker, rawGPSSensorChecker, sensorRequirementChecker);
            }
        });
    }

    @Ignore
    @Test
    public void testRawGPSNavigationGatherer() throws Exception {
        testSensor(new GathererCreator() {
            @Override
            public GPSGatherer create() {
                return new RawGPSNavigationGatherer(sensorConfig, locationManager,
                        sensorEnableRequester, permissionChecker, rawGPSSensorChecker, sensorRequirementChecker);
            }
        });
    }

    @Ignore
    @Test
    public void testRawGPSStatusGaterer() throws Exception {
        testSensor(new GathererCreator() {
            @Override
            public GPSGatherer create() {
                return new RawGPSStatusGatherer(sensorConfig, locationManager,
                        sensorEnableRequester, permissionChecker, rawGPSSensorChecker, sensorRequirementChecker);
            }
        });
    }

    private void testSensor(final GathererCreator gathererCreator) {

        final String sensor = gathererCreator.create().getSensorType().name().toUpperCase();

        Log.d(sensor + " BENCHMARK", "Nº\treal");

        Disposable subscribe = locationGatherer.recordStream().subscribeOn(Schedulers.newThread()).subscribe();

        Observable.rangeLong(0, 10)
                .map(new Function<Long, Single<BenchmarkStep>>() {
                    @Override
                    public Single<BenchmarkStep> apply(final Long i) throws Exception {
                        return grab1SecGatheringSamplesAndAverage(gathererCreator)
                                .map(new Function<Long, BenchmarkStep>() {
                                    @Override
                                    public BenchmarkStep apply(Long count) throws Exception {
                                        return new BenchmarkStep(i, count);
                                    }
                                });
                    }
                })
                .map(new Function<Single<BenchmarkStep>, BenchmarkStep>() {
                    @Override
                    public BenchmarkStep apply(Single<BenchmarkStep> benchmarkStepSingle) throws Exception {
                        return benchmarkStepSingle.blockingGet();
                    }
                })
                .subscribe(new Consumer<BenchmarkStep>() {
                    @Override
                    public void accept(BenchmarkStep benchmarkStep) throws Exception {
                        Log.d(sensor + " BENCHMARK",
                                String.format("%d\t%d", benchmarkStep.number, benchmarkStep.count));
                    }
                });

        subscribe.dispose();
    }

    private Single<Long> grab1SecGatheringSamplesAndAverage(final GathererCreator gathererCreator) {
        return Observable.range(0, 3)
                .map(new Function<Integer, Single<Long>>() {
                    @Override
                    public Single<Long> apply(Integer integer) throws Exception {
                        return gatherDuring10Sec(gathererCreator);
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

    private Single<Long> gatherDuring10Sec(GathererCreator gathererCreator) {
        return gathererCreator.create().recordStream()
                .subscribeOn(Schedulers.newThread())
                .take(10, TimeUnit.SECONDS)
                .toObservable()
                .count();
    }

    private class BenchmarkStep {
        private final long number;
        private final long count;

        private BenchmarkStep(long number, long count) {
            this.number = number;
            this.count = count;
        }
    }

    private interface GathererCreator {
        GPSGatherer create();
    }
}
