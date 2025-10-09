// Base class
class LibraryUser {
    String name;

    public LibraryUser(String name) {
        this.name = name;
    }

    public void enterLibrary() {
        System.out.println(name + " entered the library.");
    }

    public void displayInfo() {
        System.out.println("Library user: " + name);
    }
}

// Student subclass
class Student extends LibraryUser {
    public Student(String name) {
        super(name);
    }

    public void borrowBook() {
        System.out.println(name + " borrowed a book.");
    }

    public void accessComputer() {
        System.out.println(name + " accessed a computer.");
    }
}

// Faculty subclass
class Faculty extends LibraryUser {
    public Faculty(String name) {
        super(name);
    }

    public void reserveBook() {
        System.out.println(name + " reserved a book.");
    }

    public void accessResearchDatabase() {
        System.out.println(name + " accessed the research database.");
    }
}

// Guest subclass
class Guest extends LibraryUser {
    public Guest(String name) {
        super(name);
    }

    public void browseBooks() {
        System.out.println(name + " is browsing books.");
    }
}

public class UniversityLibrarySystem {
    public static void main(String[] args) {
        // Upcasting: Store different user types as general LibraryUser
        LibraryUser u1 = new Student("Alice");
        LibraryUser u2 = new Faculty("Bob");
        LibraryUser u3 = new Guest("Charlie");

        // Common operations available to all
        u1.enterLibrary();
        u2.enterLibrary();
        u3.enterLibrary();

        u1.displayInfo();
        u2.displayInfo();
        u3.displayInfo();

        // Only general operations are accessible (specific ones are hidden here)
    }
}
