// Base Vehicle Class
abstract class Vehicle {
    String vehicleId, brand, model, fuelType, currentStatus;
    int year;
    double mileage;

    // Static fields
    static int totalVehicles = 0;
    static double fleetValue = 0;
    static String companyName = "TransFleet Ltd.";
    static double totalFuelConsumption = 0;

    Driver assignedDriver;

    Vehicle(String vehicleId, String brand, String model, int year, 
            double mileage, String fuelType) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.currentStatus = "Available";
        totalVehicles++;
    }

    public void assignDriver(Driver d) {
        this.assignedDriver = d;
        d.assignedVehicle = this;
        this.currentStatus = "Assigned";
    }

    public void scheduleMaintenance() {
        System.out.println("Vehicle " + vehicleId + " scheduled for maintenance.");
        this.currentStatus = "Maintenance";
    }

    public abstract double calculateRunningCost();

    public void updateMileage(double extraMiles) {
        this.mileage += extraMiles;
        System.out.println("Mileage updated. Current mileage: " + mileage);
    }

    public boolean checkServiceDue() {
        return mileage % 10000 < 500; // due near every 10,000 km
    }

    // Static utility methods
    public static double getFleetUtilization(int activeVehicles) {
        return (double) activeVehicles / totalVehicles * 100;
    }

    public static double calculateTotalMaintenanceCost(Vehicle[] fleet) {
        double total = 0;
        for (Vehicle v : fleet) {
            total += v.calculateRunningCost() * 0.05; // assume 5% goes to maintenance
        }
        return total;
    }

    public static void getVehiclesByType(Vehicle[] fleet, String type) {
        System.out.println("Listing all " + type + "s:");
        for (Vehicle v : fleet) {
            if (v.getClass().getSimpleName().equals(type)) {
                System.out.println(v.vehicleId + " - " + v.brand + " " + v.model);
            }
        }
    }
}

// Car class
class Car extends Vehicle {
    String carType; // Sedan, SUV, etc.

    Car(String id, String brand, String model, int year, double mileage,
        String fuelType, String carType) {
        super(id, brand, model, year, mileage, fuelType);
        this.carType = carType;
    }

    @Override
    public double calculateRunningCost() {
        return mileage * 0.12; // Example cost/km
    }
}

// Bus class
class Bus extends Vehicle {
    int seatingCapacity;

    Bus(String id, String brand, String model, int year, double mileage,
        String fuelType, int seatingCapacity) {
        super(id, brand, model, year, mileage, fuelType);
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public double calculateRunningCost() {
        return mileage * 0.20; // More expensive per km
    }
}

// Truck class
class Truck extends Vehicle {
    double loadCapacity;

    Truck(String id, String brand, String model, int year, double mileage,
          String fuelType, double loadCapacity) {
        super(id, brand, model, year, mileage, fuelType);
        this.loadCapacity = loadCapacity;
    }

    @Override
    public double calculateRunningCost() {
        return mileage * 0.30; // Trucks cost more to run
    }
}

// Driver Class
class Driver {
    String driverId, driverName, licenseType;
    Vehicle assignedVehicle;
    int totalTrips;

    Driver(String id, String name, String licenseType) {
        this.driverId = id;
        this.driverName = name;
        this.licenseType = licenseType;
        this.totalTrips = 0;
    }

    public void startTrip(double distance, double fuelConsumed) {
        if (assignedVehicle == null) {
            System.out.println("Driver " + driverName + " has no assigned vehicle.");
            return;
        }
        assignedVehicle.updateMileage(distance);
        Vehicle.totalFuelConsumption += fuelConsumed;
        totalTrips++;
        System.out.println(driverName + " completed a trip of " + distance + " km.");
    }
}

// Main Simulation
public class FleetManagementSystem {
    public static void main(String[] args) {
        Vehicle[] fleet = new Vehicle[3];

        fleet[0] = new Car("C101", "Toyota", "Corolla", 2020, 15000, "Petrol", "Sedan");
        fleet[1] = new Bus("B201", "Volvo", "B9R", 2018, 50000, "Diesel", 50);
        fleet[2] = new Truck("T301", "Tata", "Prima", 2019, 80000, "Diesel", 15);

        Driver d1 = new Driver("D001", "Rajesh", "Heavy");
        fleet[1].assignDriver(d1);

        d1.startTrip(120, 30);

        System.out.println("Fleet Utilization: " + Vehicle.getFleetUtilization(2) + "%");
        System.out.println("Total Maintenance Cost: " + Vehicle.calculateTotalMaintenanceCost(fleet));
        Vehicle.getVehiclesByType(fleet, "Truck");
    }
}
