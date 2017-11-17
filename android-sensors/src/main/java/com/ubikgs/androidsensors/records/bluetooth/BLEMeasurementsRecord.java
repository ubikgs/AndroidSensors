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
        return Arrays.equals(areLegacy, that.areLegacy);
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
}
