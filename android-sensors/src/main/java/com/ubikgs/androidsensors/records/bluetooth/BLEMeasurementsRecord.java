package com.ubikgs.androidsensors.records.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.ubikgs.androidsensors.records.RecordInfo;
import com.ubikgs.androidsensors.records.SensorRecord;

import java.util.Arrays;
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

public class BLEMeasurementsRecord extends SensorRecord {

    private int deviceCount;

    private String[] deviceAddresses;
    private String[] deviceNames;
    private int[] rssis;
    private int[] deviceBondStatuses;
    private int[] deviceTypes;
    private long[] timestampsNano;
    private int[] advertisingSids;
    private int[] dataStatuses;
    private int[] periodicAdvertisingIntervals;
    private int[] primaryPhies;
    private int[] secondaryPhies;
    private int[] txPowers;
    private boolean[] areConnectable;
    private boolean[] areLegacy;
    private String[] uuids;
    private int[] majors;
    private int[] minors;


    public BLEMeasurementsRecord() {
        super();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BLEMeasurementsRecord(List<ScanResult> scanResults) {
        super(new RecordInfo());

        this.deviceCount = scanResults.size();

        this.deviceAddresses = new String[deviceCount];
        this.deviceNames = new String[deviceCount];
        this.rssis = new int[deviceCount];
        this.deviceBondStatuses = new int[deviceCount];
        this.deviceTypes = new int[deviceCount];
        this.timestampsNano = new long[deviceCount];
        this.advertisingSids = new int[deviceCount];
        this.dataStatuses = new int[deviceCount];
        this.periodicAdvertisingIntervals = new int[deviceCount];
        this.primaryPhies = new int[deviceCount];
        this.secondaryPhies = new int[deviceCount];
        this.txPowers = new int[deviceCount];
        this.areConnectable = new boolean[deviceCount];
        this.areLegacy = new boolean[deviceCount];
        this.uuids = new String[deviceCount];
        this.majors = new int[deviceCount];
        this.minors = new int[deviceCount];

        for (int i = 0; i < scanResults.size(); i++) {
            ScanResult scanResult = scanResults.get(i);

            BluetoothDevice bluetoothDevice = scanResult.getDevice();
            this.deviceAddresses[i] = nullStringToEmpty(bluetoothDevice.getAddress());
            this.deviceNames[i] = nullStringToEmpty(bluetoothDevice.getName());
            this.rssis[i] = scanResult.getRssi();
            this.deviceBondStatuses[i] = bluetoothDevice.getBondState();
            this.deviceTypes[i] = bluetoothDevice.getType();
            this.timestampsNano[i] = scanResult.getTimestampNanos();

            this.advertisingSids[i] = getAdvertisingSid(scanResult);
            this.dataStatuses[i] = getDataStatus(scanResult);
            this.periodicAdvertisingIntervals[i] = getPeriodicAdvertisingInterval(scanResult);
            this.primaryPhies[i] = getPrimaryPhy(scanResult);
            this.secondaryPhies[i] = getSecondaryPhy(scanResult);
            this.txPowers[i] = getTxPower(scanResult);
            this.areConnectable[i] = getIsConnectable(scanResult);
            this.areLegacy[i] = getIsLegacy(scanResult);

            byte[] scanRecord = scanResult.getScanRecord().getBytes();

            this.uuids[i] = getUuid(scanRecord);
            this.majors[i] = getMajor(scanRecord);
            this.minors[i] = getMinor(scanRecord);
        }
    }

    public BLEMeasurementsRecord(BLEMeasurementsRecord bleMeasurementsRecord) {
        super(bleMeasurementsRecord);
        this.deviceCount = bleMeasurementsRecord.getDeviceCount();
        this.deviceAddresses = bleMeasurementsRecord.getDeviceAddresses();
        this.deviceNames = bleMeasurementsRecord.getDeviceNames();
        this.rssis = bleMeasurementsRecord.getRssis();
        this.deviceBondStatuses = bleMeasurementsRecord.getDeviceBondStatuses();
        this.deviceTypes = bleMeasurementsRecord.getDeviceTypes();
        this.timestampsNano = bleMeasurementsRecord.getTimestampsNano();
        this.advertisingSids = bleMeasurementsRecord.getAdvertisingSids();
        this.dataStatuses = bleMeasurementsRecord.getDataStatuses();
        this.periodicAdvertisingIntervals = bleMeasurementsRecord.getPeriodicAdvertisingIntervals();
        this.primaryPhies = bleMeasurementsRecord.getPrimaryPhies();
        this.secondaryPhies = bleMeasurementsRecord.getSecondaryPhies();
        this.txPowers = bleMeasurementsRecord.getTxPowers();
        this.areConnectable = bleMeasurementsRecord.getAreConnectable();
        this.areLegacy = bleMeasurementsRecord.getAreLegacy();
        this.uuids = bleMeasurementsRecord.getUuids();
        this.majors = bleMeasurementsRecord.getMajors();
        this.minors = bleMeasurementsRecord.getMinors();
    }

    public int getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(int deviceCount) {
        this.deviceCount = deviceCount;
    }

    public String[] getDeviceAddresses() {
        return deviceAddresses;
    }

    public void setDeviceAddresses(String[] deviceAddresses) {
        this.deviceAddresses = deviceAddresses;
    }

    public String[] getDeviceNames() {
        return deviceNames;
    }

    public void setDeviceNames(String[] deviceNames) {
        this.deviceNames = deviceNames;
    }

    public int[] getRssis() {
        return rssis;
    }

    public void setRssis(int[] rssis) {
        this.rssis = rssis;
    }

    public int[] getDeviceBondStatuses() {
        return deviceBondStatuses;
    }

    public void setDeviceBondStatuses(int[] deviceBondStatuses) {
        this.deviceBondStatuses = deviceBondStatuses;
    }

    public int[] getDeviceTypes() {
        return deviceTypes;
    }

    public void setDeviceTypes(int[] deviceTypes) {
        this.deviceTypes = deviceTypes;
    }

    public long[] getTimestampsNano() {
        return timestampsNano;
    }

    public void setTimestampsNano(long[] timestampsNano) {
        this.timestampsNano = timestampsNano;
    }

    public int[] getAdvertisingSids() {
        return advertisingSids;
    }

    public void setAdvertisingSids(int[] advertisingSids) {
        this.advertisingSids = advertisingSids;
    }

    public int[] getDataStatuses() {
        return dataStatuses;
    }

    public void setDataStatuses(int[] dataStatuses) {
        this.dataStatuses = dataStatuses;
    }

    public int[] getPeriodicAdvertisingIntervals() {
        return periodicAdvertisingIntervals;
    }

    public void setPeriodicAdvertisingIntervals(int[] periodicAdvertisingIntervals) {
        this.periodicAdvertisingIntervals = periodicAdvertisingIntervals;
    }

    public int[] getPrimaryPhies() {
        return primaryPhies;
    }

    public void setPrimaryPhies(int[] primaryPhies) {
        this.primaryPhies = primaryPhies;
    }

    public int[] getSecondaryPhies() {
        return secondaryPhies;
    }

    public void setSecondaryPhies(int[] secondaryPhies) {
        this.secondaryPhies = secondaryPhies;
    }

    public int[] getTxPowers() {
        return txPowers;
    }

    public void setTxPowers(int[] txPowers) {
        this.txPowers = txPowers;
    }

    public boolean[] getAreConnectable() {
        return areConnectable;
    }

    public void setAreConnectable(boolean[] areConnectable) {
        this.areConnectable = areConnectable;
    }

    public boolean[] getAreLegacy() {
        return areLegacy;
    }

    public void setAreLegacy(boolean[] areLegacy) {
        this.areLegacy = areLegacy;
    }

    public String[] getUuids() {
        return uuids;
    }

    public void setUuids(String[] uuids) {
        this.uuids = uuids;
    }

    public int[] getMajors() {
        return majors;
    }

    public void setMajors(int[] majors) {
        this.majors = majors;
    }

    public int[] getMinors() {
        return minors;
    }

    public void setMinors(int[] minors) {
        this.minors = minors;
    }

    @Override
    public String toString() {
        return "BLEMeasurementsRecord{" +
                "deviceCount=" + deviceCount +
                ", deviceAddresses=" + Arrays.toString(deviceAddresses) +
                ", deviceNames=" + Arrays.toString(deviceNames) +
                ", rssis=" + Arrays.toString(rssis) +
                ", deviceBondStatuses=" + Arrays.toString(deviceBondStatuses) +
                ", deviceTypes=" + Arrays.toString(deviceTypes) +
                ", timestampsNano=" + Arrays.toString(timestampsNano) +
                ", advertisingSids=" + Arrays.toString(advertisingSids) +
                ", dataStatuses=" + Arrays.toString(dataStatuses) +
                ", periodicAdvertisingIntervals=" + Arrays.toString(periodicAdvertisingIntervals) +
                ", primaryPhies=" + Arrays.toString(primaryPhies) +
                ", secondaryPhies=" + Arrays.toString(secondaryPhies) +
                ", txPowers=" + Arrays.toString(txPowers) +
                ", areConnectable=" + Arrays.toString(areConnectable) +
                ", areLegacy=" + Arrays.toString(areLegacy) +
                ", uuids=" + Arrays.toString(uuids) +
                ", majors=" + Arrays.toString(majors) +
                ", minors=" + Arrays.toString(minors) +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BLEMeasurementsRecord)) return false;
        if (!super.equals(o)) return false;

