public class Student{
    // Private instance variables
    private String studentId;
    private String name;
    private double grade;
    private String course;

    // Default constructor
    public Student() {
        this.studentId = "";
        this.name = "";
        this.grade = 0.0;
        this.course = "";
    }

    // Parameterized constructor
    public Student(String studentId, String name, double grade, String course) {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
        this.course = course;
    }

    // Getter and Setter methods
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }
    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }

    // Method to calculate letter grade
    public String calculateLetterGrade() {
        if (grade >= 90 && grade <= 100) return "A";
        else if (grade >= 80) return "B";
        else if (grade >= 70) return "C";
        else if (grade >= 60) return "D";
        else return "F";
    }

    // Method to display student information
    public void displayStudent() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Course: " + course);
        System.out.println("Grade: " + grade);
        System.out.println("Letter Grade: " + calculateLetterGrade());
        System.out.println("---------------------------");
    }

    public static void main(String[] args) {
        // Create one student using default constructor, then set values
        Student s1 = new Student();
        s1.setStudentId("S101");
        s1.setName("Alice");
        s1.setGrade(85.5);
        s1.setCourse("Mathematics");

        // Create another student using parameterized constructor
        Student s2 = new Student("S102", "Bob", 72.0, "Physics");

        // Demonstrate getter/setter methods
        System.out.println("Student 1 Name (getter): " + s1.getName());
        s2.setGrade(78.0); // Update grade using setter

        // Show both students' information and letter grades
        s1.displayStudent();
        s2.displayStudent();