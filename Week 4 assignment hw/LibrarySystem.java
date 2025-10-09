class Book {
    String title;
    String author;
    String isbn;
    boolean isAvailable;

    // 1. Default constructor empty book
    Book() {
        this.title = "Unknown";
        this.author = "Unknown";
        this.isbn = "N/A";
        this.isAvailable = true;
    }

    // 2. Constructor with title and author
    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isbn = "N/A";
        this.isAvailable = true;
    }

    // 3. Constructor with all details
    Book(String title, String author, String isbn, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    // Borrow book
    void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("'" + title + "' has been borrowed.");
        } else {
            System.out.println("'" + title + "' is already borrowed.");
        }
    }

    // Return book
    void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("'" + title + "' has been returned.");
        } else {
            System.out.println("'" + title + "' was not borrowed.");
        }
    }

    // Display book info
    void displayBookInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("-----------------------------------");
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Book b1 = new Book();
        Book b2 = new Book("The Alchemist", "Paulo Coelho");
        Book b3 = new Book("1984", "George Orwell", "9780451524935", true);

        b1.displayBookInfo();
        b2.displayBookInfo();
        b3.displayBookInfo();

        b2.borrowBook();
        b2.displayBookInfo();

        b2.borrowBook();
        b2.returnBook();
        b2.displayBookInfo();

        b3.borrowBook();
        b3.returnBook();
    }
}
