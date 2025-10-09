import java.util.*;
public class Problem5_CSVAnalyzer {
 static List<List<String>> parseCSV(String input) {
 List<List<String>> table = new ArrayList<>();
 List<String> row = new ArrayList<>();
 StringBuilder field = new StringBuilder();
 boolean inQuotes = false;
 for (int i = 0; i < input.length(); i++) {
 char c = input.charAt(i);
 if (inQuotes) {
 if (c == '"') {
 if (i + 1 < input.length() && input.charAt(i + 1) == '"') {
 field.append('"'); i++; 
 } else inQuotes = false;
 } else field.append(c);
 } else {
 if (c == '"') inQuotes = true;
 else if (c == ',') { row.add(field.toString()); field.setLength(0); }
 else if (c == '\n' || c == '\r') {

 if (field.length() > 0 || !row.isEmpty()) { row.add(field.toString());
field.setLength(0); table.add(row); row = new ArrayList<>(); }
 } else field.append(c);
 }
 }
 if (field.length() > 0 || !row.isEmpty()) { row.add(field.toString()); table.add(row); }
 return table;
 }

 static boolean isNumber(String s) {
 if (s == null || s.isEmpty()) return false;
 int i = 0; if (s.charAt(0) == '-' || s.charAt(0) == '+') i++;
 boolean any = false;
 for (; i < s.length(); i++) { char c = s.charAt(i); if (c < '0' || c > '9') return false; any =
true; }
 return any;
 }
 static void trimAll(List<List<String>> t) {
 for (List<String> row : t)
 for (int i = 0; i < row.size(); i++)
 row.set(i, row.get(i).trim());
 }
 static class Stats {
 long min = Long.MAX_VALUE, max = Long.MIN_VALUE, sum = 0; int count = 0;
 void add(long v){ min = Math.min(min,v); max = Math.max(max,v); sum += v;
count++; }
 double avg(){ return count==0?0: (double)sum / count; }
 }
 static String pad(String s, int w) {
 if (s.length() >= w) return s.substring(0, w);
 StringBuilder sb = new StringBuilder(s);
 while (sb.length() < w) sb.append(' ');
 return sb.toString();
 }
 public static void main(String[] args) {
 Scanner sc = new Scanner(System.in);
 System.out.println("Enter CSV-like data (end with an empty line):");
 StringBuilder in = new StringBuilder();
 while (true) {
 String line = sc.nextLine();
 if (line.isEmpty()) break;
 in.append(line).append('\n');
 }
 List<List<String>> table = parseCSV(in.toString());
 trimAll(table);
 if (table.isEmpty()) { System.out.println("No data."); return; }

 int cols = 0;
 for (List<String> r : table) cols = Math.max(cols, r.size());
 int[] width = new int[cols];
 for (List<String> r : table)
 for (int c = 0; c < r.size(); c++)
 width[c] = Math.max(width[c], r.get(c).length());
 for (int c = 0; c < cols; c++) width[c] = Math.max(width[c], 10);

 int startRow = 1; 
 Stats[] stats = new Stats[cols];
 boolean[] numeric = new boolean[cols];
 for (int c = 0; c < cols; c++) { stats[c] = new Stats(); }
 for (int r = startRow; r < table.size(); r++) {
 List<String> row = table.get(r);
 for (int c = 0; c < cols && c < row.size(); c++) {
 String cell = row.get(c);
 if (isNumber(cell)) { numeric[c] = true; stats[c].add(Long.parseLong(cell)); }
 }
 }

 System.out.println("\nFormatted Table:");
 for (List<String> row : table) {
 StringBuilder line = new StringBuilder("| ");
 for (int c = 0; c < cols; c++) {
 String cell = c < row.size() ? row.get(c) : "";
 line.append(pad(cell, width[c])).append(" | ");
 }
 System.out.println(line);
 }
 System.out.println("\nColumn Statistics (numeric columns):");
 for (int c = 0; c < cols; c++) {
 if (numeric[c] && stats[c].count > 0) {
 System.out.printf("Col %d -> min=%d max=%d avg=%.2f (n=%d)%n",
 c, stats[c].min, stats[c].max, stats[c].avg(), stats[c].count);
 }
 }
 }
}