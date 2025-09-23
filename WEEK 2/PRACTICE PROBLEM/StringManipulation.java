import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StringManipulation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();

        String trimmedString = userInput.trim();
        System.out.println("Trimmed String: \"" + trimmedString + "\"");

        String replacedString = trimmedString.replace(' ', '_');
        System.out.println("Replaced Spaces: \"" + replacedString + "\"");

        String noDigits = trimmedString.replaceAll("\\d", "");
        System.out.println("Removed Digits: \"" + noDigits + "\"");

        String[] words = noDigits.split(" ");
        System.out.println("Split into words: " + Arrays.toString(words));

        String joinedString = String.join(" | ", words);
        System.out.println("Rejoined with '|': \"" + joinedString + "\"");

        String noPunctuation = removePunctuation(trimmedString);
        System.out.println("Removed Punctuation: \"" + noPunctuation + "\"");

        String capitalizedWords = capitalizeWords(noPunctuation);
        System.out.println("Capitalized Words: \"" + capitalizedWords + "\"");

        String reversedWords = reverseWordOrder(noPunctuation);
        System.out.println("Reversed Word Order: \"" + reversedWords + "\"");

        System.out.println("Word Frequency:");
        countWordFrequency(noPunctuation);

        scanner.close();
    }

    public static String removePunctuation(String text) {
        return text.replaceAll("[^a-zA-Z0-9\\s]", "");
    }

    public static String capitalizeWords(String text) {
        String[] words = text.split(" ");
        StringBuilder capitalized = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                capitalized.append(Character.toUpperCase(word.charAt(0)))
                            .append(word.substring(1).toLowerCase())
                            .append(" ");
            }
        }
        return capitalized.toString().trim();
    }

    public static String reverseWordOrder(String text) {
        String[] words = text.split(" ");
        StringBuilder reversed = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]).append(" ");
        }
        return reversed.toString().trim();
    }

    public static void countWordFrequency(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}