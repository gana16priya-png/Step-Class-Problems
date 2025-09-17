package banking;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Transaction {
    private final String transactionId;
    private final LocalDateTime timestamp;
    private final double amount;
    private final String transactionType;
    private final String description;
    private final String fromAccount;
    private final String toAccount;
    private final Map<String, String> metadata;
    
    // Transaction types
    public static final String DEPOSIT = "DEPOSIT";
    public static final String WITHDRAWAL = "WITHDRAWAL";
    public static final String TRANSFER = "TRANSFER";
    public static final String FEE = "FEE";
    public static final String INTEREST = "INTEREST";
    
    public Transaction(String transactionId, double amount, String transactionType, 
                      String description, String fromAccount, String toAccount, 
                      Map<String, String> metadata) {
        this.transactionId = Objects.requireNonNull(transactionId, "Transaction ID cannot be null");
        this.timestamp = LocalDateTime.now();
        this.amount = amount;
        this.transactionType = Objects.requireNonNull(transactionType, "Transaction type cannot be null");
        this.description = description;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.metadata = metadata != null ? 
            Collections.unmodifiableMap(new HashMap<>(metadata)) : 
            Collections.emptyMap();
    }
    
    // Constructor for simple transactions (deposit/withdrawal)
    public Transaction(String transactionId, double amount, String transactionType, 
                      String description, String account) {
        this(transactionId, amount, transactionType, description, 
             transactionType.equals(DEPOSIT) ? null : account,
             transactionType.equals(DEPOSIT) ? account : null,
             null);
    }
    
    // Getters with defensive copying where needed
    public String getTransactionId() { return transactionId; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public double getAmount() { return amount; }
    public String getTransactionType() { return transactionType; }
    public String getDescription() { return description; }
    public String getFromAccount() { return fromAccount; }
    public String getToAccount() { return toAccount; }
    public Map<String, String> getMetadata() { return metadata; }
    
    // Final method for financial integrity - cannot be overridden
    public final boolean isValid() {
        if (transactionId == null || transactionId.trim().isEmpty()) {
            return false;
        }
        if (amount <= 0 && !transactionType.equals(FEE)) {
            return false;
        }
        if (transactionType == null) {
            return false;
        }
        
        // Validate account references based on transaction type
        switch (transactionType) {
            case DEPOSIT:
                return toAccount != null;
            case WITHDRAWAL:
            case FEE:
                return fromAccount != null;
            case TRANSFER:
                return fromAccount != null && toAccount != null && !fromAccount.equals(toAccount);
            case INTEREST:
                return toAccount != null;
            default:
                return false;
        }
    }
    
    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + transactionId + '\'' +
                ", timestamp=" + timestamp +
                ", amount=" + amount +
                ", type='" + transactionType + '\'' +
                ", from='" + (fromAccount != null ? maskAccount(fromAccount) : "N/A") + '\'' +
                ", to='" + (toAccount != null ? maskAccount(toAccount) : "N/A") + '\'' +
                '}';
    }
    
    private String maskAccount(String accountNumber) {
        if (accountNumber == null || accountNumber.length() < 4) {
            return "****";
        }
        return "****" + accountNumber.substring(accountNumber.length() - 4);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transactionId, that.transactionId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(transactionId);
    }
}