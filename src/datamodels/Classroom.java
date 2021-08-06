package datamodels;

import exceptionhandlers.InvalidDataException;
import interfaces.IClassroom;

public class Classroom implements IClassroom {

    private String roomNumber;
    private String typeOfRoom;
    private int capacity;

    public void setRoomNumber(String p_roomNumber) throws InvalidDataException {
        // Test for valid room number
        if (p_roomNumber.length() == 0) {
            throw new InvalidDataException("No room number specified");
        }
        if (!p_roomNumber.matches("^[a-zA-Z]{2}[0-9]{3}$")) {
            throw new InvalidDataException("Invalid room number specified");
        } 
        // If valid, set room number
        String updatedRoomNumber = p_roomNumber.substring(0, 2).toUpperCase() +
                p_roomNumber.substring(2, 5);
        this.roomNumber = updatedRoomNumber;
    }

    public void setTypeOfRoom(String p_typeOfRoom) throws InvalidDataException {
        // Test for valid room type
        if (p_typeOfRoom.length() == 0) {
            throw new InvalidDataException("No room type specified");
        }
         if ((!p_typeOfRoom.equals("LAB")) && (!p_typeOfRoom.equals("CLASSROOM"))
                 && (!p_typeOfRoom.equals("LECTURE HALL"))) {
            throw new InvalidDataException("Invalid room type specified");
        }
        // If valid, set room type
        typeOfRoom = p_typeOfRoom;
    }

    public void setCapacity(int p_capacity) throws InvalidDataException {

        // Test for valid value 
        if (p_capacity <= 0) {
            throw new InvalidDataException("Invalid Room capacity");
        }
        // If valid, set room capacity
        capacity = p_capacity;

    }
    
    public void setCapacity(String p_capacity) throws InvalidDataException {

        // Test for valid value 
        try {
            Integer.parseInt(p_capacity);
        } catch (Exception exp) {
            throw new InvalidDataException("Invalid Room capacity");
        }
        // If valid, set room capacity
        capacity = Integer.parseInt(p_capacity);
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getTypeOfRoom() {
        return typeOfRoom;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Classroom{" + "roomNumber=" + roomNumber + ", typeOfRoom=" + 
                typeOfRoom + ", capacity=" + capacity + '}';
    }

}