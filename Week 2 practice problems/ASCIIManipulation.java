import java.util.Scanner;
public class ASCIIManipulation {
 public static void main(String[] args) {
 Scanner sc = new Scanner(System.in);
 System.out.print("Enter a character: ");
 char ch = sc.next().charAt(0);
 int asciiValue = (int) ch;
 System.out.println("ASCII value of '" + ch + "' is: " + asciiValue);
 System.out.println("Character from ASCII value " + asciiValue + ": " + (char)
asciiValue);
 System.out.println("Next character: " + (char) (asciiValue + 1));
 System.out.println("Previous character: " + (char) (asciiValue - 1));
 sc.nextLine(); 
 System.out.print("\nEnter a string: ");
 String text = sc.nextLine();
 System.out.println("\nCharacters and their ASCII values:");
 for (int i = 0; i < text.length(); i++) {
 char c = text.charAt(i);
 System.out.println("'" + c + "' → " + (int) c);
 }
 sc.close();
 }
}