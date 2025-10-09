import java.util.*;
public class Problem3_TextCompression {
 static class Freq {
 char[] chars;
 int[] counts;
 int size;
 }
 static Freq countFreq(String text) {
 char[] cArr = new char[text.length()];
 int[] fArr = new int[text.length()];
 int size = 0;
 for (int i = 0; i < text.length(); i++) {
 char c = text.charAt(i);
 int idx = -1;
 for (int j = 0; j < size; j++) if (cArr[j] == c) { idx = j; break; }
 if (idx == -1) {
 cArr[size] = c; fArr[size] = 1; size++;
 } else fArr[idx]++;
 }
 Freq f = new Freq();
 f.chars = Arrays.copyOf(cArr, size);
 f.counts = Arrays.copyOf(fArr, size);
 f.size = size;
 return f;
 }
 
 static void sortByFreq(Freq f) {
 for (int i = 0; i < f.size - 1; i++) {
 int max = i;
 for (int j = i + 1; j < f.size; j++)
 if (f.counts[j] > f.counts[max]) max = j;
 // swap
 int tc = f.counts[i]; f.counts[i] = f.counts[max]; f.counts[max] = tc;
 char ch = f.chars[i]; f.chars[i] = f.chars[max]; f.chars[max] = ch;
 }
 }

 static String[] buildCodes(Freq f) {
 String digits = "0123456789";
 String upp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
 String low = "abcdefghijklmnopqrstuvwxyz";
 String pool = digits + upp + low; 
 String[] codes = new String[f.size];
 for (int i = 0; i < f.size; i++) {
 if (i < pool.length()) codes[i] = "" + pool.charAt(i);
 else codes[i] = "x" + (i - pool.length()); 
 }
 return codes;
 }
 static String codeForChar(char c, Freq f, String[] codes) {
 for (int i = 0; i < f.size; i++) if (f.chars[i] == c) return codes[i];
 return "";
 }
 static String compress(String text, Freq f, String[] codes) {
 StringBuilder sb = new StringBuilder();
 for (int i = 0; i < text.length(); i++) sb.append(codeForChar(text.charAt(i), f,
codes)).append('|');
 return sb.toString();
 }
 static String decompress(String comp, Freq f, String[] codes) {
 StringBuilder sb = new StringBuilder();
 int i = 0, n = comp.length();
 StringBuilder token = new StringBuilder();
 while (i < n) {
 char ch = comp.charAt(i);
 if (ch == '|') {
 String t = token.toString();
 
 for (int k = 0; k < codes.length; k++)
 if (codes[k].equals(t)) { sb.append(f.chars[k]); break; }
 token.setLength(0);
 } else token.append(ch);
 i++;
 }
 return sb.toString();
 }
 public static void main(String[] args) {
 Scanner sc = new Scanner(System.in);
 System.out.print("Enter text to compress: ");
 String text = sc.nextLine();
 Freq f = countFreq(text);
 sortByFreq(f);
 String[] codes = buildCodes(f);
 System.out.println("\nChar Freq Code");
 for (int i = 0; i < f.size; i++) {
 String display = (f.chars[i] == ' ') ? "[space]" : String.valueOf(f.chars[i]);
 System.out.printf("%-6s%-6d%s%n", display, f.counts[i], codes[i]);
 }
 String compressed = compress(text, f, codes);
 String decompressed = decompress(compressed, f, codes);
 int originalBits = text.length() * 8;
 int compressedChars = compressed.length(); 
 double efficiency = (1.0 - ((double)compressedChars / (double)(text.length()))) *
100.0;
 System.out.println("\nOriginal: " + text);
 System.out.println("Compressed: " + compressed);
 System.out.println("Decompressed:" + decompressed);
 System.out.printf("Compression efficiency (chars vs tokens): %.2f%%%n",
efficiency);
 System.out.println("Validation: " + (text.equals(decompressed) ? "OK" :
"FAILED"));
 }
}
