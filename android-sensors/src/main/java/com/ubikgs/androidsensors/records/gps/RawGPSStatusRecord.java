package com.ubikgs.androidsensors.records.gps;

import android.location.GnssStatus;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.ubikgs.androidsensors.records.RecordInfo;
import com.ubikgs.androidsensors.records.SensorRecord;

import java.util.Arrays;

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

public class RawGPSStatusRecord extends SensorRecord {

    private int satelliteCount;
    private float[] azimuths;
    private float[] cn0DHzs;
    private int[] constellationTypes; // 0 = UNKNOWN, 1 = GPS, 2 = SBAS, 3 = GLONASS, 4 = QZSS, 5 = BEIDOU, 6 = GALILEO
    private float[] elevations;
    private int[] svids;

    public RawGPSStatusRecord() {
        super();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public RawGPSStatusRecord(GnssStatus status) {
        super(new RecordInfo(System.currentTimeMillis()));

        this.satelliteCount = status.getSatelliteCount();

        this.azimuths = new float[satelliteCount];
        this.cn0DHzs = new float[satelliteCount];
        this.constellationTypes = new int[satelliteCount];
        this.elevations = new float[satelliteCount];
        this.svids = new int[satelliteCount];

        for (int i = 0; i < satelliteCount; i++) {
            azimuths[i] = status.getAzimuthDegrees(i);
            cn0DHzs[i] = status.getCn0DbHz(i);
            constellationTypes[i] = status.getConstellationType(i);
            elevations[i] = status.getElevationDegrees(i);
            svids[i] = status.getSvid(i);
        }

    }

    public RawGPSStatusRecord(RawGPSStatusRecord rawGPSStatusRecord) {
        super(rawGPSStatusRecord);
        this.satelliteCount = rawGPSStatusRecord.getSatelliteCount();
        this.azimuths = rawGPSStatusRecord.getAzimuths();
        this.cn0DHzs = rawGPSStatusRecord.getCn0DHzs();
        this.constellationTypes = rawGPSStatusRecord.getConstellationTypes();
        this.elevations = rawGPSStatusRecord.getElevations();
        this.svids = rawGPSStatusRecord.getSvids();
    }

    public int getSatelliteCount() {
        return satelliteCount;
    }

    public void setSatelliteCount(int satelliteCount) {
        this.satelliteCount = satelliteCount;
    }

    public float[] getAzimuths() {
        return azimuths;
    }

    public void setAzimuths(float[] azimuths) {
        this.azimuths = azimuths;
    }

    public float[] getCn0DHzs() {
        return cn0DHzs;
    }

    public void setCn0DHzs(float[] cn0DHzs) {
        this.cn0DHzs = cn0DHzs;
    }

    public int[] getConstellationTypes() {
        return constellationTypes;
    }

    public void setConstellationTypes(int[] constellationTypes) {
        this.constellationTypes = constellationTypes;
    }

    public float[] getElevations() {
        return elevations;
    }

    public void setElevations(float[] elevations) {
        this.elevations = elevations;
    }

    public int[] getSvids() {
        return svids;
    }

    public void setSvids(int[] svids) {
        this.svids = svids;
    }

    @Override
    public String toString() {
        return "RawGPSStatusRecord{" +
                "satelliteCount=" + satelliteCount +
                ", azimuths=" + Arrays.toString(azimuths) +
                ", cn0DHzs=" + Arrays.toString(cn0DHzs) +
                ", constellationTypes=" + Arrays.toString(constellationTypes) +
                ", elevations=" + Arrays.toString(elevations) +
                ", svids=" + Arrays.toString(svids) +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RawGPSStatusRecord)) return false;
        if (!super.equals(o)) return false;

        RawGPSStatusRecord that = (RawGPSStatusRecord) o;

        if (getSatelliteCount() != that.getSatelliteCount()) return false;
        if (!Arrays.equals(getAzimuths(), that.getAzimuths())) return false;
        if (!Arrays.equals(getCn0DHzs(), that.getCn0DHzs())) return false;
        if (!Arrays.equals(getConstellationTypes(), that.getConstellationTypes()))
            return false;
        if (!Arrays.equals(getElevations(), that.getElevations())) return false;
        return Arrays.equals(getSvids(), that.getSvids());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getSatelliteCount();
        result = 31 * result + Arrays.hashCode(getAzimuths());
        result = 31 * result + Arrays.hashCode(getCn0DHzs());
        result = 31 * result + Arrays.hashCode(getConstellationTypes());
        result = 31 * result + Arrays.hashCode(getElevations());
        result = 31 * result + Arrays.hashCode(getSvids());
        return result;
    }

}
