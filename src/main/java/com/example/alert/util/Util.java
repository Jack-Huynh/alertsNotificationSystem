package com.example.alert.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jsoniter.JsonIterator;
import com.example.alert.model.FireStation;
import com.example.alert.model.Person;
public class Util {
    private static Util access;

    private Person[] persons;
    private FireStation[] firestations;

    public static Util getAccess() throws IOException{
        if(access==null) {
            InputStream inputStream = new FileInputStream("src/main/resources/alerts-data.json");
            JsonIterator iter = JsonIterator.parse(inputStream.readAllBytes());
            access = iter.read(Util.class);
            iter.close();
            inputStream.close();
        }
        return access;
    }
    public FireStation[] getFireStations() throws IOException{
        return getAccess().firestations;
    }
    public Person[] getPersons() throws IOException {
        return getAccess().persons;
    }
}