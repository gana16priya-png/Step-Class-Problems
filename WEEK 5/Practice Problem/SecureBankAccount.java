public class SecureBankAccount {
    // ==========================
    // Private fields (hidden)
    // ==========================
    private final String accountNumber;  // read-only after creation
    private double balance;              // only via deposit/withdraw
    private int pin;                     // write-only for security
    private boolean isLocked;            // internal lock state
    private int failedAttempts;          // internal counter

    // ==========================
    // Private constants
    // ==========================
    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final double MIN_BALANCE = 0.0;

    // ==========================
    // Constructor
    // ==========================
    public SecureBankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = Math.max(initialBalance, MIN_BALANCE);
        this.pin = 0; // must be set separately
        this.isLocked = false;
        this.failedAttempts = 0;
    }

    // ==========================
    // Account Info Methods
    // ==========================
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        if (isLocked) {
            System.out.println("❌ Account is locked. Cannot view balance.");
            return -1;
        }
        return balance;
    }

    public boolean isAccountLocked() {
        return isLocked;
    }

    // ==========================
    // Security Methods
    // ==========================
    public boolean setPin(int oldPin, int newPin) {
        if (validatePin(oldPin)) {
            this.pin = newPin;
            System.out.println("✅ PIN updated successfully.");
            return true;
        }
        System.out.println("❌ Failed to update PIN.");
        return false;
    }

    public boolean validatePin(int enteredPin) {
        if (isLocked) {
            System.out.println("❌ Account is locked. Cannot validate PIN.");
            return false;
        }
        if (enteredPin == pin) {
            resetFailedAttempts();
            return true;
        } else {
            incrementFailedAttempts();
            System.out.println("❌ Incorrect PIN.");
            return false;
        }
    }

    public boolean unlockAccount(int correctPin) {
        if (pin == correctPin) {
            isLocked = false;
            resetFailedAttempts();
            System.out.println("✅ Account unlocked successfully.");
            return true;
        }
        System.out.println("❌ Failed to unlock account. Wrong PIN.");
        return false;
    }

    // ==========================
    // Transaction Methods
    // ==========================
    public boolean deposit(double amount, int pin) {
        if (validatePin(pin) && amount > 0) {
            balance += amount;
            System.out.println("✅ Deposited: " + amount + ". New Balance: " + balance);
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount, int pin) {
        if (!validatePin(pin)) return false;
        if (balance - amount >= MIN_BALANCE) {
            balance -= amount;
            System.out.println("✅ Withdrawn: " + amount + ". New Balance: " + balance);
            return true;
        } else {
            System.out.println("❌ Insufficient funds. Current Balance: " + balance);
            return false;
        }
    }

    public boolean transfer(SecureBankAccount target, double amount, int pin) {
        if (withdraw(amount, pin)) {
            target.deposit(amount, pin); // target requires same PIN validation
            System.out.println("✅ Transferred " + amount + " from " 
                + this.accountNumber + " to " + target.accountNumber);
            return true;
        }
        return false;
    }

    // ==========================
    // Private helper methods
    // ==========================
    private void lockAccount() {
        isLocked = true;
        System.out.println("🔒 Account locked due to too many failed attempts.");
    }

    private void resetFailedAttempts() {
        failedAttempts = 0;
    }

    private void incrementFailedAttempts() {
        failedAttempts++;
        if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
            lockAccount();
        }
    }

    // ==========================
    // Demo in main
    // ==========================
    public static void main(String[] args) {
        SecureBankAccount acc1 = new SecureBankAccount("ACC123", 500.0);
        SecureBankAccount acc2 = new SecureBankAccount("ACC456", 1000.0);

        // ❌ Trying to access private fields (uncomment → compilation error)
        // System.out.println(acc1.balance);

        // ✅ Proper usage
        acc1.setPin(0, 1234);
        acc2.setPin(0, 4321);

        acc1.deposit(200, 1234);
        acc1.withdraw(100, 1234);

        // ❌ Wrong PIN attempts
        acc1.withdraw(50, 9999);
        acc1.withdraw(50, 9999);
        acc1.withdraw(50, 9999); // locks account

        // ✅ Unlock account
        acc1.unlockAccount(1234);

        // ❌ Withdraw more than balance
        acc1.withdraw(10000, 1234);

        // ✅ Transfer funds
        acc1.deposit(500, 1234);
        acc1.transfer(acc2, 200, 1234);

        // ✅ Check balances
        System.out.println("Acc1 Balance: " + acc1.getBalance());
        System.out.println("Acc2 Balance: " + acc2.getBalance());
    }
}
