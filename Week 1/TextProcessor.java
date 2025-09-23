import java.util.Scanner;

public class TextProcessor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a line of text: ");
        String text = sc.nextLine();

        System.out.println("You entered: " + text);
        System.out.println("Length: " + text.length());
        System.out.println("Uppercase: " + text.toUpperCase());
        System.out.println("Lowercase: " + text.toLowerCase());
        System.out.println("Replace spaces with '-': " + text.replace(" ", "-"));
        if (text.length() >= 5) {
            System.out.println("First 5 characters: " + text.substring(0, 5));
        }
    }
}