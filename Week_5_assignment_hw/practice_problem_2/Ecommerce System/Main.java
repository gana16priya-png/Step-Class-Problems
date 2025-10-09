// File: Main.java
import java.util.*;

/**
 * Demo main tying all classes together.
 */
public class Main {
    public static void main(String[] args) {
        // Create products
        Map<String, String> phoneSpecs = new HashMap<>();
        phoneSpecs.put("CPU", "Octa-core 2.8GHz");
        phoneSpecs.put("RAM", "8GB");
        Product phone = Product.createElectronics("P1001", "FastPhone X", "PhoneCorp", 349.99, 0.18,
                new String[]{"5G", "OLED display"}, phoneSpecs);

        Product tshirt = Product.createClothing("C2001", "Cool T-Shirt", "ClothCo", 19.99, 0.25,
                new String[]{"100% cotton", "Unisex"});

        Product book = Product.createBooks("B3001", "Java Patterns", "BookHouse", 39.50, 0.5,
                new String[]{"paperback"});

        // Add to catalog
        ECommerceSystem.addProduct(phone.getProductId(), phone);
        ECommerceSystem.addProduct(tshirt.getProductId(), tshirt);
        ECommerceSystem.addProduct(book.getProductId(), book);

        ECommerceSystem.showCatalog();

        // Create a registered customer
        Customer alice = new Customer("CUST100", "alice@example.com", "2025-01-01");
        alice.setName("Alice");
        alice.setPreferredLanguage("English");

        // Create shopping cart and add items
        ShoppingCart cart = new ShoppingCart("CART100", alice.getCustomerId());
        cart.addItem(phone, 1);
        cart.addItem(tshirt, 3);
        cart.addItem(book, 1);

        System.out.println("Cart summary (package-private): " + cart.getCartSummary());

        // Create an order for a registered customer
        Order order = new Order(null, alice.getCustomerId(), cart); // orderId null -> system generates in some constructors

        // Setup payment and shipping
        PaymentProcessor processor = new PaymentProcessor("StripeMock", "secretKey123");
        Map<String, Double> rates = new HashMap<>();
        rates.put("default", 5.0); // 5 currency units per kg
        ShippingCalculator shipCalc = new ShippingCalculator(rates);

        // Process order via ECommerceSystem
        boolean processed = ECommerceSystem.processOrder(order, alice, processor, shipCalc);
        System.out.println("Order processed: " + processed);
    }
}
