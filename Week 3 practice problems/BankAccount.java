public class BankAccount {
    // ------------------ Static variables (shared by all accounts) ------------------
    private static String bankName;
    private static int totalAccounts = 0;
    private static double interestRate;

    // ------------------ Instance variables (unique per account) ------------------
    private String accountNumber;
    private String accountHolder;
    private double balance;

    // ------------------ Constructor ------------------
    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        totalAccounts++; // increment total accounts whenever new account is created
    }

    // ------------------ Static methods ------------------
    public static void setBankName(String name) {
        bankName = name;
    }

    public static void setInterestRate(double rate) {
        interestRate = rate;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    public static void displayBankInfo() {
        System.out.println("Bank Name: " + bankName);
        System.out.println("Total Accounts: " + totalAccounts);
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("----------------------------------");
    }

    // ------------------ Instance methods ------------------
    public void deposit(double amount) {
        balance += amount;
        System.out.println(accountHolder + " deposited " + amount + ". New Balance: " + balance);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(accountHolder + " withdrew " + amount + ". New Balance: " + balance);
        } else {
            System.out.println(accountHolder + " - Insufficient balance!");
        }
    }

    public void calculateInterest() {
        double interest = balance * (interestRate / 100);
        System.out.println(accountHolder + " earned interest: " + interest);
    }

    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: " + balance);
        System.out.println("----------------------------------");
    }

    // ------------------ Main Method ------------------
    public static void main(String[] args) {
        // Set static members (shared for all accounts)
        BankAccount.setBankName("OpenAI Bank");
        BankAccount.setInterestRate(5.0);

        // Create BankAccount objects (instance members)
        BankAccount acc1 = new BankAccount("1001", "Alice", 5000);
        BankAccount acc2 = new BankAccount("1002", "Bob", 3000);

        // Show that static members are shared
        BankAccount.displayBankInfo(); // Static method call using class name

        // Show unique instance members
        acc1.displayAccountInfo();
        acc2.displayAccountInfo();

        // Perform transactions
        acc1.deposit(2000);
        acc2.withdraw(1000);

        // Interest calculation (uses static interestRate)
        acc1.calculateInterest();
        acc2.calculateInterest();

        // Show total accounts (static variable updated)
        System.out.println("Total Accounts (via class): " + BankAccount.getTotalAccounts());
        System.out.println("Total Accounts (via object): " + acc1.getTotalAccounts()); // discouraged, but works

        // Change static interest rate
        BankAccount.setInterestRate(6.0);
        System.out.println("\nAfter changing interest rate:");
        BankAccount.displayBankInfo();

        // Both accounts see updated interest rate (proves static sharing)
        acc1.calculateInterest();
        acc2.calculateInterest();
    }
}
