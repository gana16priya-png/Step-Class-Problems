import java.util.*;

public class SimpleSpellChecker {
    public static String[] extractWords(String sentence) {
        sentence = sentence + " "; // Add a trailing space to catch last word
        ArrayList<String> list = new ArrayList<>();
        int start = 0;

        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);

   
            if (c == ' ' || c == '.' || c == ',' || c == '!' || c == '?') {
                if (start < i) {
                    String word = sentence.substring(start, i);
                    list.add(word);
                }
                start = i + 1;
            }
        }
        return list.toArray(new String[0]);
    }

       public static int stringDistance(String a, String b) {
        int n = a.length(), m = b.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) dp[i][0] = i;
        for (int j = 0; j <= m; j++) dp[0][j] = j;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],  // replace
                                    Math.min(dp[i - 1][j],    // delete
                                             dp[i][j - 1]));  // insert
                }
            }
        }
        return dp[n][m];
    }

    public static String findClosest(String word, String[] dictionary) {
        String best = word;
        int minDist = Integer.MAX_VALUE;

        for (String dictWord : dictionary) {
            int dist = stringDistance(word, dictWord);
            if (dist < minDist) {
                minDist = dist;
                best = dictWord;
            }
        }
        return (minDist <= 2) ? best : word; // Suggest only if distance ≤ 2
    }
    public static void displayResults(String[] words, String[] dictionary) {
        System.out.printf("%-15s %-15s %-10s %-12s\n",
                "Word", "Suggestion", "Distance", "Status");
        System.out.println("--------------------------------------------------------");

        for (String w : words) {
            String suggestion = findClosest(w, dictionary);
            int dist = stringDistance(w, suggestion);
            String status = (dist == 0) ? "Correct" : "Misspelled";

            System.out.printf("%-15s %-15s %-10d %-12s\n",
                    w, suggestion, dist, status);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] dictionary = {"hello", "world", "java", "program", "spell", "checker", "simple"};

        System.out.println("Enter a sentence:");
        String sentence = sc.nextLine();

        String[] words = extractWords(sentence);
        displayResults(words, dictionary);
    }
}
