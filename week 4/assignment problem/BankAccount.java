import java.util.Random;

class BankAccount {
    String accountHolder;
    int accountNumber;
    double balance;

    // 1. Default constructor → balance = 0
    BankAccount() {
        this.accountHolder = "Unknown";
        this.accountNumber = generateAccountNumber();
        this.balance = 0.0;
    }

    // 2. Constructor with name → assigns random account number
    BankAccount(String accountHolder) {
        this.accountHolder = accountHolder;
        this.accountNumber = generateAccountNumber();
        this.balance = 0.0;
    }

    // 3. Constructor with name and initial balance
    BankAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = generateAccountNumber();
        this.balance = balance;
    }

    // Helper to generate random account numbers
    private int generateAccountNumber() {
        Random rand = new Random();
        return 10000 + rand.nextInt(90000);  // 5-digit random number
    }

    // ---- Methods ----
    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Deposited ₹" + amount + " to " + accountHolder);
        } else {
            System.out.println("⚠️ Invalid deposit amount!");
        }
    }

    void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("✅ Withdrawn ₹" + amount + " from " + accountHolder);
        } else {
            System.out.println("⚠️ Insufficient balance or invalid amount!");
        }
    }

    void displayAccount() {
        System.out.println("🏦 Bank Account Details:");
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: ₹" + balance);
        System.out.println("---------------------------");
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        // Create accounts using different constructors
        BankAccount acc1 = new BankAccount();
        BankAccount acc2 = new BankAccount("Alice");
        BankAccount acc3 = new BankAccount("Bob", 5000.0);

        // Perform operations
        acc1.deposit(1000);
        acc2.deposit(2000);
        acc2.withdraw(500);
        acc3.withdraw(6000);  // should show insufficient balance

        // Display accounts
        acc1.displayAccount();
        acc2.displayAccount();
        acc3.displayAccount();
    }
}
