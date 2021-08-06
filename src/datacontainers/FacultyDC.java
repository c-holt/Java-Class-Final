/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacontainers;

import datamodels.Faculty;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Cody
 */
public class FacultyDC {
    private List<Faculty> listOfFaculty = new ArrayList<>();

    public FacultyDC() {
    }    
    
    public List<Faculty> getListOfFaculty() {
        return listOfFaculty;
    }

    public void setListOfFaculty(List<Faculty> listOfFaculty) {
        this.listOfFaculty = listOfFaculty;
    }
    
    
}
