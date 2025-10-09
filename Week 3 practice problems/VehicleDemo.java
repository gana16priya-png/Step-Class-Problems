// Base Vehicle class
class Vehicle {
    protected String make;
    protected String model;
    protected int year;
    protected double fuelLevel;

    // Constructor
    public Vehicle(String make, String model, int year, double fuelLevel) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelLevel = fuelLevel;
    }

    // Common methods
    public void startVehicle() {
        System.out.println(make + " " + model + " started.");
    }

    public void stopVehicle() {
        System.out.println(make + " " + model + " stopped.");
    }

    public void refuel(double amount) {
        fuelLevel += amount;
        System.out.println(make + " " + model + " refueled. Current fuel: " + fuelLevel);
    }

    public void displayVehicleInfo() {
        System.out.println(year + " " + make + " " + model + " | Fuel: " + fuelLevel);
    }
}

// Subclasses demonstrating reuse and extension
class Car extends Vehicle {
    public Car(String make, String model, int year, double fuelLevel) {
        super(make, model, year, fuelLevel);
    }
}

class Truck extends Vehicle {
    public Truck(String make, String model, int year, double fuelLevel) {
        super(make, model, year, fuelLevel);
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String make, String model, int year, double fuelLevel) {
        super(make, model, year, fuelLevel);
    }
}

public class VehicleDemo {
    public static void main(String[] args) {
        Vehicle[] vehicles = {
            new Car("Toyota", "Camry", 2020, 50),
            new Truck("Ford", "F-150", 2018, 70),
            new Motorcycle("Yamaha", "R1", 2021, 30)
        };

        // Polymorphic behavior
        for (Vehicle v : vehicles) {
            v.displayVehicleInfo();
            v.startVehicle();
            v.refuel(10);
            v.stopVehicle();
            System.out.println("-------------------");
        }

        // Comments:
        // Reusability: Base Vehicle class reused for Car, Truck, Motorcycle.
        // Extensibility: New vehicle types (e.g., Bus, Van) can be added easily.
        // Benefit: Avoids duplicate code, leverages inheritance.
    }
}
