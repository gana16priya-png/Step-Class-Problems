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

    public String getSubjectName() {
        return subjectName;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public int getPassMarks() {
        return passMarks;
    }
}

class Student {
    private String studentId;
    private String studentName;
    private int grade;
    private double[] marks; // 5 subjects
    private double totalMarks;
    private double percentage;

    public Student(String studentId, String studentName, int grade) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.grade = grade;
        this.marks = new double[5];
        this.totalMarks = 0;
        this.percentage = 0;
    }

    public void setMarks(int subjectIndex, double mark) {
        if (subjectIndex >= 0 && subjectIndex < marks.length) {
            marks[subjectIndex] = mark;
        }
    }

    public double getMark(int subjectIndex) {
        return marks[subjectIndex];
    }

    public void calculateTotal() {
        totalMarks = 0;
        for (double m : marks) {
            totalMarks += m;
        }
    }

    public void calculatePercentage() {
        percentage = totalMarks / marks.length;
    }

    public boolean isPass(Subject[] subjects) {
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] < subjects[i].getPassMarks()) {
                return false;
            }
        }
        return true;
    }

    public void displayResult(Subject[] subjects) {
        calculateTotal();
        calculatePercentage();
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + studentName);
        System.out.println("Grade: " + grade);
        for (int i = 0; i < marks.length; i++) {
            System.out.println(subjects[i].getSubjectName() + ": " + marks[i]);
        }
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Percentage: " + percentage);
        System.out.println("Result: " + (isPass(subjects) ? "Pass" : "Fail"));
        System.out.println("---------------------------");
    }

    public double getTotalMarks() {
        return totalMarks;
    }

    public double getPercentage() {
        return percentage;
    }

    // Static methods for school-wide statistics
    public static Student getTopStudent(Student[] students) {
        Student top = students[0];
        for (Student s : students) {
            s.calculateTotal();
            if (s.getTotalMarks() > top.getTotalMarks()) {
                top = s;
            }
        }
        return top;
    }

    public static double getClassAverage(Student[] students) {
        double sum = 0;
        for (Student s : students) {
            s.calculateTotal();
            sum += s.getTotalMarks();
        }
        return sum / students.length;
    }

    public static double getPassPercentage(Student[] students, Subject[] subjects) {
        int passCount = 0;
        for (Student s : students) {
            if (s.isPass(subjects)) {
                passCount++;
            }
        }
        return (passCount * 100.0) / students.length;
    }
}

class Teacher {
    private String teacherId;
    private String teacherName;
    private String subject;
    private int studentsHandled;
    private static int totalTeachers = 0;

    public Teacher(String teacherId, String teacherName, String subject) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.subject = subject;
        this.studentsHandled = 0;
        totalTeachers++;
    }

    public void assignGrades(Student student, Subject subject, double marks, int subjectIndex) {
        student.setMarks(subjectIndex, marks);
        studentsHandled++;
    }

    public void displayTeacherInfo() {
        System.out.println("Teacher ID: " + teacherId);
        System.out.println("Name: " + teacherName);
        System.out.println("Subject: " + subject);
        System.out.println("Students Handled: " + studentsHandled);
        System.out.println("---------------------------");
    }

    public static int getTotalTeachers() {
        return totalTeachers;
    }
}

public class SchoolManagementSystem{
    public static void main(String[] args) {
        // Create subjects
        Subject[] subjects = new Subject[5];
        subjects[0] = new Subject("S01", "Math", 100, 35);
        subjects[1] = new Subject("S02", "Science", 100, 35);
        subjects[2] = new Subject("S03", "English", 100, 35);
        subjects[3] = new Subject("S04", "History", 100, 35);
        subjects[4] = new Subject("S05", "Computer", 100, 35);

        // Create students
        Student[] students = new Student[3];
        students[0] = new Student("ST01", "Alice", 10);
        students[1] = new Student("ST02", "Bob", 10);
        students[2] = new Student("ST03", "Charlie", 10);

        // Create teachers
        Teacher[] teachers = new Teacher[2];
        teachers[0] = new Teacher("T01", "Mr. Sharma", "Math");
        teachers[1] = new Teacher("T02", "Ms. Gupta", "Science");

        // Teachers assign grades
        teachers[0].assignGrades(students[0], subjects[0], 90, 0); // Math to Alice
        teachers[0].assignGrades(students[1], subjects[0], 80, 0); // Math to Bob
        teachers[0].assignGrades(students[2], subjects[0], 70, 0); // Math to Charlie

        teachers[1].assignGrades(students[0], subjects[1], 85, 1); // Science to Alice
        teachers[1].assignGrades(students[1], subjects[1], 75, 1); // Science to Bob
        teachers[1].assignGrades(students[2], subjects[1], 65, 1); // Science to Charlie

        // Assign other subjects directly
        students[0].setMarks(2, 95); // English
        students[0].setMarks(3, 88); // History
        students[0].setMarks(4, 92); // Computer

        students[1].setMarks(2, 70);
        students[1].setMarks(3, 60);
        students[1].setMarks(4, 75);

        students[2].setMarks(2, 55);
        students[2].setMarks(3, 50);
        students[2].setMarks(4, 65);

        // Display results for each student
        for (Student s : students) {
            s.displayResult(subjects);
        }

        // Display teacher info
        for (Teacher t : teachers) {
            t.displayTeacherInfo();
        }

        // School-wide statistics
        Student topStudent = Student.getTopStudent(students);
        System.out.println("Top Student: " + topStudent.studentName + " with Total Marks: " + topStudent.getTotalMarks());

        double classAvg = Student.getClassAverage(students);
        System.out.println("Class Average: " + classAvg);

        double passPercent = Student.getPassPercentage(students, subjects);
        System.out.println("Pass Percentage: " + passPercent + "%");

        System.out.println("Total Teachers: " + Teacher.getTotalTeachers());
    }
}