public class StringMethodsDemo {
 public static void main(String[] args) {
  String text = " Java Programming is Powerful and Fun! ";
 System.out.println("Original text: \"" + text + "\"");
 System.out.println("Length: " + text.length());
 String trimmed = text.trim();
 System.out.println("Trimmed text: \"" + trimmed + "\"");
 System.out.println("Uppercase: " + trimmed.toUpperCase());
 System.out.println("Lowercase: " + trimmed.toLowerCase());
 System.out.println("Substring (0, 4): " + trimmed.substring(0, 4));
 System.out.println("Character at index 5: " + trimmed.charAt(5));
 System.out.println("Index of 'Powerful': " + trimmed.indexOf("Powerful"));
 System.out.println("Contains 'Fun': " + trimmed.contains("Fun"));
 String replaced = trimmed.replace("Powerful", "Awesome");
 System.out.println("After replace: " + replaced);
 String[] words = trimmed.split(" ");
 System.out.println("Words in the text:");
 for (String word : words) {
 System.out.println(word);
 }
 String anotherText = "java programming is powerful and fun!";
 System.out.println("Equals: " + trimmed.equals(anotherText));
 System.out.println("Equals ignore case: " +
trimmed.equalsIgnoreCase(anotherText));
 }
}