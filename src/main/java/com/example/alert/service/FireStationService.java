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

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FireStationService {

    @Autowired
    private FireStationRepository fireStationRepository;
    @Autowired
    private PersonService personService;
    public HashMap<Object, Object> getAddressByStationNumber(int stationNumber) throws ParseException {
        List<String> addresses = fireStationRepository.getAddressByStationNumber(stationNumber);
        HashMap<Object, Object> mapPersonsDTO = new HashMap<>();
        if (addresses == null)
            return mapPersonsDTO;
        else {
            List<Object> stat = new ArrayList<>();
            List<Object> persons = new ArrayList<>();
            int adults = 0;
            int child = 0;
            HashMap<Object, Object> mapCount = new HashMap<>();

            for (String address : addresses) {
                ArrayList<Person> personsByAddress = personService.getPersonsByAddress(address);
                for (Person person : personsByAddress) {
                    PersonDTO personDTO = new PersonDTO();
                    personDTO.setFirstName(person.getFirstName());
                    personDTO.setLastName(person.getLastName());
                    personDTO.setPhone(person.getPhone());
                    personDTO.setAddress(person.getAddress());
                    personDTO.setAge(person.getAge());
                    persons.add(personDTO);
                    if (personDTO.getAge() < 18) {
                        child++;
                    } else {
                        adults++;
                    }
                }
            }
            mapCount.put("adults", adults);
            mapCount.put("child", child);
            stat.add(stationNumber);
            stat.add(mapCount);
            mapPersonsDTO.put(stat, persons);
        }
        return mapPersonsDTO;
    }
    public HashMap<Object, Object> getPersonsByAddressWithStation(String address) throws ParseException {
        List<Person> personsList = personService.getAllPersons();
        List<PersonDTO> personsListByAddress = new ArrayList<>();
        HashMap<Object, Object> mapPersons = new HashMap<>();
        for (Person person : personsList) {
            if (person.getAddress().equals(address)) {
                PersonDTO personDTO = new PersonDTO();
                personDTO.setFirstName(person.getFirstName());
                personDTO.setLastName(person.getLastName());
                personDTO.setPhone(person.getPhone());
                personDTO.setAge(person.getAge());
                personsListByAddress.add(personDTO);
            }
        }
        mapPersons.put(getFireStationByAddress(address), personsListByAddress);
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
                    PersonDTO personDTO = new PersonDTO();
                    personDTO.setFirstName(person.getFirstName());
                    personDTO.setLastName(person.getLastName());
                    personDTO.setPhone(person.getPhone());
                    personDTO.setAge(person.getAge());
                    personsListDTO.add(personDTO);
                }
                //medical
                mapByAddress.put(address, personsListDTO);
            }
            mapFlood.put(stationNum, mapByAddress);
        }
        return mapFlood;
    }
}

