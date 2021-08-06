/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author Cody
 */
import exceptionhandlers.InvalidDataException;

public interface IPerson {
    public void setName(String name) throws InvalidDataException;
    public void setAddress(String address) throws InvalidDataException;
    public void setDateOfBirth(java.time.LocalDate dateOfBirth) throws InvalidDataException;
    
    public java.time.LocalDate getDateOfBirth();
    public String getName();
    public String getAddress();

}
