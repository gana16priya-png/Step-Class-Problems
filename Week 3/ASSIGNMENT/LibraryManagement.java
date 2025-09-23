import java.util.*;

class Book {
    private String bookId;
    private String title;
    private String author;
    private String isbn;
    private String category;
    private boolean isIssued;
    private String issueDate;
    private String dueDate;
    private int timesIssued;

    private static int totalBooks = 0;
    private static String libraryName = "City Library";
    private static double finePerDay = 2.0;

    public Book(String bookId, String title, String author, String isbn, String category) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.isIssued = false;
        this.issueDate = "";
        this.dueDate = "";
        this.timesIssued = 0;
        totalBooks++;
    }

    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public String getCategory() { return category; }
    public boolean getIsIssued() { return isIssued; }
    public String getIssueDate() { return issueDate; }
    public String getDueDate() { return dueDate; }
    public int getTimesIssued() { return timesIssued; }

    public void issue(String issueDate, String dueDate) {
        isIssued = true;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        timesIssued++;
    }

    public void returnBook() {
        isIssued = false;
        this.issueDate = "";
        this.dueDate = "";
    }

    public void displayBook() {
        System.out.println(bookId + " | " + title + " | " + author + " | " + category + " | Issued: " + isIssued);
    }

    public static int getTotalBooks() { return totalBooks; }
    public static String getLibraryName() { return libraryName; }
    public static double getFinePerDay() { return finePerDay; }

    public static void setFinePerDay(double fine) { finePerDay = fine; }

    public static void generateLibraryReport(Book[] books, Member[] members) {
        System.out.println("Library: " + libraryName);
        System.out.println("Total Books: " + totalBooks);
        System.out.println("Total Members: " + Member.getTotalMembers());
        int issued = 0;
        for (Book b : books) if (b.getIsIssued()) issued++;
        System.out.println("Books Currently Issued: " + issued);
        System.out.println("Most Popular Books:");
        getMostPopularBooks(books);
        System.out.println("---------------------------");
    }

    public static void getOverdueBooks(Book[] books, String currentDate) {
        System.out.println("Overdue Books:");
        for (Book b : books) {
            if (b.getIsIssued() && compareDates(currentDate, b.getDueDate()) > 0) {
                System.out.println(b.getBookId() + " - " + b.getTitle() + " (Due: " + b.getDueDate() + ")");
            }
        }
    }

    public static void getMostPopularBooks(Book[] books) {
        Arrays.sort(books, (a, b) -> b.getTimesIssued() - a.getTimesIssued());
        for (int i = 0; i < Math.min(3, books.length); i++) {
            System.out.println(books[i].getTitle() + " (Issued " + books[i].getTimesIssued() + " times)");
        }
    }

    // Helper for date comparison (format: dd-mm-yyyy)
    public static int compareDates(String d1, String d2) {
        try {
            String[] p1 = d1.split("-");
            String[] p2 = d2.split("-");
            int date1 = Integer.parseInt(p1[2] + p1[1] + p1[0]);
            int date2 = Integer.parseInt(p2[2] + p2[1] + p2[0]);
            return date1 - date2;
        } catch (Exception e) {
            return 0;
        }
    }
}

class Member {
    private String memberId;
    private String memberName;
    private String memberType;
    private Book[] booksIssued;
    private int booksCount;
    private double totalFines;
    private String membershipDate;

    private static int totalMembers = 0;
    private static int maxBooksAllowed = 3;

