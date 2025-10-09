// Parent class
class Phone {
    protected String brand;
    protected String model;

    // Default constructor
    Phone() {
        System.out.println("Phone default constructor called");
    }

    // Parameterized constructor
    Phone(String brand, String model) {
        this.brand = brand;
        this.model = model;
        System.out.println("Phone parameterized constructor called");
    }
}

// Child class
class SmartPhone extends Phone {
    private String operatingSystem;

    // Constructor 1 - default
    SmartPhone() {
        super(); // calls parent default constructor
        System.out.println("SmartPhone default constructor called");
    }

    // Constructor 2 - parameterized
    SmartPhone(String brand, String model, String os) {
        super(brand, model); // calls parent parameterized constructor
        this.operatingSystem = os;
        System.out.println("SmartPhone parameterized constructor called");
    }

    void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Operating System: " + operatingSystem);
    }
}

// Test class
public class LabProblem2 {
    public static void main(String[] args) {
        System.out.println("Creating SmartPhone using default constructor:");
        SmartPhone sp1 = new SmartPhone();

        System.out.println("\nCreating SmartPhone using parameterized constructor:");
        SmartPhone sp2 = new SmartPhone("Samsung", "Galaxy S23", "Android");
        sp2.displayInfo();
    }
}
