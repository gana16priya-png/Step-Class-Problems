// Thread for printing odd numbers
class OddThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 20; i += 2) {  // odd numbers
            System.out.println("Odd: " + i);
            try {
                Thread.sleep(500); // pause to simulate concurrency
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

// Thread for printing even numbers
class EvenThread extends Thread {
    @Override
    public void run() {
        for (int i = 2; i <= 20; i += 2) {  // even numbers
            System.out.println("Even: " + i);
            try {
                Thread.sleep(500); // pause to simulate concurrency
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}


public class OddEven {
    public static void main(String[] args) {
        OddThread odd = new OddThread();   // create odd thread
        EvenThread even = new EvenThread(); // create even thread

        // start both threads
        odd.start();
        even.start();
    }
}

