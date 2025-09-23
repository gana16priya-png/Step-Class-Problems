class FoodOrder {
    String customerName;
    String foodItem;
    int quantity;
    double price;

    // Fixed rate per food item (for simplicity)
    private static final double FIXED_RATE = 150.0;

    // 1. Default constructor → assigns "Unknown" order
    FoodOrder() {
        this.customerName = "Unknown";
        this.foodItem = "Unknown";
        this.quantity = 0;
        this.price = 0.0;
    }

    // 2. Constructor with food item → sets quantity = 1, price = default
    FoodOrder(String foodItem) {
        this.customerName = "Unknown";
        this.foodItem = foodItem;
        this.quantity = 1;
        this.price = FIXED_RATE;
    }

    // 3. Constructor with food item and quantity → calculates price
    FoodOrder(String foodItem, int quantity) {
        this.customerName = "Unknown";
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.price = quantity * FIXED_RATE;
    }

    // 4. Full constructor (extra) for named customer
    FoodOrder(String customerName, String foodItem, int quantity) {
        this.customerName = customerName;
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.price = quantity * FIXED_RATE;
    }

    // ---- Method ----
    void printBill() {
        System.out.println("🍴 Food Order Bill:");
        System.out.println("Customer: " + customerName);
        System.out.println("Food Item: " + foodItem);
        System.out.println("Quantity: " + quantity);
        System.out.println("Total Price: ₹" + price);
        System.out.println("---------------------------");
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        // Different order creations
        FoodOrder order1 = new FoodOrder();
        FoodOrder order2 = new FoodOrder("Burger");
        FoodOrder order3 = new FoodOrder("Pizza", 3);
        FoodOrder order4 = new FoodOrder("Alice", "Pasta", 2);

        // Print bills
        order1.printBill();
        order2.printBill();
        order3.printBill();
        order4.printBill();
    }
}

