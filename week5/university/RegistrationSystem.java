package university;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegistrationSystem {
    private final Map<String, Object> enrolledStudents;
    private final Map<String, Course> availableCourses;
    
    // Static final academic policies
    public static final int MAX_CREDIT_HOURS = 18;
    public static final double MIN_GPA_FOR_ENROLLMENT = 2.0;
    public static final String[] ENROLLMENT_PERIODS = {"Fall", "Spring", "Summer"};
    
    public RegistrationSystem() {
        this.enrolledStudents = new HashMap<>();
        this.availableCourses = new HashMap<>();
    }
    
    public boolean enrollStudent(Object student, Object course) {
        if (!(student instanceof Student)) {
            System.out.println("Invalid student type");
            return false;
        }
        
        if (!(course instanceof Course)) {
            System.out.println("Invalid course type");
            return false;
        }
        
        Student s = (Student) student;
        Course c = (Course) course;
        
        // Validate prerequisites
        if (!validatePrerequisites(student, course)) {
            System.out.println("Prerequisites not met for course: " + c.getCourseCode());
            return false;
        }
        
        // Check GPA requirement
        if (s.getAcademicRecord().getCumulativeGPA() < MIN_GPA_FOR_ENROLLMENT) {
            System.out.println("GPA requirement not met for enrollment");
            return false;
        }
        
        // Enroll student
        String enrollmentKey = s.getStudentId() + "_" + c.getCourseCode();
        enrolledStudents.put(enrollmentKey, new Enrollment(s, c));
        
        System.out.println("Student " + s.getStudentId() + " successfully enrolled in " + c.getCourseCode());
        return true;
    }
    
    private boolean validatePrerequisites(Object student, Object course) {
        if (!(student instanceof Student) || !(course instanceof Course)) {
            return false;
        }
        
        Student s = (Student) student;
        Course c = (Course) course;
        
        return s.getAcademicRecord().meetsPrerequisites(c.getCourseCode());
    }
    
    // Package-private methods for academic administration
    Map<String, Object> getEnrolledStudents() {
        return new HashMap<>(enrolledStudents);
    }
    
    boolean removeStudentEnrollment(String studentId, String courseCode) {
        String key = studentId + "_" + courseCode;
        return enrolledStudents.remove(key) != null;
    }
    
    void generateTranscript(String studentId) {
        Student student = findStudentById(studentId);
        if (student != null) {
            System.out.println("=== OFFICIAL TRANSCRIPT ===");
            System.out.println(student.getAcademicRecord().toString());
            System.out.println("Academic Standing: " + student.getAcademicStanding());
        }
    }
    
    private Student findStudentById(String studentId) {
        for (Object enrollment : enrolledStudents.values()) {
            if (enrollment instanceof Enrollment) {
                Enrollment e = (Enrollment) enrollment;
                if (e.getStudent().getStudentId().equals(studentId)) {
                    return e.getStudent();
                }
            }
        }
        return null;
    }
    
    public void addCourse(Course course) {
        availableCourses.put(course.getCourseCode(), course);
    }
    
    public Course getCourse(String courseCode) {
        return availableCourses.get(courseCode);
    }
    
    // Inner class for enrollment records
    private static class Enrollment {
        private final Student student;
        private final Course course;
        
        public Enrollment(Student student, Course course) {
            this.student = Objects.requireNonNull(student);
            this.course = Objects.requireNonNull(course);
        }
        
        public Student getStudent() { return student; }
        public Course getCourse() { return course; }
        
        @Override
        public String toString() {
            return "Enrollment{student=" + student.getStudentId() + 
                   ", course=" + course.getCourseCode() + "}";
        }
    }
}