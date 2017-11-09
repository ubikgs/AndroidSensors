package com.ubikgs.androidsensors.records.bluetooth;

import android.bluetooth.le.ScanResult;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.ubikgs.androidsensors.records.SensorRecord;

/**
 * Created by geotec-laptop01 on 08/11/2017.
 */

public class BluetoothMeasurementsRecord extends SensorRecord {

    private int dataStatus;
    private String device;
    private int periodicAdvertisingInterval;
    private int rssi;
    private int primaryPhy;
    private int secondaryPhy;
    private String deviceName;
    private long timestampNano;
    private int txPower;
    private boolean isConnectable;


    public BluetoothMeasurementsRecord() {
        super();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BluetoothMeasurementsRecord(ScanResult scanResult){

        this.dataStatus = getDataStatus(scanResult);
        this.device = scanResult.getDevice().toString();
        this.periodicAdvertisingInterval = getPeriodicAdvertisingInterval(scanResult);
        this.rssi = scanResult.getRssi();
        this.primaryPhy = getPrimaryPhy(scanResult);
        this.secondaryPhy = getSecondaryPhy(scanResult);
        this.deviceName = scanResult.getScanRecord().getDeviceName();
        this.timestampNano = scanResult.getTimestampNanos();
        this.txPower = getTxPower(scanResult);
        this.isConnectable = getIsConnectable(scanResult);
    }

    public BluetoothMeasurementsRecord(BluetoothMeasurementsRecord bluetoothMeasurementsRecord){
        super(bluetoothMeasurementsRecord);
        this.dataStatus = bluetoothMeasurementsRecord.getDataStatus();
        this.device = bluetoothMeasurementsRecord.getDevice();
        this.periodicAdvertisingInterval = bluetoothMeasurementsRecord.getPeriodicAdvertisingInterval();
        this.rssi = bluetoothMeasurementsRecord.getRssi();
        this.primaryPhy = bluetoothMeasurementsRecord.getPrimaryPhy();
        this.secondaryPhy = bluetoothMeasurementsRecord.getSecondaryPhy();
        this.deviceName = bluetoothMeasurementsRecord.getDeviceName();
        this.timestampNano = bluetoothMeasurementsRecord.getTimestampNano();
        this.txPower = bluetoothMeasurementsRecord.getTxPower();
        this.isConnectable = bluetoothMeasurementsRecord.isConnectable();
    }

    public int getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(int dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public int getPeriodicAdvertisingInterval() {
        return periodicAdvertisingInterval;
    }

    public void setPeriodicAdvertisingInterval(int periodicAdvertisingInterval) {
        this.periodicAdvertisingInterval = periodicAdvertisingInterval;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public int getPrimaryPhy() {
        return primaryPhy;
    }

    public void setPrimaryPhy(int primaryPhy) {
        this.primaryPhy = primaryPhy;
    }

    public int getSecondaryPhy() {
        return secondaryPhy;
    }

    public void setSecondaryPhy(int secondaryPhy) {
        this.secondaryPhy = secondaryPhy;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public long getTimestampNano() {
        return timestampNano;
    }

    public void setTimestampNano(long timestampNano) {
        this.timestampNano = timestampNano;
    }

    public int getTxPower() {
        return txPower;
    }

    public void setTxPower(int txPower) {
        this.txPower = txPower;
    }

    public boolean isConnectable() {
        return isConnectable;
    }

    public void setConnectable(boolean connectable) {
        isConnectable = connectable;
    }

    @Override
    public String toString() {
        return "BluetoothMeasurementsRecord{" +
                "dataStatus=" + dataStatus +
                ", device='" + device + '\'' +
                ", periodicAdvertisingInterval=" + periodicAdvertisingInterval +
                ", rssi=" + rssi +
                ", primaryPhy=" + primaryPhy +
                ", secondaryPhy=" + secondaryPhy +
                ", deviceName='" + deviceName + '\'' +
                ", timestampNano=" + timestampNano +
                ", txPower=" + txPower +
                ", isConnectable=" + isConnectable +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BluetoothMeasurementsRecord)) return false;
        if (!super.equals(o)) return false;

        BluetoothMeasurementsRecord that = (BluetoothMeasurementsRecord) o;

        if (getDataStatus() != that.getDataStatus()) return false;
        if (getPeriodicAdvertisingInterval() != that.getPeriodicAdvertisingInterval()) return false;
        if (getRssi() != that.getRssi()) return false;
        if (getPrimaryPhy() != that.getPrimaryPhy()) return false;
        if (getSecondaryPhy() != that.getSecondaryPhy()) return false;
        if (getTimestampNano() != that.getTimestampNano()) return false;
        if (getTxPower() != that.getTxPower()) return false;
        if (isConnectable() != that.isConnectable()) return false;
        if (!getDevice().equals(that.getDevice())) return false;
        return getDeviceName().equals(that.getDeviceName());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getDataStatus();
        result = 31 * result + getDevice().hashCode();
        result = 31 * result + getPeriodicAdvertisingInterval();
        result = 31 * result + getRssi();
        result = 31 * result + getPrimaryPhy();
        result = 31 * result + getSecondaryPhy();
        result = 31 * result + getDeviceName().hashCode();
        result = 31 * result + (int) (getTimestampNano() ^ (getTimestampNano() >>> 32));
        result = 31 * result + getTxPower();
        result = 31 * result + (isConnectable() ? 1 : 0);
        return result;
    }

    private int getDataStatus(ScanResult scanResult){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            return scanResult.getDataStatus();
        }
        return -1;
    }

    private int getPeriodicAdvertisingInterval(ScanResult scanResult){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            return scanResult.getPeriodicAdvertisingInterval();
        }
        return -1;
    }

    private int getPrimaryPhy(ScanResult scanResult){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            return scanResult.getPrimaryPhy();
        }
        return -1;
    }

    private int getSecondaryPhy(ScanResult scanResult){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            return scanResult.getSecondaryPhy();
        }
        return -1;
    }

    private int getTxPower(ScanResult scanResult){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            return scanResult.getTxPower();
        }
        return 127;
    }

    private boolean getIsConnectable(ScanResult scanResult){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            return scanResult.isConnectable();
        }
        return false;
    }
}
