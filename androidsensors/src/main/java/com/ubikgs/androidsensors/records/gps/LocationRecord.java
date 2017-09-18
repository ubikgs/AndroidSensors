package com.ubikgs.androidsensors.records.gps;

import android.location.Location;

import com.ubikgs.androidsensors.records.RecordInfo;
import com.ubikgs.androidsensors.records.SensorRecord;

/**
 * Created by alberto on 18/9/17.
 */

public class LocationRecord extends SensorRecord {

    private double latitude;
    private double longitude;
    private double altitude;
    private float speed;
    private float bearing;

    public LocationRecord() {
        super();
    }

    public LocationRecord(Location location) {
        super(new RecordInfo(location.getAccuracy(), location.getTime()));
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.altitude = location.getAltitude();
        this.speed = location.getSpeed();
        this.bearing = location.getBearing();
    }

    public double getLatitude() {
        return latitude;
    }

    public LocationRecord setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public LocationRecord setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public double getAltitude() {
        return altitude;
    }

    public LocationRecord setAltitude(double altitude) {
        this.altitude = altitude;
        return this;
    }

    public float getSpeed() {
        return speed;
    }

    public LocationRecord setSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    public float getBearing() {
        return bearing;
    }

    public LocationRecord setBearing(float bearing) {
        this.bearing = bearing;
        return this;
    }


    @Override
    public String toString() {
        return "LocationRecord{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                ", speed=" + speed +
                ", bearing=" + bearing +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocationRecord)) return false;
        if (!super.equals(o)) return false;

        LocationRecord that = (LocationRecord) o;

        if (Double.compare(that.getLatitude(), getLatitude()) != 0) return false;
        if (Double.compare(that.getLongitude(), getLongitude()) != 0) return false;
        if (Double.compare(that.getAltitude(), getAltitude()) != 0) return false;
        if (Float.compare(that.getSpeed(), getSpeed()) != 0) return false;
        return Float.compare(that.getBearing(), getBearing()) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(getLatitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getLongitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getAltitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getSpeed() != +0.0f ? Float.floatToIntBits(getSpeed()) : 0);
        result = 31 * result + (getBearing() != +0.0f ? Float.floatToIntBits(getBearing()) : 0);
        return result;
    }
}
