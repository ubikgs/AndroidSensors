package com.ubikgs.androidsensors.records.imu;

import android.hardware.SensorEvent;

import com.ubikgs.androidsensors.records.RecordInfo;
import com.ubikgs.androidsensors.records.SensorRecord;

/**
 * Created by alberto on 18/9/17.
 */

public class RotationVectorRecord extends SensorRecord {

    private static final int X_SIN = 0, Y_SIN = 1, Z_SIN = 2, COS = 3, HEAD_ACCURACY = 4;

    private float xSin;
    private float ySin;
    private float zSin;
    private float cos;
    private float headingAccuracy;

    public RotationVectorRecord() {
        super();
    }

    public RotationVectorRecord(RecordInfo recordInfo, float xSin, float ySin, float zSin, float cos, float headingAccuracy) {
        super(recordInfo);
        this.xSin = xSin;
        this.ySin = ySin;
        this.zSin = zSin;
        this.cos = cos;
        this.headingAccuracy = headingAccuracy;
    }

    public RotationVectorRecord(SensorEvent event) {
        this(
                new RecordInfo(event.accuracy, event.timestamp),
                event.values[X_SIN],
                event.values[Y_SIN],
                event.values[Z_SIN],
                event.values[COS],
                event.values[HEAD_ACCURACY]
        );
    }

    public float getXSin() {
        return xSin;
    }

    public RotationVectorRecord setXSin(float xSin) {
        this.xSin = xSin;
        return this;
    }

    public float getYSin() {
        return ySin;
    }

    public RotationVectorRecord setYSin(float ySin) {
        this.ySin = ySin;
        return this;
    }

    public float getZSin() {
        return zSin;
    }

    public RotationVectorRecord setZSin(float zSin) {
        this.zSin = zSin;
        return this;
    }

    public float getCos() {
        return cos;
    }

    public RotationVectorRecord setCos(float cos) {
        this.cos = cos;
        return this;
    }

    public float getHeadingAccuracy() {
        return headingAccuracy;
    }

    public RotationVectorRecord setHeadingAccuracy(float headingAccuracy) {
        this.headingAccuracy = headingAccuracy;
        return this;
    }

    @Override
    public String toString() {
        return "RotationVectorRecord{" +
                "xSin=" + xSin +
                ", ySin=" + ySin +
                ", zSin=" + zSin +
                ", cos=" + cos +
                ", headingAccuracy=" + headingAccuracy +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RotationVectorRecord)) return false;
        if (!super.equals(o)) return false;

        RotationVectorRecord that = (RotationVectorRecord) o;

        if (Float.compare(that.xSin, xSin) != 0) return false;
        if (Float.compare(that.ySin, ySin) != 0) return false;
        if (Float.compare(that.zSin, zSin) != 0) return false;
        if (Float.compare(that.getCos(), getCos()) != 0) return false;
        return Float.compare(that.getHeadingAccuracy(), getHeadingAccuracy()) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (xSin != +0.0f ? Float.floatToIntBits(xSin) : 0);
        result = 31 * result + (ySin != +0.0f ? Float.floatToIntBits(ySin) : 0);
        result = 31 * result + (zSin != +0.0f ? Float.floatToIntBits(zSin) : 0);
        result = 31 * result + (getCos() != +0.0f ? Float.floatToIntBits(getCos()) : 0);
        result = 31 * result + (getHeadingAccuracy() != +0.0f ? Float.floatToIntBits(getHeadingAccuracy()) : 0);
        return result;
    }
}
