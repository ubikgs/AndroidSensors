package com.ubikgs.androidsensors.gatherers.imu;

import com.ubikgs.androidsensors.DaggerTestBedComponent;
import com.ubikgs.androidsensors.gatherers.SensorGathererIntegrationTest;
import com.ubikgs.androidsensors.records.SensorRecord;
import com.ubikgs.androidsensors.records.imu.GyroscopeRecord;

import org.junit.Before;

import javax.inject.Inject;

/**
 * Created by alberto on 12/9/17.
 */
public class GyroscopeGathererIntegrationTest extends SensorGathererIntegrationTest {

    @Inject GyroscopeGatherer gyroscopeGatherer;

    @Before
    public void setUp() throws Exception {
        DaggerTestBedComponent.create().inject(this);
        this.sensorGatherer = gyroscopeGatherer;
    }

    @Override
    protected Class<? extends SensorRecord> getSensorRecordClass() {
        return GyroscopeRecord.class;
    }
}