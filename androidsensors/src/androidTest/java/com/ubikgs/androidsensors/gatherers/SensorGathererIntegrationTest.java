package com.ubikgs.androidsensors.gatherers;

import android.support.test.runner.AndroidJUnit4;

import com.ubikgs.androidsensors.records.SensorRecord;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alberto on 12/9/17.
 */

@RunWith(AndroidJUnit4.class)
public abstract class SensorGathererIntegrationTest {
    protected SensorGatherer sensorGatherer;

    protected int gatheringPeriod = 1;
    protected int expectedRecordsDuringPeriod = 10;

    @Before
    public void setUp() throws Exception {
        if (sensorGatherer == null) {
            throw new Error("Inject the corresponding gatherer class and assign it to the sensorGatherer property");
        }
    }

    @Test(timeout = 20000)
    public void dataStream_emitsAtLeastOneRecord_whenSensorIsAvailable() throws Exception {
        SensorRecord sensorRecord = sensorGatherer.recordStream()
                .subscribeOn(Schedulers.newThread())
                .take(1)
                .blockingFirst();

        assertThat(sensorRecord.getClass(), equalTo(getSensorRecordClass()));
    }

    @Test
    public void dataStream_emitsAtLeastXRecords_duringPeriod_whenSensorIsAvailable() throws Exception {
        List<SensorRecord> sensorRecords = sensorGatherer.recordStream()
                .subscribeOn(Schedulers.newThread())
                .take(gatheringPeriod, TimeUnit.SECONDS)
                .toList().blockingGet();

        assertThat(sensorRecords.size() >= expectedRecordsDuringPeriod, is(true));
    }

    protected abstract Class<? extends SensorRecord> getSensorRecordClass();
}
