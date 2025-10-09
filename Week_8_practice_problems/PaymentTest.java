// File: PaymentGateway.java
interface PaymentGateway {
    void pay(double amount);
    void refund(double amount);
}

// File: CreditCardPayment.java
class CreditCardPayment implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via Credit Card");
    }

    @Override
    public void refund(double amount) {
        System.out.println("Refunded " + amount + " to Credit Card");
    }
}

// File: UPIPayment.java
class UPIPayment implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via UPI");
    }

    @Override
    public void refund(double amount) {
        System.out.println("Refunded " + amount + " to UPI");
    }
}

// File: PaymentTest.java
public class PaymentTest {
    public static void main(String[] args) {
        PaymentGateway pg1 = new CreditCardPayment();
        pg1.pay(5000);
        pg1.refund(1000);

        PaymentGateway pg2 = new UPIPayment();
        pg2.pay(2000);
        pg2.refund(500);
    }
}
