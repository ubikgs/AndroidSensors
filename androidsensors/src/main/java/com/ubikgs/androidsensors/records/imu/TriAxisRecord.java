package com.ubikgs.androidsensors.records.imu;

import android.hardware.SensorEvent;

import com.ubikgs.androidsensors.records.RecordInfo;
import com.ubikgs.androidsensors.records.SensorRecord;

/**
 * Created by alberto on 18/9/17.
 */

public abstract class TriAxisRecord extends SensorRecord {
    private static final int X = 0, Y = 1, Z = 2;

    private float x;
    private float y;
    private float z;

    public TriAxisRecord() {
        super();
    }

    public TriAxisRecord(RecordInfo recordInfo, float x, float y, float z) {
        super(recordInfo);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public TriAxisRecord(SensorEvent event) {
        this(
                new RecordInfo(event.accuracy, event.timestamp),
                event.values[X],
                event.values[Y],
                event.values[Z]
        );
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "TriAxisRecord{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TriAxisRecord)) return false;
        if (!super.equals(o)) return false;

        TriAxisRecord that = (TriAxisRecord) o;

        if (Float.compare(that.getX(), getX()) != 0) return false;
        if (Float.compare(that.getY(), getY()) != 0) return false;
        return Float.compare(that.getZ(), getZ()) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getX() != +0.0f ? Float.floatToIntBits(getX()) : 0);
        result = 31 * result + (getY() != +0.0f ? Float.floatToIntBits(getY()) : 0);
        result = 31 * result + (getZ() != +0.0f ? Float.floatToIntBits(getZ()) : 0);
        return result;
    }
}
