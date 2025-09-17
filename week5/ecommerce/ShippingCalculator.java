package week5.ecommerce;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ShippingCalculator {
    private final Map<String, Double> shippingRates; // Region to rate mapping

    public ShippingCalculator(Map<String, Double> shippingRates) {
        if (shippingRates == null || shippingRates.isEmpty()) {
            throw new IllegalArgumentException("Shipping rates cannot be null or empty.");
        }
        // Defensive copy and unmodifiable map
        this.shippingRates = Collections.unmodifiableMap(new HashMap<>(shippingRates));
    }

    public Map<String, Double> getShippingRates() {
        return shippingRates; // Already unmodifiable
    }

    public double calculateShippingCost(String region, double weight) {
        if (region == null || region.trim().isEmpty()) {
            throw new IllegalArgumentException("Region cannot be null or empty.");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive.");
        }

        Double rate = shippingRates.get(region.toUpperCase());
        if (rate == null) {
            System.out.println("Warning: No specific shipping rate found for region " + region + ". Using default rate.");
            rate = shippingRates.get("DEFAULT"); // Assume a default rate exists
            if (rate == null) {
                throw new IllegalArgumentException("No shipping rate found for region " + region + " and no default rate available.");
            }
        }
        return rate * weight;
    }

    @Override
    public String toString() {
        return "ShippingCalculator{" +
               "shippingRates=" + shippingRates +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingCalculator that = (ShippingCalculator) o;
        return Objects.equals(shippingRates, that.shippingRates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shippingRates);
    }
}
