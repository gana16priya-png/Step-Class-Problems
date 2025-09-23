public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private static int totalAccounts = 0;
    private static int accountCounter = 1;

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

    // Static method to generate unique account number
    public static String generateAccountNumber() {
        String accNum = String.format("ACC%03d", accountCounter);
        accountCounter++;
        return accNum;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds.");
            return;
        }
        balance -= amount;
        System.out.println("Withdrawn: " + amount);
    }

    // Check balance
    public double checkBalance() {
        return balance;
    }

    // Static method to get total accounts
    public static int getTotalAccounts() {
        return totalAccounts;
    }

    // Display account info
    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("---------------------------");
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        // Create array for 3 accounts
        BankAccount[] accounts = new BankAccount[3];
        accounts[0] = new BankAccount("Alice", 1000);
        accounts[1] = new BankAccount("Bob", 500);
        accounts[2] = new BankAccount("Charlie", 1500);

        // Perform transactions
        accounts[0].deposit(500);
        accounts[1].withdraw(200);
        accounts[2].withdraw(2000); // Should show insufficient funds

        // Display account info
        for (int i = 0; i < accounts.length; i++) {
            accounts[i].displayAccountInfo();
        }

        // Show static vs instance variable
        System.out.println("Total Accounts Created: " + BankAccount.getTotalAccounts());
        System.out.println("Account 1 Holder Name (instance variable): " + accounts[0].accountHolderName);
    }
}