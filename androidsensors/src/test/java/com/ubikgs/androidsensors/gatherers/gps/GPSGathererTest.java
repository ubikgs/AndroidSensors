package com.ubikgs.androidsensors.gatherers.gps;

import android.location.LocationManager;

import com.ubikgs.androidsensors.gatherers.SensorGathererTest;

import org.mockito.Mock;

/**
 * Created by alberto on 13/9/17.
 */

public abstract class GPSGathererTest extends SensorGathererTest {

    @Mock protected LocationManager locationManager;

}
