import java.util.Random;

class BankAccount {
    String accountHolder;
    int accountNumber;
    double balance;

    // 1. Default constructor  balance = 0
    BankAccount() {
        this.accountHolder = "Unknown";
        this.accountNumber = generateAccountNumber();
        this.balance = 0.0;
    }

    // 2. Constructor with name  assigns random account number
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

    // Helper method to generate random account number
    private int generateAccountNumber() {
        Random rand = new Random();
        return 100000 + rand.nextInt(900000);
    }

    // Deposit method
    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount + " | New Balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive");
        }
    }

    // Withdraw method
    void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " | New Balance: " + balance);
        } else if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive");
        } else {
            System.out.println("Insufficient balance | Current Balance: " + balance);
        }
    }

    // Display account details
    void displayAccount() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
        System.out.println("-----------------------------------");
    }
}

public class BankAccountSystem {
    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount();
        BankAccount acc2 = new BankAccount("Adrija");
        BankAccount acc3 = new BankAccount("Rahul", 5000.0);

        acc1.displayAccount();
        acc2.displayAccount();
        acc3.displayAccount();

        acc2.deposit(2000);
        acc2.withdraw(500);
        acc2.displayAccount();

        acc3.withdraw(7000);
        acc3.deposit(3000);
        acc3.displayAccount();
    }
}
