// Base class demonstrating OOP reusability
public class Vehicle {
    // Protected instance variables
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
        System.out.println(make + " " + model + " refueled by " + amount + " liters.");
    }

    public void displayVehicleInfo() {
        System.out.println("Make: " + make + ", Model: " + model + ", Year: " + year + ", Fuel Level: " + fuelLevel);
    }

    // Main method to demonstrate reusability and polymorphism
    public static void main(String[] args) {
        // Subclasses reusing Vehicle base class
        class Car extends Vehicle {
            public Car(String make, String model, int year, double fuelLevel) {
                super(make, model, year, fuelLevel);
            }
            @Override
            public void displayVehicleInfo() {
                System.out.print("Car - ");
                super.displayVehicleInfo();
            }
        }

        class Truck extends Vehicle {
            public Truck(String make, String model, int year, double fuelLevel) {
                super(make, model, year, fuelLevel);
            }
            @Override
            public void displayVehicleInfo() {
                System.out.print("Truck - ");
                super.displayVehicleInfo();
            }
        }

        class Motorcycle extends Vehicle {
            public Motorcycle(String make, String model, int year, double fuelLevel) {
                super(make, model, year, fuelLevel);
            }
            @Override
            public void displayVehicleInfo() {
                System.out.print("Motorcycle - ");
                super.displayVehicleInfo();
            }
        }

        // Create different types of vehicles
        Vehicle v1 = new Car("Toyota", "Corolla", 2020, 40);
        Vehicle v2 = new Truck("Tata", "Ace", 2018, 60);
        Vehicle v3 = new Motorcycle("Honda", "CBR", 2022, 15);

        // Array of Vehicle objects (polymorphism)
        Vehicle[] vehicles = {v1, v2, v3};

        // Demonstrate polymorphic behavior
        for (Vehicle v : vehicles) {
            v.startVehicle();
            v.refuel(10);
            v.displayVehicleInfo();
            v.stopVehicle();
            System.out.println("-------------------");
        }

        // Comments:
        // - This shows reusability because Car, Truck, and Motorcycle all reuse Vehicle's code.
        // - To extend for new vehicle types, simply create a new subclass (e.g., Bus) that extends Vehicle.
        // - Benefits: Less code duplication, easier maintenance, and consistent interface for all vehicles.
        //   If you wrote separate classes, you'd have to repeat common logic in each class.
    }
}