package datacontainers;

/**
 *
 * @author Cody
 */

import datamodels.Classroom;
import java.util.ArrayList;

public class ClassroomDC {

    private ArrayList<Classroom> listOfClassrooms = new ArrayList<>();

    public ClassroomDC() {
    }

    public ArrayList<Classroom> getListOfClassrooms() {
        return listOfClassrooms;
    }

    public void setListOfClassrooms(ArrayList<Classroom> listOfClassrooms) {
        this.listOfClassrooms = listOfClassrooms;
    }
    
}
