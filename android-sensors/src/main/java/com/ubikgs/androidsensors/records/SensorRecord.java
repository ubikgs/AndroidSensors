package com.ubikgs.androidsensors.records;

import java.io.Serializable;
import java.util.Date;

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
