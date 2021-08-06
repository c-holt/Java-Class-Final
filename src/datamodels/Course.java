/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodels;


import exceptionhandlers.InvalidDataException;


/**
 *
 * @author Cody
 */
public class Course {
    private String courseID;
    private String courseName;
    private Classroom classroom;

    public void setCourseID(String p_courseID) throws InvalidDataException {        
        // Test for valid course ID
        if (p_courseID.length() == 0) {
            throw new InvalidDataException("Course ID missing");
        }
        if (!p_courseID.matches("^[a-zA-Z]{4}[0-9]{3}$")) {
            throw new InvalidDataException("Invalid course ID specified");
        } 
        // If valid, set course ID
        String updatedRoomNumber = p_courseID.substring(0, 4).toUpperCase() + p_courseID.substring(4, 7);
        this.courseID = updatedRoomNumber;        
    }

  public void setCourseName(String p_courseName) {
        this.courseName = p_courseName;
    }

    public void setClassroom(Classroom p_classroom) {
        if (p_classroom == null) {
            Classroom newClassroom = new Classroom();
            classroom = newClassroom;
        }
        else {
            classroom = p_classroom;
        }
        
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    @Override
    public String toString() {
        return "Course{" + "courseID=" + courseID + ", courseName=" + courseName + ", classroom=" + classroom + '}';
    }

}
