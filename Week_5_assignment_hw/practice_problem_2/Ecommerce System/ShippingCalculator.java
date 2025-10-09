// File: ShippingCalculator.java
import java.util.Map;

/**
 * ShippingCalculator with shippingRates map
 */
public class ShippingCalculator {
    private final Map<String, Double> shippingRates;

    public ShippingCalculator(Map<String, Double> shippingRates) {
        if (shippingRates == null) throw new IllegalArgumentException("rates required");
        this.shippingRates = Map.copyOf(shippingRates); // immutable copy
    }

    public double calculateShipping(String region, double weight) {
        if (region == null) region = "default";
        double ratePerKg = shippingRates.getOrDefault(region.toLowerCase(), shippingRates.getOrDefault("default", 10.0));
        return ratePerKg * Math.max(0.0, weight);
    }
}
