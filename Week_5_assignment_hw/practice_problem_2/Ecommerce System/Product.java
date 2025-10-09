// File: Product.java
import java.util.*;

/**
 * Immutable Product class
 */
public final class Product {
    private final String productId;
    private final String name;
    private final String category;
    private final String manufacturer;
    private final double basePrice;
    private final double weight;
    private final String[] features;
    private final Map<String, String> specifications;

    // Private constructor
    private Product(String productId,
                    String name,
                    String category,
                    String manufacturer,
                    double basePrice,
                    double weight,
                    String[] features,
                    Map<String, String> specifications) {
        if (productId == null || name == null || category == null || manufacturer == null) {
            throw new IllegalArgumentException("productId, name, category and manufacturer cannot be null");
        }
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.manufacturer = manufacturer;
        this.basePrice = basePrice;
        this.weight = weight;
        this.features = (features == null) ? new String[0] : features.clone(); // defensive copy
        this.specifications = (specifications == null) ? Collections.emptyMap() : new HashMap<>(specifications); // defensive copy
    }

    // Factory methods
    public static Product createElectronics(String productId, String name, String manufacturer,
                                            double price, double weight, String[] features,
                                            Map<String, String> specifications) {
        return new Product(productId, name, "Electronics", manufacturer, price, weight, features, specifications);
    }

    public static Product createClothing(String productId, String name, String manufacturer,
                                         double price, double weight, String[] features) {
        return new Product(productId, name, "Clothing", manufacturer, price, weight, features, null);
    }

    public static Product createBooks(String productId, String name, String manufacturer,
                                      double price, double weight, String[] features) {
        return new Product(productId, name, "Books", manufacturer, price, weight, features, null);
    }

    // Getters (defensive copies where necessary)
    public String getProductId() { return productId; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getManufacturer() { return manufacturer; }
    public double getBasePrice() { return basePrice; }
    public double getWeight() { return weight; }
    public String[] getFeatures() { return features.clone(); }
    public Map<String, String> getSpecifications() { return new HashMap<>(specifications); }

    // Final method - cannot be overridden
    public final double calculateTax(String region) {
        if (region == null) region = "";
        switch (region.trim().toLowerCase()) {
            case "us": return basePrice * 0.07;
            case "eu": return basePrice * 0.20;
            case "in": return basePrice * 0.18;
            default: return basePrice * 0.10;
        }
    }

    @Override
    public String toString() {
        return String.format("Product[id=%s,name=%s,cat=%s,manufacturer=%s,price=%.2f]",
                productId, name, category, manufacturer, basePrice);
    }
}
