package com.example.alert.service;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.alert.model.Person;
import com.example.alert.model.DTO.PersonDTO;
import com.example.alert.repository.PersonRepository;
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

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
            if (person.getLastName().equals(lastName)&&person.getFirstName().equals(firstName)) {
                PersonDTO personDTO = new PersonDTO();
                personDTO.setFirstName(person.getFirstName());
                personDTO.setLastName(person.getLastName());
                personDTO.setAddress(person.getAddress());
                personDTO.setAge(person.getAge());
                personDTO.setEmail(person.getEmail());
                personsDTOList.add(personDTO);
            }
        }
        return personsDTOList;
    }
    public Iterable<PersonDTO> getChildByAddress(String address) throws ParseException {
        ArrayList<PersonDTO>childrenByAddressList = new ArrayList<>();
        List<Person> personsList = personRepository.getAllPersons();
        for (Person person : personsList) {
            String firstName = person.getFirstName();
            String lastName = person.getLastName();
            int age=person.getAge();

            if (age < 18) {
                if (person.getAddress() != null && person.getAddress().equals(address)) {
                    PersonDTO personDTO = new PersonDTO();
                    personDTO.setFirstName(firstName);
                    personDTO.setLastName(lastName);
                    personDTO.setAge(age);
                    personDTO.setFamilyMembers(personRepository.getFamilyMembers(firstName, lastName));
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
