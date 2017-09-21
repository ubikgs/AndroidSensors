package com.ubikgs.androidsensors.records.gps;

import android.location.Location;

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

    public LocationRecord(LocationRecord locationRecord) {
        super(locationRecord);
        this.latitude = locationRecord.getLatitude();
        this.longitude = locationRecord.getLongitude();
        this.altitude = locationRecord.getAltitude();
        this.speed = locationRecord.getSpeed();
        this.bearing = locationRecord.getBearing();
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getBearing() {
        return bearing;
    }

    public void setBearing(float bearing) {
        this.bearing = bearing;
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
