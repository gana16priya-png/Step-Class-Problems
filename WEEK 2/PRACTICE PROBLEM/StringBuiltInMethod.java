public class StringBuiltInMethod {

    public static void main(String[] args) {
        String sampleText = " Java Programming is Fun and Challenging! ";

        System.out.println(sampleText.length());

        String trimmedText = sampleText.trim();
        System.out.println(trimmedText.length());

        System.out.println(trimmedText.charAt(5));

        System.out.println(trimmedText.substring(5, 17));

        System.out.println(trimmedText.indexOf("Fun"));

        System.out.println(trimmedText.contains("Java"));

        System.out.println(trimmedText.startsWith("Java"));

        System.out.println(trimmedText.endsWith("!"));

        System.out.println(trimmedText.toUpperCase());

        System.out.println(trimmedText.toLowerCase());

        System.out.println("Vowel Count: " + countVowels(trimmedText));

        findAllOccurrences(trimmedText, 'a');
    }

    public static int countVowels(String text) {
        int count = 0;
        String vowels = "aeiouAEIOU";
        for (int i = 0; i < text.length(); i++) {
            if (vowels.indexOf(text.charAt(i)) != -1) {
                count++;
            }
        }
        return count;
    }

    public static void findAllOccurrences(String text, char target) {
        System.out.print("Occurrences of '" + target + "': ");
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}