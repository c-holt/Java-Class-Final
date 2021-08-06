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
public class Student extends Person {
    private float gpa;
    private int studentID;
    private List<ICourse> listOfCourses = new ArrayList<ICourse>();
    private LocalDate dateOfGraduation;

    public void setDateOfGraduation(java.time.LocalDate p_dateOfGraduation) {
        dateOfGraduation = p_dateOfGraduation;
    }

    public void setGPA(float p_gpa) throws InvalidDataException {
        if (1 / p_gpa < 0){
            gpa = 0;
            throw new InvalidDataException("Invalid GPA, Setting to 0");
        }            
        else {
            gpa = p_gpa;
        }
    }

    public void setStudentID(int p_studentID) throws InvalidDataException {
        int b = Integer.toString(p_studentID).length();
        int firstDigit = Integer.parseInt(Integer.toString(p_studentID).substring(0, 1));
        if (b != 7 ) {
            throw new InvalidDataException("Student ID must be 7 chracters in length: " + p_studentID);
        }
        if (firstDigit == 0){
            throw new InvalidDataException("Student ID can not start with zero: " + p_studentID);
        }
        studentID = p_studentID;
    }
    
     public void setStudentID(String p_studentID) throws InvalidDataException {
         if (p_studentID == null) {
             throw new InvalidDataException("Student ID not specified");
         }
         if (!p_studentID.matches("^[0-9]{7}$")){
             throw new InvalidDataException("Student ID must be 7 chracters in length: " + p_studentID);
         }
         if(p_studentID.substring(0, 1).equalsIgnoreCase("0")){
             throw new InvalidDataException("Student ID can not start with zero: " + p_studentID);
         }
        studentID = Integer.parseInt(p_studentID);         
    }

    public java.time.LocalDate getDateOfGraduation() {
        return dateOfGraduation;
    }

    public int getStudentID() {
        return studentID;
    }
    
    public float getGPA() {
        return gpa;
    }

    public List<ICourse> getListOfCourses() {
        return listOfCourses;
    }
         
    @Override
    public String toString() {
        return "Student{"+ super.toString() + ", studentID=" + studentID + ", dateOfGraduation=" + dateOfGraduation + ", gpa=" + gpa + ", listOfCourses" + listOfCourses + '}';
        
    }
}
