class Color {
    protected String name;

    public Color(String name) {
        this.name = name;
        System.out.println("Color constructor called: " + name);
    }
}

class PrimaryColor extends Color {
    protected int intensity;

    public PrimaryColor(String name, int intensity) {
        super(name);
        this.intensity = intensity;
        System.out.println("PrimaryColor constructor called: Intensity = " + intensity);
    }
}

class RedColor extends PrimaryColor {
    protected String shade;

    public RedColor(String name, int intensity, String shade) {
        super(name, intensity);
        this.shade = shade;
        System.out.println("RedColor constructor called: Shade = " + shade);
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Intensity: " + intensity);
        System.out.println("Shade: " + shade);
    }
}

public class ColourTest {
    public static void main(String[] args) {
        RedColor rc = new RedColor("Red", 80, "Crimson");
        rc.displayInfo();
    }
}