package com.ubikgs.androidsensors.records;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by alberto on 18/9/17.
 */

public abstract class SensorRecord implements Serializable {
    private float accuracy;
    private long sensorTimestamp;
    private long systemTimestamp;

    public SensorRecord() {
        super();
    }

    public SensorRecord(RecordInfo recordInfo) {
        this.accuracy = recordInfo.getAccuracy();
        this.sensorTimestamp = recordInfo.getTimestamp();
        this.systemTimestamp = new Date().getTime();
    }

    public float getAccuracy() {
        return accuracy;
    }

    public SensorRecord setAccuracy(float accuracy) {
        this.accuracy = accuracy;
        return this;
    }

    public long getSensorTimestamp() {
        return sensorTimestamp;
    }

    public SensorRecord setSensorTimestamp(long sensorTimestamp) {
        this.sensorTimestamp = sensorTimestamp;
        return this;
    }

    public long getSystemTimestamp() {
        return systemTimestamp;
    }

    public SensorRecord setSystemTimestamp(long systemTimestamp) {
        this.systemTimestamp = systemTimestamp;
        return this;
    }

    @Override
    public String toString() {
        return "SensorRecord{" +
                "accuracy=" + accuracy +
                ", sensorTimestamp=" + sensorTimestamp +
                ", systemTimestamp=" + systemTimestamp +
                "} ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SensorRecord)) return false;

        SensorRecord that = (SensorRecord) o;

        if (Float.compare(that.getAccuracy(), getAccuracy()) != 0) return false;
        if (getSensorTimestamp() != that.getSensorTimestamp()) return false;
        return getSystemTimestamp() == that.getSystemTimestamp();
    }

    @Override
    public int hashCode() {
        int result = (getAccuracy() != +0.0f ? Float.floatToIntBits(getAccuracy()) : 0);
        result = 31 * result + (int) (getSensorTimestamp() ^ (getSensorTimestamp() >>> 32));
        result = 31 * result + (int) (getSystemTimestamp() ^ (getSystemTimestamp() >>> 32));
        return result;
    }
}
