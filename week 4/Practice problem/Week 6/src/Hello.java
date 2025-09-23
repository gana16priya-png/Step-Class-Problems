// Thread for printing "Hello"
class HelloThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Hello");
            try {
                Thread.sleep(500); // pause for readability
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

// Thread for printing "Welcome"
class WelcomeThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Welcome");
            try {
                Thread.sleep(500); // pause for readability
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

// Main class
public class Hello {
    public static void main(String[] args) {
        HelloThread t1 = new HelloThread();     // create Hello thread
        WelcomeThread t2 = new WelcomeThread(); // create Welcome thread

        t1.start(); // start Hello thread
        t2.start(); // start Welcome thread
    }
}

