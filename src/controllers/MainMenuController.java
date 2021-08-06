/*
 * Listens for events on the menu form. 
 * Implements the ActionListener interface which contains a single method, 
 * "actionPerformed"
 */
package controllers;

import java.awt.event.ActionListener;
import datacontainers.ClassroomDC;
import datacontainers.CourseDC;
import datacontainers.FacultyDC;
import datacontainers.StudentDC;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.ClassroomIO;
import utilities.CourseIO;
import utilities.FacultyIO;
import utilities.StudentIO;

import view.MainMenu;

public class MainMenuController implements ActionListener {

    // File location
    private String fileLocation;
    
     // The data models are instantiated here and passed to the 
    // constructors for the controllers
    private ClassroomDC classroomDC = new ClassroomDC();
    private CourseDC courseDC = new CourseDC();
    private StudentDC studentDC = new StudentDC();
    private FacultyDC facultyDC = new FacultyDC();

    //logger
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
  
    
    
    
    /**
     * Constructor
     * @param fileLocation 
     */
    public MainMenuController(String fileLocation) {
        this.fileLocation = fileLocation;
    }
    
    // The main menu form gets created here. Notice it takes this controller object
    // as an argument to the constructor
    private MainMenu mainMenu = new MainMenu(this);

    MainMenuController() {        
    }

    /**
     * The ActionListener interface contains a single method, actionPerformed
     */
    public void actionPerformed(java.awt.event.ActionEvent event) {

       
        // Set the level of logging in the logger object
        LOGGER.setLevel(Level.ALL);
                
        
        //  Figure out which button was clicked
        String menuItemClicked = event.getActionCommand();

         // create the controller which will open the correct form (refer to the controller constructor
        // methods do determine which data container classes need to be passed to the controller constructors)
        if (menuItemClicked.equals("Add Classroom")) {
            LOGGER.log(Level.INFO,"Opening Add Classroom");
            InputClassroomFormController inputController = new InputClassroomFormController(classroomDC);
        } else if (menuItemClicked.equals("List Classrooms")) {
            LOGGER.log(Level.INFO,"Opening Classroom list");
            ReportClassroomController reportController = new ReportClassroomController(classroomDC);
        }  if (menuItemClicked.equals("Add Course")) {
            LOGGER.log(Level.INFO,"Opening Add Course");
            InputCourseFormController inputController = new InputCourseFormController(courseDC, classroomDC);
        } else if (menuItemClicked.equals("List Courses")) {
            LOGGER.log(Level.INFO,"Opening Course List");
            ReportCourseController reportController = new ReportCourseController(courseDC);
        }  if (menuItemClicked.equals("Add Faculty")) {
            LOGGER.log(Level.INFO,"Opening Add Faculty");
            InputFacultyFormController inputFacultyController = new InputFacultyFormController(facultyDC, courseDC);
        } else if (menuItemClicked.equals("List Faculty")) {
            LOGGER.log(Level.INFO,"Opening faculty list");
            ReportFacultyController reportController = new ReportFacultyController(facultyDC);
        }  if (menuItemClicked.equals("Add Student")) {
            LOGGER.log(Level.INFO,"Opening Add Student");
            InputStudentFormController inputController = new InputStudentFormController(studentDC, courseDC);
        } else if (menuItemClicked.equals("List Students")) {
            LOGGER.log(Level.INFO,"Opening Student List");
            ReportStudentController reportController = new ReportStudentController(studentDC);
        } else if (menuItemClicked.equals("Exit")) {
            LOGGER.log(Level.INFO,"Exiting Program");
            System.exit(0);      
        } else if (menuItemClicked.equals("Save Data")) {
            LOGGER.log(Level.INFO,"Save data starting");
            
            ClassroomIO.writeJSONFile(fileLocation, classroomDC);
            CourseIO.writeJSONFile(fileLocation, courseDC);
            FacultyIO.writeJSONFile(fileLocation, facultyDC);
            StudentIO.writeJSONFile(fileLocation, studentDC);
            
            LOGGER.log(Level.INFO,"Save data Finished");
        } else if (menuItemClicked.equals("Load Data")) {
            LOGGER.log(Level.INFO,"Load data starting");
            
            classroomDC.setListOfClassrooms(ClassroomIO.readJSONFile(fileLocation));
            courseDC.setListOfCourses(CourseIO.readJSONFile(fileLocation));
            studentDC.setListOfStudents(StudentIO.readJSONFile(fileLocation));
            facultyDC.setListOfFaculty(FacultyIO.readJSONFile(fileLocation));
            
            LOGGER.log(Level.INFO,"Load data Finished");
        }
        
    }

    // Getter used in the Application.java class
    public MainMenu getMainMenu() {
        return mainMenu;
    }
}
