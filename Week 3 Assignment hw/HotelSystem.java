
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

class Room {
    String roomNumber;
    String roomType;        // e.g., "Standard", "Deluxe", "Suite"
    double pricePerNight;
    boolean isAvailable;    // general flag
    int maxOccupancy;

    public Room(String roomNumber, String roomType, double pricePerNight, boolean isAvailable, int maxOccupancy) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.isAvailable = isAvailable;
        this.maxOccupancy = maxOccupancy;
    }

    @Override
    public String toString() {
        return roomNumber + " | " + roomType + " | ₹" + pricePerNight + "/night | occ:" + maxOccupancy
                + " | " + (isAvailable ? "Online" : "Offline");
    }
}

class Guest {
    String guestId;
    String guestName;
    String phoneNumber;
    String email;
    List<String> bookingHistory; // store bookingIds

    public Guest(String guestId, String guestName, String phoneNumber, String email) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bookingHistory = new ArrayList<>();
    }

    void addBookingHistory(String bookingId) {
        bookingHistory.add(bookingId);
    }

    @Override
    public String toString() {
        return guestId + " | " + guestName + " | " + phoneNumber + " | " + email;
    }
}

class Booking {
    String bookingId;
    Guest guest;
    Room room;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    double totalAmount;

    public Booking(String bookingId, Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.bookingId = bookingId;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalAmount = 0.0;
    }

    void calculateBill() {
        long nights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        if (nights < 1) nights = 1;
        totalAmount = nights * room.pricePerNight;
    }

    boolean overlaps(LocalDate start, LocalDate end) {
        // two intervals [a,b) and [c,d) overlap if a < d and c < b
        return (this.checkInDate.isBefore(end)) && (start.isBefore(this.checkOutDate));
    }

    @Override
    public String toString() {
        return bookingId + " | Guest: " + guest.guestName + " | Room: " + room.roomNumber +
                " | " + checkInDate + " -> " + checkOutDate + " | ₹" + totalAmount;
    }
}

class HotelManager {
    static String hotelName = "Default Hotel";
    static int totalBookings = 0;
    static double hotelRevenue = 0.0;

    Map<String, Room> rooms = new HashMap<>();
    Map<String, Guest> guests = new HashMap<>();
    Map<String, Booking> bookings = new HashMap<>();

    // helper counters
    private int bookingCounter = 0;

    public HotelManager(String name) {
        hotelName = name;
    }

    // add room/guest helpers
    void addRoom(Room room) {
        rooms.put(room.roomNumber, room);
    }

    void registerGuest(Guest guest) {
        guests.put(guest.guestId, guest);
    }

    // availability check for a given room and date range
    boolean checkAvailability(Room room, LocalDate start, LocalDate end) {
        if (!room.isAvailable) return false;
        for (Booking b : bookings.values()) {
            if (b.room.roomNumber.equals(room.roomNumber)) {
                if (b.overlaps(start, end)) return false;
            }
        }
        return true;
    }

    // search rooms by type and availability
    List<Room> searchRooms(String roomType, LocalDate start, LocalDate end) {
        List<Room> result = new ArrayList<>();
        for (Room r : rooms.values()) {
            if ((roomType == null || r.roomType.equalsIgnoreCase(roomType)) && checkAvailability(r, start, end)) {
                result.add(r);
            }
        }
        return result;
    }

    // create booking
    Booking makeReservation(String guestId, String roomNumber, LocalDate checkIn, LocalDate checkOut) {
        Guest g = guests.get(guestId);
        Room r = rooms.get(roomNumber);
        if (g == null) {
            System.out.println("Guest not found: " + guestId);
            return null;
        }
        if (r == null) {
            System.out.println("Room not found: " + roomNumber);
            return null;
        }
        if (!checkAvailability(r, checkIn, checkOut)) {
            System.out.println("Room not available for requested dates.");
            return null;
        }
        bookingCounter++;
        totalBookings++;
        String bookingId = "BKG" + String.format("%04d", bookingCounter);
        Booking b = new Booking(bookingId, g, r, checkIn, checkOut);
        b.calculateBill();
        bookings.put(bookingId, b);
        g.addBookingHistory(bookingId);
        hotelRevenue += b.totalAmount;

        System.out.println("Booking successful: " + b);
        return b;
    }

    // cancel booking
    boolean cancelReservation(String bookingId) {
        Booking b = bookings.get(bookingId);
        if (b == null) {
            System.out.println("Booking not found: " + bookingId);
            return false;
        }
        // refund policy simple: full refund and subtract from revenue
        hotelRevenue -= b.totalAmount;
        bookings.remove(bookingId);
        b.guest.bookingHistory.remove(bookingId);
        System.out.println("Cancelled booking: " + bookingId);
        return true;
    }

