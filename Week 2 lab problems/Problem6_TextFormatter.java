import java.util.*;

public class Problem6_TextFormatter {
    static List<String> splitWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (start < i) words.add(text.substring(start, i));
                start = i + 1;
            }
        }
        if (start < text.length()) words.add(text.substring(start));
        return words;
    }
    static List<String> justify(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        List<String> lineWords = new ArrayList<>();
        int lineLen = 0;

        for (String w : words) {
            if (lineLen + w.length() + lineWords.size() > width) {
                int spaces = width - lineLen;
                int gaps = Math.max(1, lineWords.size() - 1);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < lineWords.size(); i++) {
                    sb.append(lineWords.get(i));
                    if (i < gaps) {
                        int spaceCount = spaces / gaps + (i < spaces % gaps ? 1 : 0);
                        sb.append(" ".repeat(spaceCount));
                    }
                }
                lines.add(sb.toString());
                lineWords.clear();
                lineLen = 0;
            }
            lineWords.add(w);
            lineLen += w.length();
        }
        StringBuilder last = new StringBuilder();
        for (int i = 0; i < lineWords.size(); i++) {
            last.append(lineWords.get(i));
            if (i < lineWords.size() - 1) last.append(" ");
        }
        lines.add(last.toString());

        return lines;
    }
    static List<String> centerAlign(List<String> lines, int width) {
        List<String> centered = new ArrayList<>();
        for (String line : lines) {
            int pad = width - line.length();
            int left = pad / 2, right = pad - left;
            centered.add(" ".repeat(left) + line + " ".repeat(right));
        }
        return centered;
    }
    static void performanceTest(List<String> words, int width) {
        long t1 = System.nanoTime();
        justify(words, width); 
        long t2 = System.nanoTime();

        long sbTime = t2 - t1;

        t1 = System.nanoTime();
        String s = "";
        for (String w : words) s = s + w;  
        t2 = System.nanoTime();

        long strTime = t2 - t1;

        System.out.println("\nPerformance:");
        System.out.println("Using StringBuilder: " + sbTime + " ns");
        System.out.println("Using String concat: " + strTime + " ns");
    }
    static void display(List<String> lines) {
        int n = 1;
        for (String l : lines) {
            System.out.printf("%2d | %-20s | (%d chars)%n", n, l, l.length());
            n++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text:");
        String text = sc.nextLine();
        System.out.println("Enter line width:");
        int width = sc.nextInt();

        List<String> words = splitWords(text);

        List<String> justified = justify(words, width);
        List<String> centered = centerAlign(justified, width);

        System.out.println("\n--- Original ---");
        System.out.println(text);

        System.out.println("\n--- Justified ---");
        display(justified);

        System.out.println("\n--- Centered ---");
        display(centered);

        performanceTest(words, width);
    }
}
