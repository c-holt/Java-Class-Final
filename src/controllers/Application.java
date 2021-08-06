/*
 * This is the main entry into the application. It creates a menu controller object
 * and the controller object creates the forms and the data models as needed
 */
package controllers;

import exceptionhandlers.InvalidDataException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
    //private static final ConsoleHandler HANDLER = new ConsoleHandler();
    
    public static void main(String[] args) throws InvalidDataException {
        
        // Set the level of logging in the logger object
        LOGGER.setLevel(Level.ALL);                
       
        if (args.length != 1){
             JOptionPane.showMessageDialog(null, "Please enter 1 argument only (File path for saving/ loading)", "alert", JOptionPane.ERROR_MESSAGE);
             System.exit(0);
        }
        
        String fileLocation = args[0];
        
        // Create main menu controller, the controller creates the menu form
        MainMenuController controller = new MainMenuController(fileLocation);

        //log main menu controller created
        LOGGER.log(Level.INFO,"Main Menu Controller has been created!");
        
        // Retrieve the main menu form from the controller and make it visible
        controller.getMainMenu().setVisible(true);
        
        //log
        LOGGER.log(Level.INFO,"Main Menu made visible");
    }
}
