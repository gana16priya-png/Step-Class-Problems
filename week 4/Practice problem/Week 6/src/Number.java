// Create a class extending Thread
class NumberPrinter extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Number: " + i);
            try {
                Thread.sleep(500); // pause for 0.5 seconds (optional)
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

// Main class
public class Number {
    public static void main(String[] args) {
        NumberPrinter t1 = new NumberPrinter(); // create thread
        t1.start(); // start thread (calls run() internally)
    }
}
