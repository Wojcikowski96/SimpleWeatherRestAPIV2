package com.example.task.clients.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Component
@Entity
public class City {
    private String name;
    private double latitude;
    private double longitude;
    @Id
    private Long identity;

    public City(String name, double lat, double lon, Long identity) {
        this.name = name;
        this.latitude = lat;
        this.longitude = lon;
        this.identity = identity;
    }
    public City() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getIdentity(){
        return this.identity;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
