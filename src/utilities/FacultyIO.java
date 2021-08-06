/*
 *  This Class contains methods to write out the classroom objects in several different formats
 *  and read in the data in the same formats.
 */
package utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controllers.Application;
import datacontainers.FacultyDC;
import datamodels.Faculty;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FacultyIO {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
    
    
    private FacultyIO() {
    }

    public static void writeJSONFile(String fileLocation, FacultyDC facultyDataContainer) {

        // Set the level of logging in the logger object
        LOGGER.setLevel(Level.ALL);
        
        PrintWriter jsonFile = null;

        try {
            LOGGER.log(Level.INFO,"Creating file for Faculty");
            // Create output file
            jsonFile = new PrintWriter(fileLocation + "\\faculty.json");

            // Create JSON object
            Gson gson = new GsonBuilder().create();

            // Convert classroom list to JSON format
            gson.toJson(facultyDataContainer.getListOfFaculty(), jsonFile);
            LOGGER.log(Level.INFO,"Finished creating file");
        } catch (Exception exp) {
            LOGGER.log(Level.SEVERE,"There was a problem creating file for Faculty");
            JOptionPane.showMessageDialog(null, exp, "alert", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Flush the output stream and close the file
            jsonFile.flush();
            jsonFile.close();
        }
    }

    
     public static ArrayList<Faculty> readJSONFile(String fileLocation) {

        // Set the level of logging in the logger object
        LOGGER.setLevel(Level.ALL);
         
        ArrayList<Faculty> listOfFaculty = new ArrayList<>();

        try {
            LOGGER.log(Level.INFO,"Opening file for faculty to read data");
            // Create input file
            BufferedReader jsonFile = new BufferedReader(new FileReader(fileLocation + "\\faculty.json"));

            // Create JSON object
            Gson gson = new GsonBuilder().create();

            // fromJson returns an array
            Faculty[] facultyArray = gson.fromJson(jsonFile, Faculty[].class);

            // Convert to arraylist for the data model
            for (int i = 0; i < facultyArray.length; i++) {
                listOfFaculty.add(facultyArray[i]);
            }
            LOGGER.log(Level.INFO,"Finished reading data for faculty");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,"There was a problem reading data for Faculty");
            JOptionPane.showMessageDialog(null, e, "alert", JOptionPane.ERROR_MESSAGE);
        } finally {
            return listOfFaculty;
        }
    }
}
