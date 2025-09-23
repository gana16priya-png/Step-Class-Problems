import java.util.Scanner;

class Room {
    private String roomNumber;
    private String roomType;
    private double pricePerNight;
    private boolean isAvailable;
    private int maxOccupancy;

    public Room(String roomNumber, String roomType, double pricePerNight, int maxOccupancy) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
        this.maxOccupancy = maxOccupancy;
    }

    public String getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public double getPricePerNight() { return pricePerNight; }
    public boolean isAvailable() { return isAvailable; }
    public int getMaxOccupancy() { return maxOccupancy; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public void displayRoom() {
        System.out.println(roomNumber + " | " + roomType + " | Rs." + pricePerNight + " | Available: " + isAvailable + " | Max: " + maxOccupancy);
    }
}

class Guest {
    private String guestId;
    private String guestName;
    private String phoneNumber;
    private String email;
    private String[] bookingHistory;
    private int bookingCount;

    public Guest(String guestId, String guestName, String phoneNumber, String email) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bookingHistory = new String[10];
        this.bookingCount = 0;
    }

    public String getGuestId() { return guestId; }
    public String getGuestName() { return guestName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }

    public void addBookingToHistory(String bookingId) {
        if (bookingCount < bookingHistory.length) {
            bookingHistory[bookingCount++] = bookingId;
        }
    }

    public void displayGuest() {
        System.out.println("Guest ID: " + guestId + ", Name: " + guestName + ", Phone: " + phoneNumber + ", Email: " + email);
        System.out.print("Booking History: ");
        for (int i = 0; i < bookingCount; i++) {
            System.out.print(bookingHistory[i] + " ");
        }
        System.out.println();
    }
}

class Booking {
    private String bookingId;
    private Guest guest;
    private Room room;
    private String checkInDate;
    private String checkOutDate;
    private double totalAmount;

    private static int totalBookings = 0;
    private static double hotelRevenue = 0;
    private static String hotelName = "Grand Stay Hotel";

    public Booking(String bookingId, Guest guest, Room room, String checkInDate, String checkOutDate, double totalAmount) {
        this.bookingId = bookingId;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalAmount = totalAmount;
        totalBookings++;
        hotelRevenue += totalAmount;
        guest.addBookingToHistory(bookingId);
    }

    public String getBookingId() { return bookingId; }
    public Guest getGuest() { return guest; }
    public Room getRoom() { return room; }
    public String getCheckInDate() { return checkInDate; }
    public String getCheckOutDate() { return checkOutDate; }
    public double getTotalAmount() { return totalAmount; }

    public static int getTotalBookings() { return totalBookings; }
    public static double getHotelRevenue() { return hotelRevenue; }
    public static String getHotelName() { return hotelName; }

    public static double getOccupancyRate(Room[] rooms) {
        int occupied = 0;
        for (Room r : rooms) {
            if (!r.isAvailable()) occupied++;
        }
        return (occupied * 100.0) / rooms.length;
    }

    public static double getTotalRevenue() {
        return hotelRevenue;
    }

    public static String getMostPopularRoomType(Booking[] bookings) {
        int single = 0, doubleRoom = 0, suite = 0;
        for (Booking b : bookings) {
            if (b == null) continue;
            String type = b.getRoom().getRoomType();
            if (type.equalsIgnoreCase("Single")) single++;
            else if (type.equalsIgnoreCase("Double")) doubleRoom++;
            else if (type.equalsIgnoreCase("Suite")) suite++;
        }
        if (single >= doubleRoom && single >= suite) return "Single";
        else if (doubleRoom >= single && doubleRoom >= suite) return "Double";
        else return "Suite";
    }

