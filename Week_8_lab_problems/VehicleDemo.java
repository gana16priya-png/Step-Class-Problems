// Abstract Class
abstract class Vehicle {
    protected int speed;
    protected String fuelType;

    // Constructor
    public Vehicle(int speed, String fuelType) {
        this.speed = speed;
        this.fuelType = fuelType;
    }

    // Abstract method
    public abstract void startEngine();
}

// Interface
interface Maintainable {
    void serviceInfo();
}

// Concrete Class
class Car extends Vehicle implements Maintainable {
    private String brand;

    // Constructor
    public Car(int speed, String fuelType, String brand) {
        super(speed, fuelType);
        this.brand = brand;
    }

    // Implement abstract method
    @Override
    public void startEngine() {
        System.out.println(brand + " Car engine started! Speed: " + speed + " km/h, Fuel: " + fuelType);
    }

    // Implement interface method
    @Override
    public void serviceInfo() {
        System.out.println("Service Info: Regular maintenance every 6 months or 10,000 km.");
    }

    // Show car details
    public void showDetails() {
        System.out.println("Car Brand: " + brand);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Speed: " + speed + " km/h");
    }
}

// Main Class
public class VehicleDemo {
    public static void main(String[] args) {
        Car c = new Car(180, "Petrol", "Toyota");
        c.startEngine();
        c.serviceInfo();
        c.showDetails();
    }
}
