package week5.ecommerce;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Order {
    private final String orderId;
    private final LocalDateTime orderTime;
    private final String customerId;
    private final List<Object> orderedItems; // Stores products from the shopping cart
    private final double totalAmount;

    public Order(String orderId, String customerId, List<Object> orderedItems, double totalAmount) {
        if (orderId == null || orderId.trim().isEmpty()) {
            throw new IllegalArgumentException("Order ID cannot be null or empty.");
        }
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be null or empty.");
        }
        if (orderedItems == null || orderedItems.isEmpty()) {
            throw new IllegalArgumentException("Ordered items cannot be null or empty.");
        }
        if (totalAmount < 0) {
            throw new IllegalArgumentException("Total amount cannot be negative.");
        }

        this.orderId = orderId;
        this.orderTime = LocalDateTime.now();
        this.customerId = customerId;
        this.orderedItems = Collections.unmodifiableList(orderedItems); // Immutable list of items
        this.totalAmount = totalAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<Object> getOrderedItems() {
        return orderedItems; // Already unmodifiable
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Order{" +
               "orderId='" + orderId + '\'' +
               ", orderTime=" + orderTime +
               ", customerId='" + customerId + '\'' +
               ", totalAmount=" + totalAmount +
               ", itemCount=" + orderedItems.size() +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
}
