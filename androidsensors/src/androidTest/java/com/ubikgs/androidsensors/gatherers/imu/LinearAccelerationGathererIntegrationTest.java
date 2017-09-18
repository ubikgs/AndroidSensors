package com.ubikgs.androidsensors.gatherers.imu;

import com.ubikgs.androidsensors.DaggerTestBedComponent;
import com.ubikgs.androidsensors.gatherers.SensorGathererIntegrationTest;
import com.ubikgs.androidsensors.records.SensorRecord;
import com.ubikgs.androidsensors.records.imu.LinearAccelerationRecord;

import org.junit.Before;

import javax.inject.Inject;

/**
 * Created by alberto on 12/9/17.
 */
public class LinearAccelerationGathererIntegrationTest extends SensorGathererIntegrationTest {

    @Inject LinearAccelerationGatherer linearAccelerationGatherer;

    @Before
    public void setUp() throws Exception {
        DaggerTestBedComponent.create().inject(this);
        sensorGatherer = linearAccelerationGatherer;
    }

    @Override
    protected Class<? extends SensorRecord> getSensorRecordClass() {
        return LinearAccelerationRecord.class;
    }
}