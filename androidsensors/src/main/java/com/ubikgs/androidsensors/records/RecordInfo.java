package com.ubikgs.androidsensors.records;

/**
 * Created by alberto on 18/9/17.
 */

public class RecordInfo {
    private final float accuracy;
    private final long timestamp;

    public RecordInfo(float accuracy, long timestamp) {
        this.accuracy = accuracy;
        this.timestamp = timestamp;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
