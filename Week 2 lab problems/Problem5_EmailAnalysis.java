import java.util.Scanner;
public class Problem5_EmailAnalysis {
 public static boolean isValid(String email) {
 int at = email.indexOf('@');
 int lastAt = email.lastIndexOf('@');
 int dot = email.lastIndexOf('.');
 return at > 0 && at == lastAt && dot > at + 1 && dot < email.length() - 1;
 }
 public static void analyze(String email) {
 if (!isValid(email)) {
 System.out.println(email + " → Invalid");
 return;
 }
 String username = email.substring(0, email.indexOf('@'));
 String domain = email.substring(email.indexOf('@') + 1);
 String domainName = domain.contains(".") ? domain.substring(0,
domain.indexOf('.')) : domain;
 String extension = domain.contains(".") ? domain.substring(domain.indexOf('.') + 1)
: "";
 System.out.println(email + " → " + username + " | " + domain + " | " + domainName
+ " | " + extension + " | Valid");
 }
 public static void main(String[] args) {
 Scanner sc = new Scanner(System.in);
 System.out.print("Enter number of emails: ");
 int n = sc.nextInt(); sc.nextLine();
 for (int i = 0; i < n; i++) {
 System.out.print("Enter email: ");
 String email = sc.nextLine();
 analyze(email);
 }
 }
}