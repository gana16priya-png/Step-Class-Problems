public class Car extends Vehicle {
    //  Car-specific fields
    private int numberOfDoors;
    private String fuelType;
    private String transmissionType;

    // Default constructor
    public Car() {
        super(); // call Vehicle()
        this.numberOfDoors = 4;
        this.fuelType = "Petrol";
        this.transmissionType = "Manual";
        System.out.println("Car default constructor called");
    }

    //  Parameterized constructor
    public Car(String brand, String model, int year, String engineType,
               int numberOfDoors, String fuelType, String transmissionType) {
        super(brand, model, year, engineType);  // call Vehicle(...)
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        System.out.println("Car parameterized constructor called");
    }

    // Overriding start() to include car-specific actions
    @Override
    public void start() {
        super.start(); // call Vehicle.start()
        System.out.println("Car systems are warming up...");
        System.out.println("Seat belts check... Air conditioning ready.");
    }

    // Overriding displaySpecs()
    @Override
    public void displaySpecs() {
        super.displaySpecs();  // show vehicle specs
        System.out.println("--- Car Additional Specifications ---");
        System.out.println("Doors: " + numberOfDoors);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Transmission: " + transmissionType);
    }

    // Car-specific methods
    public void openTrunk() {
        System.out.println("Trunk opened");
    }

    public void playRadio() {
        System.out.println("Radio playing music");
    }

    // main method for testing
    public static void main(String[] args) {

        System.out.println("\n--- Testing default constructors ---");
        Car c1 = new Car(); // will call Vehicle() then Car()

        System.out.println("\n--- Testing parameterized constructors ---");
        Car c2 = new Car("Toyota", "Camry", 2023, "Hybrid",
                         4, "Petrol", "Automatic");

        System.out.println("\n--- Testing inheritance fields and methods ---");
        c2.start();      // overridden method
        c2.displaySpecs(); // overridden method

        System.out.println("\n--- Testing parent methods ---");
        c2.stop(); // from Vehicle
        System.out.println("Is car running? " + c2.isRunning());

        System.out.println("\n--- Testing car-specific methods ---");
        c2.openTrunk();
        c2.playRadio();

        System.out.println("\n--- Testing protected field access ---");
        System.out.println("Brand from Car (protected field): " + c2.brand);

        System.out.println("\n--- Vehicle info ---");
        System.out.println(c2.getVehicleInfo());
    }
}
