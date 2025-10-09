// Base class
class Color {
    protected String name;

    Color(String name) {
        this.name = name;
        System.out.println("Color constructor called");
    }
}

// Derived class 1
class PrimaryColor extends Color {
    protected int intensity;

    PrimaryColor(String name, int intensity) {
        super(name); // call parent constructor
        this.intensity = intensity;
        System.out.println("PrimaryColor constructor called");
    }
}

// Derived class 2
class RedColor extends PrimaryColor {
    private String shade;

    RedColor(String name, int intensity, String shade) {
        super(name, intensity); // call parent constructor
        this.shade = shade;
        System.out.println("RedColor constructor called");
    }

    void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Intensity: " + intensity);
        System.out.println("Shade: " + shade);
    }
}

// Test class
public class LabProblem4 {
    public static void main(String[] args) {
        RedColor red = new RedColor("Red", 90, "Dark Red");
        red.displayInfo();
    }
}
