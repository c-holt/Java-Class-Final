/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import exceptionhandlers.InvalidDataException;

/**
 *
 * @author Cody
 */
public interface ICourse {    
    public String getCourseID();
    public String getCourseName();
    public IClassroom getClassroom();
    
    public void setCourseID(String courseID) throws InvalidDataException;
    public void setCourseName(String courseName);
    public void setClassroom(IClassroom classroom) throws InvalidDataException;  
}
