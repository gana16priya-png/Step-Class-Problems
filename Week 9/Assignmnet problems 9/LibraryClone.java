import java.util.*;

class Book implements Cloneable {
    String title;

    Book(String title) { this.title = title; }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString() { return title; }
}

class Library implements Cloneable {
    List<Book> books;

    Library(List<Book> books) {
        this.books = books;
    }

    // Shallow clone
    protected Library shallowClone() throws CloneNotSupportedException {
        return (Library) super.clone();
    }

    // Deep clone
    protected Library deepClone() throws CloneNotSupportedException {
        List<Book> clonedBooks = new ArrayList<>();
        for (Book b : books)
            clonedBooks.add((Book) b.clone());
        return new Library(clonedBooks);
    }
}

public class LibraryCloneDemo {
    public static void main(String[] args) throws Exception {
        List<Book> list = new ArrayList<>();
        list.add(new Book("Java"));
        list.add(new Book("Python"));
        Library lib1 = new Library(list);

        Library shallow = lib1.shallowClone();
        Library deep = lib1.deepClone();

        shallow.books.get(0).title = "C++ (Modified)";
        System.out.println("Original Library after shallow clone change: " + lib1.books);
        System.out.println("Original Library after deep clone change: " + deep.books);
    }
}
