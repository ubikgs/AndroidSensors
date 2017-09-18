package com.ubikgs.androidsensors.gatherers.imu;

import com.ubikgs.androidsensors.DaggerTestBedComponent;
import com.ubikgs.androidsensors.gatherers.SensorGathererIntegrationTest;
import com.ubikgs.androidsensors.records.SensorRecord;
import com.ubikgs.androidsensors.records.imu.AccelerometerRecord;

import org.junit.Before;

import javax.inject.Inject;

/**
 * Created by alberto on 11/9/17.
 */
public class AccelerometerGathererIntegrationTest extends SensorGathererIntegrationTest {

    @Inject AccelerometerGatherer accelerometerGatherer;

    @Before
    public void setUp() throws Exception {
        DaggerTestBedComponent.create().inject(this);
        super.sensorGatherer = accelerometerGatherer;
    }

    protected Class<? extends SensorRecord> getSensorRecordClass() {
        return AccelerometerRecord.class;
    }
}