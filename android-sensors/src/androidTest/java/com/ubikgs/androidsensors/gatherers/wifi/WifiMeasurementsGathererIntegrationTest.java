package com.ubikgs.androidsensors.gatherers.wifi;

import com.ubikgs.androidsensors.DaggerTestBedComponent;
import com.ubikgs.androidsensors.gatherers.SensorGathererIntegrationTest;
import com.ubikgs.androidsensors.records.SensorRecord;
import com.ubikgs.androidsensors.records.wifi.WifiMeasurementsRecord;

import org.junit.Before;

import javax.inject.Inject;

/**
 * Created by alberto on 7/11/17.
 */
public class WifiMeasurementsGathererIntegrationTest extends SensorGathererIntegrationTest {

    @Inject WifiMeasurementsGatherer wifiMeasurementsGatherer;

    @Before
    public void setUp() throws Exception {
        DaggerTestBedComponent.create().inject(this);
        super.sensorGatherer = wifiMeasurementsGatherer;
    }

    @Override
    protected Class<? extends SensorRecord> getSensorRecordClass() {
        return WifiMeasurementsRecord.class;
    }

}