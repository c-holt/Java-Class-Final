/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacontainers;

/**
 *
 * @author Cody
 */
import datamodels.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentDC {
    private List<Student> listOfStudents = new ArrayList<>();

    public StudentDC() {
        
    }
    
    public List<Student> getListOfStudents() {
        return listOfStudents;
    }

    public void setListOfStudents(List<Student> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }
    
}
