abstract class Food {
    // Template method
    public final void prepare() {
        wash();
        cook();
        serve();
    }

    // Steps (abstract, to be implemented by children)
    protected abstract void wash();
    protected abstract void cook();
    protected abstract void serve();
}

class Pizza extends Food {
    @Override
    protected void wash() {
        System.out.println("Washing vegetables and pizza base...");
    }

    @Override
    protected void cook() {
        System.out.println("Baking pizza with cheese and toppings...");
    }

    @Override
    protected void serve() {
        System.out.println("Serving pizza in slices.\n");
    }
}

class Soup extends Food {
    @Override
    protected void wash() {
        System.out.println("Washing fresh vegetables for soup...");
    }

    @Override
    protected void cook() {
        System.out.println("Boiling vegetables and adding spices...");
    }

    @Override
    protected void serve() {
        System.out.println("Serving hot soup in a bowl.\n");
    }
}

public class hwProblem4_Main {
    public static void main(String[] args) {
        Food pizza = new Pizza();
        Food soup = new Soup();

        System.out.println("Preparing Pizza:");
        pizza.prepare();

        System.out.println("Preparing Soup:");
        soup.prepare();
    }
}
