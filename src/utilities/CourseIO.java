/*
 *  This Class contains methods to write out the classroom objects in several different formats
 *  and read in the data in the same formats.
 */
package utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controllers.Application;
import datacontainers.CourseDC;
import datamodels.Course;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CourseIO {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    
    private CourseIO() {
    }

    public static void writeJSONFile(String fileLocation, CourseDC courseDataContainer) {

        // Set the level of logging in the logger object
        LOGGER.setLevel(Level.ALL);

        PrintWriter jsonFile = null;

        try {
            LOGGER.log(Level.INFO,"Creating output file for course");
            // Create output file
            jsonFile = new PrintWriter(fileLocation + "\\course.json");

            // Create JSON object
            Gson gson = new GsonBuilder().create();

            // Convert classroom list to JSON format
            gson.toJson(courseDataContainer.getListOfCourses(), jsonFile);
            LOGGER.log(Level.INFO,"Finished creating file for course");
        } catch (Exception exp) {
            LOGGER.log(Level.SEVERE,"There was a problem creating file for Course");
            JOptionPane.showMessageDialog(null, exp, "alert", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Flush the output stream and close the file
            jsonFile.flush();
            jsonFile.close();
        }
    }

    
     public static ArrayList<Course> readJSONFile(String fileLocation) {

        // Set the level of logging in the logger object
        LOGGER.setLevel(Level.ALL);
         
        ArrayList<Course> listOfCourses = new ArrayList<>();

        try {
            LOGGER.log(Level.INFO,"Opening file for Course to read");
            // Create input file
            BufferedReader jsonFile = new BufferedReader(new FileReader(fileLocation + "\\course.json"));

            // Create JSON object
            Gson gson = new GsonBuilder().create();

            // fromJson returns an array
            Course[] courseArray = gson.fromJson(jsonFile, Course[].class);

            // Convert to arraylist for the data model
            for (int i = 0; i < courseArray.length; i++) {
                listOfCourses.add(courseArray[i]);
            }
            LOGGER.log(Level.INFO,"Finished reading data for course");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,"There was a problem reading data for Course");
            JOptionPane.showMessageDialog(null, e, "alert", JOptionPane.ERROR_MESSAGE);
        } finally {
            return listOfCourses;
        }
    }
}
