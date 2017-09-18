package com.ubikgs.androidsensors.gatherers.gps;

import com.ubikgs.androidsensors.DaggerTestBedComponent;
import com.ubikgs.androidsensors.records.SensorRecord;
import com.ubikgs.androidsensors.records.gps.RawGPSStatusRecord;

import org.junit.Before;

import javax.inject.Inject;

/**
 * Created by alberto on 13/9/17.
 */
public class RawGPSStatusGathererIntegrationTest extends RawGPSGathererIntegrationTest {

    @Inject RawGPSStatusGatherer rawGPSStatusGatherer;
    @Inject LocationGatherer locationGatherer;

    @Override
    @Before
    public void setUp() throws Exception {
        DaggerTestBedComponent.create().inject(this);
        sensorGatherer = rawGPSStatusGatherer;
        super.locationGatherer = locationGatherer;
        super.setUp();
    }

    @Override
    protected Class<? extends SensorRecord> getSensorRecordClass() {
        return RawGPSStatusRecord.class;
    }
}