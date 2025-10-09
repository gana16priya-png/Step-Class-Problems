public class SecureBankAccount {
    // ================== Private Fields ==================
    private final String accountNumber; // read-only after creation
    private double balance;             // only via methods
    private int pin;                    // write-only
    private boolean isLocked;           // internal state
    private int failedAttempts;         // security counter

    // ================== Private Constants ==================
    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final double MIN_BALANCE = 0.0;

    // ================== Constructor ==================
    public SecureBankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = Math.max(initialBalance, MIN_BALANCE); // ensure not negative
        this.pin = 0;  // must be set later
        this.isLocked = false;
        this.failedAttempts = 0;
    }

    // ================== Public Account Info Methods ==================
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        if (isLocked) {
            System.out.println("Account is locked. Cannot view balance.");
            return -1;
        }
        return balance;
    }

    public boolean isAccountLocked() {
        return isLocked;
    }

    // ================== Security Methods ==================
    public boolean setPin(int oldPin, int newPin) {
        if (this.pin == oldPin) {
            this.pin = newPin;
            System.out.println("PIN updated successfully.");
            return true;
        }
        System.out.println("Old PIN incorrect.");
        return false;
    }

    public boolean validatePin(int enteredPin) {
        if (isLocked) {
            System.out.println("Account is locked.");
            return false;
        }
        if (this.pin == enteredPin) {
            resetFailedAttempts();
            return true;
        } else {
            incrementFailedAttempts();
            return false;
        }
    }

    public boolean unlockAccount(int correctPin) {
        if (this.pin == correctPin) {
            isLocked = false;
            resetFailedAttempts();
            System.out.println("Account unlocked successfully.");
            return true;
        }
        System.out.println("Incorrect PIN. Account remains locked.");
        return false;
    }

    // ================== Transaction Methods ==================
    public boolean deposit(double amount, int pin) {
        if (validatePin(pin) && amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount, int pin) {
        if (validatePin(pin)) {
            if (amount > 0 && balance - amount >= MIN_BALANCE) {
                balance -= amount;
                System.out.println("Withdrew: " + amount);
                return true;
            } else {
                System.out.println("Insufficient funds.");
            }
        }
        return false;
    }

    public boolean transfer(SecureBankAccount target, double amount, int pin) {
        if (withdraw(amount, pin)) {
            target.balance += amount;
            System.out.println("Transferred " + amount + " to " + target.getAccountNumber());
            return true;
        }
        return false;
    }

    // ================== Private Helper Methods ==================
    private void lockAccount() {
        isLocked = true;
        System.out.println("Account locked due to too many failed attempts.");
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

    // ================== Main Method Demo ==================
    public static void main(String[] args) {
        // Create accounts
        SecureBankAccount acc1 = new SecureBankAccount("A1001", 500);
        SecureBankAccount acc2 = new SecureBankAccount("A2002", 1000);

        // Try to access fields directly (should fail)
        // acc1.balance = 10000;   // Not allowed (private)
        // System.out.println(acc1.pin); // Not allowed (private)

        // Set PINs
        acc1.setPin(0, 1234);
        acc2.setPin(0, 5678);

        // Deposits
        acc1.deposit(200, 1234);
        acc2.deposit(500, 5678);

        // Withdrawals
        acc1.withdraw(100, 1234);
        acc2.withdraw(2000, 5678); // should fail (insufficient funds)

        // Transfer
        acc1.transfer(acc2, 300, 1234);

        // Wrong PIN attempts
        acc1.withdraw(50, 1111);
        acc1.withdraw(50, 2222);
        acc1.withdraw(50, 3333); // should lock account

        // Try to operate locked account
        acc1.deposit(100, 1234); // will fail
        acc1.unlockAccount(1234); // unlock
        acc1.deposit(100, 1234);  // now works
    }
}
