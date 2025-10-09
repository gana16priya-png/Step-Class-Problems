import java.util.Scanner;
public class Problem4_CaesarCipher {
 public static String encrypt(String text, int shift) {
 StringBuilder sb = new StringBuilder();
 for (char c : text.toCharArray()) {
 if (c >= 'A' && c <= 'Z')
 sb.append((char)('A' + (c - 'A' + shift) % 26));
 else if (c >= 'a' && c <= 'z')
 sb.append((char)('a' + (c - 'a' + shift) % 26));
 else
 sb.append(c);
 }
 return sb.toString();
 }
 public static String decrypt(String text, int shift) {
 return encrypt(text, 26 - shift);
 }
 public static void main(String[] args) {
 Scanner sc = new Scanner(System.in);
 System.out.print("Enter text: ");
 String text = sc.nextLine();
 System.out.print("Enter shift: ");
 int shift = sc.nextInt();
 String enc = encrypt(text, shift);
 String dec = decrypt(enc, shift);
 System.out.println("\nOriginal: " + text);
 System.out.println("Encrypted: " + enc);
 System.out.println("Decrypted: " + dec);
 }
}