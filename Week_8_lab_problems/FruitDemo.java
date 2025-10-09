// Abstract Class
abstract class Fruit {
    protected String color;
    protected String taste;

    // Constructor
    public Fruit(String color, String taste) {
        this.color = color;
        this.taste = taste;
    }

    // Abstract Method
    public abstract void showDetails();
}

// Interface
interface Edible {
    void nutrientsInfo();
}

// Concrete Class
class Apple extends Fruit implements Edible {
    private String variety;

    // Constructor
    public Apple(String color, String taste, String variety) {
        super(color, taste);
        this.variety = variety;
    }

    // Implement abstract method
    @Override
    public void showDetails() {
        System.out.println("Apple Details:");
        System.out.println("Color: " + color);
        System.out.println("Taste: " + taste);
        System.out.println("Variety: " + variety);
    }

    // Implement interface method
    @Override
    public void nutrientsInfo() {
        System.out.println("Nutrients: Rich in fiber, Vitamin C, and antioxidants.");
    }
}

// Main Class
public class FruitDemo {
    public static void main(String[] args) {
        Apple a = new Apple("Red", "Sweet", "Fuji");
        a.showDetails();
        a.nutrientsInfo();
    }
}
