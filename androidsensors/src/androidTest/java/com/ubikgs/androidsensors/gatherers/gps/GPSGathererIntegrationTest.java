package com.ubikgs.androidsensors.gatherers.gps;

import android.support.test.rule.GrantPermissionRule;

import com.ubikgs.androidsensors.gatherers.SensorGathererIntegrationTest;

import org.junit.Before;
import org.junit.Rule;

/**
 * Created by alberto on 13/9/17.
 */

public abstract class GPSGathererIntegrationTest extends SensorGathererIntegrationTest {

    @Rule public GrantPermissionRule mRuntimePermissionRule =
            GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);

    @Override
    @Before
    public void setUp() throws Exception {
        gatheringPeriod = 10;
        expectedRecordsDuringPeriod = 5;
    }
}
