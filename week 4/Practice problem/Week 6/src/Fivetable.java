// Thread for multiplication table of 5
class Table5 extends Thread {
    @Override
    public void run() {
        System.out.println("Multiplication Table of 5:");
        for (int i = 1; i <= 10; i++) {
            System.out.println("5 x " + i + " = " + (5 * i));
            try {
                Thread.sleep(500); // pause for readability
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

// Thread for multiplication table of 10
class Table10 extends Thread {
    @Override
    public void run() {
        System.out.println("Multiplication Table of 10:");
        for (int i = 1; i <= 10; i++) {
            System.out.println("10 x " + i + " = " + (10 * i));
            try {
                Thread.sleep(500); // pause for readability
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

// Main class
public class Fivetable{
    public static void main(String[] args) {
        Table5 t1 = new Table5();   // thread for table of 5
        Table10 t2 = new Table10(); // thread for table of 10

        // Start both threads
        t1.start();
        t2.start();
    }
}
