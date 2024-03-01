package com.example.alert.repository;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.alert.model.Person;
import com.example.alert.util.Util;

@Repository
public class PersonRepository {
    public List<Person> personsList = new ArrayList<Person>();

    public PersonRepository() throws IOException , ParseException {
        Person[] arrayPersons = Util.getAccess().getPersons();
        for (Person person : arrayPersons) {
            personsList.add(person);
        }
    }
    public List<Person> getAllPersons()   {
        return personsList;
    }
    public List<String> getFamilyMembers(String firstName, String lastName) {
        int i = 0;
        List<String> familyList = new ArrayList<>();
        while (i < personsList.size()) {
            if (personsList.get(i).getLastName().equals(lastName) && !personsList.get(i).getFirstName().equals(firstName)) {
                familyList.add(personsList.get(i).getFirstName()+" "+personsList.get(i).getLastName());
            }
            i++;
        }
        return familyList;
    }
}
