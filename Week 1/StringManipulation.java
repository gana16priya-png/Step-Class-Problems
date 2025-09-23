public class StringManipulation {
    public static void main(String[] args) {

        String str1 = "Programming";

        String str2 = new String("Java Programming");

        char[] charArray = {'J','a','v','a',' ','P','r','o','g','r','a','m','m','i','n','g'};
        String str3 = new String(charArray);
        System.out.println("Comparing str1 and str2  : " + (str1 == str2));
        System.out.println("Comparing str1 and str2 : " + str1.equals(str2));

        System.out.println("Comparing str1 and str3 : " + (str1 == str3));
        System.out.println("Comparing str1 and str3 ': " + str1.equals(str3));

        System.out.println("\nExplanation:");
        System.out.println("'==' compares references (memory addresses), not actual content.");
        System.out.println("'.equals()' compares the content of the strings.");
        String quote = "Programming Quote:\n\t\"Code is poetry\" - Unknown\n\tPath: C:\\Java\\Projects";
        System.out.println("\n" + quote);
    }
}