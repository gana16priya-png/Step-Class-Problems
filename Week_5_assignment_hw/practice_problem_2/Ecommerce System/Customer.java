// File: Customer.java
/**
 * Customer with privacy tiers and controlled access
 */
public class Customer {
    private final String customerId;
    private final String email; // permanent
    private String name; // modifiable
    private String phoneNumber; // modifiable
    private String preferredLanguage; // modifiable
    private final String accountCreationDate; // immutable history

    public Customer(String customerId, String email, String accountCreationDate) {
        if (customerId == null || email == null || accountCreationDate == null) {
            throw new IllegalArgumentException("customerId, email and accountCreationDate are required");
        }
        this.customerId = customerId;
        this.email = email;
        this.accountCreationDate = accountCreationDate;
    }

    // Public getters for permanent/immutable data
    public String getCustomerId() { return customerId; }
    public String getEmail() { return email; }
    public String getAccountCreationDate() { return accountCreationDate; }

    // Modifiable personal data setters & getters
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getPhoneNumber() { return phoneNumber; }

    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }
    public String getPreferredLanguage() { return preferredLanguage; }

    // Package-private method - internal business use only (no modifier)
    int getCreditRating() {
        // stub: in real systems this would call an internal service
        // keep package access only to restrict usage outside the package
        return 720;
    }

    // Public safe profile
    public String getPublicProfile() {
        String displayName = (name == null || name.isBlank()) ? "Anonymous" : name;
        String lang = (preferredLanguage == null) ? "N/A" : preferredLanguage;
        return String.format("Customer[id=%s,name=%s,language=%s]", customerId, displayName, lang);
    }

    @Override
    public String toString() {
        return "Customer{" + "id='" + customerId + '\'' + ", email='" + email + '\'' + '}';
    }
}
