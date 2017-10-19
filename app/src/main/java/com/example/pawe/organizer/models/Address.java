package com.example.pawe.organizer.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "Address")
public class Address extends Model {

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    public Address() { super(); }

    public Address(String name, String address, double latitude, double longitude) {
        super();
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static List<Address> getAllAddresses() {
        return new Select().from(Address.class).execute();
    }

    public static Address getAddress(String name, String address) {
        return new Select().from(Address.class).where("name = ?", name).and("address = ?", address).executeSingle();
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }

    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
}
