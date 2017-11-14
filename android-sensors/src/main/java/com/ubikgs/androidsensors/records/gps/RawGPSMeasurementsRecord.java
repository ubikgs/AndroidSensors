package com.ubikgs.androidsensors.records.gps;

import android.location.GnssMeasurement;
import android.location.GnssMeasurementsEvent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.ubikgs.androidsensors.records.RecordInfo;
import com.ubikgs.androidsensors.records.SensorRecord;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

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

public class RawGPSMeasurementsRecord extends SensorRecord {

    private int satelliteCount;

    private int[] svids;
    private int[] constellations;
    private double[] timeOffsets;
    private int[] stateCodes;
    private long[] svTimes;
    private long[] svTimeUncerts;
    private double[] cn0DbHzs;
    private double[] pseudoranges;
    private double[] pseudorangeUncerts;
    private int[] deltaStates;
    private double[] deltas;
    private double[] deltaUncerts;
    private int[] multipaths;

    public RawGPSMeasurementsRecord() {
        super();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public RawGPSMeasurementsRecord(GnssMeasurementsEvent event) {
        super(new RecordInfo(event.getClock().getTimeNanos()));
        Collection<GnssMeasurement> measurements = event.getMeasurements();

        this.satelliteCount = measurements.size();

        this.svids = new int[satelliteCount];
        this.constellations = new int[satelliteCount];
        this.timeOffsets = new double[satelliteCount];
        this.stateCodes = new int[satelliteCount];
        this.svTimes = new long[satelliteCount];
        this.svTimeUncerts = new long[satelliteCount];
        this.cn0DbHzs = new double[satelliteCount];
        this.pseudoranges = new double[satelliteCount];
        this.pseudorangeUncerts = new double[satelliteCount];
        this.deltaStates = new int[satelliteCount];
        this.deltas = new double[satelliteCount];
        this.deltaUncerts = new double[satelliteCount];
        this.multipaths = new int[satelliteCount];

        Iterator<GnssMeasurement> it = measurements.iterator();
        int i = 0;

        while (it.hasNext()) {
            GnssMeasurement measurement = it.next();

            svids[i] = measurement.getSvid();
            constellations[i] = measurement.getConstellationType();
            timeOffsets[i] = measurement.getTimeOffsetNanos();
            stateCodes[i] = measurement.getState();
            svTimes[i] = measurement.getReceivedSvTimeNanos();
            svTimeUncerts[i] = measurement.getReceivedSvTimeUncertaintyNanos();
            cn0DbHzs[i] = measurement.getCn0DbHz();
            pseudoranges[i] = measurement.getPseudorangeRateMetersPerSecond();
            pseudorangeUncerts[i] = measurement.getPseudorangeRateUncertaintyMetersPerSecond();
            deltaStates[i] = measurement.getAccumulatedDeltaRangeState();
            deltas[i] = measurement.getAccumulatedDeltaRangeMeters();
            deltaUncerts[i] = measurement.getAccumulatedDeltaRangeUncertaintyMeters();
            multipaths[i] = measurement.getMultipathIndicator();

            i++;
        }
    }

    public RawGPSMeasurementsRecord(RawGPSMeasurementsRecord rawGPSMeasurementsRecord) {
        super(rawGPSMeasurementsRecord);
        this.satelliteCount = rawGPSMeasurementsRecord.getSatelliteCount();
        this.svids = rawGPSMeasurementsRecord.getSvids();
        this.constellations = rawGPSMeasurementsRecord.getConstellations();
        this.timeOffsets = rawGPSMeasurementsRecord.getTimeOffsets();
        this.stateCodes = rawGPSMeasurementsRecord.getStateCodes();
        this.svTimes = rawGPSMeasurementsRecord.getSvTimes();
        this.svTimeUncerts = rawGPSMeasurementsRecord.getSvTimeUncerts();
        this.cn0DbHzs = rawGPSMeasurementsRecord.getCn0DbHzs();
        this.pseudoranges = rawGPSMeasurementsRecord.getPseudoranges();
        this.pseudorangeUncerts = rawGPSMeasurementsRecord.getPseudorangeUncerts();
        this.deltaStates = rawGPSMeasurementsRecord.getDeltaStates();
        this.deltas = rawGPSMeasurementsRecord.getDeltas();
        this.deltaUncerts = rawGPSMeasurementsRecord.getDeltaUncerts();
        this.multipaths = rawGPSMeasurementsRecord.getMultipaths();
    }

    public int getSatelliteCount() {
        return satelliteCount;
    }

    public void setSatelliteCount(int satelliteCount) {
        this.satelliteCount = satelliteCount;
    }

    public int[] getSvids() {
        return svids;
    }

    public void setSvids(int[] svids) {
        this.svids = svids;
    }

    public int[] getConstellations() {
        return constellations;
    }

    public void setConstellations(int[] constellations) {
        this.constellations = constellations;
    }

    public double[] getTimeOffsets() {
        return timeOffsets;
    }

    public void setTimeOffsets(double[] timeOffsets) {
        this.timeOffsets = timeOffsets;
    }

    public int[] getStateCodes() {
        return stateCodes;
    }

    public void setStateCodes(int[] stateCodes) {
        this.stateCodes = stateCodes;
    }

    public long[] getSvTimes() {
        return svTimes;
    }

    public void setSvTimes(long[] svTimes) {
        this.svTimes = svTimes;
    }

    public long[] getSvTimeUncerts() {
        return svTimeUncerts;
    }

    public void setSvTimeUncerts(long[] svTimeUncerts) {
        this.svTimeUncerts = svTimeUncerts;
    }

    public double[] getCn0DbHzs() {
        return cn0DbHzs;
    }

    public void setCn0DbHzs(double[] cn0DbHzs) {
        this.cn0DbHzs = cn0DbHzs;
    }

    public double[] getPseudoranges() {
        return pseudoranges;
    }

    public void setPseudoranges(double[] pseudoranges) {
        this.pseudoranges = pseudoranges;
    }

    public double[] getPseudorangeUncerts() {
        return pseudorangeUncerts;
    }

    public void setPseudorangeUncerts(double[] pseudorangeUncerts) {
        this.pseudorangeUncerts = pseudorangeUncerts;
    }

    public int[] getDeltaStates() {
        return deltaStates;
    }

    public void setDeltaStates(int[] deltaStates) {
        this.deltaStates = deltaStates;
    }

    public double[] getDeltas() {
        return deltas;
    }

    public void setDeltas(double[] deltas) {
        this.deltas = deltas;
    }

    public double[] getDeltaUncerts() {
        return deltaUncerts;
    }

    public void setDeltaUncerts(double[] deltaUncerts) {
        this.deltaUncerts = deltaUncerts;
    }

    public int[] getMultipaths() {
        return multipaths;
    }

    public void setMultipaths(int[] multipaths) {
        this.multipaths = multipaths;
    }

    @Override
    public String toString() {
        return "RawGPSMeasurementsRecord{" +
                "satelliteCount=" + satelliteCount +
                ", svids=" + Arrays.toString(svids) +
                ", constellations=" + Arrays.toString(constellations) +
                ", timeOffsets=" + Arrays.toString(timeOffsets) +
                ", stateCodes=" + Arrays.toString(stateCodes) +
                ", svTimes=" + Arrays.toString(svTimes) +
                ", svTimeUncerts=" + Arrays.toString(svTimeUncerts) +
                ", cn0DbHzs=" + Arrays.toString(cn0DbHzs) +
                ", pseudoranges=" + Arrays.toString(pseudoranges) +
                ", pseudorangeUncerts=" + Arrays.toString(pseudorangeUncerts) +
                ", deltaStates=" + Arrays.toString(deltaStates) +
                ", deltas=" + Arrays.toString(deltas) +
                ", deltaUncerts=" + Arrays.toString(deltaUncerts) +
                ", multipaths=" + Arrays.toString(multipaths) +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RawGPSMeasurementsRecord)) return false;
        if (!super.equals(o)) return false;

