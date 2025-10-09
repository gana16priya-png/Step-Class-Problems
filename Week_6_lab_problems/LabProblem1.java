// Parent class
class Fruit {
    protected String color;
    protected String taste;

    // Constructor
    Fruit(String color, String taste) {
        this.color = color;
        this.taste = taste;
    }
}

// Child class
class Apple extends Fruit {
    private String variety;

    // Constructor
    Apple(String color, String taste, String variety) {
        // call parent constructor using super
        super(color, taste);
        this.variety = variety;
    }

    // Display method
    void displayInfo() {
        System.out.println("Color: " + color);
        System.out.println("Taste: " + taste);
        System.out.println("Variety: " + variety);
    }
}

// Test class
public class LabProblem1 {
    public static void main(String[] args) {
        Apple a1 = new Apple("Red", "Sweet", "Fuji");
        a1.displayInfo();
    }
}
