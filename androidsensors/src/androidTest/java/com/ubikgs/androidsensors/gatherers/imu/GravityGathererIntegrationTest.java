package com.ubikgs.androidsensors.gatherers.imu;

import com.ubikgs.androidsensors.DaggerTestBedComponent;
import com.ubikgs.androidsensors.gatherers.SensorGathererIntegrationTest;
import com.ubikgs.androidsensors.records.SensorRecord;
import com.ubikgs.androidsensors.records.imu.GravityRecord;

import org.junit.Before;

import javax.inject.Inject;

/**
 * Created by alberto on 12/9/17.
 */
public class GravityGathererIntegrationTest extends SensorGathererIntegrationTest {

    @Inject GravityGatherer gravityGatherer;

    @Before
    public void setUp() throws Exception {
        DaggerTestBedComponent.create().inject(this);
        super.sensorGatherer = gravityGatherer;
    }

    protected Class<? extends SensorRecord> getSensorRecordClass() {
        return GravityRecord.class;
    }
}