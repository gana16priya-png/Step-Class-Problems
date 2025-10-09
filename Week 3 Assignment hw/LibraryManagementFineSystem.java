import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/* ----------------- Book Class ----------------- */
class Book {
    String bookId;
    String title;
    String author;
    String isbn;
    String category;
    boolean isIssued;
    LocalDate issueDate;
    LocalDate dueDate;

    static int totalBooks = 0;

    public Book(String bookId, String title, String author, String isbn, String category) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.isIssued = false;
        totalBooks++;
    }

    @Override
    public String toString() {
        return bookId + " | " + title + " | " + author + " | " + category + " | " + (isIssued ? "Issued" : "Available");
    }
}

/* ----------------- Member Class ----------------- */
class Member {
    String memberId;
    String memberName;
    String memberType; // Student, Faculty, General
    List<Book> booksIssued;
    double totalFines;
    LocalDate membershipDate;

    static int totalMembers = 0;
    static String libraryName = "City Central Library";
    static double finePerDay = 5.0;
    static int maxBooksAllowed = 3;

    public Member(String memberId, String memberName, String memberType, String membershipDate) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberType = memberType;
        this.booksIssued = new ArrayList<>();
        this.totalFines = 0.0;
        this.membershipDate = LocalDate.parse(membershipDate);
        totalMembers++;
    }

    /* ---------- Business Methods ---------- */
    public void issueBook(Book book, String issueDate, int daysAllowed) {
        if (booksIssued.size() >= maxBooksAllowed) {
            System.out.println("Cannot issue more books! Limit reached for " + memberName);
            return;
        }
        if (book.isIssued) {
            System.out.println("Book already issued: " + book.title);
            return;
        }
        LocalDate issue = LocalDate.parse(issueDate);
        LocalDate due = issue.plusDays(daysAllowed);

        book.isIssued = true;
        book.issueDate = issue;
        book.dueDate = due;
        booksIssued.add(book);

        System.out.println("Book issued: " + book.title + " to " + memberName + " (Due: " + due + ")");
    }

    public void returnBook(String bookId, String returnDate) {
        Book toReturn = null;
        for (Book b : booksIssued) {
            if (b.bookId.equals(bookId)) {
                toReturn = b;
                break;
            }
        }
        if (toReturn == null) {
            System.out.println("Book not found in member's issued list.");
            return;
        }
        LocalDate retDate = LocalDate.parse(returnDate);
        double fine = calculateFine(toReturn, retDate);
        totalFines += fine;

        toReturn.isIssued = false;
        toReturn.issueDate = null;
        toReturn.dueDate = null;
        booksIssued.remove(toReturn);

        System.out.println("Book returned: " + toReturn.title + ". Fine: ₹" + fine);
    }

    public double calculateFine(Book book, LocalDate returnDate) {
        if (returnDate.isAfter(book.dueDate)) {
            long overdueDays = ChronoUnit.DAYS.between(book.dueDate, returnDate);
            return overdueDays * finePerDay;
        }
        return 0.0;
    }

    public void renewBook(String bookId, int extraDays) {
        for (Book b : booksIssued) {
            if (b.bookId.equals(bookId)) {
                b.dueDate = b.dueDate.plusDays(extraDays);
                System.out.println("Book renewed: " + b.title + " | New Due Date: " + b.dueDate);
                return;
            }
        }
        System.out.println("Book not found for renewal.");
    }

    public void searchBooks(List<Book> allBooks, String keyword) {
        System.out.println("Search results for '" + keyword + "':");
        for (Book b : allBooks) {
            if (b.title.toLowerCase().contains(keyword.toLowerCase()) ||
                b.author.toLowerCase().contains(keyword.toLowerCase()) ||
                b.category.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("   " + b);
            }
        }
    }

    public void reserveBook(Book book) {
        if (book.isIssued) {
            System.out.println(memberName + " reserved the book: " + book.title);
        } else {
            System.out.println("Book is available, no need to reserve.");
        }
    }

    public void showMemberSummary() {
        System.out.println("Member: " + memberName + " (" + memberType + ")");
        System.out.println("   Books Issued: " + booksIssued.size());
        for (Book b : booksIssued) {
            System.out.println("      - " + b.title + " (Due: " + b.dueDate + ")");
        }
        System.out.println("   Total Fines: ₹" + totalFines);
    }

    /* ---------- Static Reports ---------- */
    public static void generateLibraryReport(List<Member> members, List<Book> books) {
        System.out.println("\nLibrary Report (" + libraryName + ")");
        System.out.println("   Total Members: " + totalMembers);
        System.out.println("   Total Books: " + Book.totalBooks);

        long issuedCount = books.stream().filter(b -> b.isIssued).count();
        System.out.println("   Books Issued: " + issuedCount);

        double totalFinesCollected = members.stream().mapToDouble(m -> m.totalFines).sum();
        System.out.println("   Total Fines Collected: ₹" + totalFinesCollected);
    }

    public static List<Book> getOverdueBooks(List<Book> books, String todayDate) {
        LocalDate today = LocalDate.parse(todayDate);
        List<Book> overdue = new ArrayList<>();
        for (Book b : books) {
            if (b.isIssued && today.isAfter(b.dueDate)) {
                overdue.add(b);
            }
        }
        return overdue;
    }

    public static void getMostPopularBooks(Map<String, Integer> issueStats) {
        System.out.println("\nMost Popular Books:");
        issueStats.entrySet()
                  .stream()
                  .sorted((a, b) -> b.getValue() - a.getValue())
                  .limit(3)
                  .forEach(e -> System.out.println("   " + e.getKey() + " | Issued " + e.getValue() + " times"));
    }
}