    public Member(String memberId, String memberName, String memberType, String membershipDate) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberType = memberType;
        this.membershipDate = membershipDate;
        this.booksIssued = new Book[maxBooksAllowed];
        this.booksCount = 0;
        this.totalFines = 0;
        totalMembers++;
        if (memberType.equalsIgnoreCase("Faculty")) maxBooksAllowed = 5;
        else if (memberType.equalsIgnoreCase("Student")) maxBooksAllowed = 3;
        else maxBooksAllowed = 2;
    }

    public String getMemberId() { return memberId; }
    public String getMemberName() { return memberName; }
    public String getMemberType() { return memberType; }
    public double getTotalFines() { return totalFines; }
    public int getBooksCount() { return booksCount; }
    public static int getTotalMembers() { return totalMembers; }

    public boolean issueBook(Book book, String issueDate, String dueDate) {
        if (booksCount >= maxBooksAllowed) {
            System.out.println("Cannot issue more books. Limit reached.");
            return false;
        }
        if (book.getIsIssued()) {
            System.out.println("Book already issued.");
            return false;
        }
        booksIssued[booksCount++] = book;
        book.issue(issueDate, dueDate);
        System.out.println("Book issued: " + book.getTitle());
        return true;
    }

    public boolean returnBook(Book book, String returnDate) {
        for (int i = 0; i < booksCount; i++) {
            if (booksIssued[i] != null && booksIssued[i].getBookId().equals(book.getBookId())) {
                int overdueDays = calculateOverdueDays(book.getDueDate(), returnDate);
                if (overdueDays > 0) {
                    double fine = overdueDays * Book.getFinePerDay();
                    totalFines += fine;
                    System.out.println("Fine for overdue: Rs." + fine);
                }
                book.returnBook();
                booksIssued[i] = null;
                // Shift remaining books
                for (int j = i; j < booksCount - 1; j++) {
                    booksIssued[j] = booksIssued[j + 1];
                }
                booksIssued[booksCount - 1] = null;
                booksCount--;
                System.out.println("Book returned: " + book.getTitle());
                return true;
            }
        }
        System.out.println("Book not found in issued list.");
        return false;
    }

    public void renewBook(Book book, String newDueDate) {
        for (int i = 0; i < booksCount; i++) {
            if (booksIssued[i] != null && booksIssued[i].getBookId().equals(book.getBookId())) {
                booksIssued[i].issue(booksIssued[i].getIssueDate(), newDueDate);
                System.out.println("Book renewed: " + book.getTitle() + " New Due Date: " + newDueDate);
                return;
            }
        }
        System.out.println("Book not found in issued list.");
    }

    public void searchBooks(Book[] books, String keyword) {
        System.out.println("Search Results for \"" + keyword + "\":");
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                b.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                b.getCategory().toLowerCase().contains(keyword.toLowerCase())) {
                b.displayBook();
            }
        }
    }

    public void reserveBook(Book book) {
        if (!book.getIsIssued()) {
            System.out.println("Book is available. No need to reserve.");
        } else {
            System.out.println("Book reserved: " + book.getTitle());
        }
    }

    public void displayMember() {
        System.out.println("Member ID: " + memberId + ", Name: " + memberName + ", Type: " + memberType + ", Fines: Rs." + totalFines);
        System.out.print("Books Issued: ");
        for (int i = 0; i < booksCount; i++) {
            if (booksIssued[i] != null) System.out.print(booksIssued[i].getTitle() + " | ");
        }
        System.out.println();
    }

    private int calculateOverdueDays(String dueDate, String returnDate) {
        // Simple date diff (format: dd-mm-yyyy)
        try {
            String[] d1 = dueDate.split("-");
            String[] d2 = returnDate.split("-");
            Calendar cal1 = Calendar.getInstance();
            cal1.set(Integer.parseInt(d1[2]), Integer.parseInt(d1[1]), Integer.parseInt(d1[0]));
            Calendar cal2 = Calendar.getInstance();
            cal2.set(Integer.parseInt(d2[2]), Integer.parseInt(d2[1]), Integer.parseInt(d2[0]));
            long diff = (cal2.getTimeInMillis() - cal1.getTimeInMillis()) / (1000 * 60 * 60 * 24);
            return (int) diff > 0 ? (int) diff : 0;
        } catch (Exception e) {
            return 0;
        }
    }
}

