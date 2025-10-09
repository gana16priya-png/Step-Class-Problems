import java.util.*;

class PersonalAccount {
    // Instance variables (unique per object)
    private String accountHolderName;
    private String accountNumber;
    private double currentBalance;
    private double totalIncome;
    private double totalExpenses;

    // Static variables (shared by all accounts)
    private static int totalAccounts = 0;
    private static String bankName = "Default Bank";

    // Constructor
    public PersonalAccount(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = generateAccountNumber();
        this.currentBalance = 0.0;
        this.totalIncome = 0.0;
        this.totalExpenses = 0.0;
        totalAccounts++;
    }

    // Instance Methods
    public void addIncome(double amount, String description) {
        currentBalance += amount;
        totalIncome += amount;
        System.out.println("Income added: " + amount + " (" + description + ")");
    }

    public void addExpense(double amount, String description) {
        if (amount <= currentBalance) {
            currentBalance -= amount;
            totalExpenses += amount;
            System.out.println("Expense added: " + amount + " (" + description + ")");
        } else {
            System.out.println("Insufficient balance for expense: " + description);
        }
    }

    public double calculateSavings() {
        return totalIncome - totalExpenses;
    }

    public void displayAccountSummary() {
        System.out.println("\n--- Account Summary ---");
        System.out.println("Bank Name      : " + bankName);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Current Balance: " + currentBalance);
        System.out.println("Total Income   : " + totalIncome);
        System.out.println("Total Expenses : " + totalExpenses);
        System.out.println("Savings        : " + calculateSavings());
    }

    // Static Methods
    public static void setBankName(String name) {
        bankName = name;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    public static String generateAccountNumber() {
        return "ACC" + (1000 + totalAccounts + 1);
    }
}

// Main Class
public class FinanceManager {
    public static void main(String[] args) {
        // Set bank name (static variable)
        PersonalAccount.setBankName("Smart Bank");

        // Create accounts
        PersonalAccount acc1 = new PersonalAccount("Amara");
        PersonalAccount acc2 = new PersonalAccount("Berkley");
        PersonalAccount acc3 = new PersonalAccount("Charlie");

        // Perform transactions
        acc1.addIncome(5000, "Salary");
        acc1.addExpense(2000, "Rent");

        acc2.addIncome(3000, "Freelance Work");
        acc2.addExpense(500, "Groceries");

        acc3.addIncome(8000, "Business Profit");
        acc3.addExpense(2500, "Travel");

        // Display account summaries
        acc1.displayAccountSummary();
        acc2.displayAccountSummary();
        acc3.displayAccountSummary();

        // Show total accounts
        System.out.println("\nTotal Accounts in Bank: " + PersonalAccount.getTotalAccounts());
    }
}
