package week5.ecommerce;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ECommerceSystem { // Cannot be extended, maintains business rule integrity
    private static final Map<String, Object> productCatalog = new HashMap<>();
    private static final Map<String, Customer> customerRegistry = new HashMap<>();
    private static final Map<String, Order> orderRegistry = new HashMap<>();

    // Static initializer to populate some initial products (for demonstration)
    static {
        Product laptop = Product.createElectronics("E001", "Laptop Pro", "TechCorp", 1200.00, 2.5, new String[]{"16GB RAM", "512GB SSD"}, Map.of("CPU", "Intel i7", "Screen", "15 inch"));
        Product tShirt = Product.createClothing("C001", "Cotton T-Shirt", "FashionWear", 25.00, 0.2, new String[]{"100% Cotton"}, Map.of("Size", "M", "Color", "Blue"));
        Product novel = Product.createBooks("B001", "The Great Adventure", "PublisherX", 15.00, 0.5, new String[]{"Hardcover"}, Map.of("Author", "Jane Doe", "Pages", "300"));

        productCatalog.put(laptop.getProductId(), laptop);
        productCatalog.put(tShirt.getProductId(), tShirt);
        productCatalog.put(novel.getProductId(), novel);
    }

    private ECommerceSystem() {
        // Private constructor to prevent instantiation
    }

    public static boolean processOrder(Object order, Object customer) {
        if (!(order instanceof Order)) {
            System.out.println("Order processing failed: Invalid order object.");
            return false;
        }
        if (!(customer instanceof Customer)) {
            System.out.println("Order processing failed: Invalid customer object.");
            return false;
        }

        Order o = (Order) order;
        Customer c = (Customer) customer;

        // Basic validation
        if (!customerRegistry.containsKey(c.getCustomerId()) && !c.getCustomerId().startsWith("GUEST-")) {
            System.out.println("Order processing failed: Customer " + c.getCustomerId() + " not registered.");
            return false;
        }
        if (orderRegistry.containsKey(o.getOrderId())) {
            System.out.println("Order processing failed: Order ID " + o.getOrderId() + " already exists.");
            return false;
        }

        // Simulate inventory management and order fulfillment
        if (!manageInventory(o.getOrderedItems())) {
            System.out.println("Order processing failed: Inventory management failed.");
            return false;
        }

        orderRegistry.put(o.getOrderId(), o);
        System.out.println("Order " + o.getOrderId() + " processed successfully for customer " + c.getName());
        return true;
    }

    // Static method for inventory management
    private static boolean manageInventory(List<Object> items) {
        // Simplified inventory management: just prints a message
        for (Object item : items) {
            if (item instanceof Product) {
                Product p = (Product) item;
                System.out.println("Decreasing stock for product: " + p.getName());
                // In a real system, this would update a database
            }
        }
        return true;
    }

    // Static method for order fulfillment (simplified)
    public static void fulfillOrder(String orderId) {
        Order order = orderRegistry.get(orderId);
        if (order != null) {
            System.out.println("Fulfilling order: " + order.getOrderId() + ". Shipping items...");
            // Integration with shipping services would go here
        } else {
            System.out.println("Order " + orderId + " not found for fulfillment.");
        }
    }

    // Public static method to register a customer
    public static void registerCustomer(Customer customer) {
        if (customer == null || customerRegistry.containsKey(customer.getCustomerId())) {
            System.out.println("Customer registration failed: Invalid customer or ID already exists.");
            return;
        }
        customerRegistry.put(customer.getCustomerId(), customer);
        System.out.println("Customer " + customer.getName() + " registered successfully.");
    }

    // Public static method to get an unmodifiable view of the product catalog
    public static Map<String, Object> getProductCatalog() {
        return Collections.unmodifiableMap(productCatalog);
    }

    // Public static method to get an unmodifiable view of the customer registry
    public static Map<String, Customer> getCustomerRegistry() {
        return Collections.unmodifiableMap(customerRegistry);
    }

    // Public static method to get an unmodifiable view of the order registry
    public static Map<String, Order> getOrderRegistry() {
        return Collections.unmodifiableMap(orderRegistry);
    }
}
