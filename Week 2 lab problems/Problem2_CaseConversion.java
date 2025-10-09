import java.util.Scanner;
public class Problem2_CaseConversion {
 public static char toUpper(char c) {
 return (c >= 'a' && c <= 'z') ? (char)(c - 32) : c;
 }
 public static char toLower(char c) {
 return (c >= 'A' && c <= 'Z') ? (char)(c + 32) : c;
 }
 public static String toUpperCase(String text) {
 StringBuilder sb = new StringBuilder();
 for (char c : text.toCharArray()) sb.append(toUpper(c));
 return sb.toString();
 }
 public static String toLowerCase(String text) {
 StringBuilder sb = new StringBuilder();
 for (char c : text.toCharArray()) sb.append(toLower(c));
 return sb.toString();
 }
 public static String toTitleCase(String text) {
 StringBuilder sb = new StringBuilder();
 boolean newWord = true;
 for (char c : text.toCharArray()) {
 if (c == ' ') {
 sb.append(c);
 newWord = true;
 } else {
 sb.append(newWord ? toUpper(c) : toLower(c));
 newWord = false;
 }
 }
 return sb.toString();
 }
 public static void main(String[] args) {
 Scanner sc = new Scanner(System.in);
 System.out.print("Enter text: ");
 String text = sc.nextLine();
 System.out.println("\nOriginal: " + text);
 System.out.println("Uppercase: " + toUpperCase(text));
 System.out.println("Lowercase: " + toLowerCase(text));
 System.out.println("Title Case: " + toTitleCase(text));
 }
}