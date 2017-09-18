package com.ubikgs.androidsensors.factories;

import com.ubikgs.androidsensors.gatherers.SensorGatherer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by alberto on 15/9/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class SensorGathererFactoryTest {

    @Mock SensorGatherer sensorGatherer1;
    @Mock SensorGatherer sensorGatherer2;

    private SensorGathererFactory sensorGathererFactory;

    @Before
    public void setUp() throws Exception {
        HashSet<SensorGatherer> sensorGatherers =
                new HashSet<>(Arrays.asList(sensorGatherer1, sensorGatherer2));
        sensorGathererFactory = new SensorGathererFactory(sensorGatherers);

        when(sensorGatherer1.isReady()).thenReturn(true);
        when(sensorGatherer2.isReady()).thenReturn(true);

        when(sensorGatherer1.isCritical()).thenReturn(true);
        when(sensorGatherer2.isCritical()).thenReturn(true);
    }

    @Test
    public void getGatherers_allAreCritical_returnsTwo() throws Exception {
        assertGatherersSizeIs(2);
    }

    @Test
    public void getGatherers_allAreNonCriticalAndAreAvailable_returnsTwo() throws Exception {
        when(sensorGatherer1.isCritical()).thenReturn(false);
        when(sensorGatherer2.isCritical()).thenReturn(false);

        assertGatherersSizeIs(2);
    }

    @Test
    public void getGatherers_allAreNonCriticalAndAreNotAvailable_returnsZero() throws Exception {
        when(sensorGatherer1.isReady()).thenReturn(false);
        when(sensorGatherer2.isReady()).thenReturn(false);

        when(sensorGatherer1.isCritical()).thenReturn(false);
        when(sensorGatherer2.isCritical()).thenReturn(false);

        assertGatherersSizeIs(0);
    }

    @Test
    public void getGatherers_oneIsNotCriticalAndIsNotAvailableAndTheOtherIsCritical() throws Exception {
        when(sensorGatherer1.isReady()).thenReturn(false);

        when(sensorGatherer1.isCritical()).thenReturn(false);

        assertGatherersSizeIs(1);
    }

    private void assertGatherersSizeIs(int expectedSize) {
        Set<SensorGatherer> gatherers = sensorGathererFactory.getGatherers();

        assertThat(gatherers.size(), equalTo(expectedSize));
    }
}