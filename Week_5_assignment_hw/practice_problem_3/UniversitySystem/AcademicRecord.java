import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

public final class AcademicRecord {
    private final String studentId;
    private final String major;
    private final LocalDate enrollmentDate;
    private final Map<String, String> completedCourses; // course -> grade
    private final double cumulativeGPA;
    private final String[] academicHonors;

    public AcademicRecord(String studentId, String major, LocalDate enrollmentDate,
                          Map<String, String> completedCourses, double cumulativeGPA,
                          String[] academicHonors) {
        this.studentId = studentId;
        this.major = major;
        this.enrollmentDate = enrollmentDate;
        this.completedCourses = Collections.unmodifiableMap(completedCourses);
        this.cumulativeGPA = cumulativeGPA;
        this.academicHonors = academicHonors.clone();
    }

    public String getStudentId() { return studentId; }
    public String getMajor() { return major; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public Map<String, String> getCompletedCourses() { return completedCourses; }
    public double getCumulativeGPA() { return cumulativeGPA; }
    public String[] getAcademicHonors() { return academicHonors.clone(); }

    public final boolean meetsPrerequisites(String courseCode) {
        return completedCourses.containsKey(courseCode);
    }

    @Override
    public String toString() {
        return "Transcript: " + studentId + " | GPA: " + cumulativeGPA;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof AcademicRecord r) && studentId.equals(r.studentId);
    }

    @Override
    public int hashCode() { return studentId.hashCode(); }
}
