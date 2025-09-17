package week5.ecommerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ShoppingCart {
    private final String cartId;
    private final String customerId; // Cart ownership
    private final List<Object> items; // stores different product types
    private double totalAmount; // Calculated values
    private int itemCount; // Calculated values

    public ShoppingCart(String cartId, String customerId) {
        if (cartId == null || cartId.trim().isEmpty()) {
            throw new IllegalArgumentException("Cart ID cannot be null or empty.");
        }
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be null or empty.");
        }
        this.cartId = cartId;
        this.customerId = customerId;
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
        this.itemCount = 0;
    }

    public boolean addItem(Object product, int quantity) {
        if (!(product instanceof Product)) {
            System.out.println("Failed to add item: Invalid product object.");
            return false;
        }
        if (quantity <= 0) {
            System.out.println("Failed to add item: Quantity must be positive.");
            return false;
        }

        Product p = (Product) product;
        for (int i = 0; i < quantity; i++) {
            items.add(p);
        }
        this.totalAmount += p.getBasePrice() * quantity;
        this.itemCount += quantity;
        System.out.println(quantity + " x " + p.getName() + " added to cart " + cartId);
        return true;
    }

    private double calculateDiscount() {
        // Internal pricing logic: simplified discount calculation
        if (totalAmount > 500) {
            return totalAmount * 0.10; // 10% discount for orders over $500
        }
        return 0.0;
    }

    // Package-private method for checkout process
    String getCartSummary() {
        double discount = calculateDiscount();
        double finalAmount = totalAmount - discount;
        return "Cart ID: " + cartId + ", Customer ID: " + customerId +
               ", Total Items: " + itemCount + ", Subtotal: $" + String.format("%.2f", totalAmount) +
               ", Discount: $" + String.format("%.2f", discount) + ", Final Amount: $" + String.format("%.2f", finalAmount);
    }

    // Getters
    public String getCartId() {
        return cartId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<Object> getItems() {
        return Collections.unmodifiableList(items); // Defensive copy
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getItemCount() {
        return itemCount;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
               "cartId='" + cartId + '\'' +
               ", customerId='" + customerId + '\'' +
               ", itemCount=" + itemCount +
               ", totalAmount=" + totalAmount +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return Objects.equals(cartId, that.cartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId);
    }
}
