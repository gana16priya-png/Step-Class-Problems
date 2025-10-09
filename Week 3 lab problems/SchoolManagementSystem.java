import java.util.Scanner;

class Subject {
    private String subjectCode;
    private String subjectName;
    private int maxMarks;
    private int passMarks;

    public Subject(String subjectCode, String subjectName, int maxMarks, int passMarks) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.maxMarks = maxMarks;
        this.passMarks = passMarks;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getPassMarks() {
        return passMarks;
    }

    public int getMaxMarks() {
        return maxMarks;
    }
}

class Student {
    private String studentId;
    private String studentName;
    private int grade;
    private double[] marks;
    private double totalMarks;
    private double percentage;

    private static int studentCounter = 0;

    public Student(String studentName, int grade, int numSubjects) {
        this.studentId = generateStudentId();
        this.studentName = studentName;
        this.grade = grade;
        this.marks = new double[numSubjects];
        this.totalMarks = 0;
        this.percentage = 0;
    }

    private static String generateStudentId() {
        studentCounter++;
        return "S" + String.format("%03d", studentCounter);
    }

    public void setMarks(int subjectIndex, double mark) {
        marks[subjectIndex] = mark;
    }

    public void calculateTotal() {
        totalMarks = 0;
        for (double m : marks) {
            totalMarks += m;
        }
    }

    public void calculatePercentage(int numSubjects) {
        this.percentage = totalMarks / numSubjects;
    }

    public boolean isPass(Subject[] subjects) {
        for (int i = 0; i < subjects.length; i++) {
            if (marks[i] < subjects[i].getPassMarks()) {
                return false;
            }
        }
        return true;
    }

    public void displayResult(Subject[] subjects) {
        System.out.println("\n--- Student Report ---");
        System.out.println("ID: " + studentId + " | Name: " + studentName + " | Grade: " + grade);
        for (int i = 0; i < subjects.length; i++) {
            System.out.println(subjects[i].getSubjectName() + ": " + marks[i]);
        }
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Percentage: " + percentage + "%");
        System.out.println("Result: " + (isPass(subjects) ? "PASS" : "FAIL"));
    }

    public String getStudentName() {
        return studentName;
    }

    public double getPercentage() {
        return percentage;
    }

    // static methods
    public static Student getTopStudent(Student[] students) {
        Student top = students[0];
        for (Student s : students) {
            if (s != null && s.getPercentage() > top.getPercentage()) {
                top = s;
            }
        }
        return top;
    }

    public static double getClassAverage(Student[] students) {
        double sum = 0;
        int count = 0;
        for (Student s : students) {
            if (s != null) {
                sum += s.getPercentage();
                count++;
            }
        }
        return (count == 0) ? 0 : sum / count;
    }

    public static double getPassPercentage(Student[] students, Subject[] subjects) {
        int passCount = 0;
        int total = 0;
        for (Student s : students) {
            if (s != null) {
                total++;
                if (s.isPass(subjects)) passCount++;
            }
        }
        return (total == 0) ? 0 : (passCount * 100.0 / total);
    }
}

class Teacher {
    private String teacherId;
    private String teacherName;
    private String subject;
    private int studentsHandled;

    private static int teacherCounter = 0;
    private static int totalTeachers = 0;

    public Teacher(String teacherName, String subject) {
        this.teacherId = generateTeacherId();
        this.teacherName = teacherName;
        this.subject = subject;
        this.studentsHandled = 0;
        totalTeachers++;
    }

    private static String generateTeacherId() {
        teacherCounter++;
        return "T" + String.format("%03d", teacherCounter);
    }

    public void assignGrades(Student student, Subject subject, double marks, int subjectIndex) {
        if (marks > subject.getMaxMarks()) {
            System.out.println("Marks cannot exceed maximum for " + subject.getSubjectName());
            return;
        }
        student.setMarks(subjectIndex, marks);
        student.calculateTotal();
        student.calculatePercentage(5);
        studentsHandled++;
    }

    public void displayTeacherInfo() {
        System.out.println("ID: " + teacherId + " | Name: " + teacherName + " | Subject: " + subject +
                " | Students Handled: " + studentsHandled);
    }

    public static void displayTeacherStats() {
        System.out.println("Total Teachers in School: " + totalTeachers);
    }
}

public class SchoolManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Subjects
        Subject[] subjects = new Subject[5];
        subjects[0] = new Subject("SUB001", "Math", 100, 35);
        subjects[1] = new Subject("SUB002", "Science", 100, 35);
        subjects[2] = new Subject("SUB003", "English", 100, 35);
        subjects[3] = new Subject("SUB004", "History", 100, 35);
        subjects[4] = new Subject("SUB005", "Computer", 100, 35);

        // Students
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();
        Student[] students = new Student[n];

        for (int i = 0; i < n; i++) {
            System.out.print("\nEnter Student Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Grade: ");
            int grade = sc.nextInt();
            sc.nextLine();
            students[i] = new Student(name, grade, subjects.length);
        }

        // Teachers
        Teacher t1 = new Teacher("Mr. Sharma", "Math");
        Teacher t2 = new Teacher("Ms. Gupta", "Science");

        // Assign Marks
        for (Student s : students) {
            System.out.println("\nEntering marks for " + s.getStudentName());
            for (int j = 0; j < subjects.length; j++) {
                System.out.print("Enter marks in " + subjects[j].getSubjectName() + ": ");
                double m = sc.nextDouble();
                t1.assignGrades(s, subjects[j], m, j);
            }
        }

        // Display Results
        for (Student s : students) {
            s.displayResult(subjects);
        }

        // Reports
        System.out.println("\n===== School Reports =====");
        Student top = Student.getTopStudent(students);
        System.out.println("Top Student: " + top.getStudentName() + " (" + top.getPercentage() + "%)");

        System.out.println("Class Average: " + Student.getClassAverage(students) + "%");
        System.out.println("Pass Percentage: " + Student.getPassPercentage(students, subjects) + "%");

        // Teacher Stats
        t1.displayTeacherInfo();
        t2.displayTeacherInfo();
        Teacher.displayTeacherStats();

        sc.close();
    }
}
