/*
 * Listens for events on the input form. 
 * Implements the ActionListener interface which contains a single method, 
 * "actionPerformed" - this method contains all the logic to process the data
 * on the form, as well as several other events
 */
package controllers;

import datacontainers.CourseDC;
import datacontainers.FacultyDC;
import datamodels.Faculty;
import exceptionhandlers.ErrorPopup;
import exceptionhandlers.InvalidDataException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.inputforms.FacultyInputForm;

public class InputFacultyFormController implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
    
    // The data containers are passed in via the constructor
    FacultyDC facultyDC;
    CourseDC offeredCourseDC;

    // The form is declared here
    FacultyInputForm form;

    public InputFacultyFormController(FacultyDC facultyDC,
            CourseDC offeredCourseDC) {

        this.facultyDC = facultyDC;
        this.offeredCourseDC = offeredCourseDC;
        form = new FacultyInputForm(this);
        form.setVisible(true);

    }

    public void actionPerformed(ActionEvent event) {
        // Set the level of logging in the logger object
        LOGGER.setLevel(Level.ALL); 
        
        if (event.getActionCommand().equals("Save")) {
            try {
                LOGGER.log(Level.INFO,"Saving data for add a faculty");
                this.saveData();
            } catch (InvalidDataException ex) {
                LOGGER.log(Level.INFO,"A problem occurred saving this faculty");
                ErrorPopup error = new ErrorPopup(form, new InvalidDataException("A problem occured when creating a faculty: " + ex));
            }
        } else if (event.getActionCommand().equals("Clear")) {
            LOGGER.log(Level.INFO,"Clearing the faculty form");
            this.clearForm();
        } else if (event.getActionCommand().equals("Close")) {
            LOGGER.log(Level.INFO,"closing the faculty form");
            this.closeForm();
        }
    }

    /**
     * Private method to save all the data on the form and create a new faculty
     * object
     */
    private void saveData() throws InvalidDataException {
        LOGGER.setLevel(Level.ALL);
        
        Faculty newFaculty = new Faculty();

            // Retrieve data from all text fields and store directly into object
        // Flags errors on the required fields
        newFaculty.setName(form.getNameField().getText());
        newFaculty.setAddress(form.getAddressField().getText());

        // Retrieve status and convert to Status enumerated type and store in object
        String selectedStatus = (String) form.getStatusField().getSelectedItem();
        newFaculty.setStatus(selectedStatus);

            // Retrieve salary and convert to a double before storing in object
        // Notice we are catching an error here in the UI code, not in the data model
        String salarystring = form.getSalaryField().getText();
        // Default value is zero
        Double salarydouble = 0d;
        
        if (salarystring.isEmpty()) {
            salarystring = "0";
        }
        try {
            salarydouble = Double.parseDouble(salarystring);
            newFaculty.setSalary(salarydouble);
        } catch (Exception e) {
            LOGGER.log(Level.INFO,"A problem occurred saving this faculty data inside 'Savedata' function");
            ErrorPopup error = new ErrorPopup(form, new InvalidDataException("A problem occured when creating a Faculty: " + e));            
        }



        Integer dobYear = Integer.parseInt(form.getDateOfBirthYear().getValue().toString());
        Integer dobDay = Integer.parseInt(form.getDateOfBirthDay().getValue().toString());    
        Integer dobMonth = this.getIntegerMonthFromString(form.getDateOfBirthMonth().getValue().toString());
        LocalDate dateOfBirth = LocalDate.of(dobYear, dobMonth, dobDay);
        newFaculty.setDateOfBirth(dateOfBirth);

            // Retrieve date of hire values from spinners and convert to a date object
        // These fields return objects so convert them to first convert them to strings 
        // and thenconvert them to integers
        Integer dohYear = Integer.parseInt(form.getDateOfHireYear().getValue().toString());
        Integer dohDay = Integer.parseInt(form.getDateOfHireDay().getValue().toString());
            // Let's use a different month mapper for date of hire and convert the 
        // string month to a GregorianCalendar class month
        Integer dohMonth = this.getMonthFromMonthArray(form.getDateOfHireMonth().getValue().toString());
        LocalDate dateOfHire = LocalDate.of(dohYear, dohMonth, dohDay);
        // Store in object
        newFaculty.setDateOfHire(dateOfHire);

        // Store the object in the application data model 
        this.facultyDC.getListOfFaculty().add(newFaculty);

    }

    /**
     * Private method to clear the data
     */
    private void clearForm() {

        // The spinners are reset to their first value
        this.form.getDateOfBirthYear().getModel().setValue("1950");
        this.form.getDateOfBirthDay().getModel().setValue("01");
        this.form.getDateOfBirthMonth().getModel().setValue("Jan");
        this.form.getDateOfHireDay().getModel().setValue("01");
        this.form.getDateOfHireMonth().getModel().setValue("Jan");
        this.form.getDateOfHireYear().getModel().setValue("1950");

        // The text fields are set to blank
        this.form.getNameField().setText("");
        this.form.getAddressField().setText("");
        this.form.getSalaryField().setText("");
        this.form.getStatusField().setSelectedIndex(0);
    }

    /**
     * Private method to close the form
     */
    private void closeForm() {
        this.form.dispose();
    }


    /**
     * Private method to convert the year, month, day to a long
     */
    private long getLong(int year, int month, int day) {
        long datevalue = 0;
        return datevalue;
    }


    private int getIntegerMonthFromString(String stringmonth) {
        if (stringmonth.equals("Jan")) {
            return 1;
        } else if (stringmonth.equals("Feb")) {
            return 2;
        } else if (stringmonth.equals("Mar")) {
            return 3;
        } else if (stringmonth.equals("Apr")) {
            return 4;
        } else if (stringmonth.equals("May")) {
            return 5;
        } else if (stringmonth.equals("June")) {
            return 6;
        } else if (stringmonth.equals("Jul")) {
            return 7;
        } else if (stringmonth.equals("Aug")) {
            return 8;
        } else if (stringmonth.equals("Sep")) {
            return 9;
        } else if (stringmonth.equals("Oct")) {
            return 10;
        } else if (stringmonth.equals("Nov")) {
            return 11;
        } else {
            return 12;  // default december
        }
    }

    private int getMonthFromMonthArray(String stringmonth) {
        int month = 0;
        String[] monthArray = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        for (int i = 0; i < 12; i++) {
            if (stringmonth.equals(monthArray[i])) {
                month = i + 1;
            }
        }
        return month;
    }
}
