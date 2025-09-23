import java.util.Scanner;

public class StringMethods {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter your full name: ");
        String fullName = scanner.nextLine();


        System.out.print("Enter your favorite programming language: ");
        String programmingLanguage = scanner.nextLine();


        System.out.print("Enter a sentence about your programming experience: ");
        String experienceSentence = scanner.nextLine();

        int charCount = experienceSentence.replace(" ", "").length();

        String languageUpper = programmingLanguage.toUpperCase();
        System.out.println("First Name: " + fullName);
        System.out.println("Favorite Language (uppercase): " + languageUpper);
        System.out.println("Experience sentence character count (no spaces): " + charCount);
        System.out.println("Original Experience: " + experienceSentence);

        scanner.close();
    }
}