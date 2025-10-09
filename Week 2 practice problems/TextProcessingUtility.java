import java.util.Scanner;
public class TextProcessingUtility {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter your text: ");
String text = sc.nextLine();
System.out.println("\n--- Text Analysis and Manipulation ---");
System.out.println("Original text: \"" + text + "\"");
String trimmed = text.trim();
System.out.println("Trimmed text: \"" + trimmed + "\"");
System.out.println("Length (including spaces): " + text.length());
System.out.println("Uppercase: " + trimmed.toUpperCase());
System.out.println("Lowercase: " + trimmed.toLowerCase());
String[] words = trimmed.split("\\s+");
System.out.println("Word count: " + words.length);
if (trimmed.length() > 0) {
System.out.println("First character: " + trimmed.charAt(0));
System.out.println("Last character: " + trimmed.charAt(trimmed.length() - 1));
}
System.out.print("Enter a word to search: ");
String searchWord = sc.nextLine();
if (trimmed.contains(searchWord)) {
System.out.println("The text contains \"" + searchWord + "\" at index: " +
trimmed.indexOf(searchWord));
} else {
System.out.println("The text does not contain \"" + searchWord + "\".");
}
System.out.print("Enter a word to replace: ");
String oldWord = sc.nextLine();
System.out.print("Enter the new word: ");
String newWord = sc.nextLine();
String replacedText = trimmed.replace(oldWord, newWord);
System.out.println("Text after replacement: " + replacedText);
String reversed = new StringBuilder(trimmed).reverse().toString();
System.out.println("Reversed text: " + reversed);
sc.close();
}
}