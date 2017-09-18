package com.ubikgs.androidsensors.utils;

import javax.inject.Inject;

/**
 * Created by alberto on 15/2/17.
 */

public class MillisecondsToMicroseconds {
    private static final int STEP = 1000;

    @Inject
    public MillisecondsToMicroseconds() {
    }

    public long convert(long milliseconds) {
        if (Long.MAX_VALUE / STEP < milliseconds) return Long.MAX_VALUE;
        return milliseconds * STEP;
    }
}