public class Book {
    String title;
    String author;
    double price;

    // Default constructor
    public Book() {
        this.title = "Unknown Title";
        this.author = "Unknown Author";
        this.price = 0.0;
    }

    // Parameterized constructor
    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Display method
    public void display() {
        System.out.println("Title: " + title + ", Author: " + author + ", Price: Rs." + price);
    }

    public static void main(String[] args) {
        // Create book1 using default constructor
        Book book1 = new Book();
        // Create book2 using parameterized constructor
        Book book2 = new Book("Java Programming", "James Gosling", 499.0);
        // Display both books
        book1.display();
        book2.display();
    }
}