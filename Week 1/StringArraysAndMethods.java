import java.util.Scanner;

public class StringArraysAndMethods {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[] students = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter name: ");
            students[i] = sc.nextLine();
        }

        System.out.println("Student Names:");
        for (int i = 0; i < n; i++) {
            System.out.println(students[i]);
        }
    }
}