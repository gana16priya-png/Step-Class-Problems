import java.util.Scanner;

class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean isAvailable;

    private static int totalBooks = 0;
    private static int availableBooks = 0;
    private static int bookCounter = 0;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.bookId = generateBookId();
        totalBooks++;
        availableBooks++;
    }

    public boolean issueBook() {
        if (isAvailable) {
            isAvailable = false;
            availableBooks--;
            return true;
        }
        return false;
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            availableBooks++;
        }
    }

    public void displayBookInfo() {
        System.out.println(bookId + " | " + title + " by " + author + " | " +
                (isAvailable ? "Available" : "Issued"));
    }

    public String getBookId() {
        return bookId;
    }

    public boolean getAvailability() {
        return isAvailable;
    }

    public static int getTotalBooks() {
        return totalBooks;
    }

    public static int getAvailableBooks() {
        return availableBooks;
    }

    private static String generateBookId() {
        bookCounter++;
        return "B" + String.format("%03d", bookCounter);
    }
}

class Member {
    private String memberId;
    private String memberName;
    private String[] booksIssued;
    private int bookCount;

    private static int memberCounter = 0;

    public Member(String memberName) {
        this.memberName = memberName;
        this.memberId = generateMemberId();
        this.booksIssued = new String[3]; // max 3 books per member
        this.bookCount = 0;
    }

    public void borrowBook(Book book) {
        if (bookCount == booksIssued.length) {
            System.out.println(memberName + " cannot borrow more books (limit reached).");
            return;
        }
        if (book.issueBook()) {
            booksIssued[bookCount++] = book.getBookId();
            System.out.println(memberName + " borrowed " + book.getBookId());
        } else {
            System.out.println("Book " + book.getBookId() + " is not available.");
        }
    }

    public void returnBook(String bookId, Book[] books) {
        int index = -1;
        for (int i = 0; i < bookCount; i++) {
            if (booksIssued[i].equals(bookId)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println(memberName + " did not borrow book ID: " + bookId);
            return;
        }
        // Shift array
        for (int i = index; i < bookCount - 1; i++) {
            booksIssued[i] = booksIssued[i + 1];
        }
        booksIssued[--bookCount] = null;

        // Mark book as returned
        for (Book b : books) {
            if (b != null && b.getBookId().equals(bookId)) {
                b.returnBook();
                System.out.println(memberName + " returned " + bookId);
                return;
            }
        }
    }

    public void displayMemberInfo() {
        System.out.print(memberId + " | " + memberName + " | Books: ");
        if (bookCount == 0) {
            System.out.println("None");
        } else {
            for (int i = 0; i < bookCount; i++) {
                System.out.print(booksIssued[i] + (i < bookCount - 1 ? ", " : ""));
            }
            System.out.println();
        }
    }

    private static String generateMemberId() {
        memberCounter++;
        return "M" + String.format("%03d", memberCounter);
    }
}

public class LibraryManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Book[] books = new Book[10];
        Member[] members = new Member[5];
        int bookCount = 0, memberCount = 0;

        while (true) {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Add Book");
            System.out.println("2. Show All Books");
            System.out.println("3. Add Member");
            System.out.println("4. Show All Members");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Show Library Stats");
            System.out.println("8. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: // Add Book
                    if (bookCount == books.length) {
                        System.out.println("Book storage full!");
                        break;
                    }
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    books[bookCount++] = new Book(title, author);
                    System.out.println("Book added successfully.");
                    break;

                case 2: // Show Books
                    System.out.println("\n--- Book List ---");
                    for (int i = 0; i < bookCount; i++) {
                        books[i].displayBookInfo();
                    }
                    break;

                case 3: // Add Member
                    if (memberCount == members.length) {
                        System.out.println("Member storage full!");
                        break;
                    }
                    System.out.print("Enter Member Name: ");
                    String name = sc.nextLine();
                    members[memberCount++] = new Member(name);
                    System.out.println("Member added successfully.");
                    break;

                case 4: // Show Members
                    System.out.println("\n--- Member List ---");
                    for (int i = 0; i < memberCount; i++) {
                        members[i].displayMemberInfo();
                    }
                    break;

                case 5: // Borrow Book
                    System.out.print("Enter Member Index (0-" + (memberCount - 1) + "): ");
                    int mi = sc.nextInt();
                    System.out.print("Enter Book Index (0-" + (bookCount - 1) + "): ");
                    int bi = sc.nextInt();
                    if (mi < memberCount && bi < bookCount) {
                        members[mi].borrowBook(books[bi]);
                    } else {
                        System.out.println("Invalid index!");
                    }
                    break;

                case 6: // Return Book
                    System.out.print("Enter Member Index (0-" + (memberCount - 1) + "): ");
                    mi = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Book ID: ");
                    String bookId = sc.nextLine();
                    if (mi < memberCount) {
                        members[mi].returnBook(bookId, books);
                    } else {
                        System.out.println("Invalid member index!");
                    }
                    break;

                case 7: // Stats
                    System.out.println("Total Books: " + Book.getTotalBooks());
                    System.out.println("Available Books: " + Book.getAvailableBooks());
                    break;

                case 8: // Exit
                    System.out.println("Exiting Library System...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
