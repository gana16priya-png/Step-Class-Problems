class hwProblem1_Light {
    int brightness;
    String color;

    // Constructor 1
    hwProblem1_Light() {
        this(50, "White"); // calls Constructor 3
        System.out.println("Light(): Default constructor");
    }

    // Constructor 2
    hwProblem1_Light(int brightness) {
        this(brightness, "White"); // calls Constructor 3
        System.out.println("Light(int): Brightness only");
    }

    // Constructor 3
    hwProblem1_Light(int brightness, String color) {
        this.brightness = brightness;
        this.color = color;
        System.out.println("Light(int, String): Brightness and Color set");
    }
}

// Child class
class hwProblem1_LED extends hwProblem1_Light {
    int power;

    // Constructor 1
    hwProblem1_LED() {
        this(10, 100, "Blue"); // calls Constructor 3
        System.out.println("LED(): Default constructor");
    }

    // Constructor 2
    hwProblem1_LED(int power) {
        super(70); // call parent constructor
        this.power = power;
        System.out.println("LED(int): Power set");
    }

    // Constructor 3
    hwProblem1_LED(int power, int brightness, String color) {
        super(brightness, color); // call parent constructor
        this.power = power;
        System.out.println("LED(int, int, String): Power, Brightness, Color set");
    }
}

// Test class
public class hwProblem1_Main {
    public static void main(String[] args) {
        System.out.println("-----Creating Light Object-----");
        hwProblem1_Light l1 = new hwProblem1_Light();

        System.out.println("\n-----Creating LED Object-----");
        hwProblem1_LED led1 = new hwProblem1_LED();
    }
}
