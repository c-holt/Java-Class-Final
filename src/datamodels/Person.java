/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodels;

import exceptionhandlers.InvalidDataException;
import interfaces.IPerson;
import java.time.LocalDate;
/**
 *
 * @author Cody
 */
public class Person implements IPerson {
    private LocalDate dateOfBirth;
    private String name;
    private String address;


    public void setDateOfBirth(java.time.LocalDate p_dateOfBirth) throws InvalidDataException {
        if (p_dateOfBirth == null){
            throw new InvalidDataException("Date of birth not specified, setting to null");
        }
         if (p_dateOfBirth == null){
            p_dateOfBirth = null;
        }
        dateOfBirth = p_dateOfBirth;
    }

    public void setName(String p_name) throws InvalidDataException {
        if (p_name.length() == 0) {
            throw new InvalidDataException("Name Required");
        }
        
        name = p_name;
    }

    public void setAddress(String p_address) throws InvalidDataException {
        if (p_address.length() == 0) {
            throw new InvalidDataException("Address Required");
        }
        address = p_address;
    }

    public java.time.LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }


    @Override
    public String toString() {
        return "name=" + name + ", address=" + address + ", dateOfBirth=" + dateOfBirth + '}';
    }
}
