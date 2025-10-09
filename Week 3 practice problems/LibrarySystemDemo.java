class Book {
    String title;
    String author;
    String isbn;
    double price;
    int quantity;

    public Book(String title, String author, String isbn, double price, int quantity) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotalValue() {
        return price * quantity;
    }

    public void displayBook() {
        System.out.println(title + " by " + author + " | ISBN: " + isbn +
                           " | Price: " + price + " | Quantity: " + quantity);
    }
}

class Library {
    String libraryName;
    Book[] books;
    int totalBooks;

    public Library(String libraryName, int capacity) {
        this.libraryName = libraryName;
        this.books = new Book[capacity];
        this.totalBooks = 0;
    }

    public void addBook(Book book) {
        if (totalBooks < books.length) {
            books[totalBooks++] = book;
            System.out.println(book.title + " added to library.");
        } else {
            System.out.println("Library is full!");
        }
    }

    public void searchBook(String keyword) {
        boolean found = false;
        for (int i = 0; i < totalBooks; i++) {
            if (books[i].title.contains(keyword) || books[i].author.contains(keyword)) {
                books[i].displayBook();
                found = true;
            }
        }
        if (!found) System.out.println("No book found with keyword: " + keyword);
    }

    public void displayInventory() {
        System.out.println("Library Inventory:");
        for (int i = 0; i < totalBooks; i++) {
            books[i].displayBook();
        }
    }

    public double calculateTotalValue() {
        double total = 0;
        for (int i = 0; i < totalBooks; i++) {
            total += books[i].getTotalValue();
        }
        return total;
    }
}

public class LibrarySystemDemo {
    public static void main(String[] args) {
        Library lib = new Library("City Library", 5);

        Book b1 = new Book("Java Basics", "John Doe", "ISBN001", 500, 3);
        Book b2 = new Book("Python Guide", "Jane Smith", "ISBN002", 700, 2);

        lib.addBook(b1);
        lib.addBook(b2);

        lib.displayInventory();
        lib.searchBook("Java");

        System.out.println("Total Value of Books: " + lib.calculateTotalValue());
    }
}
