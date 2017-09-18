package com.ubikgs.androidsensors.factories;

import android.support.test.runner.AndroidJUnit4;

import com.ubikgs.androidsensors.DaggerTestBedComponent;
import com.ubikgs.androidsensors.gatherers.SensorGatherer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Set;

import javax.inject.Inject;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alberto on 15/9/17.
 */

@RunWith(AndroidJUnit4.class)
public class SensorGathererFactoryIntegrationTest {

    @Inject
    SensorGathererFactory sensorGathererFactory;

    @Before
    public void setUp() throws Exception {
        DaggerTestBedComponent.create().inject(this);
    }

    @Test
    public void getAvailableGatherers_returnsNonEmptySet() throws Exception {
        Set<SensorGatherer> availableGatherers = sensorGathererFactory.getGatherers();
        assertThat(availableGatherers.isEmpty(), is(false));
    }

}