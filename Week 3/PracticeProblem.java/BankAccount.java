public class BankAccount{
    // Static variables (shared by all accounts)
    private static String bankName;
    private static int totalAccounts = 0;
    private static double interestRate;

    // Instance variables (unique for each account)
    private String accountNumber;
    private String accountHolder;
    private double balance;

    // Constructor
    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        totalAccounts++;
    }

    // Static methods
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
        System.out.println("Interest Rate: " + interestRate);
        System.out.println("---------------------------");
    }

    // Instance methods
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(accountHolder + " deposited: " + amount);
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(accountHolder + " withdrew: " + amount);
        }
    }

    public double calculateInterest() {
        return balance * interestRate / 100;
    }

    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: " + balance);
        System.out.println("Interest (at " + interestRate + "%): " + calculateInterest());
        System.out.println("---------------------------");
    }

    public static void main(String[] args) {
        // Set bank name and interest rate using static methods
        BankAccount.setBankName("Global Bank");
        BankAccount.setInterestRate(5.0);

        // Create multiple BankAccount objects
        BankAccount acc1 = new BankAccount("ACC001", "Alice", 1000);
        BankAccount acc2 = new BankAccount("ACC002", "Bob", 2000);

        // Show that static members are shared across all objects
        BankAccount.displayBankInfo(); // Called using class name
        acc1.displayBankInfo();        // Called using object (not recommended, but possible)

        // Show that instance members are unique to each object
        acc1.deposit(500);
        acc2.withdraw(300);

        acc1.displayAccountInfo();
        acc2.displayAccountInfo();

        // Demonstrate calling static methods with and without objects
        System.out.println("Total Accounts (static): " + BankAccount.getTotalAccounts());
        System.out.println("Total Accounts (via object): " + acc2.getTotalAccounts());
    }
}