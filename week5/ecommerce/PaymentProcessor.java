package week5.ecommerce;

import java.util.Objects;

public class PaymentProcessor {
    private final String processorId;
    private final String securityKey; // Should be securely handled in a real system

    public PaymentProcessor(String processorId, String securityKey) {
        if (processorId == null || processorId.trim().isEmpty()) {
            throw new IllegalArgumentException("Processor ID cannot be null or empty.");
        }
        if (securityKey == null || securityKey.trim().isEmpty()) {
            throw new IllegalArgumentException("Security key cannot be null or empty.");
        }
        this.processorId = processorId;
        this.securityKey = securityKey;
    }

    public String getProcessorId() {
        return processorId;
    }

    // In a real system, securityKey would not be exposed directly
    // public String getSecurityKey() { return securityKey; }

    public boolean processPayment(Order order, String paymentMethod, double amount) {
        if (order == null) {
            System.out.println("Payment failed: Order is null.");
            return false;
        }
        if (paymentMethod == null || paymentMethod.trim().isEmpty()) {
            System.out.println("Payment failed: Payment method not specified.");
            return false;
        }
        if (amount <= 0) {
            System.out.println("Payment failed: Payment amount must be positive.");
            return false;
        }
        if (amount != order.getTotalAmount()) {
            System.out.println("Payment failed: Amount mismatch. Expected " + order.getTotalAmount() + ", got " + amount);
            return false;
        }

        // Simulate payment processing
        System.out.println("Processing payment for Order ID: " + order.getOrderId() +
                           " with amount $" + String.format("%.2f", amount) +
                           " using " + paymentMethod + " via processor " + processorId);
        // In a real system, this would involve integration with payment gateways
        return true;
    }

    @Override
    public String toString() {
        return "PaymentProcessor{" +
               "processorId='" + processorId + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentProcessor that = (PaymentProcessor) o;
        return Objects.equals(processorId, that.processorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(processorId);
    }
}
