package com.assignment.technical.test.api.models;

import java.util.StringJoiner;

public class RegisterStation {

    private String external_id;
    private String name;
    private Double latitude;
    private Double longitude;

    private Integer altitude;


    public RegisterStation(String external_id, String name, Double latitude, Double longitude,  Integer altitude) {
        this.external_id = external_id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public String getExternal_id() {
        return external_id;
    }

    public String getName() {
        return name;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RegisterStation.class.getSimpleName() + "[", "]")
                .add("external_id='" + external_id + "'")
                .add("name='" + name + "'")
                .add("longitude=" + longitude)
                .add("latitude=" + latitude)
                .add("altitude=" + altitude)
                .toString();
    }
}
