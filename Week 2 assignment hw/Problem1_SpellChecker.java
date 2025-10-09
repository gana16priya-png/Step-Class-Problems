import java.util.*;

public class Problem1_SpellChecker {

    
    public static List<String> getWords(String text) {
        List<String> words = new ArrayList<>();
        int start = -1;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                if (start == -1) start = i;
            } else {
                if (start != -1) {
                    words.add(text.substring(start, i));
                    start = -1;
                }
            }
        }
        if (start != -1) {
            words.add(text.substring(start));
        }
        return words;
    }

    
    public static int stringDistance(String a, String b) {
        int m = a.length();
        int n = b.length();
        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a.charAt(i-1) == b.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], 
                                    Math.min(dp[i-1][j], dp[i][j-1]));
                }
            }
        }
        return dp[m][n];
    }

   
    public static String findClosest(String word, String[] dictionary) {
        String bestMatch = word;
        int minDist = Integer.MAX_VALUE;

        for (String dictWord : dictionary) {
            int dist = stringDistance(word.toLowerCase(), dictWord.toLowerCase());
            if (dist < minDist) {
                minDist = dist;
                bestMatch = dictWord;
            }
        }
        return (minDist <= 2) ? bestMatch : word;
    }


    public static void spellCheckReport(List<String> words, String[] dictionary) {
        System.out.printf("%-15s %-15s %-10s %-10s\n", "Word", "Suggestion", "Distance", "Status");
        System.out.println("------------------------------------------------------------");

        for (String word : words) {
            String suggestion = findClosest(word, dictionary);
            int distance = stringDistance(word.toLowerCase(), suggestion.toLowerCase());
            String status = (distance == 0) ? "Correct" : "Misspelled";
            System.out.printf("%-15s %-15s %-10d %-10s\n", word, suggestion, distance, status);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        
        String[] dictionary = {"apple", "banana", "orange", "grape", "fruit", "hello", "world"};

        System.out.println("Enter a sentence: ");
        String text = sc.nextLine();

        List<String> words = getWords(text);

        System.out.println("\nSpell Check Report:");
        spellCheckReport(words, dictionary);
    }
}
