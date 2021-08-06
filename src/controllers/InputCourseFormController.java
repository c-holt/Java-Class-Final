
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import datacontainers.ClassroomDC;
import datacontainers.CourseDC;
import datamodels.Classroom;
import datamodels.Course;
import exceptionhandlers.ErrorPopup;
import exceptionhandlers.InvalidDataException;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.inputforms.CourseInputForm;

public class InputCourseFormController implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
    
    // The data datacontainers needed for this form are passed in via the constructor
    CourseDC CourseDC;
    ClassroomDC ClassroomDC;

    // The form is created here
    private CourseInputForm form;


    public InputCourseFormController(CourseDC CourseDC,
            ClassroomDC ClassroomDC) {

        // Store the passed in data datacontainers
        this.CourseDC = CourseDC;
        this.ClassroomDC = ClassroomDC;

        // Create the form
        form = new CourseInputForm(this);

        // make the form visible
        this.form.setVisible(true);
    }


    public void actionPerformed(ActionEvent event) {
        // Set the level of logging in the logger object
        LOGGER.setLevel(Level.ALL); 
        
        if (event.getActionCommand().equals("Save")) {
            try {
                LOGGER.log(Level.INFO,"Saving the data for adding a Course");
                this.saveData();
            } catch (InvalidDataException ex) {
                LOGGER.log(Level.INFO,"a problem occurred when saving a course");
                ErrorPopup error = new ErrorPopup(form, new InvalidDataException("A problem occured when creating a course: " + ex));
            }
        } else if (event.getActionCommand().equals("Clear")) {
            LOGGER.log(Level.INFO,"Clearing the Course Form");
            this.clearForm();
        } else if (event.getActionCommand().equals("Close")) {
            LOGGER.log(Level.INFO,"closing the add a course form");
            this.closeForm();
        }
    }

    /**
     * Private method called from the actionPerformed method to save the
     */
    private void saveData() throws InvalidDataException {

        // Create a new Course object
        Course newCourse = new Course();

        // Retrieve the selected classroom from the drop down list
        // The objects in the list are stored as generic objects and have to be
        // converted to classroom objects
        Classroom classroom = (Classroom) this.form.getListOfClassrooms().getSelectedValue();

        // Store the classroom
        newCourse.setClassroom(classroom);

        // Retrieve the course id from the form
        String courseid = this.form.getCourseIdField().getText();

        // Retrieve the course name
        String coursename = this.form.getCourseNameField().getText();

        // Setthe course name in the object
        newCourse.setCourseName(coursename);

        // Set the course id in the object
        newCourse.setCourseID(courseid);

        // Store the object in the application data model list of courses
        this.CourseDC.getListOfCourses().add(newCourse);

    }

    /**
     * Private method to clear the data
     */
    private void clearForm() {
        this.form.getCourseIdField().setText("");
        this.form.getCourseNameField().setText("");
        this.form.getListOfClassrooms().setSelectedIndex(0);
    }

    /**
     * Private method to close the form
     */
    private void closeForm() {
        this.form.dispose();
    }

    public ClassroomDC getClassroomDC() {
        return ClassroomDC;
    }

}
