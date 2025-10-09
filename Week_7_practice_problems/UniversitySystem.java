// File: UniversitySystem.java

// Base class
class Person {
    protected String name;
    protected int age;
    protected String email;

    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // Common methods
    public void introduce() {
        System.out.println("Hi! I'm " + name + ", " + age + " years old.");
    }

    public void getContactInfo() {
        System.out.println("Email: " + email);
    }
}

// Subclass: Student
class Student extends Person {
    private String studentId;
    private String major;

    public Student(String name, int age, String email, String studentId, String major) {
        super(name, age, email);
        this.studentId = studentId;
        this.major = major;
    }

    public void attendLecture() {
        System.out.println(name + " is attending " + major + " lecture.");
    }

    public void submitAssignment() {
        System.out.println("Assignment submitted by " + studentId);
    }
}

// Subclass: Professor
class Professor extends Person {
    private String department;

    public Professor(String name, int age, String email, String department) {
        super(name, age, email);
        this.department = department;
    }

    public void conductClass() {
        System.out.println("Prof. " + name + " is teaching " + department);
    }
}

// Demo class
public class UniversitySystem {
    public static void main(String[] args) {
        // Step 1: Create Student
        Student s = new Student("Alice", 20, "alice@uni.edu", "CS2021", "Computer Science");

        // Step 2: Upcast Student to Person
        Person p = s; // safe upcasting

        // Step 3: Call parent methods
        p.introduce();
        p.getContactInfo();

        // Step 4: Try calling subclass methods
        // p.attendLecture(); // Compile-time error

        // Step 5: Access parent field
        System.out.println("Name via Person reference: " + p.name);

        // Step 6: Explanation
        System.out.println("\nUpcasting is safe because a Student *is a* Person, but we lose access to subclass-specific methods.");
    }
}
