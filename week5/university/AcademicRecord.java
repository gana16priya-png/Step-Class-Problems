package week5.university;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class AcademicRecord {
    private final String studentId;
    private final String major;
    private final LocalDate enrollmentDate;
    private final Map<String, String> completedCourses;
    private final double cumulativeGPA;
    private final String[] academicHonors;
    
    // Course prerequisites mapping
    private static final Map<String, String[]> PREREQUISITES = new HashMap<>();
    
    static {
        PREREQUISITES.put("CS201", new String[]{"CS101"});
        PREREQUISITES.put("CS301", new String[]{"CS201", "MATH201"});
        PREREQUISITES.put("MATH201", new String[]{"MATH101"});
        PREREQUISITES.put("PHYS201", new String[]{"MATH101", "PHYS101"});
    }
    
    public AcademicRecord(String studentId, String major, LocalDate enrollmentDate,
                         Map<String, String> completedCourses, String[] academicHonors) {
        this.studentId = Objects.requireNonNull(studentId, "Student ID cannot be null");
        this.major = Objects.requireNonNull(major, "Major cannot be null");
        this.enrollmentDate = Objects.requireNonNull(enrollmentDate, "Enrollment date cannot be null");
        this.completedCourses = completedCourses != null ? 
            Collections.unmodifiableMap(new HashMap<>(completedCourses)) : 
            Collections.emptyMap();
        this.academicHonors = academicHonors != null ? 
            academicHonors.clone() : new String[0];
        this.cumulativeGPA = calculateGPA(this.completedCourses);
    }
    
    private double calculateGPA(Map<String, String> courses) {
        if (courses.isEmpty()) return 0.0;
        
        double totalPoints = 0.0;
        int totalCourses = 0;
        
        for (String grade : courses.values()) {
            totalPoints += convertGradeToPoints(grade);
            totalCourses++;
        }
        
        return totalCourses > 0 ? totalPoints / totalCourses : 0.0;
    }
    
    private double convertGradeToPoints(String grade) {
        return switch (grade.toUpperCase()) {
            case "A" -> 4.0;
            case "B" -> 3.0;
            case "C" -> 2.0;
            case "D" -> 1.0;
            case "F" -> 0.0;
            default -> 0.0;
        };
    }
    
    public final boolean meetsPrerequisites(String courseCode) {
        String[] prerequisites = PREREQUISITES.get(courseCode);
        if (prerequisites == null) return true;
        
        for (String prereq : prerequisites) {
            if (!completedCourses.containsKey(prereq)) {
                return false;
            }
            String grade = completedCourses.get(prereq);
            if ("F".equals(grade)) {
                return false;
            }
        }
        return true;
    }
    
    // Getters only - no setters for immutable record
    public String getStudentId() {
        return studentId;
    }
    
    public String getMajor() {
        return major;
    }
    
    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }
    
    public Map<String, String> getCompletedCourses() {
        return completedCourses;
    }
    
    public double getCumulativeGPA() {
        return cumulativeGPA;
    }
    
    public String[] getAcademicHonors() {
        return academicHonors.clone();
    }
    
    @Override
    public String toString() {
        return "AcademicRecord{" +
                "studentId='" + studentId + '\'' +
                ", major='" + major + '\'' +
                ", enrollmentDate=" + enrollmentDate +
                ", cumulativeGPA=" + cumulativeGPA +
                ", completedCourses=" + completedCourses +
                ", academicHonors=" + Arrays.toString(academicHonors) +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicRecord that = (AcademicRecord) o;
        return Objects.equals(studentId, that.studentId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}