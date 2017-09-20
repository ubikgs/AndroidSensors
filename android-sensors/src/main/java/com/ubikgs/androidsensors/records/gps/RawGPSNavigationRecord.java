package com.ubikgs.androidsensors.records.gps;

import android.location.GnssNavigationMessage;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.ubikgs.androidsensors.records.RecordInfo;
import com.ubikgs.androidsensors.records.SensorRecord;

/**
 *  Copyright 2017 Alberto González Pérez
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     <p>http://www.apache.org/licenses/LICENSE-2.0</p>
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
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

    public void setMessage(String message) {
        this.message = message;
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
