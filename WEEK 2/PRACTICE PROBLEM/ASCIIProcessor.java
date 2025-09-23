import java.util.Scanner;

public class ASCIIProcessor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sampleText = "Hello World! 123";

        System.out.println("Processing sample string: \"" + sampleText + "\"");
        for (char ch : sampleText.toCharArray()) {
            System.out.println("Character: " + ch + ", ASCII Code: " + (int) ch);
            System.out.println("Type: " + classifyCharacter(ch));

            if (Character.isLetter(ch)) {
                char lowerCh = Character.toLowerCase(ch);
                char upperCh = Character.toUpperCase(ch);
                System.out.println("Lowercase: " + lowerCh + " (ASCII: " + (int) lowerCh + ")");
                System.out.println("Uppercase: " + upperCh + " (ASCII: " + (int) upperCh + ")");
                System.out.println("ASCII Difference: " + ((int) lowerCh - (int) upperCh));
            }
            System.out.println();
        }

        System.out.println("Toggled Case: " + toggleCase(sampleText));
        System.out.println("Caesar Cipher (shift 3): " + caesarCipher(sampleText, 3));
        
        System.out.println("\nASCII Table (65-90):");
        displayASCIITable(65, 90);

        int[] asciiArray = stringToASCII("Hello");
        System.out.println("\nASCII Array for 'Hello':");
        for(int code : asciiArray) {
            System.out.print(code + " ");
        }
        System.out.println();
        
        System.out.println("String from ASCII Array: " + asciiToString(asciiArray));

        scanner.close();
    }

    public static String classifyCharacter(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return "Uppercase Letter";
        } else if (ch >= 'a' && ch <= 'z') {
            return "Lowercase Letter";
        } else if (ch >= '0' && ch <= '9') {
            return "Digit";
        } else {
            return "Special Character";
        }
    }

    public static String toggleCase(String text) {
        StringBuilder toggled = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                toggled.append((char) (ch + 32));
            } else if (ch >= 'a' && ch <= 'z') {
                toggled.append((char) (ch - 32));
            } else {
                toggled.append(ch);
            }
        }
        return toggled.toString();
    }

    public static String caesarCipher(String text, int shift) {
        StringBuilder encrypted = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                encrypted.append((char) ('A' + (ch - 'A' + shift) % 26));
            } else if (ch >= 'a' && ch <= 'z') {
                encrypted.append((char) ('a' + (ch - 'a' + shift) % 26));
            } else {
                encrypted.append(ch);
            }
        }
        return encrypted.toString();
    }

    public static void displayASCIITable(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println("ASCII: " + i + " -> Character: " + (char) i);
        }
    }

    public static int[] stringToASCII(String text) {
        int[] asciiValues = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            asciiValues[i] = (int) text.charAt(i);
        }
        return asciiValues;
    }

    public static String asciiToString(int[] asciiValues) {
        StringBuilder result = new StringBuilder();
        for (int value : asciiValues) {
            result.append((char) value);
        }
        return result.toString();
    }
}