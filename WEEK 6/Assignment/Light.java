class Light {
    protected String type;
    protected int wattage;

    public Light() {
        this("Generic", 0);
        System.out.println("Light default constructor called");
    }

    public Light(String type) {
        this(type, 0);
        System.out.println("Light constructor with type called: " + type);
    }

    public Light(String type, int wattage) {
        this.type = type;
        this.wattage = wattage;
        System.out.println("Light constructor with type and wattage called: " + type + ", " + wattage);
    }
}

class LED extends Light {
    private String color;

    public LED() {
        this("LED", 5, "White");
        System.out.println("LED default constructor called");
    }

    public LED(String type, int wattage) {
        this(type, wattage, "White");
        System.out.println("LED constructor with type and wattage called: " + type + ", " + wattage);
    }

    public LED(String type, int wattage, String color) {
        super(type, wattage);
        this.color = color;
        System.out.println("LED constructor with type, wattage, and color called: " + type + ", " + wattage + ", " + color);
    }
}

public class LightTest {
    public static void main(String[] args) {
        System.out.println("Creating Light objects:");
        Light l1 = new Light();
        Light l2 = new Light("Halogen");
        Light l3 = new Light("Fluorescent", 40);

        System.out.println("\nCreating LED objects:");
        LED led1 = new LED();
        LED led2 = new LED("LED", 10);
        LED led3 = new LED("LED", 15, "Blue");
    }
}