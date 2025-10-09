public class Car {
    // Instance variables (attributes)
    String brand;
    String model;
    int year;
    String color;
    boolean isRunning;

    // Constructor to initialize attributes
    public Car(String brand, String model, int year, String color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.isRunning = false; // initially engine is off
    }

    // Method to start engine
    public void startEngine() {
        if (!isRunning) {
            isRunning = true;
            System.out.println(brand + " " + model + " engine started.");
        } else {
            System.out.println(brand + " " + model + " is already running.");
        }
    }

    // Method to stop engine
    public void stopEngine() {
        if (isRunning) {
            isRunning = false;
            System.out.println(brand + " " + model + " engine stopped.");
        } else {
            System.out.println(brand + " " + model + " is already off.");
        }
    }

    // Method to display car information
    public void displayInfo() {
        System.out.println("Car Info: " + brand + " " + model + " (" + year + "), Color: " + color + ", Running: " + isRunning);
    }

    // Method to calculate car age
    public int getAge(int currentYear) {
        return currentYear - year;
    }

    public static void main(String[] args) {
        // Create 3 different Car objects
        Car car1 = new Car("Toyota", "Camry", 2018, "Black");
        Car car2 = new Car("Honda", "Civic", 2020, "Blue");
        Car car3 = new Car("Ford", "Mustang", 2015, "Red");

        // Demonstrate calling methods
        car1.displayInfo();
        car1.startEngine();
        car1.stopEngine();

        car2.displayInfo();
        car2.startEngine();
        System.out.println(car2.brand + " age: " + car2.getAge(2025) + " years");

        car3.displayInfo();
        car3.startEngine();
        car3.stopEngine();

        // Explanation in comments:
        // Each car object has its own state (brand, model, year, etc.).
        // Even if we call the same methods, the behavior/output depends on
        // the object’s attributes.
        // This is similar to the real world: every car has unique details,
        // but they all share common functionalities like starting, stopping, etc.
    }
}
