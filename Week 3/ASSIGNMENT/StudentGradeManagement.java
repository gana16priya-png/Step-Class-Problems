import java.util.*;

class Subject {
    private String subjectCode;
    private String subjectName;
    private int credits;
    private String instructor;

    public Subject(String subjectCode, String subjectName, int credits, String instructor) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credits = credits;
        this.instructor = instructor;
    }

    public String getSubjectCode() { return subjectCode; }
    public String getSubjectName() { return subjectName; }
    public int getCredits() { return credits; }
    public String getInstructor() { return instructor; }
}

class Student {
    private String studentId;
    private String studentName;
    private String className;
    private String[] subjects;
    private double[][] marks; // [subject][exam]
    private double gpa;

    private static int totalStudents = 0;
    private static String schoolName = "Springfield High";
    private static String[] gradingScale = {"A", "B", "C", "D", "F"};
    private static double passPercentage = 40.0;

    public Student(String studentId, String studentName, String className, String[] subjects) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.className = className;
        this.subjects = subjects;
        this.marks = new double[subjects.length][3]; // 3 exams per subject
        this.gpa = 0.0;
        totalStudents++;
    }

    public void addMarks(String subject, double[] examMarks) {
        for (int i = 0; i < subjects.length; i++) {
            if (subjects[i].equals(subject)) {
                for (int j = 0; j < examMarks.length && j < 3; j++) {
                    marks[i][j] = examMarks[j];
                }
            }
        }
    }

    public void calculateGPA() {
        double total = 0;
        int count = 0;
        for (int i = 0; i < subjects.length; i++) {
            for (int j = 0; j < 3; j++) {
                total += marks[i][j];
                count++;
            }
        }
        double avg = (count > 0) ? total / count : 0;
        // GPA scale: 90+ = 4.0, 80+ = 3.0, 70+ = 2.0, 60+ = 1.0, else 0
        if (avg >= 90) gpa = 4.0;
        else if (avg >= 80) gpa = 3.0;
        else if (avg >= 70) gpa = 2.0;
        else if (avg >= 60) gpa = 1.0;
        else gpa = 0.0;
    }

    public String getGradeCategory() {
        double total = 0;
        int count = 0;
        for (int i = 0; i < subjects.length; i++)
            for (int j = 0; j < 3; j++) {
                total += marks[i][j];
                count++;
            }
        double percent = (count > 0) ? total / count : 0;
        if (percent >= 90) return "A";
        else if (percent >= 80) return "B";
        else if (percent >= 70) return "C";
        else if (percent >= 60) return "D";
        else return "F";
    }

    public void generateReportCard() {
        System.out.println("School: " + schoolName);
        System.out.println("Student ID: " + studentId + ", Name: " + studentName + ", Class: " + className);
        for (int i = 0; i < subjects.length; i++) {
            System.out.print(subjects[i] + ": ");
            for (int j = 0; j < 3; j++) {
                System.out.print(marks[i][j] + " ");
            }
            System.out.println();
        }
        calculateGPA();
        System.out.println("GPA: " + gpa);
        System.out.println("Grade: " + getGradeCategory());
        System.out.println("Promotion Eligible: " + (checkPromotionEligibility() ? "Yes" : "No"));
        System.out.println("---------------------------");
    }

    public boolean checkPromotionEligibility() {
        double total = 0;
        int count = 0;
        for (int i = 0; i < subjects.length; i++)
            for (int j = 0; j < 3; j++) {
                total += marks[i][j];
                count++;
            }
        double percent = (count > 0) ? total / count : 0;
        return percent >= passPercentage;
    }

    // Static methods
    public static void setGradingScale(String[] scale) {
        gradingScale = scale;
    }

    public static double calculateClassAverage(Student[] students) {
        double sum = 0;
        int count = 0;
        for (Student s : students) {
            for (int i = 0; i < s.subjects.length; i++)
                for (int j = 0; j < 3; j++) {
                    sum += s.marks[i][j];
                    count++;
                }
        }
        return (count > 0) ? sum / count : 0;
    }

    public static Student[] getTopPerformers(Student[] students, int count) {
        Arrays.sort(students, (a, b) -> {
            a.calculateGPA();
            b.calculateGPA();
            return Double.compare(b.gpa, a.gpa);
        });
        Student[] top = new Student[Math.min(count, students.length)];
        for (int i = 0; i < top.length; i++) top[i] = students[i];
        return top;
    }

    public static void generateSchoolReport(Student[] students) {
        System.out.println("School Report for " + schoolName);
        System.out.println("Total Students: " + totalStudents);
        System.out.println("Class Average: " + calculateClassAverage(students));
        Student[] top = getTopPerformers(students, 3);
        System.out.println("Top Performers:");
        for (Student s : top) {
            System.out.println(s.studentName + " (GPA: " + s.gpa + ", Grade: " + s.getGradeCategory() + ")");
        }
        int promoted = 0;
        for (Student s : students) {
            if (s.checkPromotionEligibility()) promoted++;
        }
        System.out.println("Promotion Rate: " + ((promoted * 100.0) / students.length) + "%");
        System.out.println("---------------------------");
    }
}

public class StudentGradeManagement {
    public static void main(String[] args) {
        // Subjects
        Subject[] subjects = {
            new Subject("M01", "Math", 4, "Mr. Sharma"),
            new Subject("S01", "Science", 4, "Ms. Gupta"),
            new Subject("E01", "English", 3, "Mrs. Singh")
        };
        String[] subjectNames = {subjects[0].getSubjectName(), subjects[1].getSubjectName(), subjects[2].getSubjectName()};

        // Students
        Student[] students = new Student[4];
        students[0] = new Student("ST01", "Alice", "10A", subjectNames);
        students[1] = new Student("ST02", "Bob", "10A", subjectNames);
        students[2] = new Student("ST03", "Charlie", "10B", subjectNames);
        students[3] = new Student("ST04", "Daisy", "10B", subjectNames);

        // Add marks (3 exams per subject)
        students[0].addMarks("Math", new double[]{95, 90, 92});
        students[0].addMarks("Science", new double[]{88, 85, 90});
        students[0].addMarks("English", new double[]{80, 82, 85});

        students[1].addMarks("Math", new double[]{78, 80, 75});
        students[1].addMarks("Science", new double[]{70, 72, 68});
        students[1].addMarks("English", new double[]{85, 80, 78});

        students[2].addMarks("Math", new double[]{60, 65, 62});
        students[2].addMarks("Science", new double[]{55, 58, 60});
        students[2].addMarks("English", new double[]{70, 72, 68});

        students[3].addMarks("Math", new double[]{40, 42, 45});
        students[3].addMarks("Science", new double[]{38, 40, 42});
        students[3].addMarks("English", new double[]{50, 52, 48});

        // Generate report cards
        for (Student s : students) {
            s.generateReportCard();
        }

        // School-wide report
        Student.generateSchoolReport(students);

        // Comparative report for classes
        System.out.println("Comparative Class Report:");
        Map<String, List<Student>> classMap = new HashMap<>();
        for (Student s : students) {
            classMap.putIfAbsent(s.className, new ArrayList<>());
            classMap.get(s.className).add(s);
        }
        for (String cls : classMap.keySet()) {
            List<Student> clsStudents = classMap.get(cls);
            Student[] arr = clsStudents.toArray(new Student[0]);
            System.out.println("Class: " + cls + ", Average: " + Student.calculateClassAverage(arr));
        }
    }
}