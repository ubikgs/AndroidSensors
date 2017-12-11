package com.ubikgs.androidsensors.gatherers;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.test.rule.GrantPermissionRule;
import android.util.Log;

import com.ubikgs.androidsensors.DaggerTestBedComponent;
import com.ubikgs.androidsensors.checkers.applevel.SensorRequirementChecker;
import com.ubikgs.androidsensors.checkers.internal.SensorChecker;
import com.ubikgs.androidsensors.checkers.permissions.PermissionChecker;
import com.ubikgs.androidsensors.config.BasicSensorConfig;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;
import com.ubikgs.androidsensors.gatherers.wifi.WifiMeasurementsGatherer;
import com.ubikgs.androidsensors.utils.MillisecondsToMicroseconds;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

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

public class WifiSensorGatheringTest {
    @Rule
    public GrantPermissionRule mRuntimePermissionRule =
            GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);

    @Inject WifiManager wifiManager;
    @Inject SensorEnableRequester sensorEnableRequester;
    @Inject @Named("fineLocationPermissionChecker") PermissionChecker permissionChecker;
    @Inject @Named("wifiSensorChecker") SensorChecker wifiSensorChecker;
    @Inject SensorRequirementChecker sensorRequirementChecker;
    @Inject Context context;

    @Before
    public void setUp() throws Exception {
        DaggerTestBedComponent.create().inject(this);
    }


    @Ignore
    @Test
    public void testWifiMeasurementsGatherer() throws Exception {
        testSensorDelay(new GathererCreator() {
            @Override
            public WifiMeasurementsGatherer createWithDelay(long delay) {
                return new WifiMeasurementsGatherer(
                        new BasicSensorConfig(delay, new MillisecondsToMicroseconds()), wifiManager,
                        sensorEnableRequester, permissionChecker, wifiSensorChecker,
                        sensorRequirementChecker, context);
            }
        });
    }

    private void testSensorDelay(final GathererCreator gathererCreator) {

        final String sensor = gathererCreator.createWithDelay(0).getSensorType().name().toUpperCase();

        Log.d(sensor + " BENCHMARK", "TBR\treal\texpected");

        Observable.rangeLong(1, 21)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return aLong * 50;
                    }
                })
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
        return Observable.range(0, 3)
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
                .take(10, TimeUnit.SECONDS)
                .toObservable()
                .count()
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long count) throws Exception {
                        return count / 10;
                    }
                });
    }

    private interface GathererCreator {
        WifiMeasurementsGatherer createWithDelay(long delay);
    }
}
