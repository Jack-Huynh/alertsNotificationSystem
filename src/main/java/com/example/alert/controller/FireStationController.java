package com.example.alert.controller;

import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.alert.service.FireStationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class FireStationController {
    @Autowired
    private FireStationService fireStationService;

    @GetMapping("/firestation")
    public Object findAddressByStationNumber(@RequestParam("stationNumber") int stationNumber) throws ParseException {
        return fireStationService.getAddressByStationNumber(stationNumber);
    }
    @GetMapping("/phoneAlert")
    public List<String> phoneAlert(@RequestParam("firestation") int station) {
        return fireStationService.getPhoneNumberByStation(station);
    }
    @GetMapping("/fire")
    public Object getPersonsByAddress(@RequestParam String address) throws ParseException {
        return fireStationService.getPersonsByAddressWithStation(address);
    }
    @GetMapping("/flood/stations")
    public Object getFlood(@RequestParam List<Integer> stations) throws ParseException {
        return fireStationService.getFlood(stations);
    }
}
