package com.example.alert.model;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String phone;
    private int age;
    private String email;
    private String medicalRecord;
    private String medications;
    private String allergies;

    public Person() {
    }

    public Person(String firstName, String lastName, String address, String city, String zip, String phone, int age, String email, String medicalRecord, String medications, String allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.age = age;
        this.email = email;
        this.medicalRecord = medicalRecord;
        this.medications = medications;
        this.allergies = allergies;
    }
     public String getFirstName() {
         return firstName;
     }

     public void setFirstName(String firstName) {
         this.firstName = firstName;
     }

     public String getLastName() {
         return lastName;
     }

     public void setLastName(String lastName) {
         this.lastName = lastName;
     }

     public String getAddress() {
         return address;
     }

     public void setAddress(String address) {
         this.address = address;
     }
     public String getPhone() {
         return phone;
     }
     public void setPhone(String phone) {
         this.phone = phone;
     }
     public int getAge() {
         return age;
     }
     public void setAge(int age) {
         this.age = age;
     }
     public String getEmail() {
         return email;
     }
     public void setEmail(String email) {
         this.email = email;
     }
     public String getMedicalRecord() {
         return medicalRecord;
     }
     public void setMedicalRecord(String medicalRecord) {
         this.medicalRecord = medicalRecord;
     }
     public String getMedications() {
         return medications;
     }
     public void setMedications(String medications) {
         this.medications = medications;
     }
     public String getAllergies() {
         return allergies;
     }
     public void setAllergies(String allergies) {
         this.allergies = allergies;
     }
     public String getCity() {
         return city;
     }
     public void setCity(String city) {
         this.city = city;
     }
}
