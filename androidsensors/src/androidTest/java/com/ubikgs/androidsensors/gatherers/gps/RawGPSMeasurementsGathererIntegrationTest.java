package com.ubikgs.androidsensors.gatherers.gps;

import com.ubikgs.androidsensors.DaggerTestBedComponent;
import com.ubikgs.androidsensors.records.SensorRecord;
import com.ubikgs.androidsensors.records.gps.RawGPSMeasurementsRecord;

import org.junit.Before;

import javax.inject.Inject;

/**
 * Created by alberto on 13/9/17.
 */
public class RawGPSMeasurementsGathererIntegrationTest extends RawGPSGathererIntegrationTest {

    @Inject RawGPSMeasurementsGatherer rawGPSMeasurementsGatherer;
    @Inject LocationGatherer locationGatherer;

    @Override
    @Before
    public void setUp() throws Exception {
        DaggerTestBedComponent.create().inject(this);
        sensorGatherer = rawGPSMeasurementsGatherer;
        super.locationGatherer = locationGatherer;
        super.setUp();
    }

    @Override
    protected Class<? extends SensorRecord> getSensorRecordClass() {
        return RawGPSMeasurementsRecord.class;
    }
}