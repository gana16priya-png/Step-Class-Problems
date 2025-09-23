
class Vehicle {
    protected String vehicleId;
    protected String brand;
    protected String model;
    protected int year;
    protected double mileage;
    protected String fuelType;
    protected String currentStatus;
    protected Driver assignedDriver;

    protected static int totalVehicles = 0;
    protected static double fleetValue = 0;
    protected static String companyName = "TranspoFleet";
    protected static double totalFuelConsumption = 0;

    public Vehicle(String vehicleId, String brand, String model, int year, double mileage, String fuelType, String currentStatus, double value) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.currentStatus = currentStatus;
        this.assignedDriver = null;
        totalVehicles++;
        fleetValue += value;
    }

    public void assignDriver(Driver driver) {
        this.assignedDriver = driver;
        driver.assignedVehicle = this;
        System.out.println(driver.driverName + " assigned to " + brand + " " + model);
    }

    public void scheduleMaintenance() {
        System.out.println("Maintenance scheduled for " + brand + " " + model + " (" + vehicleId + ")");
        currentStatus = "Maintenance";
    }

    public double calculateRunningCost(double fuelConsumed, double fuelPrice, double maintenanceCost) {
        totalFuelConsumption += fuelConsumed;
        return (fuelConsumed * fuelPrice) + maintenanceCost;
    }

    public void updateMileage(double tripDistance) {
        mileage += tripDistance;
    }

    public boolean checkServiceDue(double serviceInterval) {
        return mileage >= serviceInterval;
    }

    public String getVehicleType() {
        return "Vehicle";
    }

    public void displayInfo() {
        System.out.println(vehicleId + " | " + brand + " | " + model + " | " + year + " | Mileage: " + mileage + " | Status: " + currentStatus);
    }

    // Static methods
    public static double getFleetUtilization(Vehicle[] vehicles) {
        int active = 0;
        for (Vehicle v : vehicles) {
            if (v.currentStatus.equalsIgnoreCase("Active")) active++;
        }
        return (active * 100.0) / vehicles.length;
    }

    public static double calculateTotalMaintenanceCost(Vehicle[] vehicles, double[] maintenanceCosts) {
        double total = 0;
        for (double cost : maintenanceCosts) total += cost;
        return total;
    }

    public static void getVehiclesByType(Vehicle[] vehicles, String type) {
        System.out.println("Vehicles of type: " + type);
        for (Vehicle v : vehicles) {
            if (v.getVehicleType().equalsIgnoreCase(type)) v.displayInfo();
        }
    }
}

class Car extends Vehicle {
    private int seatingCapacity;

    public Car(String vehicleId, String brand, String model, int year, double mileage, String fuelType, String currentStatus, double value, int seatingCapacity) {
        super(vehicleId, brand, model, year, mileage, fuelType, currentStatus, value);
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }

    public int getSeatingCapacity() { return seatingCapacity; }
}

class Bus extends Vehicle {
    private int seatingCapacity;

    public Bus(String vehicleId, String brand, String model, int year, double mileage, String fuelType, String currentStatus, double value, int seatingCapacity) {
        super(vehicleId, brand, model, year, mileage, fuelType, currentStatus, value);
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public String getVehicleType() {
        return "Bus";
    }

    public int getSeatingCapacity() { return seatingCapacity; }
}

class Truck extends Vehicle {
    private double loadCapacity;

    public Truck(String vehicleId, String brand, String model, int year, double mileage, String fuelType, String currentStatus, double value, double loadCapacity) {
        super(vehicleId, brand, model, year, mileage, fuelType, currentStatus, value);
        this.loadCapacity = loadCapacity;
    }

    @Override
    public String getVehicleType() {
        return "Truck";
    }

    public double getLoadCapacity() { return loadCapacity; }
}

class Driver {
    public String driverId;
    public String driverName;
    public String licenseType;
    public Vehicle assignedVehicle;
    public int totalTrips;

    public Driver(String driverId, String driverName, String licenseType) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.licenseType = licenseType;
        this.assignedVehicle = null;
        this.totalTrips = 0;
    }

    public void recordTrip(double tripDistance, Vehicle vehicle) {
        totalTrips++;
        vehicle.updateMileage(tripDistance);
        System.out.println(driverName + " completed trip of " + tripDistance + " km with " + vehicle.brand + " " + vehicle.model);
    }
}

public class VehicleManagement {
    public static void main(String[] args) {
        // Create vehicles
        Vehicle[] fleet = new Vehicle[5];
        fleet[0] = new Car("V001", "Toyota", "Corolla", 2018, 50000, "Petrol", "Active", 800000, 5);
        fleet[1] = new Bus("V002", "Volvo", "9400", 2020, 120000, "Diesel", "Active", 3500000, 45);
        fleet[2] = new Truck("V003", "Tata", "Ultra", 2019, 90000, "Diesel", "Active", 2500000, 12000);
        fleet[3] = new Car("V004", "Honda", "City", 2017, 60000, "Petrol", "Maintenance", 750000, 5);
        fleet[4] = new Truck("V005", "Ashok Leyland", "Boss", 2021, 40000, "Diesel", "Active", 2700000, 15000);

        // Create drivers
        Driver d1 = new Driver("D001", "Alice", "Car");
        Driver d2 = new Driver("D002", "Bob", "Bus");
        Driver d3 = new Driver("D003", "Charlie", "Truck");

        // Assign drivers
        fleet[0].assignDriver(d1);
        fleet[1].assignDriver(d2);
        fleet[2].assignDriver(d3);

        // Record trips
        d1.recordTrip(120, fleet[0]);
        d2.recordTrip(300, fleet[1]);
        d3.recordTrip(500, fleet[2]);

        // Schedule maintenance
        fleet[3].scheduleMaintenance();

        // Calculate running cost for a trip
        double cost1 = fleet[0].calculateRunningCost(10, 100, 500); // fuelConsumed, fuelPrice, maintenanceCost
        System.out.println("Running cost for " + fleet[0].brand + ": Rs." + cost1);

        // Check service due
        System.out.println("Service due for " + fleet[0].brand + ": " + fleet[0].checkServiceDue(60000));

        // Fleet utilization
        System.out.println("Fleet Utilization: " + Vehicle.getFleetUtilization(fleet) + "%");

        // Maintenance costs
        double[] maintenanceCosts = {500, 1000, 1500, 700, 1200};
        System.out.println("Total Maintenance Cost: Rs." + Vehicle.calculateTotalMaintenanceCost(fleet, maintenanceCosts));

        // Get vehicles by type
        Vehicle.getVehiclesByType(fleet, "Truck");

        // Display all vehicles
        System.out.println("All Vehicles:");
        for (Vehicle v : fleet) v.displayInfo();

        // Show static members
        System.out.println("Company Name: " + Vehicle.companyName);
        System.out.println("Total Vehicles: " + Vehicle.totalVehicles);
        System.out.println("Fleet Value: Rs." + Vehicle.fleetValue);
        System.out.println("Total Fuel Consumption: " + Vehicle.totalFuelConsumption + " liters");
    }
}