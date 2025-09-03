package com.example.alert.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.alert.model.FireStation;
import com.example.alert.model.Person;
import com.example.alert.model.DTO.PersonDTO;
import com.example.alert.repository.FireStationRepository;
import com.example.alert.mapper.PersonMapper;
import com.example.alert.mapper.FireStationMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FireStationService {

    @Autowired
    private FireStationRepository fireStationRepository;
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonMapper personMapper; // ✅ MapStruct injection
    @Autowired 
    private FireStationMapper fireStationMapper; // ✅ MapStruct injection
    
    public HashMap<Object, Object> getAddressByStationNumber(int stationNumber) throws ParseException {
        List<String> addresses = fireStationRepository.getAddressByStationNumber(stationNumber);
        HashMap<Object, Object> mapPersonsDTO = new HashMap<>();
        if (addresses == null)
            return mapPersonsDTO;
        else {
            List<Object> persons = new ArrayList<>();
            int adults = 0;
            int child = 0;

            for (String address : addresses) {
                ArrayList<Person> personsByAddress = personService.getPersonsByAddress(address);
                for (Person person : personsByAddress) {
                    // ✅ MapStruct: 1 line instead of 6!
                    PersonDTO personDTO = personMapper.toDTO(person);
                    persons.add(personDTO);
                    if (personDTO.getAge() < 18) {
                        child++;
                    } else {
                        adults++;
                    }
                }
            }
            mapPersonsDTO.put("persons", persons);
            mapPersonsDTO.put("adultsCount", adults);
            mapPersonsDTO.put("childrenCount", child);
        }
        return mapPersonsDTO;
    }
    
    public HashMap<Object, Object> getPersonsByAddressWithStation(String address) throws ParseException {
        List<Person> personsList = personService.getAllPersons();
        List<PersonDTO> personsListByAddress = new ArrayList<>();
        HashMap<Object, Object> mapPersons = new HashMap<>();
        for (Person person : personsList) {
            if (person.getAddress().equals(address)) {
                // ✅ MapStruct: Automatic mapping
                PersonDTO personDTO = personMapper.toDTO(person);
                personsListByAddress.add(personDTO);
            }
        }
        mapPersons.put("fireStation", getFireStationByAddress(address));
        mapPersons.put("persons", personsListByAddress);
        return mapPersons;
    }
    
    public List<FireStation> getFireStationByAddress(String address) {
        return fireStationRepository.getFireStationByAddress(address);
    }
    
    public List<String> getPhoneNumberByStation(int station) {
        List<String> stationAddressList = fireStationRepository.getAddressByStationNumber(station);
        List<Person> personsList = personService.getAllPersons();
        List<String> phoneNumbersList = new ArrayList<>();
        for (String address : stationAddressList) {
            int i = 0;
            while (i < personsList.size()) {
                if (personsList.get(i).getAddress().equals(address)) {
                    phoneNumbersList.add(personsList.get(i).getPhone());
                }
                i++;
            }
        }
        return phoneNumbersList;
    }
    
    public HashMap<Object, Object> getFlood(List<Integer> stations) throws ParseException {
        HashMap<Object, Object> mapFlood = new HashMap<>();
        for (Integer stationNum : stations) {
            HashMap<Object, Object> mapByAddress = new HashMap<>();
            List<Person> personsList = new ArrayList<>();
            List<String>addressList = fireStationRepository.getAddressByStationNumber(stationNum);
            for (String address :addressList) {
                ArrayList<Object> personsListDTO = new ArrayList<>();
                personsList = personService.getPersonsByAddress(address);
                for (Person person : personsList) {
                    // ✅ MapStruct: Clean and simple
                    PersonDTO personDTO = personMapper.toDTO(person);
                    personsListDTO.add(personDTO);
                }
                mapByAddress.put(address, personsListDTO);
            }
            mapFlood.put(stationNum, mapByAddress);
        }
        return mapFlood;
    }
}