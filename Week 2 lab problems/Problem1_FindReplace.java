import java.util.Scanner;
public class Problem1_FindReplace {
 public static String manualReplace(String text, String find, String replace) {
 StringBuilder result = new StringBuilder();
 int i = 0;
 while (i < text.length()) {
 if (i + find.length() <= text.length() && text.substring(i, i +
find.length()).equals(find)) {
 result.append(replace);
 i += find.length();
 } else {
 result.append(text.charAt(i));
 i++;
 }
 }
 return result.toString();
 }
 public static void main(String[] args) {
 Scanner sc = new Scanner(System.in);
 System.out.print("Enter text: ");
 String text = sc.nextLine();
 System.out.print("Enter substring to find: ");
 String find = sc.nextLine();
 System.out.print("Enter replacement: ");
 String replace = sc.nextLine();
 String manual = manualReplace(text, find, replace);
 String builtin = text.replace(find, replace);
 System.out.println("\nManual Replace: " + manual);
 System.out.println("Built-in Replace: " + builtin);
 System.out.println("Match? " + manual.equals(builtin));
 }}