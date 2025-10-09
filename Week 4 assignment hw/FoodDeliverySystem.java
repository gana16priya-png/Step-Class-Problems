class FoodOrder {
    String customerName;
    String foodItem;
    int quantity;
    double price;

    static final double FIXED_RATE = 150.0; // assume each item costs 150

    // 1. Default constructor  assigns "Unknown" order
    FoodOrder() {
        this.customerName = "Unknown";
        this.foodItem = "Not Selected";
        this.quantity = 0;
        this.price = 0.0;
    }

    // 2. Constructor with food item sets quantity = 1, price = default
    FoodOrder(String foodItem) {
        this.customerName = "Guest";
        this.foodItem = foodItem;
        this.quantity = 1;
        this.price = FIXED_RATE;
    }

    // 3. Constructor with food item and quantity calculates price = quantity fixedRate
    FoodOrder(String foodItem, int quantity) {
        this.customerName = "Guest";
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.price = quantity * FIXED_RATE;
    }

    // Print Bill
    void printBill() {
        System.out.println(" Order Bill:");
        System.out.println("Customer: " + customerName);
        System.out.println("Food Item: " + foodItem);
        System.out.println("Quantity: " + quantity);
        System.out.println("Total Price: " + price);
        System.out.println("---------------------------------");
    }
}

public class FoodDeliverySystem {
    public static void main(String[] args) {
        FoodOrder o1 = new FoodOrder(); // default
        FoodOrder o2 = new FoodOrder("Burger"); // 1 quantity
        FoodOrder o3 = new FoodOrder("Pizza", 3); // multiple quantities

        o1.printBill();
        o2.printBill();
        o3.printBill();
    }
}