public class LibraryManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Books
        Book[] books = {
            new Book("B001", "Java Programming", "James Gosling", "123456", "Education"),
            new Book("B002", "Harry Potter", "J.K. Rowling", "234567", "Fiction"),
            new Book("B003", "Data Structures", "Mark Allen", "345678", "Education"),
            new Book("B004", "The Alchemist", "Paulo Coelho", "456789", "Fiction"),
            new Book("B005", "Algorithms", "Robert Sedgewick", "567890", "Education")
        };

        // Members
        Member[] members = {
            new Member("M001", "Alice", "Student", "01-01-2022"),
            new Member("M002", "Bob", "Faculty", "15-02-2022"),
            new Member("M003", "Charlie", "General", "10-03-2022")
        };

        while (true) {
            System.out.println("\n--- Library Management Menu ---");
            System.out.println("1. Issue Book");
            System.out.println("2. Return Book");
            System.out.println("3. Renew Book");
            System.out.println("4. Search Books");
            System.out.println("5. Reserve Book");
            System.out.println("6. Member Info");
            System.out.println("7. Library Report");
            System.out.println("8. Overdue Books");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Member ID: ");
                    String memId = sc.nextLine();
                    Member mem = null;
                    for (Member m : members) if (m.getMemberId().equals(memId)) mem = m;
                    if (mem == null) { System.out.println("Member not found."); break; }
                    System.out.print("Enter Book ID: ");
                    String bookId = sc.nextLine();
                    Book book = null;
                    for (Book b : books) if (b.getBookId().equals(bookId)) book = b;
                    if (book == null) { System.out.println("Book not found."); break; }
                    System.out.print("Enter Issue Date (dd-mm-yyyy): ");
                    String issueDate = sc.nextLine();
                    System.out.print("Enter Due Date (dd-mm-yyyy): ");
                    String dueDate = sc.nextLine();
                    mem.issueBook(book, issueDate, dueDate);
                    break;
                case 2:
                    System.out.print("Enter Member ID: ");
                    memId = sc.nextLine();
                    mem = null;
                    for (Member m : members) if (m.getMemberId().equals(memId)) mem = m;
                    if (mem == null) { System.out.println("Member not found."); break; }
                    System.out.print("Enter Book ID: ");
                    bookId = sc.nextLine();
                    book = null;
                    for (Book b : books) if (b.getBookId().equals(bookId)) book = b;
                    if (book == null) { System.out.println("Book not found."); break; }
                    System.out.print("Enter Return Date (dd-mm-yyyy): ");
                    String returnDate = sc.nextLine();
                    mem.returnBook(book, returnDate);
                    break;
                case 3:
                    System.out.print("Enter Member ID: ");
                    memId = sc.nextLine();
                    mem = null;
                    for (Member m : members) if (m.getMemberId().equals(memId)) mem = m;
                    if (mem == null) { System.out.println("Member not found."); break; }
                    System.out.print("Enter Book ID: ");
                    bookId = sc.nextLine();
                    book = null;
                    for (Book b : books) if (b.getBookId().equals(bookId)) book = b;
                    if (book == null) { System.out.println("Book not found."); break; }
                    System.out.print("Enter New Due Date (dd-mm-yyyy): ");
                    String newDueDate = sc.nextLine();
                    mem.renewBook(book, newDueDate);
                    break;
                case 4:
                    System.out.print("Enter Member ID: ");
                    memId = sc.nextLine();
                    mem = null;
                    for (Member m : members) if (m.getMemberId().equals(memId)) mem = m;
                    if (mem == null) { System.out.println("Member not found."); break; }
                    System.out.print("Enter keyword to search: ");
                    String keyword = sc.nextLine();
                    mem.searchBooks(books, keyword);
                    break;
                case 5:
                    System.out.print("Enter Member ID: ");
                    memId = sc.nextLine();
                    mem = null;
                    for (Member m : members) if (m.getMemberId().equals(memId)) mem = m;
                    if (mem == null) { System.out.println("Member not found."); break; }
                    System.out.print("Enter Book ID: ");
                    bookId = sc.nextLine();
                    book = null;
                    for (Book b : books) if (b.getBookId().equals(bookId)) book = b;
                    if (book == null) { System.out.println("Book not found."); break; }
                    mem.reserveBook(book);
                    break;
                case 6:
                    System.out.print("Enter Member ID: ");
                    memId = sc.nextLine();
                    mem = null;
                    for (Member m : members) if (m.getMemberId().equals(memId)) mem = m;
                    if (mem == null) { System.out.println("Member not found."); break; }
                    mem.displayMember();
                    break;
                case 7:
                    Book.generateLibraryReport(books, members);
                    break;
                case 8:
                    System.out.print("Enter current date (dd-mm-yyyy): ");
                    String currDate = sc.nextLine();
                    Book.getOverdueBooks(books, currDate);
                    break;
                case 9:
                    System.out.println("Thank you for using the Library Management System!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}