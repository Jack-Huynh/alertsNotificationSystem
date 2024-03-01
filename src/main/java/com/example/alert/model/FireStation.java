package com.example.alert.model;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@Entity
@AllArgsConstructor
public class FireStation {
    private String address;
    private String station;
    public FireStation() {
    }

    @Override
    public String toString() {
        return station;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

}