        BLEMeasurementsRecord that = (BLEMeasurementsRecord) o;

        if (deviceCount != that.deviceCount) return false;
        if (!Arrays.deepEquals(deviceAddresses, that.deviceAddresses)) return false;
        if (!Arrays.deepEquals(deviceNames, that.deviceNames)) return false;
        if (!Arrays.equals(rssis, that.rssis)) return false;
        if (!Arrays.equals(deviceBondStatuses, that.deviceBondStatuses)) return false;
        if (!Arrays.equals(deviceTypes, that.deviceTypes)) return false;
        if (!Arrays.equals(timestampsNano, that.timestampsNano)) return false;
        if (!Arrays.equals(advertisingSids, that.advertisingSids)) return false;
        if (!Arrays.equals(dataStatuses, that.dataStatuses)) return false;
        if (!Arrays.equals(periodicAdvertisingIntervals, that.periodicAdvertisingIntervals))
            return false;
        if (!Arrays.equals(primaryPhies, that.primaryPhies)) return false;
        if (!Arrays.equals(secondaryPhies, that.secondaryPhies)) return false;
        if (!Arrays.equals(txPowers, that.txPowers)) return false;
        if (!Arrays.equals(areConnectable, that.areConnectable)) return false;
        if (!Arrays.equals(areLegacy, that.areLegacy)) return false;
        if (!Arrays.equals(uuids, that.uuids)) return false;
        if (!Arrays.equals(majors, that.majors)) return false;
        return Arrays.equals(minors, that.minors);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + deviceCount;
        result = 31 * result + Arrays.hashCode(deviceAddresses);
        result = 31 * result + Arrays.hashCode(deviceNames);
        result = 31 * result + Arrays.hashCode(rssis);
        result = 31 * result + Arrays.hashCode(deviceBondStatuses);
        result = 31 * result + Arrays.hashCode(deviceTypes);
        result = 31 * result + Arrays.hashCode(timestampsNano);
        result = 31 * result + Arrays.hashCode(advertisingSids);
        result = 31 * result + Arrays.hashCode(dataStatuses);
        result = 31 * result + Arrays.hashCode(periodicAdvertisingIntervals);
        result = 31 * result + Arrays.hashCode(primaryPhies);
        result = 31 * result + Arrays.hashCode(secondaryPhies);
        result = 31 * result + Arrays.hashCode(txPowers);
        result = 31 * result + Arrays.hashCode(areConnectable);
        result = 31 * result + Arrays.hashCode(areLegacy);
        result = 31 * result + Arrays.hashCode(uuids);
        result = 31 * result + Arrays.hashCode(majors);
        result = 31 * result + Arrays.hashCode(minors);
        return result;
    }

    private int getAdvertisingSid(ScanResult scanResult) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return scanResult.getAdvertisingSid();
        }
        return 255; // SID NOT PRESENT
    }

    private int getDataStatus(ScanResult scanResult) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return scanResult.getDataStatus();
        }
        return -1;
    }

    private int getPeriodicAdvertisingInterval(ScanResult scanResult) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return scanResult.getPeriodicAdvertisingInterval();
        }
        return 0; // PERIODIC INTERVAL NOT PRESENT
    }

    private int getPrimaryPhy(ScanResult scanResult) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return scanResult.getPrimaryPhy();
        }
        return -1;
    }

    private int getSecondaryPhy(ScanResult scanResult) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return scanResult.getSecondaryPhy();
        }
        return -1;
    }

    private int getTxPower(ScanResult scanResult) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return scanResult.getTxPower();
        }
        return 127; // TX POWER NOT PRESENT
    }

    private boolean getIsConnectable(ScanResult scanResult) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
                && scanResult.isConnectable();
    }

    private boolean getIsLegacy(ScanResult scanResult) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
                && scanResult.isLegacy();
    }

    private String nullStringToEmpty(String string) {
        if (string == null) return "";
        return string;
    }

    private String getUuid(byte[] scanRecord){
        if (scanRecord.length > 28){
            return IntToHex2(scanRecord[9] & 0xff) + IntToHex2(scanRecord[10] & 0xff) + IntToHex2(scanRecord[11] & 0xff) + IntToHex2(scanRecord[12] & 0xff)
                    + "-" + IntToHex2(scanRecord[13] & 0xff) + IntToHex2(scanRecord[14] & 0xff)
                    + "-" + IntToHex2(scanRecord[15] & 0xff) + IntToHex2(scanRecord[16] & 0xff)
                    + "-" + IntToHex2(scanRecord[17] & 0xff) + IntToHex2(scanRecord[18] & 0xff)
                    + "-" + IntToHex2(scanRecord[19] & 0xff) + IntToHex2(scanRecord[20] & 0xff) + IntToHex2(scanRecord[21] & 0xff) + IntToHex2(scanRecord[22] & 0xff) + IntToHex2(scanRecord[23] & 0xff) + IntToHex2(scanRecord[24] & 0xff);
        }
        return "";  //UUID NOT PRESENT
    }

    private int getMajor(byte[] scanRecord){
        if (scanRecord.length > 28) {
            return (scanRecord[25] & 0xff) * 0x100 + (scanRecord[26] & 0xff);
        }
        return -1;  //MAJOR NOT PRESENT
    }

    private int getMinor(byte[] scanRecord){
        if (scanRecord.length > 28) {
            return (scanRecord[27] & 0xff) * 0x100 + (scanRecord[28] & 0xff);
        }
        return -1;  //MINOR NOT PRESENT
    }

    private String IntToHex2(int i) {
        char hex_2[] = {Character.forDigit((i >> 4) & 0x0f, 16), Character.forDigit(i & 0x0f, 16)};
        String hex_2_str = new String(hex_2);
        return hex_2_str.toUpperCase();

    }
}
