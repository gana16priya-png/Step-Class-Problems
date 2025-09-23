class MovieTicket {
    String movieName;
    String theatreName;
    int seatNumber;
    double price;

    // 1. Default constructor → assigns "Unknown" movie
    MovieTicket() {
        this.movieName = "Unknown";
        this.theatreName = "Not Assigned";
        this.seatNumber = 0;
        this.price = 0.0;
    }

    // 2. Constructor with movie name → assigns default price = 200
    MovieTicket(String movieName) {
        this.movieName = movieName;
        this.theatreName = "Not Assigned";
        this.seatNumber = 0;
        this.price = 200.0;
    }

    // 3. Constructor with movie name and seat number → assigns default theatre "PVR"
    MovieTicket(String movieName, int seatNumber) {
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.theatreName = "PVR";
        this.price = 250.0;  // fixed price for PVR seats
    }

    // 4. Full constructor → sets all details
    MovieTicket(String movieName, String theatreName, int seatNumber, double price) {
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    // Method to display ticket details
    void printTicket() {
        System.out.println("🎟 Movie Ticket Details:");
        System.out.println("Movie: " + movieName);
        System.out.println("Theatre: " + theatreName);
        System.out.println("Seat: " + seatNumber);
        System.out.println("Price: ₹" + price);
        System.out.println("---------------------------");
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        // Creating multiple tickets using different constructors
        MovieTicket t1 = new MovieTicket();
        MovieTicket t2 = new MovieTicket("Inception");
        MovieTicket t3 = new MovieTicket("Interstellar", 42);
        MovieTicket t4 = new MovieTicket("Oppenheimer", "IMAX", 15, 500.0);

        // Print tickets
        t1.printTicket();
        t2.printTicket();
        t3.printTicket();
        t4.printTicket();
    }
}