    // checkout (finalize) - for demo we'll just remove booking and keep revenue
    boolean checkout(String bookingId) {
        Booking b = bookings.get(bookingId);
        if (b == null) {
            System.out.println("Booking not found: " + bookingId);
            return false;
        }
        bookings.remove(bookingId);
        System.out.println("Checked out booking: " + bookingId + " | Total: ₹" + b.totalAmount);
        return true;
    }

    // static reporting
    double getOccupancyRate(LocalDate date) {
        int totalRooms = rooms.size();
        if (totalRooms == 0) return 0.0;
        int occupied = 0;
        for (Booking b : bookings.values()) {
            if (!date.isBefore(b.checkInDate) && date.isBefore(b.checkOutDate)) { // date in [checkIn, checkOut)
                occupied++;
            }
        }
        return (occupied * 100.0) / totalRooms;
    }

    static double getTotalRevenue() {
        return hotelRevenue;
    }

    String getMostPopularRoomType() {
        Map<String, Integer> count = new HashMap<>();
        for (Booking b : bookings.values()) {
            count.put(b.room.roomType, count.getOrDefault(b.room.roomType, 0) + 1);
        }
        String popular = "N/A";
        int max = 0;
        for (Map.Entry<String, Integer> e : count.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                popular = e.getKey();
            }
        }
        return popular;
    }

    // utility to print current state
    void printRooms() {
        System.out.println("\nRooms in " + hotelName + ":");
        for (Room r : rooms.values()) System.out.println(r);
    }

    void printGuests() {
        System.out.println("\nRegistered Guests:");
        for (Guest g : guests.values()) System.out.println(g);
    }

    void printBookings() {
        System.out.println("\nActive Bookings:");
        for (Booking b : bookings.values()) System.out.println(b);
    }
}

public class HotelSystem {
    public static void main(String[] args) {
        HotelManager manager = new HotelManager("Seaside Residency");

        // Add rooms
        manager.addRoom(new Room("101", "Standard", 2500, true, 2));
        manager.addRoom(new Room("102", "Standard", 2500, true, 2));
        manager.addRoom(new Room("201", "Deluxe", 4000, true, 3));
        manager.addRoom(new Room("202", "Deluxe", 4000, true, 3));
        manager.addRoom(new Room("301", "Suite", 8000, true, 4));

        // Register guests
        manager.registerGuest(new Guest("G001", "Alice Sharma", "+91-9000000001", "alice@example.com"));
        manager.registerGuest(new Guest("G002", "Ravi Kumar", "+91-9000000002", "ravi@example.com"));
        manager.registerGuest(new Guest("G003", "Meera Roy", "+91-9000000003", "meera@example.com"));

        manager.printRooms();
        manager.printGuests();

        // Make reservations
        LocalDate in1 = LocalDate.parse("2025-09-10");
        LocalDate out1 = LocalDate.parse("2025-09-13"); // 3 nights
        manager.makeReservation("G001", "201", in1, out1);

        LocalDate in2 = LocalDate.parse("2025-09-11");
        LocalDate out2 = LocalDate.parse("2025-09-12"); // 1 night
        manager.makeReservation("G002", "101", in2, out2);

        // Attempt overlapping booking (should fail)
        LocalDate in3 = LocalDate.parse("2025-09-12");
        LocalDate out3 = LocalDate.parse("2025-09-14");
        manager.makeReservation("G003", "201", in3, out3); // overlaps with Alice -> should print not available

        // Another booking
        manager.makeReservation("G003", "301", LocalDate.parse("2025-09-12"), LocalDate.parse("2025-09-14"));

        manager.printBookings();

        // Reports
        LocalDate reportDate = LocalDate.parse("2025-09-11");
        System.out.printf("\nOccupancy on %s: %.2f%%\n", reportDate, manager.getOccupancyRate(reportDate));
        System.out.println("Total Revenue (so far): ₹" + HotelManager.getTotalRevenue());
        System.out.println("Most popular room type (active bookings): " + manager.getMostPopularRoomType());

        // Cancel a booking
        // (retrieve a bookingId from active bookings map — for demo assume BKG0001 exists)
        manager.cancelReservation("BKG0001");

        manager.printBookings();
        System.out.println("\nTotal Revenue after cancellation: ₹" + HotelManager.getTotalRevenue());
    }
}
