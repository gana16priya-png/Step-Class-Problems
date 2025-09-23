public class PersonalAccount{
    // Private attributes
    private String accountHolderName;
    private String accountNumber;
    private double currentBalance;
    private double totalIncome;
    private double totalExpenses;

    // Static variables
    private static int totalAccounts = 0;
    private static String bankName = "Default Bank";
    private static int accountCounter = 1;

    // Constructor
    public PersonalAccount(String accountHolderName, double initialBalance) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = generateAccountNumber();
        this.currentBalance = initialBalance;
        this.totalIncome = initialBalance;
        this.totalExpenses = 0;
        totalAccounts++;
    }

    // Static methods
    public static void setBankName(String name) {
        bankName = name;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    public static String generateAccountNumber() {
        String accNum = String.format("PA%03d", accountCounter);
        accountCounter++;
        return accNum;
    }

    // Instance methods
    public void addIncome(double amount, String description) {
        if (amount > 0) {
            currentBalance += amount;
            totalIncome += amount;
            System.out.println(accountHolderName + " received income: " + amount + " (" + description + ")");
        }
    }

    public void addExpense(double amount, String description) {
        if (amount > 0 && amount <= currentBalance) {
            currentBalance -= amount;
            totalExpenses += amount;
            System.out.println(accountHolderName + " spent: " + amount + " (" + description + ")");
        } else {
            System.out.println("Expense failed for " + accountHolderName + ": insufficient balance or invalid amount.");
        }
    }

    public double calculateSavings() {
        return totalIncome - totalExpenses;
    }

    public void displayAccountSummary() {
        System.out.println("Bank Name: " + bankName);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: " + currentBalance);
        System.out.println("Total Income: " + totalIncome);
        System.out.println("Total Expenses: " + totalExpenses);
        System.out.println("Total Savings: " + calculateSavings());
        System.out.println("---------------------------");
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Set bank name (static variable shared by all accounts)
        PersonalAccount.setBankName("Smart Finance Bank");

        // Create 3 different personal accounts
        PersonalAccount acc1 = new PersonalAccount("Alice", 5000);
        PersonalAccount acc2 = new PersonalAccount("Bob", 3000);
        PersonalAccount acc3 = new PersonalAccount("Charlie", 7000);

        // Perform various transactions
        acc1.addIncome(2000, "Salary");
        acc1.addExpense(1500, "Rent");
        acc1.addExpense(500, "Groceries");

        acc2.addIncome(1000, "Freelance");
        acc2.addExpense(800, "Utilities");
        acc2.addExpense(500, "Shopping");

        acc3.addIncome(3000, "Bonus");
        acc3.addExpense(2500, "Travel");
        acc3.addExpense(1000, "Dining");

        // Display account summaries
        acc1.displayAccountSummary();
        acc2.displayAccountSummary();
        acc3.displayAccountSummary();

        // Demonstrate static vs instance variables
        System.out.println("Bank Name (static, shared): " + PersonalAccount.bankName);
        System.out.println("Total Accounts (static): " + PersonalAccount.getTotalAccounts());
        System.out.println("Alice's Balance (instance): " + acc1.currentBalance);
        System.out.println("Bob's Balance (instance): " + acc2.currentBalance);
        System.out.println("Charlie's Balance (instance): " + acc3.currentBalance);
    }
}