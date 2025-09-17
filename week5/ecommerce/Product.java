package week5.ecommerce;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Product {
    private final String productId;
    private final String name;
    private final String category;
    private final String manufacturer;
    private final double basePrice;
    private final double weight;
    private final String[] features;
    private final Map<String, String> specifications;

    private Product(String productId, String name, String category, String manufacturer, double basePrice, double weight, String[] features, Map<String, String> specifications) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty.");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Product category cannot be null or empty.");
        }
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Base price must be positive.");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive.");
        }

        this.productId = productId;
        this.name = name;
        this.category = category;
        this.manufacturer = manufacturer;
        this.basePrice = basePrice;
        this.weight = weight;
        this.features = (features != null) ? Arrays.copyOf(features, features.length) : new String[0];
        this.specifications = (specifications != null) ? Collections.unmodifiableMap(new HashMap<>(specifications)) : Collections.emptyMap();
    }

    // Factory methods
    public static Product createElectronics(String productId, String name, String manufacturer, double basePrice, double weight, String[] features, Map<String, String> specifications) {
        return new Product(productId, name, "Electronics", manufacturer, basePrice, weight, features, specifications);
    }

    public static Product createClothing(String productId, String name, String manufacturer, double basePrice, double weight, String[] features, Map<String, String> specifications) {
        return new Product(productId, name, "Clothing", manufacturer, basePrice, weight, features, specifications);
    }

    public static Product createBooks(String productId, String name, String manufacturer, double basePrice, double weight, String[] features, Map<String, String> specifications) {
        return new Product(productId, name, "Books", manufacturer, basePrice, weight, features, specifications);
    }

    // Getters with defensive copying for collections
    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getWeight() {
        return weight;
    }

    public String[] getFeatures() {
        return Arrays.copyOf(features, features.length); // Defensive copy
    }

    public Map<String, String> getSpecifications() {
        return specifications; // Already unmodifiable
    }

    public final double calculateTax(String region) {
        // Simplified tax calculation for business consistency
        if ("EU".equalsIgnoreCase(region)) {
            return basePrice * 0.20; // 20% VAT
        } else if ("US".equalsIgnoreCase(region)) {
            return basePrice * 0.07; // 7% Sales Tax
        }
        return 0.0;
    }

    @Override
    public String toString() {
        return "Product{" +
               "productId='" + productId + '\'' +
               ", name='" + name + '\'' +
               ", category='" + category + '\'' +
               ", basePrice=" + basePrice +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
