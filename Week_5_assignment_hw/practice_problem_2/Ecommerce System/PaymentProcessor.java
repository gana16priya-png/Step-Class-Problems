// File: PaymentProcessor.java
/**
 * PaymentProcessor handles payment logic. Key fields are private final
 */
public class PaymentProcessor {
    private final String processorId;
    private final String securityKey; // should be securely stored in real apps

    public PaymentProcessor(String processorId, String securityKey) {
        if (processorId == null || securityKey == null) throw new IllegalArgumentException("processorId and securityKey required");
        this.processorId = processorId;
        this.securityKey = securityKey;
    }

    public String getProcessorId() { return processorId; }

    /**
     * Simple mock processPayment method.
     * In real systems this would integrate with external payments and secure keys.
     */
    public boolean processPayment(double amount) {
        if (amount <= 0) return false;
        // stub: accept payments under some mock rule
        System.out.printf("PaymentProcessor[%s]: processing payment of %.2f\n", processorId, amount);
        return true;
    }
}
