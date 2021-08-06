/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacontainers;

import datamodels.Course;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Cody
 */
public class CourseDC {
    private List<Course> listOfCourses = new ArrayList<>();

    public CourseDC() {
    
    }
    
    public List<Course> getListOfCourses() {
        return listOfCourses;
    }

    public void setListOfCourses(List<Course> listOfCourses) {
        this.listOfCourses = listOfCourses;
    }
}
