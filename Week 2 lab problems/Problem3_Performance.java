import java.util.Scanner;
public class Problem3_Performance {
 public static long testStringConcat(int n) {
 long start = System.currentTimeMillis();
 String s = "";
 for (int i = 0; i < n; i++) {
 s += "a";
 }
 return System.currentTimeMillis() - start;
 }
 public static long testStringBuilder(int n) {
 long start = System.currentTimeMillis();
 StringBuilder sb = new StringBuilder();
 for (int i = 0; i < n; i++) sb.append("a");
 return System.currentTimeMillis() - start;
 }
 public static long testStringBuffer(int n) {
 long start = System.currentTimeMillis();
 StringBuffer sbf = new StringBuffer();
 for (int i = 0; i < n; i++) sbf.append("a");
 return System.currentTimeMillis() - start;
 }
 public static void main(String[] args) {
 Scanner sc = new Scanner(System.in);
 System.out.print("Enter number of iterations: ");
 int n = sc.nextInt();
 System.out.println("\nPerformance Analysis:");
 System.out.println("String: " + testStringConcat(n) + " ms");
 System.out.println("StringBuilder: " + testStringBuilder(n) + " ms");
 System.out.println("StringBuffer: " + testStringBuffer(n) + " ms");
 }
}