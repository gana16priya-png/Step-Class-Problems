// Base class
class Vehicle {
    protected String id;

    public Vehicle(String id) {
        this.id = id;
    }

    public void dispatch() {
        System.out.println("Dispatching generic vehicle...");
    }
}

// Bus subclass
class Bus extends Vehicle {
    private int capacity;

    public Bus(String id, int capacity) {
        super(id);
        this.capacity = capacity;
    }

    @Override
    public void dispatch() {
        System.out.println("Bus " + id + " dispatched on fixed route with capacity " + capacity);
    }
}

// Taxi subclass
class Taxi extends Vehicle {
    private double ratePerKm;

    public Taxi(String id, double ratePerKm) {
        super(id);
        this.ratePerKm = ratePerKm;
    }

    @Override
    public void dispatch() {
        System.out.println("Taxi " + id + " dispatched for door-to-door service. Rate: $" + ratePerKm + "/km");
    }
}

// Train subclass
class Train extends Vehicle {
    private int carCount;

    public Train(String id, int carCount) {
        super(id);
        this.carCount = carCount;
    }

    @Override
    public void dispatch() {
        System.out.println("Train " + id + " dispatched with " + carCount + " cars on schedule");
    }
}

// Bike subclass
class Bike extends Vehicle {
    public Bike(String id) {
        super(id);
    }

    @Override
    public void dispatch() {
        System.out.println("Bike " + id + " dispatched for eco-friendly short trips");
    }
}

// Main program
public class Problem3_Transport {
    public static void main(String[] args) {
        Vehicle[] fleet = {
            new Bus("B101", 50),
            new Taxi("T202", 15.0),
            new Train("TR303", 12),
            new Bike("BK404")
        };

        // Same command -> different behavior at runtime
        for (Vehicle v : fleet) {
            v.dispatch(); // dynamic method dispatch
        }
    }
}
