package com.ubikgs.androidsensors.records.wifi;

import android.net.wifi.ScanResult;
import android.os.Build;

import com.ubikgs.androidsensors.records.RecordInfo;
import com.ubikgs.androidsensors.records.SensorRecord;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Copyright 2017 Alberto González Pérez
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * <p>http://www.apache.org/licenses/LICENSE-2.0</p>
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class WifiMeasurementsRecord extends SensorRecord {

    private int ssidCount;

    private String[] bssids;
    private String[] ssids;
    private String[] capabilities;
    private int[] centerFreq0s;
    private int[] centerFreq1s;
    private int[] channelWidths;
    private int[] frequencies;
    private int[] levels;
    private String[] operatorsFriendlyName;
    private long[] timestamps;
    private String[] venueNames;

    public WifiMeasurementsRecord(){
        super();
    }

    public WifiMeasurementsRecord(List<ScanResult> results){
        super(new RecordInfo());

        this.ssidCount = results.size();

        this.bssids = new String[ssidCount];
        this.ssids = new String[ssidCount];
        this.capabilities = new String[ssidCount];
        this.centerFreq0s = new int[ssidCount];
        this.centerFreq1s = new int[ssidCount];
        this.channelWidths = new int[ssidCount];
        this.frequencies = new int[ssidCount];
        this.levels = new int[ssidCount];
        this.operatorsFriendlyName = new String[ssidCount];
        this.timestamps = new long[ssidCount];
        this.venueNames = new String[ssidCount];

        Iterator<ScanResult> it = results.iterator();
        int i = 0;


        while (it.hasNext()) {
            ScanResult result = it.next();

            bssids[i] = result.BSSID;
            ssids[i] = result.SSID;
            capabilities[i] = result.capabilities;
            centerFreq0s[i] = getCenterFrequency0(result);
            centerFreq1s[i] = getCenterFrequency1(result);
            channelWidths[i] = getChannelWidth(result);
            frequencies[i] = result.frequency;
            levels[i] = result.level;
            operatorsFriendlyName[i] = getOperatorFriendlyName(result);
            timestamps[i] = getTimestamp(result);
            venueNames[i] = getVenueName(result);

            i++;
        }
    }

    public WifiMeasurementsRecord(WifiMeasurementsRecord wifiMeasurementsRecord){
        super(wifiMeasurementsRecord);

        this.ssidCount = wifiMeasurementsRecord.getSsidCount();
        this.bssids = wifiMeasurementsRecord.getBssids();
        this.ssids = wifiMeasurementsRecord.getSsids();
        this.capabilities = wifiMeasurementsRecord.getCapabilities();
        this.centerFreq0s = wifiMeasurementsRecord.getCenterFreq0s();
        this.centerFreq1s = wifiMeasurementsRecord.getCenterFreq1s();
        this.channelWidths = wifiMeasurementsRecord.getChannelWidths();
        this.frequencies = wifiMeasurementsRecord.getFrequencies();
        this.levels = wifiMeasurementsRecord.getLevels();
        this.operatorsFriendlyName = wifiMeasurementsRecord.getOperatorsFriendlyName();
        this.timestamps = wifiMeasurementsRecord.getTimestamps();
        this.venueNames = wifiMeasurementsRecord.getVenueNames();
    }

    public int getSsidCount() {
        return ssidCount;
    }

    public void setSsidCount(int ssidCount) {
        this.ssidCount = ssidCount;
    }

    public String[] getBssids() {
        return bssids;
    }

    public void setBssids(String[] bssids) {
        this.bssids = bssids;
    }

    public String[] getSsids() {
        return ssids;
    }

    public void setSsids(String[] ssids) {
        this.ssids = ssids;
    }

    public String[] getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String[] capabilities) {
        this.capabilities = capabilities;
    }

    public int[] getCenterFreq0s() {
        return centerFreq0s;
    }

    public void setCenterFreq0s(int[] centerFreq0s) {
        this.centerFreq0s = centerFreq0s;
    }

    public int[] getCenterFreq1s() {
        return centerFreq1s;
    }

    public void setCenterFreq1s(int[] centerFreq1s) {
        this.centerFreq1s = centerFreq1s;
    }

    public int[] getChannelWidths() {
        return channelWidths;
    }

    public void setChannelWidths(int[] channelWidths) {
        this.channelWidths = channelWidths;
    }

    public int[] getFrequencies() {
        return frequencies;
    }

    public void setFrequencies(int[] frequencies) {
        this.frequencies = frequencies;
    }

    public int[] getLevels() {
        return levels;
    }

    public void setLevels(int[] levels) {
        this.levels = levels;
    }

    public String[] getOperatorsFriendlyName() {
        return operatorsFriendlyName;
    }

    public void setOperatorsFriendlyName(String[] operatorsFriendlyName) {
        this.operatorsFriendlyName = operatorsFriendlyName;
    }

    public long[] getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(long[] timestamps) {
        this.timestamps = timestamps;
    }

    public String[] getVenueNames() {
        return venueNames;
    }

    public void setVenueNames(String[] venueNames) {
        this.venueNames = venueNames;
    }

    @Override
    public String toString() {
        return "WifiMeasurementsRecord{" +
                "ssidCount=" + ssidCount +
                ", bssids=" + Arrays.toString(bssids) +
                ", ssids=" + Arrays.toString(ssids) +
                ", capabilities=" + Arrays.toString(capabilities) +
                ", centerFreq0s=" + Arrays.toString(centerFreq0s) +
                ", centerFreq1s=" + Arrays.toString(centerFreq1s) +
                ", channelWidths=" + Arrays.toString(channelWidths) +
                ", frequencies=" + Arrays.toString(frequencies) +
                ", levels=" + Arrays.toString(levels) +
                ", operatorsFriendlyName=" + Arrays.toString(operatorsFriendlyName) +
                ", timestamps=" + Arrays.toString(timestamps) +
                ", venueNames=" + Arrays.toString(venueNames) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WifiMeasurementsRecord)) return false;
        if (!super.equals(o)) return false;

        WifiMeasurementsRecord that = (WifiMeasurementsRecord) o;

        if (getSsidCount() != that.getSsidCount()) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getBssids(), that.getBssids())) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getSsids(), that.getSsids())) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getCapabilities(), that.getCapabilities())) return false;
        if (!Arrays.equals(getCenterFreq0s(), that.getCenterFreq0s())) return false;
        if (!Arrays.equals(getCenterFreq1s(), that.getCenterFreq1s())) return false;
        if (!Arrays.equals(getChannelWidths(), that.getChannelWidths())) return false;
        if (!Arrays.equals(getFrequencies(), that.getFrequencies())) return false;
        if (!Arrays.equals(getLevels(), that.getLevels())) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getOperatorsFriendlyName(), that.getOperatorsFriendlyName()))
            return false;
        if (!Arrays.equals(getTimestamps(), that.getTimestamps())) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(getVenueNames(), that.getVenueNames());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getSsidCount();
        result = 31 * result + Arrays.hashCode(getBssids());
        result = 31 * result + Arrays.hashCode(getSsids());
        result = 31 * result + Arrays.hashCode(getCapabilities());
        result = 31 * result + Arrays.hashCode(getCenterFreq0s());
        result = 31 * result + Arrays.hashCode(getCenterFreq1s());
        result = 31 * result + Arrays.hashCode(getChannelWidths());
        result = 31 * result + Arrays.hashCode(getFrequencies());
        result = 31 * result + Arrays.hashCode(getLevels());
        result = 31 * result + Arrays.hashCode(getOperatorsFriendlyName());
        result = 31 * result + Arrays.hashCode(getTimestamps());
        result = 31 * result + Arrays.hashCode(getVenueNames());
        return result;
    }

    private int getCenterFrequency0(ScanResult result){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return result.centerFreq0;
        }
        return -1;
    }

    private int getCenterFrequency1(ScanResult result){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return result.centerFreq1;
        }
        return -1;
    }

    private int getChannelWidth(ScanResult result){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return result.channelWidth;
        }
        return -1;
    }

    private String getOperatorFriendlyName(ScanResult result){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return result.operatorFriendlyName.toString();
        }
        return "";
    }

    private long getTimestamp(ScanResult result){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return result.timestamp;
        }
        return -1;
    }

    private String getVenueName(ScanResult result){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return result.venueName.toString();
        }
        return "";
    }
}
