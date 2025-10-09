abstract class Vehicle {
    // Abstract method
    public abstract void start();

    // Non-abstract method
    public void fuelType() {
        System.out.println("Uses fuel");
    }
}

class Car extends Vehicle {
    @Override
    public void start() {
        System.out.println("Car starts with key");
    }
}

class Bike extends Vehicle {
    @Override
    public void start() {
        System.out.println("Bike starts with kick");
    }
}

public class VehicleTest {
    public static void main(String[] args) {
        Vehicle v1 = new Car();   // Vehicle reference -> Car
        v1.start();
        v1.fuelType();

        Vehicle v2 = new Bike();  // Vehicle reference -> Bike
        v2.start();
        v2.fuelType();
    }
}