    public void displayBooking() {
        System.out.println("Booking ID: " + bookingId + ", Guest: " + guest.getGuestName() + ", Room: " + room.getRoomNumber() +
                ", Check-in: " + checkInDate + ", Check-out: " + checkOutDate + ", Amount: Rs." + totalAmount);
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create rooms
        Room[] rooms = new Room[6];
        rooms[0] = new Room("101", "Single", 2000, 1);
        rooms[1] = new Room("102", "Single", 2000, 1);
        rooms[2] = new Room("201", "Double", 3500, 2);
        rooms[3] = new Room("202", "Double", 3500, 2);
        rooms[4] = new Room("301", "Suite", 6000, 4);
        rooms[5] = new Room("302", "Suite", 6000, 4);

        // Create guests
        Guest[] guests = new Guest[3];
        guests[0] = new Guest("G001", "Alice", "9876543210", "alice@email.com");
        guests[1] = new Guest("G002", "Bob", "9123456780", "bob@email.com");
        guests[2] = new Guest("G003", "Charlie", "9988776655", "charlie@email.com");

        // Booking records
        Booking[] bookings = new Booking[10];
        int bookingIndex = 0;

        while (true) {
            System.out.println("\n--- Hotel Reservation Menu ---");
            System.out.println("1. View available rooms");
            System.out.println("2. Make reservation");
            System.out.println("3. Cancel reservation");
            System.out.println("4. View guest info");
            System.out.println("5. View booking info");
            System.out.println("6. Hotel reports");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Available Rooms:");
                    for (Room r : rooms) {
                        if (r.isAvailable()) r.displayRoom();
                    }
                    break;
                case 2:
                    System.out.print("Enter Guest ID: ");
                    String guestId = sc.nextLine();
                    Guest guest = null;
                    for (Guest g : guests) {
                        if (g.getGuestId().equals(guestId)) guest = g;
                    }
                    if (guest == null) {
                        System.out.println("Guest not found.");
                        break;
                    }
                    System.out.print("Enter Room Number: ");
                    String roomNum = sc.nextLine();
                    Room room = null;
                    for (Room r : rooms) {
                        if (r.getRoomNumber().equals(roomNum) && r.isAvailable()) room = r;
                    }
                    if (room == null) {
                        System.out.println("Room not available.");
                        break;
                    }
                    System.out.print("Enter Check-in Date (dd-mm-yyyy): ");
                    String checkIn = sc.nextLine();
                    System.out.print("Enter Check-out Date (dd-mm-yyyy): ");
                    String checkOut = sc.nextLine();
                    System.out.print("Enter number of nights: ");
                    int nights = sc.nextInt();
                    sc.nextLine();
                    double amount = room.getPricePerNight() * nights;
                    String bookingId = "B" + (bookingIndex + 1);
                    bookings[bookingIndex] = new Booking(bookingId, guest, room, checkIn, checkOut, amount);
                    bookingIndex++;
                    room.setAvailable(false);
                    System.out.println("Reservation successful! Booking ID: " + bookingId);
                    break;
                case 3:
                    System.out.print("Enter Booking ID to cancel: ");
                    String cancelId = sc.nextLine();
                    boolean found = false;
                    for (int i = 0; i < bookingIndex; i++) {
                        if (bookings[i] != null && bookings[i].getBookingId().equals(cancelId)) {
                            bookings[i].getRoom().setAvailable(true);
                            System.out.println("Booking " + cancelId + " cancelled.");
                            bookings[i] = null;
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("Booking not found.");
                    break;
                case 4:
                    System.out.print("Enter Guest ID: ");
                    String gid = sc.nextLine();
                    for (Guest g : guests) {
                        if (g.getGuestId().equals(gid)) {
                            g.displayGuest();
                        }
                    }
                    break;
                case 5:
                    System.out.println("All Bookings:");
                    for (int i = 0; i < bookingIndex; i++) {
                        if (bookings[i] != null) bookings[i].displayBooking();
                    }
                    break;
                case 6:
                    System.out.println("Hotel Name: " + Booking.getHotelName());
                    System.out.println("Total Bookings: " + Booking.getTotalBookings());
                    System.out.println("Total Revenue: Rs." + Booking.getTotalRevenue());
                    System.out.println("Occupancy Rate: " + Booking.getOccupancyRate(rooms) + "%");
                    System.out.println("Most Popular Room Type: " + Booking.getMostPopularRoomType(bookings));
                    break;
                case 7:
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}