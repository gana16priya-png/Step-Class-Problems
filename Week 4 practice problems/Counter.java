public class Counter {
    // static variable to track object count
    static int count = 0;

    // constructor increments count whenever a new object is created
    Counter() {
        count++;
    }

    // static method to return the count
    public static int getCount() {
        return count;
    }

    public static void main(String[] args) {
        // Create several Counter objects
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        Counter c3 = new Counter();
        Counter c4 = new Counter();

        // Display number of objects created
        System.out.println("Number of Counter objects created: " + Counter.getCount());
    }
}
