// File: Order.java
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Order with constructor chaining for different order types:
 * - Guest checkout
 * - Registered customer
 * - Premium member
 * - Corporate account
 */
public class Order {
    private final String orderId;
    private final LocalDateTime orderTime;
    private final String customerType;
    private final String customerId; // may be null for guest
    private final ShoppingCart cart;

    // Private master constructor used by chaining
    private Order(String orderId, String customerType, String customerId, ShoppingCart cart) {
        if (orderId == null) orderId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.orderTime = LocalDateTime.now();
        this.customerType = (customerType == null) ? "Guest" : customerType;
        this.customerId = customerId;
        this.cart = cart;
    }

    // Guest checkout (minimal info)
    public Order(String orderId, ShoppingCart cart) {
        this(orderId, "Guest", null, cart);
    }

    // Registered customer
    public Order(String orderId, String customerId, ShoppingCart cart) {
        this(orderId, "Registered", customerId, cart);
    }

    // Premium member
    public Order(String orderId, String customerId, boolean premium, ShoppingCart cart) {
        this(orderId, premium ? "Premium" : "Registered", customerId, cart);
    }

    // Corporate account (bulk ordering with company validation)
    public Order(String orderId, String customerId, String companyName, boolean corporate, ShoppingCart cart) {
        String type = corporate ? "Corporate:" + companyName : "Registered";
        this.orderId = (orderId == null) ? UUID.randomUUID().toString() : orderId;
        this.orderTime = LocalDateTime.now();
        this.customerType = type;
        this.customerId = customerId;
        this.cart = cart;
    }

    public String getOrderId() { return orderId; }
    public LocalDateTime getOrderTime() { return orderTime; }
    public String getCustomerType() { return customerType; }
    public String getCustomerId() { return customerId; }
    public ShoppingCart getCart() { return cart; }

    public String getOrderDetails() {
        return String.format("Order[id=%s,type=%s,time=%s,cart=%s]", orderId, customerType, orderTime, (cart == null ? "N/A" : cart.getCartSummary()));
    }

    @Override
    public String toString() {
        return getOrderDetails();
    }
}
