// File: ShoppingCart.java
import java.util.*;

/**
 * ShoppingCart with access control.
 * - items stored as List<Object> to accept different product types
 * - totalAmount and itemCount are maintained
 */
public class ShoppingCart {
    private final String cartId;
    private final String customerId;
    private final List<Object> items = new ArrayList<>();
    private double totalAmount = 0.0;
    private int itemCount = 0;

    public ShoppingCart(String cartId, String customerId) {
        if (cartId == null || customerId == null) throw new IllegalArgumentException("cartId and customerId required");
        this.cartId = cartId;
        this.customerId = customerId;
    }

    public String getCartId() { return cartId; }
    public String getCustomerId() { return customerId; }
    public double getTotalAmount() { return totalAmount; }
    public int getItemCount() { return itemCount; }

    /**
     * Adds product to cart using instanceof checks for validation.
     * Accepts Product objects. Returns true if added.
     */
    public boolean addItem(Object product, int quantity) {
        if (product == null || quantity <= 0) return false;

        // For demo we only accept Product instances; but design allows other product types
        if (product instanceof Product) {
            Product p = (Product) product;
            for (int i = 0; i < quantity; i++) {
                items.add(p);
            }
            itemCount += quantity;
            totalAmount += p.getBasePrice() * quantity;
            // Apply discounts immediately (recalculate)
            double discount = calculateDiscount();
            totalAmount -= discount;
            return true;
        } else {
            // Unknown product type - reject
            return false;
        }
    }

    // Private internal pricing logic
    private double calculateDiscount() {
        // Example: if 5 or more items -> 5% discount, if premium item count >=10 -> 10%
        if (itemCount >= 10) return totalAmount * 0.10;
        if (itemCount >= 5) return totalAmount * 0.05;
        return 0.0;
    }

    // Package-private: used by checkout process inside the package
    String getCartSummary() {
        return String.format("Cart[id=%s,customer=%s,items=%d,total=%.2f]", cartId, customerId, itemCount, totalAmount);
    }

    @Override
    public String toString() {
        return getCartSummary();
    }
}
