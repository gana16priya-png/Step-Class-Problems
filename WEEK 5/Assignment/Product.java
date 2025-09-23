import java.time.LocalDateTime;
import java.util.*;

// ================== IMMUTABLE PRODUCT ================== //
final class Product {
    private final String productId, name, category, manufacturer;
    private final double basePrice, weight;
    private final String[] features;
    private final Map<String, String> specifications;

    // Private constructor
    private Product(String productId, String name, String category,
                   String manufacturer, double basePrice, double weight,
                   String[] features, Map<String, String> specifications) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.manufacturer = manufacturer;
        this.basePrice = basePrice;
        this.weight = weight;
        this.features = features.clone(); // defensive copy
        this.specifications = new HashMap<>(specifications);
    }

    // Factory Methods
    public static Product createElectronics(String name, String manufacturer, double basePrice) {
        return new Product(UUID.randomUUID().toString(), name, "Electronics",
                manufacturer, basePrice, 1.5,
                new String[]{"1-year warranty", "Rechargeable"},
                Map.of("Power", "220V", "Warranty", "1 Year"));
    }

    public static Product createClothing(String name, String manufacturer, double basePrice) {
        return new Product(UUID.randomUUID().toString(), name, "Clothing",
                manufacturer, basePrice, 0.5,
                new String[]{"Washable", "Comfort Fit"},
                Map.of("Material", "Cotton", "Size", "M"));
    }

    public static Product createBooks(String name, String author, double basePrice) {
        return new Product(UUID.randomUUID().toString(), name, "Books",
                author, basePrice, 0.3,
                new String[]{"Paperback"},
                Map.of("Author", author, "Language", "English"));
    }

    // Getters with defensive copying
    public String getProductId() { return productId; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getManufacturer() { return manufacturer; }
    public double getBasePrice() { return basePrice; }
    public double getWeight() { return weight; }
    public String[] getFeatures() { return features.clone(); }
    public Map<String, String> getSpecifications() { return new HashMap<>(specifications); }

    // Final tax calculation
    public final double calculateTax(String region) {
        return switch (region.toUpperCase()) {
            case "US" -> basePrice * 0.07;
            case "EU" -> basePrice * 0.20;
            case "IN" -> basePrice * 0.18;
            default -> basePrice * 0.10;
        };
    }

    @Override
    public String toString() {
        return "Product[" + name + ", Category=" + category + ", Price=$" + basePrice + "]";
    }
}

// ================== CUSTOMER CLASS ================== //
class Customer {
    private final String customerId, email, accountCreationDate;
    private String name, phoneNumber, preferredLanguage;

    // Constructor chaining
    public Customer(String email) {
        this(UUID.randomUUID().toString(), "Guest", email, "N/A", "EN", LocalDateTime.now().toString());
    }

    public Customer(String name, String email, String phone, String lang) {
        this(UUID.randomUUID().toString(), name, email, phone, lang, LocalDateTime.now().toString());
    }

    private Customer(String id, String name, String email, String phone, String lang, String created) {
        this.customerId = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phone;
        this.preferredLanguage = lang;
        this.accountCreationDate = created;
    }

    // Package-private for internal business
    int getCreditRating() {
        return new Random().nextInt(800) + 100; // 100 - 900 score
    }

    // Public safe profile
    public Map<String, String> getPublicProfile() {
        return Map.of("Name", name, "Language", preferredLanguage);
    }

    public String getCustomerId() { return customerId; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getPreferredLanguage() { return preferredLanguage; }
    public String getAccountCreationDate() { return accountCreationDate; }

    public void setName(String name) { this.name = name; }
    public void setPhoneNumber(String phone) { this.phoneNumber = phone; }
    public void setPreferredLanguage(String lang) { this.preferredLanguage = lang; }

    @Override
    public String toString() {
        return "Customer[" + name + ", Email=" + email + "]";
    }
}

// ================== SHOPPING CART ================== //
class ShoppingCart {
    private final String cartId, customerId;
    private final List<Object> items = new ArrayList<>();
    private double totalAmount;
    private int itemCount;

    public ShoppingCart(String customerId) {
        this.cartId = UUID.randomUUID().toString();
        this.customerId = customerId;
    }

