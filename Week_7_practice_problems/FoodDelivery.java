// File: FoodDelivery.java

// Base class
class Restaurant {
    protected String name;

    public Restaurant(String name) {
        this.name = name;
    }

    // Base methods
    public void prepareFood() {
        System.out.println(name + " is preparing generic food");
    }

    public void estimateTime() {
        System.out.println("Estimated time: 30 minutes");
    }
}

// Subclass PizzaPlace
class PizzaPlace extends Restaurant {
    public PizzaPlace(String name) {
        super(name);
    }

    @Override
    public void prepareFood() {
        System.out.println(name + " is making delicious pizza with fresh toppings!");
    }

    @Override
    public void estimateTime() {
        System.out.println("Pizza ready in 20 minutes!");
    }
}

// Subclass SushiBar
class SushiBar extends Restaurant {
    public SushiBar(String name) {
        super(name);
    }

    @Override
    public void prepareFood() {
        System.out.println(name + " is crafting fresh sushi with precision!");
    }

    @Override
    public void estimateTime() {
        System.out.println("Sushi will be ready in 25 minutes!");
    }
}

// Demo class
public class FoodDelivery {
    public static void main(String[] args) {
        // Step 1: Restaurant reference
        Restaurant order;

        // Step 2: Assign PizzaPlace to reference
        order = new PizzaPlace("Mario's Pizza");

        // Step 3: Calls PizzaPlace methods at runtime
        order.prepareFood();
        order.estimateTime();

        // Step 4: Reassign to SushiBar
        order = new SushiBar("Tokyo Sushi");

        // Step 5: Calls SushiBar methods at runtime
        order.prepareFood();
        order.estimateTime();

        // Step 6: Explanation
        System.out.println("\n JVM decides which overridden method to call based on the actual object type at runtime, not the reference type.");
    }
}
