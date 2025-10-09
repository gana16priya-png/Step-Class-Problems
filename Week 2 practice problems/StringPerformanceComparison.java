public class StringPerformanceComparison {
 public static void main(String[] args) {
 int iterations = 100000; 
 long startTime = System.nanoTime();
 String str = "A";
 for (int i = 0; i < iterations; i++) {
 str += "A"; 
 }
 long endTime = System.nanoTime();
 long stringTime = endTime - startTime;
 System.out.println("Time taken by String: " + stringTime / 1_000_000.0 + " ms");
 startTime = System.nanoTime();
 StringBuilder sb = new StringBuilder("A");
 for (int i = 0; i < iterations; i++) {
 sb.append("A");
 }
 endTime = System.nanoTime();
 long builderTime = endTime - startTime;
 System.out.println("Time taken by StringBuilder: " + builderTime / 1_000_000.0 + "
ms");
 startTime = System.nanoTime();
 StringBuffer sbuf = new StringBuffer("A");
 for (int i = 0; i < iterations; i++) {
 sbuf.append("A");
 }
 endTime = System.nanoTime();
 long bufferTime = endTime - startTime;
 System.out.println("Time taken by StringBuffer: " + bufferTime / 1_000_000.0 + "
ms");
 System.out.println("\n--- Performance Summary ---");
 System.out.println("Fastest: " +
 (builderTime < bufferTime && builderTime < stringTime ? "StringBuilder" :
 (bufferTime < builderTime && bufferTime < stringTime ? "StringBuffer" :
"String")));
 }
}