// File: ECommerceSystem.java
import java.util.*;

/**
 * Final ECommerceSystem - cannot be extended.
 * Contains product catalog and basic order processing orchestration.
 */
public final class ECommerceSystem {
    // productCatalog maps productId -> product object (flexible)
    private static final Map<String, Object> productCatalog = new HashMap<>();

    // Private constructor to prevent instantiation
    private ECommerceSystem() {}

    // Inventory/product management
    public static void addProduct(String id, Object product) {
        if (id == null || product == null) throw new IllegalArgumentException("id/product required");
        productCatalog.put(id, product);
    }

    public static Object getProduct(String id) {
        return productCatalog.get(id);
    }

    public static void removeProduct(String id) {
        productCatalog.remove(id);
    }

    public static void showCatalog() {
        System.out.println("Catalog contents:");
        productCatalog.forEach((k, v) -> System.out.println(" - " + k + " => " + v));
    }

    /**
     * Process order: performs basic validation and coordinates payment and shipping
     * Expects order to be Order and customer to be Customer.
     */
    public static boolean processOrder(Object orderObj, Object customerObj, PaymentProcessor paymentProcessor, ShippingCalculator shippingCalculator) {
        if (!(orderObj instanceof Order) || !(customerObj instanceof Customer)) {
            System.out.println("Order or Customer invalid");
            return false;
        }
        Order order = (Order) orderObj;
        Customer customer = (Customer) customerObj;

        System.out.println("Processing order: " + order.getOrderId());
        System.out.println("Customer public profile: " + customer.getPublicProfile());

        // Basic checks
        if (order.getCart() == null || order.getCart().getItemCount() == 0) {
            System.out.println("Cart empty. Cannot process order.");
            return false;
        }

        double amount = order.getCart().getTotalAmount();
        boolean paymentOk = paymentProcessor.processPayment(amount);
        if (!paymentOk) {
            System.out.println("Payment failed.");
            return false;
        }

        // Calculate shipping (simple demo: use total weight as itemCount * 0.5 kg)
        double estimatedWeight = order.getCart().getItemCount() * 0.5;
        double shippingCost = shippingCalculator.calculateShipping("default", estimatedWeight);

        // Fulfill order (mock)
        System.out.printf("Order %s processed: amount=%.2f, shipping=%.2f\n", order.getOrderId(), amount, shippingCost);

        // In a real system: update inventory, store order history, send confirmation, etc.
        return true;
    }
}
