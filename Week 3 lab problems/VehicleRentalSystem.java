import java.util.Scanner;

class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private double rentPerDay;
    private boolean isAvailable;

    // static members (shared across all vehicles)
    private static int vehicleCounter = 0;
    private static int totalVehicles = 0;
    private static double totalRevenue = 0;
    private static int totalRentalDays = 0;
    private static String companyName = "Default Rentals";

    // constructor
    public Vehicle(String brand, String model, double rentPerDay) {
        this.vehicleId = generateVehicleId();
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.isAvailable = true;
        totalVehicles++;
    }

    // rent vehicle
    public double rentVehicle(int days) {
        if (isAvailable) {
            double rent = calculateRent(days);
            isAvailable = false;
            System.out.println("Vehicle " + vehicleId + " rented for " + days + " days. Rent: " + rent);
            return rent;
        } else {
            System.out.println("Vehicle " + vehicleId + " is not available!");
            return 0;
        }
    }

    // return vehicle
    public void returnVehicle() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("Vehicle " + vehicleId + " returned and is now available.");
        } else {
            System.out.println("Vehicle " + vehicleId + " was not rented!");
        }
    }

    // calculate rent
    private double calculateRent(int days) {
        double rent = days * rentPerDay;
        totalRevenue += rent;
        totalRentalDays += days;
        return rent;
    }

    // display vehicle info
    public void displayVehicleInfo() {
        System.out.println(vehicleId + " | " + brand + " " + model +
                " | Rent/Day: " + rentPerDay +
                " | " + (isAvailable ? "Available" : "Rented"));
    }

    // static methods
    public static void setCompanyName(String name) {
        companyName = name;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }

    public static double getAverageRentPerDay() {
        if (totalRentalDays == 0) return 0;
        return totalRevenue / totalRentalDays;
    }

    public static void displayCompanyStats() {
        System.out.println("\n===== " + companyName + " Stats =====");
        System.out.println("Total Vehicles: " + totalVehicles);
        System.out.println("Total Revenue: " + totalRevenue);
        System.out.println("Total Rental Days: " + totalRentalDays);
        System.out.println("Average Rent/Day: " + getAverageRentPerDay());
        System.out.println("======================================");
    }

    // generate unique vehicleId
    private static String generateVehicleId() {
        vehicleCounter++;
        return "V" + String.format("%03d", vehicleCounter);
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Vehicle[] vehicles = new Vehicle[10];
        int vCount = 0;

        Vehicle.setCompanyName("SuperRide Rentals");

        while (true) {
            System.out.println("\n===== Vehicle Rental Menu =====");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Show All Vehicles");
            System.out.println("3. Rent a Vehicle");
            System.out.println("4. Return a Vehicle");
            System.out.println("5. Show Company Stats");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    if (vCount == vehicles.length) {
                        System.out.println("Cannot add more vehicles.");
                        break;
                    }
                    System.out.print("Enter Brand: ");
                    String brand = sc.nextLine();
                    System.out.print("Enter Model: ");
                    String model = sc.nextLine();
                    System.out.print("Enter Rent per Day: ");
                    double rent = sc.nextDouble();
                    vehicles[vCount++] = new Vehicle(brand, model, rent);
                    System.out.println("Vehicle added successfully.");
                    break;

                case 2:
                    System.out.println("\n--- Vehicle List ---");
                    for (int i = 0; i < vCount; i++) {
                        vehicles[i].displayVehicleInfo();
                    }
                    break;

                case 3:
                    System.out.print("Enter Vehicle Index (0-" + (vCount - 1) + "): ");
                    int ri = sc.nextInt();
                    if (ri >= 0 && ri < vCount) {
                        System.out.print("Enter Rental Days: ");
                        int days = sc.nextInt();
                        vehicles[ri].rentVehicle(days);
                    } else {
                        System.out.println("Invalid vehicle index!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Vehicle Index (0-" + (vCount - 1) + "): ");
                    int rti = sc.nextInt();
                    if (rti >= 0 && rti < vCount) {
                        vehicles[rti].returnVehicle();
                    } else {
                        System.out.println("Invalid vehicle index!");
                    }
                    break;

                case 5:
                    Vehicle.displayCompanyStats();
                    break;

                case 6:
                    System.out.println("Exiting Vehicle Rental System...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
