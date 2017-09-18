package com.ubikgs.androidsensors.gatherers.gps;

import org.junit.Before;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by alberto on 14/9/17.
 */

public abstract class RawGPSGathererIntegrationTest extends GPSGathererIntegrationTest {

    protected LocationGatherer locationGatherer;

    @Override
    @Before
    public void setUp() throws Exception {
        if (locationGatherer == null) {
            throw new Error("Fill in attribute locationGatherer before calling super.setUp()");
        }
        super.setUp();
    }

    @Override
    public void dataStream_emitsAtLeastOneRecord_whenSensorIsAvailable() throws Exception {
        Disposable subscribe = createLocationGathererSubscription();
        super.dataStream_emitsAtLeastOneRecord_whenSensorIsAvailable();
        subscribe.dispose();
    }

    @Override
    public void dataStream_emitsAtLeastXRecords_duringPeriod_whenSensorIsAvailable() throws Exception {
        Disposable subscribe = createLocationGathererSubscription();
        super.dataStream_emitsAtLeastXRecords_duringPeriod_whenSensorIsAvailable();
        subscribe.dispose();
    }

    private Disposable createLocationGathererSubscription() {
        return locationGatherer.recordStream().subscribeOn(Schedulers.newThread()).subscribe();
    }
}
