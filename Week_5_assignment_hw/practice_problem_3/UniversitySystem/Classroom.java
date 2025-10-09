public class Classroom {
    private final String roomNumber;
    private final int capacity;
    private final String[] equipment;

    public Classroom(String roomNumber, int capacity, String[] equipment) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.equipment = equipment.clone();
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " | Capacity: " + capacity;
    }
}
