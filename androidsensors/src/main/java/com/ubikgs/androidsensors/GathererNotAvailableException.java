package com.ubikgs.androidsensors;

import com.ubikgs.androidsensors.gatherers.SensorGatherer;

/**
 * Created by alberto on 18/9/17.
 */

public class GathererNotAvailableException extends RuntimeException {

    public GathererNotAvailableException(Class<? extends SensorGatherer> type) {
        super(String.format("%s is not available in this device", type.getClass().getSimpleName()));
    }

    public GathererNotAvailableException(SensorType sensorType) {
        super(String.format("%s is not available in this device", sensorType.name()));
    }
}
