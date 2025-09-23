import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StringPerformanceComparison {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== PERFORMANCE COMPARISON ===");
        int iterations = 10000;

        long startTime = System.nanoTime();
        String result1 = concatenateWithString(iterations);
        long endTime = System.nanoTime();
        System.out.println("String concatenation time: " + (endTime - startTime) / 1000000.0 + " ms");

        startTime = System.nanoTime();
        String result2 = concatenateWithStringBuilder(iterations);
        endTime = System.nanoTime();
        System.out.println("StringBuilder concatenation time: " + (endTime - startTime) / 1000000.0 + " ms");

        startTime = System.nanoTime();
        String result3 = concatenateWithStringBuffer(iterations);
        endTime = System.nanoTime();
        System.out.println("StringBuffer concatenation time: " + (endTime - startTime) / 1000000.0 + " ms");

        System.out.println("\n=== STRINGBUILDER METHODS ===");
        demonstrateStringBuilderMethods();

        System.out.println("\n=== THREAD SAFETY DEMO ===");
        demonstrateThreadSafety();
    }

    public static String concatenateWithString(int iterations) {
        String result = "";
        for (int i = 0; i < iterations; i++) {
            result += "Java";
        }
        return result;
    }

    public static String concatenateWithStringBuilder(int iterations) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("Java");
        }
        return sb.toString();
    }

    public static String concatenateWithStringBuffer(int iterations) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sb.append("Java");
        }
        return sb.toString();
    }

    public static void demonstrateStringBuilderMethods() {
        StringBuilder sb = new StringBuilder("Hello World");
        System.out.println("Original: " + sb);

        sb.append("!");
        System.out.println("append(): " + sb);

        sb.insert(6, "Java ");
        System.out.println("insert(): " + sb);

        sb.delete(5, 11);
        System.out.println("delete(): " + sb);

        sb.deleteCharAt(4);
        System.out.println("deleteCharAt(): " + sb);

        sb.reverse();
        System.out.println("reverse(): " + sb);

        sb.reverse();
        sb.replace(0, 5, "Hi");
        System.out.println("replace(): " + sb);

        sb.setCharAt(3, 'x');
        System.out.println("setCharAt(): " + sb);

        System.out.println("capacity(): " + sb.capacity());

        sb.ensureCapacity(100);
        System.out.println("ensureCapacity(): " + sb.capacity());

        sb.trimToSize();
        System.out.println("trimToSize(): " + sb.capacity());
    }

    public static void demonstrateThreadSafety() throws InterruptedException {
        final int numThreads = 10;
        final int iterationsPerThread = 1000;

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        System.out.println("\nTesting StringBuffer (Thread-Safe):");
        StringBuffer safeBuffer = new StringBuffer();
        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < iterationsPerThread; j++) {
                    safeBuffer.append("a");
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("StringBuffer length: " + safeBuffer.length());

        ExecutorService executorService2 = Executors.newFixedThreadPool(numThreads);
        System.out.println("\nTesting StringBuilder (NOT Thread-Safe):");
        StringBuilder unsafeBuilder = new StringBuilder();
        for (int i = 0; i < numThreads; i++) {
            executorService2.submit(() -> {
                for (int j = 0; j < iterationsPerThread; j++) {
                    unsafeBuilder.append("a");
                }
            });
        }
        executorService2.shutdown();
        executorService2.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("StringBuilder length: " + unsafeBuilder.length());
    }
}