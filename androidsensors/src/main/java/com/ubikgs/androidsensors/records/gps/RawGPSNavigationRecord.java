package com.ubikgs.androidsensors.records.gps;

import android.location.GnssNavigationMessage;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.ubikgs.androidsensors.records.RecordInfo;
import com.ubikgs.androidsensors.records.SensorRecord;

/**
 * Created by alberto on 18/9/17.
 */

public class RawGPSNavigationRecord extends SensorRecord {

    private String message;

    public RawGPSNavigationRecord() {
        super();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public RawGPSNavigationRecord(GnssNavigationMessage navigationMessage) {
        super(new RecordInfo(0.0f, System.currentTimeMillis()));
        this.message = navigationMessage.toString().replace('\n', ',');
    }

    public String getMessage() {
        return message;
    }

    public RawGPSNavigationRecord setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "RawGPSNavigationRecord{" +
                "message='" + message + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RawGPSNavigationRecord)) return false;
        if (!super.equals(o)) return false;

        RawGPSNavigationRecord that = (RawGPSNavigationRecord) o;

        return getMessage() != null ? getMessage().equals(that.getMessage()) : that.getMessage() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getMessage() != null ? getMessage().hashCode() : 0);
        return result;
    }
}
