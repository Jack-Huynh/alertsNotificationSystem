package com.example.alert.controller;

import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.alert.model.Person;
import com.example.alert.model.DTO.PersonDTO;
import com.example.alert.service.PersonService;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/childAlert")
    public Iterable<PersonDTO> childAlert(@RequestParam String address) throws ParseException {//Iterable interface is a part of the Java Collections Framework and is designed to represent a collection of elements that can be iterated (looped) sequentially.
        Iterable<PersonDTO> persons = personService.getChildByAddress(address);
        return persons;
    }
    @GetMapping("/personInfo")
    public Object personInfo(@RequestParam String firstName, @RequestParam String lastName) throws ParseException {
        return personService.getPersonInfo(firstName, lastName);
    }
    @GetMapping("/communityEmail")
    public ArrayList<Object> getEmailsByCity(@RequestParam String city) {
        return personService.getEmailsByCity(city);
    }
}

