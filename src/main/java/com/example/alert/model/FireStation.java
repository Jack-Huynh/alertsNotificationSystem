package com.example.alert.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "fire_stations")
@AllArgsConstructor
@NoArgsConstructor
public class FireStation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "address", nullable = false)
    private String address;
    
    @Column(name = "station", nullable = false)
    private String station;

    // Constructor for existing code compatibility
    public FireStation(String address, String station) {
        this.address = address;
        this.station = station;
    }

    // Explicit getters and setters for compatibility
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return station;
    }
}
