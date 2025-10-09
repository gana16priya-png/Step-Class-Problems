import java.util.*;

class Subject {
    String subjectCode;
    String subjectName;
    int credits;
    String instructor;

    public Subject(String subjectCode, String subjectName, int credits, String instructor) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credits = credits;
        this.instructor = instructor;
    }
}

class Student {
    String studentId;
    String studentName;
    String className;
    String[] subjects;     // subject codes
    double[] marks;        // parallel to subjects
    double gpa;

    static int totalStudents = 0;
    static String schoolName = "Default School";
    static String[] gradingScale = {"A","B","C","D","F"};
    static double passPercentage = 40.0; // percent

    public Student(String studentId, String studentName, String className, String[] subjects) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.className = className;
        this.subjects = subjects.clone();
        this.marks = new double[subjects.length];
        Arrays.fill(this.marks, -1.0); // -1 indicates not yet entered
        this.gpa = 0.0;
        totalStudents++;
    }

    // add marks for a given subject code
    public boolean addMarks(String subjectCode, double mark) {
        for (int i = 0; i < subjects.length; i++) {
            if (subjects[i].equalsIgnoreCase(subjectCode)) {
                marks[i] = mark;
                return true;
            }
        }
        return false;
    }

    // calculate GPA given subject credit mapping
    public void calculateGPA(Map<String, Subject> subjectMap) {
        double weightedSum = 0.0;
        int totalCredits = 0;
        for (int i = 0; i < subjects.length; i++) {
            String code = subjects[i];
            double mark = marks[i];
            if (mark < 0) continue; // skip if no mark
            Subject s = subjectMap.get(code);
            int credits = (s != null) ? s.credits : 1;
            weightedSum += (mark * credits);
            totalCredits += credits;
        }
        if (totalCredits == 0) {
            this.gpa = 0.0;
        } else {
            double percentage = weightedSum / totalCredits;
            // simple GPA scale: map percentage/10 -> 0-10, then normalize to 10-scale
            this.gpa = (percentage / 100.0) * 10.0;
            // round to 2 decimals
            this.gpa = Math.round(this.gpa * 100.0) / 100.0;
        }
    }

    public String gradeForPercentage(double pct) {
        if (pct >= 85) return "A";
        if (pct >= 70) return "B";
        if (pct >= 55) return "C";
        if (pct >= 40) return "D";
        return "F";
    }

    public void generateReportCard(Map<String, Subject> subjectMap) {
        System.out.println("\nReport Card: " + studentName + " (" + studentId + ")");
        double totalMarks = 0.0;
        int count = 0;
        for (int i = 0; i < subjects.length; i++) {
            String code = subjects[i];
            Subject s = subjectMap.get(code);
            String name = (s != null) ? s.subjectName : code;
            double mark = marks[i];
            String markStr = (mark < 0) ? "N/A" : String.format("%.2f", mark);
            String grade = (mark < 0) ? "N/A" : gradeForPercentage(mark);
            System.out.println(code + " - " + name + " | Marks: " + markStr + " | Grade: " + grade);
            if (mark >= 0) {
                totalMarks += mark;
                count++;
            }
        }
        double percent = (count == 0) ? 0.0 : (totalMarks / count);
        System.out.println("Average Percentage: " + String.format("%.2f", percent) + "%");
        System.out.println("GPA: " + String.format("%.2f", gpa));
        System.out.println("Promotion Eligibility: " + (checkPromotionEligibility() ? "Eligible" : "Not Eligible"));
    }

    public boolean checkPromotionEligibility() {
        // require every entered subject >= passPercentage and overall average >= passPercentage
        double sum = 0.0;
        int count = 0;
        for (double m : marks) {
            if (m < 0) continue; // not counted
            if (m < passPercentage) return false;
            sum += m;
            count++;
        }
        if (count == 0) return false;
        double avg = sum / count;
        return avg >= passPercentage;
    }

    // static helpers
    public static void setGradingScale(String[] scale) {
        gradingScale = scale.clone();
    }

    public static double calculateClassAverage(Student[] students) {
        double total = 0.0;
        int count = 0;
        for (Student s : students) {
            if (s == null) continue;
            double sum = 0.0;
            int c = 0;
            for (double m : s.marks) {
                if (m >= 0) { sum += m; c++; }
            }
            if (c > 0) {
                total += (sum / c);
                count++;
            }
        }
        return (count == 0) ? 0.0 : (total / count);
    }

    public static List<Student> getTopPerformers(Student[] students, int countTop) {
        List<Student> list = new ArrayList<>();
        for (Student s : students) if (s != null) list.add(s);
        list.sort((a,b) -> Double.compare(b.gpa, a.gpa));
        return list.subList(0, Math.min(countTop, list.size()));
    }

    public static void generateSchoolReport(Student[] students) {
        System.out.println("\n--- School Report: " + schoolName + " ---");
        System.out.println("Total Students: " + totalStudents);
        double classAvg = calculateClassAverage(students);
        System.out.println("Overall Class Average Percentage: " + String.format("%.2f", classAvg) + "%");
        List<Student> top = getTopPerformers(students, 3);
        System.out.println("Top Performers:");
        for (Student s : top) {
            System.out.println(s.studentId + " | " + s.studentName + " | GPA: " + s.gpa);
        }
    }
}

public class GradeSystem {
    public static void main(String[] args) {
        // set school details
        Student.schoolName = "Greenfield High School";
        Student.passPercentage = 40.0;

        // create subjects
        Map<String, Subject> subjectMap = new HashMap<>();
        subjectMap.put("MTH101", new Subject("MTH101", "Mathematics", 4, "Mr. Rao"));
        subjectMap.put("PHY101", new Subject("PHY101", "Physics", 3, "Ms. Iyer"));
        subjectMap.put("CHM101", new Subject("CHM101", "Chemistry", 3, "Mr. Singh"));
        subjectMap.put("ENG101", new Subject("ENG101", "English", 2, "Ms. Patel"));

        // create students
        Student s1 = new Student("S001", "Ananya", "10A", new String[]{"MTH101","PHY101","CHM101","ENG101"});
        Student s2 = new Student("S002", "Karan", "10A", new String[]{"MTH101","PHY101","CHM101","ENG101"});
        Student s3 = new Student("S003", "Rhea", "10A", new String[]{"MTH101","PHY101","CHM101","ENG101"});

        Student[] students = new Student[]{s1, s2, s3};

        // add marks
        s1.addMarks("MTH101", 92);
        s1.addMarks("PHY101", 85);
        s1.addMarks("CHM101", 78);
        s1.addMarks("ENG101", 81);

        s2.addMarks("MTH101", 65);
        s2.addMarks("PHY101", 70);
        s2.addMarks("CHM101", 55);
        s2.addMarks("ENG101", 60);

        s3.addMarks("MTH101", 38); // below pass
        s3.addMarks("PHY101", 45);
        s3.addMarks("CHM101", 50);
        s3.addMarks("ENG101", 48);

        // calculate GPA for each student (weighted by credits)
        for (Student s : students) {
            s.calculateGPA(subjectMap);
            s.generateReportCard(subjectMap);
        }

        // school report
        Student.generateSchoolReport(students);
    }
}
