package com.ubikgs.androidsensors.enablers;

import com.ubikgs.androidsensors.SensorType;

/**
 * Created by alberto on 11/9/17.
 */

public interface SensorEnableRequester {

    void performEnableRequestFor(SensorType sensorType);
}
