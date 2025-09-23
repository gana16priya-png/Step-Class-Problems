class Book {
    String title;
    String author;
    String isbn;
    boolean isAvailable;

    // 1. Default constructor → empty book
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

    // ---- Methods ----
    void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("✅ Book borrowed: " + title);
        } else {
            System.out.println("⚠️ Book already borrowed: " + title);
        }
    }

    void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("📖 Book returned: " + title);
        } else {
            System.out.println("⚠️ Book was not borrowed: " + title);
        }
    }

    void displayBookInfo() {
        System.out.println("📚 Book Info:");
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("---------------------------");
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        // Create books using different constructors
        Book b1 = new Book();
        Book b2 = new Book("1984", "George Orwell");
        Book b3 = new Book("The Hobbit", "J.R.R. Tolkien", "978-0547928227", true);

        // Borrow and return operations
        b1.borrowBook();
        b2.borrowBook();
        b2.borrowBook();  // try borrowing again
        b2.returnBook();

        // Display book info
        b1.displayBookInfo();
        b2.displayBookInfo();
        b3.displayBookInfo();
    }
}