/* ----------------- Main Test ----------------- */
public class LibraryManagementFineSystem {
    public static void main(String[] args) {
        // Create books
        List<Book> books = new ArrayList<>();
        books.add(new Book("B1", "Java Programming", "Herbert Schildt", "111", "Education"));
        books.add(new Book("B2", "Python Basics", "Mark Lutz", "222", "Education"));
        books.add(new Book("B3", "Rich Dad Poor Dad", "Robert Kiyosaki", "333", "Finance"));
        books.add(new Book("B4", "The Alchemist", "Paulo Coelho", "444", "Fiction"));

        // Issue statistics tracker
        Map<String, Integer> issueStats = new HashMap<>();

        // Create members
        Member m1 = new Member("M1", "Alice", "Student", "2025-01-10");
        Member m2 = new Member("M2", "Bob", "Faculty", "2025-02-01");

        List<Member> members = Arrays.asList(m1, m2);

        // Workflow
        m1.issueBook(books.get(0), "2025-02-01", 7);
        issueStats.put(books.get(0).title, issueStats.getOrDefault(books.get(0).title, 0) + 1);

        m1.issueBook(books.get(1), "2025-02-01", 7);
        issueStats.put(books.get(1).title, issueStats.getOrDefault(books.get(1).title, 0) + 1);

        m2.issueBook(books.get(2), "2025-02-05", 10);
        issueStats.put(books.get(2).title, issueStats.getOrDefault(books.get(2).title, 0) + 1);

        // Return book late
        m1.returnBook("B1", "2025-02-15");

        // Renew book
        m2.renewBook("B3", 5);

        // Search books
        m1.searchBooks(books, "java");

        // Reserve
        m1.reserveBook(books.get(2));

        // Show summaries
        m1.showMemberSummary();
        m2.showMemberSummary();

        // Library report
        Member.generateLibraryReport(members, books);

        // Overdue books
        System.out.println("\nOverdue Books:");
        for (Book b : Member.getOverdueBooks(books, "2025-02-20")) {
            System.out.println("   " + b.title + " (Due: " + b.dueDate + ")");
        }

        // Popular books
        Member.getMostPopularBooks(issueStats);
    }
}
