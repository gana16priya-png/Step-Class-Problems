package week5.ecommerce;

import java.time.LocalDate;
import java.util.Objects;

public class Customer {
    private final String customerId;
    private final String email; // Permanent account info
    private final LocalDate accountCreationDate; // Immutable account history

    private String name;
    private String phoneNumber;
    private String preferredLanguage; // Modifiable personal data

    // Constructor for registered customer (full account access)
    public Customer(String customerId, String email, String name, String phoneNumber, String preferredLanguage) {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be null or empty.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        this.customerId = customerId;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.preferredLanguage = preferredLanguage;
        this.accountCreationDate = LocalDate.now(); // Set creation date upon registration
    }

    // Constructor for guest checkout (minimal customer info)
    public Customer(String email, String name) {
        this("GUEST-" + System.nanoTime(), email, name, null, "en"); // Generate temporary ID for guest
    }

    // Constructor for Premium member (special pricing and features) - chaining to full constructor
    public Customer(String customerId, String email, String name, String phoneNumber, String preferredLanguage, boolean isPremium) {
        this(customerId, email, name, phoneNumber, preferredLanguage);
        if (!isPremium) {
            throw new IllegalArgumentException("This constructor is for premium members only.");
        }
        // Additional premium member specific logic could go here
    }

    // Constructor for Corporate account (bulk ordering with company validation) - chaining to full constructor
    public Customer(String customerId, String email, String name, String phoneNumber, String preferredLanguage, String companyName, String taxId) {
        this(customerId, email, name, phoneNumber, preferredLanguage);
        if (companyName == null || companyName.trim().isEmpty() || taxId == null || taxId.trim().isEmpty()) {
            throw new IllegalArgumentException("Company name and tax ID are required for corporate accounts.");
        }
        // Additional corporate account specific logic and validation
        System.out.println("Corporate account for " + companyName + " with Tax ID: " + taxId + " created.");
    }


    // Package-private method for internal business operations
    String getCreditRating() {
        // In a real system, this would involve complex logic to calculate credit rating
        return "Good";
    }

    // Public method for safe customer information for reviews/ratings
    public String getPublicProfile() {
        return "Customer Name: " + name + ", Member Since: " + accountCreationDate;
    }

    // Getters for final fields
    public String getCustomerId() {
        return customerId;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }

    // Getters and setters for modifiable fields
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    @Override
    public String toString() {
        return "Customer{" +
               "customerId='" + customerId + '\'' +
               ", email='" + email + '\'' +
               ", name='" + name + '\'' +
               ", accountCreationDate=" + accountCreationDate +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }
}
