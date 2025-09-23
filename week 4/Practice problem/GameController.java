public class GameController {
    // Instance variables for controller configuration
    private String controllerBrand;
    private String connectionType;
    private boolean hasVibration;
    private int batteryLevel;
    private double sensitivity;

    // Default constructor - creates standard gaming setup
    public GameController() {
        this.controllerBrand = "GenericPad";
        this.connectionType = "USB";
        this.hasVibration = true;
        this.batteryLevel = 100;
        this.sensitivity = 1.0;
    }

    // Parameterized constructor for custom configuration
    public GameController(String controllerBrand, String connectionType,
                         boolean hasVibration, int batteryLevel, double sensitivity) {
        this.controllerBrand = controllerBrand;
        this.connectionType = connectionType;
        this.hasVibration = hasVibration;
        // Validate battery level (0-100)
        if (batteryLevel < 0) {
            this.batteryLevel = 0;
        } else if (batteryLevel > 100) {
            this.batteryLevel = 100;
        } else {
            this.batteryLevel = batteryLevel;
        }
        // Validate sensitivity (0.1-3.0)
        if (sensitivity < 0.1) {
            this.sensitivity = 0.1;
        } else if (sensitivity > 3.0) {
            this.sensitivity = 3.0;
        } else {
            this.sensitivity = sensitivity;
        }
    }

    // Two-parameter convenience constructor
    public GameController(String brand, String connectionType) {
        this(brand, connectionType, true, 100, 1.0);
    }

    // Methods to test functionality
    public void calibrateController() {
        System.out.println("Calibrating " + controllerBrand + " controller...");
    }

    public void displayConfiguration() {
        System.out.println("Brand: " + controllerBrand);
        System.out.println("Connection: " + connectionType);
        System.out.println("Vibration: " + (hasVibration ? "Enabled" : "Disabled"));
        System.out.println("Battery Level: " + batteryLevel + "%");
        System.out.println("Sensitivity: " + sensitivity);
    }

    public void testVibration() {
        if (hasVibration) {
            System.out.println("*BUZZ* Vibration test successful!");
        } else {
            System.out.println("Vibration disabled on this controller.");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== GAMING CONTROLLER SETUP ===");

        // Create controller with default constructor
        GameController defaultController = new GameController();
        System.out.println("\nDefault Controller:");
        defaultController.displayConfiguration();
        defaultController.calibrateController();
        defaultController.testVibration();

        // Create controller with full parameterized constructor
        GameController customController = new GameController(
            "ProGamer", "Bluetooth", false, 85, 2.5
        );
        System.out.println("\nCustom Controller:");
        customController.displayConfiguration();
        customController.calibrateController();
        customController.testVibration();

        // Create controller with convenience constructor
        GameController quickController = new GameController("Speedy", "Wireless");
        System.out.println("\nQuick Setup Controller:");
        quickController.displayConfiguration();
        quickController.calibrateController();
        quickController.testVibration();

        // Compare different configurations
        System.out.println("\nComparing Controllers:");
        System.out.println("Default vs Custom: " +
            (!defaultController.controllerBrand.equals(customController.controllerBrand) ? "Different Brands" : "Same Brand"));