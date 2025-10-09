class BankAccount {
    // Private instance variables
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    // Static variables
    private static int totalAccounts = 0;
    private static int accountCounter = 0;

    // Constructor
    public BankAccount(String accountHolderName, double initialDeposit) {
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative.");
        }
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.accountNumber = generateAccountNumber();
        totalAccounts++;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive!");
            return;
        }
        balance += amount;
        System.out.println("Deposited: " + amount + " | New Balance: " + balance);
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive!");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds! Withdrawal failed.");
            return;
        }
        balance -= amount;
        System.out.println("Withdrawn: " + amount + " | Remaining Balance: " + balance);
    }

    // Check balance
    public double checkBalance() {
        return balance;
    }

    // Display account info
    public void displayAccountInfo() {
        System.out.println("--------------------------------------------------");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.println("Balance        : " + balance);
        System.out.println("--------------------------------------------------");
    }

    // Static method to get total accounts
    public static int getTotalAccounts() {
        return totalAccounts;
    }

    // Static method to generate account number
    private static String generateAccountNumber() {
        accountCounter++;
        return String.format("ACC%03d", accountCounter);
    }
}

// Main class
public class BankAccountManagement {
    public static void main(String[] args) {
        // Array of BankAccount (size fixed for demonstration)
        BankAccount[] accounts = new BankAccount[5];
        int count = 0;

        // Creating accounts
        accounts[count++] = new BankAccount("Akhira", 10000);
        accounts[count++] = new BankAccount("Kiara", 5000);
        accounts[count++] = new BankAccount("Arjun", 15000);

        // Display account details
        for (int i = 0; i < count; i++) {
            accounts[i].displayAccountInfo();
        }

        // Perform some transactions
        accounts[0].deposit(5000);      // Akhira deposits
        accounts[1].withdraw(2000);     // Kiara withdraws
        accounts[2].withdraw(16000);    // Arjun tries to withdraw more than balance

        // Show balances
        System.out.println("Akhira Balance: " + accounts[0].checkBalance());
        System.out.println("Kiara Balance: " + accounts[1].checkBalance());
        System.out.println("Arjun Balance: " + accounts[2].checkBalance());

        // Show static vs instance variable effect
        System.out.println("\nTotal Accounts Created (Static): " + BankAccount.getTotalAccounts());
    }
}