    public boolean addItem(Object product, int quantity) {
        if (product instanceof Product p && quantity > 0) {
            for (int i = 0; i < quantity; i++) {
                items.add(p);
            }
            itemCount += quantity;
            totalAmount += (p.getBasePrice() * quantity);
            return true;
        }
        return false;
    }

    private double calculateDiscount() {
        return itemCount > 5 ? totalAmount * 0.1 : 0.0;
    }

    // Package-private
    Map<String, Object> getCartSummary() {
        return Map.of("CartId", cartId, "CustomerId", customerId,
                "Items", itemCount, "Total", totalAmount - calculateDiscount());
    }

    @Override
    public String toString() {
        return "ShoppingCart[" + cartId + ", Items=" + itemCount + ", Total=$" + totalAmount + "]";
    }
}

// ================== ORDER PROCESSING ================== //
class Order {
    private final String orderId;
    private final LocalDateTime orderTime;
    private final ShoppingCart cart;

    public Order(ShoppingCart cart) {
        this.orderId = UUID.randomUUID().toString();
        this.orderTime = LocalDateTime.now();
        this.cart = cart;
    }

    public String getOrderId() { return orderId; }
    public LocalDateTime getOrderTime() { return orderTime; }
    public ShoppingCart getCart() { return cart; }

    @Override
    public String toString() {
        return "Order[" + orderId + ", Time=" + orderTime + "]";
    }
}

class PaymentProcessor {
    private final String processorId, securityKey;

    public PaymentProcessor(String key) {
        this.processorId = UUID.randomUUID().toString();
        this.securityKey = key;
    }

    public boolean processPayment(Order order, double amount) {
        System.out.println("Processing payment of $" + amount + " for order " + order.getOrderId());
        return true;
    }
}

class ShippingCalculator {
    private final Map<String, Double> shippingRates = Map.of(
            "US", 10.0, "EU", 20.0, "IN", 5.0, "DEFAULT", 15.0);

    public double calculateShipping(String region, double weight) {
        return shippingRates.getOrDefault(region.toUpperCase(), shippingRates.get("DEFAULT")) * weight;
    }
}

// ================== ECOMMERCE SYSTEM ================== //
final class ECommerceSystem {
    private static final Map<String, Object> productCatalog = new HashMap<>();

    public static void addProductToCatalog(Product p) {
        productCatalog.put(p.getProductId(), p);
    }

    public static boolean processOrder(Order order, Customer customer) {
        System.out.println("Processing order for customer: " + customer);
        return true;
    }

    public static void showCatalog() {
        System.out.println("📦 Product Catalog:");
        productCatalog.values().forEach(System.out::println);
    }
}

// ================== MAIN DEMO ================== //
public class ECommerceDemo {
    public static void main(String[] args) {
        // Create immutable products
        Product laptop = Product.createElectronics("Gaming Laptop", "ASUS", 1200);
        Product tshirt = Product.createClothing("Cool T-Shirt", "H&M", 25);
        Product book = Product.createBooks("Java Basics", "James Gosling", 40);

        // Add to catalog
        ECommerceSystem.addProductToCatalog(laptop);
        ECommerceSystem.addProductToCatalog(tshirt);
        ECommerceSystem.addProductToCatalog(book);

        // Show catalog
        ECommerceSystem.showCatalog();

        // Create customers
        Customer guest = new Customer("guest@example.com");
        Customer regUser = new Customer("Alice", "alice@example.com", "1234567890", "EN");

        // Shopping cart
        ShoppingCart cart = new ShoppingCart(regUser.getCustomerId());
        cart.addItem(laptop, 1);
        cart.addItem(tshirt, 3);
        cart.addItem(book, 2);
        System.out.println(cart);

        // Order
        Order order = new Order(cart);
        PaymentProcessor processor = new PaymentProcessor("SEC123");
        ShippingCalculator shipping = new ShippingCalculator();

        processor.processPayment(order, 1350);
        double shippingCost = shipping.calculateShipping("IN", laptop.getWeight() + tshirt.getWeight() + book.getWeight());
        System.out.println("Shipping Cost: $" + shippingCost);

        // Final processing
        ECommerceSystem.processOrder(order, regUser);
    }
}
