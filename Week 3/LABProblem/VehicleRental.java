public class VehicleRental{
    private String vehicleId;
    private String brand;
    private String model;
    private double rentPerDay;
    private boolean isAvailable;
    private int totalRentedDays; // rental history for this vehicle

    private static int totalVehicles = 0;
    private static double totalRevenue = 0;
    private static String companyName = "Default Rentals";
    private static int rentalDays = 0;

    // Constructor
    public VehicleRental(String vehicleId, String brand, String model, double rentPerDay) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.isAvailable = true;
        this.totalRentedDays = 0;
        totalVehicles++;
    }

    // Instance method to rent vehicle
    public double rentVehicle(int days) {
        if (!isAvailable) {
            System.out.println("Vehicle " + vehicleId + " is not available.");
            return 0;
        }
        double rent = calculateRent(days);
        isAvailable = false;
        totalRentedDays += days;
        System.out.println("Vehicle " + vehicleId + " rented for " + days + " days. Rent: " + rent);
        return rent;
    }

    // Instance method to return vehicle
    public void returnVehicle() {
        if (isAvailable) {
            System.out.println("Vehicle " + vehicleId + " is already available.");
        } else {
            isAvailable = true;
            System.out.println("Vehicle " + vehicleId + " returned and is now available.");
        }
    }

    // Instance method to calculate rent and update static revenue/days
    public double calculateRent(int days) {
        double rent = rentPerDay * days;
        totalRevenue += rent;
        rentalDays += days;
        return rent;
    }

    // Display vehicle info
    public void displayVehicleInfo() {
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Rent Per Day: " + rentPerDay);
        System.out.println("Available: " + isAvailable);
        System.out.println("Total Rented Days: " + totalRentedDays);
        System.out.println("---------------------------");
    }

    // Static methods
    public static void setCompanyName(String name) {
        companyName = name;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }

    public static double getAverageRentPerDay() {
        if (rentalDays == 0) return 0;
        return totalRevenue / rentalDays;
    }

    public static void displayCompanyStats() {
        System.out.println("Company Name: " + companyName);
        System.out.println("Total Vehicles: " + totalVehicles);
        System.out.println("Total Revenue: " + totalRevenue);
        System.out.println("Total Rental Days: " + rentalDays);
        System.out.println("Average Rent Per Day: " + getAverageRentPerDay());
        System.out.println("===========================");
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        setCompanyName("SuperDrive Rentals");

        VehicleRental[] vehicles = new VehicleRental[3];
        vehicles[0] = new VehicleRental("V001", "Toyota", "Camry", 1000);
        vehicles[1] = new VehicleRental("V002", "Honda", "Civic", 900);
        vehicles[2] = new VehicleRental("V003", "Ford", "Focus", 800);

        // Rent vehicles
        vehicles[0].rentVehicle(3); // Toyota for 3 days
        vehicles[1].rentVehicle(2); // Honda for 2 days

        // Try to rent an already rented vehicle
        vehicles[0].rentVehicle(1);

        // Return vehicles
        vehicles[0].returnVehicle();
        vehicles[0].rentVehicle(1); // Now available

        // Display info for each vehicle
        for (VehicleRental v : vehicles) {
            v.displayVehicleInfo();
        }

        // Display company stats (static members)
        displayCompanyStats();

        // Show difference between static and instance members
        System.out.println("Vehicle 1 Brand (instance): " + vehicles[0].brand);
        System.out.println("Company Name (static): " + companyName);
    }
}