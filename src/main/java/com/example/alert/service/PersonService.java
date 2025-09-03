package com.example.alert.service;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.alert.model.Person;
import com.example.alert.model.DTO.PersonDTO;
import com.example.alert.repository.PersonRepository;
import com.example.alert.mapper.PersonMapper;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private PersonMapper personMapper; // ✅ Inject MapStruct mapper

    public List<Person> getAllPersons() {
        return personRepository.getAllPersons();
    }

    public ArrayList<Person> getPersonsByAddress(String address) {
        ArrayList<Person> personsList = new ArrayList<Person>();
        for (Person person : personRepository.getAllPersons()) {
            if (person.getAddress().equals(address)) {
                personsList.add(person);
            }
        }
        return personsList;
    }
    
    public ArrayList<PersonDTO> getPersonInfo(String firstName, String lastName) throws ParseException {
        ArrayList<PersonDTO> personsDTOList = new ArrayList<>();
        for (Person person : getAllPersons()) {
            if (person.getLastName().equals(lastName) && person.getFirstName().equals(firstName)) {
                // ✅ MapStruct: 1 dòng thay vì 6 dòng!
                PersonDTO personDTO = personMapper.toPersonInfoDTO(person);
                personsDTOList.add(personDTO);
            }
        }
        return personsDTOList;
    }
    
    public Iterable<PersonDTO> getChildByAddress(String address) throws ParseException {
        ArrayList<PersonDTO> childrenByAddressList = new ArrayList<>();
        List<Person> personsList = personRepository.getAllPersons();
        for (Person person : personsList) {
            int age = person.getAge();

            if (age < 18) {
                if (person.getAddress() != null && person.getAddress().equals(address)) {
                    // ✅ MapStruct: Tự động map!
                    PersonDTO personDTO = personMapper.toChildDTO(person);
                    // Chỉ cần set field đặc biệt
                    personDTO.setFamilyMembers(personRepository.getFamilyMembers(person.getFirstName(), person.getLastName()));
                    childrenByAddressList.add(personDTO);
                }
            }
        }
        return childrenByAddressList;
    }
    
    public ArrayList<Object> getEmailsByCity(String city) {
        ArrayList<Object> emailsByCityList = new ArrayList<Object>();
        List<Person> personsList = personRepository.getAllPersons();
        for (Person person : personsList) {
            if (person.getCity().equals(city)) {
                emailsByCityList.add(person.getEmail());
            }
        }
        return emailsByCityList;
    }
}
