class Tool {
    private String serialNumber;      // Private: not accessible directly in child
    protected String type;            // Protected: accessible in child
    public String brand;              // Public: accessible everywhere

    public Tool(String serialNumber, String type, String brand) {
        this.serialNumber = serialNumber;
        this.type = type;
        this.brand = brand;
    }

    // Getter for private field
    public String getSerialNumber() {
        return serialNumber;
    }
}

class Hammer extends Tool {
    public Hammer(String serialNumber, String type, String brand) {
        super(serialNumber, type, brand);
    }

    public void displayInfo() {
        // System.out.println(serialNumber); // Not accessible (private)
        System.out.println("Serial Number: " + getSerialNumber()); // Accessible via getter
        System.out.println("Type: " + type); // Accessible (protected)
        System.out.println("Brand: " + brand); // Accessible (public)
    }
}

public class ToolsTest {
    public static void main(String[] args) {
        Hammer h = new Hammer("SN123", "Claw", "Stanley");
        h.displayInfo();
    }
}