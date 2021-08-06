/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodels;


import exceptionhandlers.InvalidDataException;
import interfaces.ICourse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cody
 */
public class Faculty extends Person {
    private LocalDate dateOfHire;
    private double salary;
    private String status;
    private List<ICourse> listOfCourses = new ArrayList<ICourse>();

    public Faculty() {
        this.status = "Fulltime";
    }

    public void setDateOfHire(java.time.LocalDate p_dateOfHire) {
        dateOfHire = p_dateOfHire;
    }

    public void setSalary(double p_salary) throws InvalidDataException {
        if (p_salary <= 0) {  
            salary = 0;
            throw new InvalidDataException("Salary cannot be less than 0, setting to zero");
        } else {
            salary = p_salary;
        }

    }

    public void setStatus(String p_status) {
        String updateStatus = p_status;
        if (updateStatus.equals(null) || updateStatus.equals("")) {
            updateStatus = "Full Time";
        }
        status = updateStatus;
    }

    public java.time.LocalDate getDateOfHire() {
        return dateOfHire;
    }

    public double getSalary() {
        return salary;
    }

    public String getStatus() {
        return status;
    }
    
    public List<ICourse> getListOfCourses() {
        return listOfCourses;
    }
    
    @Override
    public String toString() {
        return "Faculty{"+ super.toString() + ", salary=" + salary + ", status=" + status + ", listOfCourses=" + listOfCourses + '}';
    }

}