        RawGPSMeasurementsRecord that = (RawGPSMeasurementsRecord) o;

        if (getSatelliteCount() != that.getSatelliteCount()) return false;
        if (!Arrays.equals(getSvids(), that.getSvids())) return false;
        if (!Arrays.equals(getConstellations(), that.getConstellations())) return false;
        if (!Arrays.equals(getTimeOffsets(), that.getTimeOffsets())) return false;
        if (!Arrays.equals(getStateCodes(), that.getStateCodes())) return false;
        if (!Arrays.equals(getSvTimes(), that.getSvTimes())) return false;
        if (!Arrays.equals(getSvTimeUncerts(), that.getSvTimeUncerts())) return false;
        if (!Arrays.equals(getCn0DbHzs(), that.getCn0DbHzs())) return false;
        if (!Arrays.equals(getPseudoranges(), that.getPseudoranges())) return false;
        if (!Arrays.equals(getPseudorangeUncerts(), that.getPseudorangeUncerts())) return false;
        if (!Arrays.equals(getDeltaStates(), that.getDeltaStates())) return false;
        if (!Arrays.equals(getDeltas(), that.getDeltas())) return false;
        if (!Arrays.equals(getDeltaUncerts(), that.getDeltaUncerts())) return false;
        return Arrays.equals(getMultipaths(), that.getMultipaths());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getSatelliteCount();
        result = 31 * result + Arrays.hashCode(getSvids());
        result = 31 * result + Arrays.hashCode(getConstellations());
        result = 31 * result + Arrays.hashCode(getTimeOffsets());
        result = 31 * result + Arrays.hashCode(getStateCodes());
        result = 31 * result + Arrays.hashCode(getSvTimes());
        result = 31 * result + Arrays.hashCode(getSvTimeUncerts());
        result = 31 * result + Arrays.hashCode(getCn0DbHzs());
        result = 31 * result + Arrays.hashCode(getPseudoranges());
        result = 31 * result + Arrays.hashCode(getPseudorangeUncerts());
        result = 31 * result + Arrays.hashCode(getDeltaStates());
        result = 31 * result + Arrays.hashCode(getDeltas());
        result = 31 * result + Arrays.hashCode(getDeltaUncerts());
        result = 31 * result + Arrays.hashCode(getMultipaths());
        return result;
    }
}
