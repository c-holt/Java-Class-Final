package utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controllers.Application;
import datacontainers.ClassroomDC;
import datamodels.Classroom;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ClassroomIO {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
    
    private ClassroomIO() {
    }

    
    public static void writeJSONFile(String fileLocation, ClassroomDC classroomDataContainer) {
        
        // Set the level of logging in the logger object
        LOGGER.setLevel(Level.ALL);
                
        PrintWriter jsonFile = null;

        try {
            LOGGER.log(Level.INFO,"Creating output file for classroom");
            // Create output file
            jsonFile = new PrintWriter(fileLocation + "\\classroom.json");

            // Create JSON object
            Gson gson = new GsonBuilder().create();

            // Convert classroom list to JSON format
            gson.toJson(classroomDataContainer.getListOfClassrooms(), jsonFile);
            LOGGER.log(Level.INFO,"Classroom json file was created");
        } catch (Exception exp) {
            LOGGER.log(Level.SEVERE,"A problem occured while creating Classroom jason file");
            JOptionPane.showMessageDialog(null, exp, "alert", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Flush the output stream and close the file
            jsonFile.flush();
            jsonFile.close();
        }
    }
    
        public static ArrayList<Classroom> readJSONFile(String fileLocation) {

        // Set the level of logging in the logger object
        LOGGER.setLevel(Level.ALL);
            
        ArrayList<Classroom> listOfClassrooms = new ArrayList<>();

        try {
            LOGGER.log(Level.INFO,"Opening classroom json file for reading");
            // Create input file
            BufferedReader jsonFile = new BufferedReader(new FileReader(fileLocation + "\\classroom.json"));

            // Create JSON object
            Gson gson = new GsonBuilder().create();

            // fromJson returns an array
            Classroom[] classroomArray = gson.fromJson(jsonFile, Classroom[].class);

            // Convert to arraylist for the data model
            for (int i = 0; i < classroomArray.length; i++) {
                listOfClassrooms.add(classroomArray[i]);
            }
            
            LOGGER.log(Level.INFO,"Finished importing classroom data");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,"A problem has occured with importing classroom data");
            JOptionPane.showMessageDialog(null, e, "alert", JOptionPane.ERROR_MESSAGE);
        } finally {
            return listOfClassrooms;
        }
    }

}
