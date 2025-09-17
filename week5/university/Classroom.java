package university;

import java.util.Arrays;
import java.util.Objects;

public class Classroom {
    private final String roomNumber;
    private final int capacity;
    private final String[] equipment;
    
    // Modifiable schedule information
    private String currentSchedule;
    private boolean isAvailable;
    
    public Classroom(String roomNumber, int capacity, String[] equipment) {
        this.roomNumber = Objects.requireNonNull(roomNumber, "Room number cannot be null");
        this.capacity = capacity;
        this.equipment = equipment != null ? equipment.clone() : new String[0];
        this.isAvailable = true;
    }
    
    // Getters for immutable properties (no setters)
    public String getRoomNumber() {
        return roomNumber;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public String[] getEquipment() {
        return equipment.clone();
    }
    
    // JavaBean compliant getters and setters for modifiable schedule
    public String getCurrentSchedule() {
        return currentSchedule;
    }
    
    public void setCurrentSchedule(String currentSchedule) {
        this.currentSchedule = currentSchedule;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    
    @Override
    public String toString() {
        return "Classroom{" +
                "roomNumber='" + roomNumber + '\'' +
                ", capacity=" + capacity +
                ", equipment=" + Arrays.toString(equipment) +
                ", currentSchedule='" + currentSchedule + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classroom classroom = (Classroom) o;
        return Objects.equals(roomNumber, classroom.roomNumber);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
}