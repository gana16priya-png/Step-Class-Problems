abstract class Food {
    // Template method
    public final void prepare() {
        wash();
        cook();
        serve();
    }

    protected abstract void wash();
    protected abstract void cook();
    protected abstract void serve();
}

class Pizza extends Food {
    @Override
    protected void wash() {
        System.out.println("Washing vegetables for pizza toppings.");
    }

    @Override
    protected void cook() {
        System.out.println("Baking pizza in oven.");
    }

    @Override
    protected void serve() {
        System.out.println("Serving pizza in slices.");
    }
}

class Soup extends Food {
    @Override
    protected void wash() {
        System.out.println("Washing vegetables for soup.");
    }

    @Override
    protected void cook() {
        System.out.println("Boiling soup ingredients.");
    }

    @Override
    protected void serve() {
        System.out.println("Serving soup in a bowl.");
    }
}

public class FoodTest {
    public static void main(String[] args) {
        Food pizza = new Pizza();
        Food soup = new Soup();

        System.out.println("Preparing Pizza:");
        pizza.prepare();

        System.out.println("\nPreparing Soup:");
        soup.prepare();
    }
}