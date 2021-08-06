/*
 *  This Class contains methods to write out the classroom objects in several different formats
 *  and read in the data in the same formats.
 */
package utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controllers.Application;
import datacontainers.StudentDC;
import datamodels.Student;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class StudentIO {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
    

    private StudentIO() {
    }

    public static void writeJSONFile(String fileLocation, StudentDC studentDataContainer) {
        
        // Set the level of logging in the logger object
        LOGGER.setLevel(Level.ALL);
                        
        PrintWriter jsonFile = null;

        try {
            LOGGER.log(Level.INFO,"Creating file for Student");
            // Create output file
            jsonFile = new PrintWriter(fileLocation + "\\student.json");

            // Create JSON object
            Gson gson = new GsonBuilder().create();

            // Convert classroom list to JSON format
            gson.toJson(studentDataContainer.getListOfStudents(), jsonFile);
            LOGGER.log(Level.INFO,"Finished creating file for Student");
        } catch (Exception exp) {
            LOGGER.log(Level.SEVERE,"A problem occured while creating file for Student");
            JOptionPane.showMessageDialog(null, exp, "alert", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Flush the output stream and close the file
            jsonFile.flush();
            jsonFile.close();
        }
    }

    
    public static ArrayList<Student> readJSONFile(String fileLocation) {
        
        // Set the level of logging in the logger object
        LOGGER.setLevel(Level.ALL);   
        
        ArrayList<Student> listOfStudents = new ArrayList<>();

        try {
            LOGGER.log(Level.INFO,"Opening file to read data for Student");
            // Create input file
            BufferedReader jsonFile = new BufferedReader(new FileReader(fileLocation + "\\student.json"));

            // Create JSON object
            Gson gson = new GsonBuilder().create();

            // fromJson returns an array
            Student[] studentArray = gson.fromJson(jsonFile, Student[].class);

            // Convert to arraylist for the data model
            for (int i = 0; i < studentArray.length; i++) {
                listOfStudents.add(studentArray[i]);
            }
            LOGGER.log(Level.INFO,"Finished reading data for Student");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,"A problem has occured when reading data for student");
            JOptionPane.showMessageDialog(null, e.toString(), "alert", JOptionPane.ERROR_MESSAGE);
        } finally {
            return listOfStudents;
        }
    }
}
