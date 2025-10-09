import java.util.*;

public class Problem2_PasswordAnalyzer {
    public static int[] analyze(String pw) {
        int upper=0, lower=0, digit=0, special=0;
        for (int i=0;i<pw.length();i++) {
            char c = pw.charAt(i);
            if (c>='A' && c<='Z') upper++;
            else if (c>='a' && c<='z') lower++;
            else if (c>='0' && c<='9') digit++;
            else special++;
        }
        return new int[]{upper,lower,digit,special};
    }
    public static int score(String pw, int[] cnt) {
        int s=0;
        if (pw.length()>8) s += (pw.length()-8)*2;
        if (cnt[0]>0) s+=10;
        if (cnt[1]>0) s+=10;
        if (cnt[2]>0) s+=10;
        if (cnt[3]>0) s+=10;
        String low = pw.toLowerCase();
        if (low.contains("123") || low.contains("abc") || low.contains("qwerty"))
            s -= 10;
        return Math.max(s,0);
    }
    public static String strength(int sc) {
        if (sc<=20) return "Weak";
        else if (sc<=50) return "Medium";
        else return "Strong";
    }
    public static String generate(int len) {
        String all="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";
        Random r=new Random();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<len;i++) {
            sb.append(all.charAt(r.nextInt(all.length())));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.print("How many passwords? ");
        int n=sc.nextInt(); sc.nextLine();

        System.out.printf("%-12s %-5s %-5s %-5s %-5s %-5s %-8s%n",
                "Password","Len","U","L","D","S","Strength");

        for(int i=0;i<n;i++) {
            String pw=sc.nextLine();
            int[] c=analyze(pw);
            int scVal=score(pw,c);
            System.out.printf("%-12s %-5d %-5d %-5d %-5d %-5d %-8s%n",
                    pw,pw.length(),c[0],c[1],c[2],c[3],strength(scVal));
        }

        System.out.print("\nEnter length for new strong password: ");
        int len=sc.nextInt();
        System.out.println("Generated: "+generate(len));
    }
}
