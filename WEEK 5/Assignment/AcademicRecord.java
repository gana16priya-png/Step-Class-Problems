import java.time.LocalDate;
import java.util.*;

// ================== IMMUTABLE ACADEMIC RECORD ================== //
final class AcademicRecord {
    private final String studentId, major;
    private final LocalDate enrollmentDate;
    private final Map<String, String> completedCourses;
    private final double cumulativeGPA;
    private final String[] academicHonors;

    public AcademicRecord(String studentId, String major, LocalDate enrollmentDate,
                          Map<String, String> completedCourses, String[] honors) {
        this.studentId = studentId;
        this.major = major;
        this.enrollmentDate = enrollmentDate;
        this.completedCourses = new HashMap<>(completedCourses);
        this.academicHonors = honors.clone();
        this.cumulativeGPA = calculateGPA(completedCourses);
    }

    private double calculateGPA(Map<String, String> courses) {
        if (courses.isEmpty()) return 0.0;
        double totalPoints = 0;
        int count = 0;
        for (String grade : courses.values()) {
            totalPoints += gradeToPoint(grade);
            count++;
        }
        return count == 0 ? 0 : totalPoints / count;
    }

    private double gradeToPoint(String grade) {
        return switch (grade.toUpperCase()) {
            case "A" -> 4.0;
            case "B" -> 3.0;
            case "C" -> 2.0;
            case "D" -> 1.0;
            default -> 0.0;
        };
    }

    // Getters only (immutability)
    public String getStudentId() { return studentId; }
    public String getMajor() { return major; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public Map<String, String> getCompletedCourses() { return new HashMap<>(completedCourses); }
    public double getCumulativeGPA() { return cumulativeGPA; }
    public String[] getAcademicHonors() { return academicHonors.clone(); }

    public final boolean meetsPrerequisites(String courseCode) {
        return completedCourses.containsKey(courseCode);
    }

    @Override
    public String toString() {
        return "AcademicRecord[" + studentId + ", Major=" + major +
                ", GPA=" + cumulativeGPA + ", Honors=" + Arrays.toString(academicHonors) + "]";
    }
}

// ================== STUDENT CLASS ================== //
class Student {
    private final String studentId;
    private final AcademicRecord academicRecord;
    private String currentName, email, phoneNumber;
    private String currentAddress, emergencyContact;

    // Freshman constructor
    public Student(String name, String email, String phone, String major) {
        this.studentId = UUID.randomUUID().toString();
        this.academicRecord = new AcademicRecord(studentId, major, LocalDate.now(),
                new HashMap<>(), new String[]{});
        this.currentName = name;
        this.email = email;
        this.phoneNumber = phone;
    }

    // Transfer student constructor
    public Student(String name, String email, String phone, AcademicRecord transferRecord) {
        this.studentId = transferRecord.getStudentId();
        this.academicRecord = transferRecord;
        this.currentName = name;
        this.email = email;
        this.phoneNumber = phone;
    }

    // Graduate student constructor
    public Student(String name, String email, String phone, AcademicRecord undergradTranscript, String gradMajor) {
        this.studentId = undergradTranscript.getStudentId();
        this.academicRecord = new AcademicRecord(studentId, gradMajor, LocalDate.now(),
                undergradTranscript.getCompletedCourses(), undergradTranscript.getAcademicHonors());
        this.currentName = name;
        this.email = email;
        this.phoneNumber = phone;
    }

    // International student constructor
    public Student(String name, String email, String phone, String major,
                   String visa, String languageProficiency) {
        this.studentId = UUID.randomUUID().toString();
        this.academicRecord = new AcademicRecord(studentId, major, LocalDate.now(),
                new HashMap<>(), new String[]{"Visa: " + visa, "Language: " + languageProficiency});
        this.currentName = name;
        this.email = email;
        this.phoneNumber = phone;
    }

    // Package-private faculty access
    String getAcademicStanding() {
        double gpa = academicRecord.getCumulativeGPA();
        if (gpa >= 3.5) return "Honors";
        if (gpa >= 2.0) return "Good Standing";
        return "Probation";
    }

    // Public safe info
    public Map<String, String> getContactInfo() {
        return Map.of("Name", currentName, "Email", email, "Phone", phoneNumber);
    }

    // JavaBean getters/setters
    public String getStudentId() { return studentId; }
    public AcademicRecord getAcademicRecord() { return academicRecord; }
    public String getCurrentName() { return currentName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getCurrentAddress() { return currentAddress; }
    public String getEmergencyContact() { return emergencyContact; }

    public void setCurrentName(String name) { this.currentName = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(String phone) { this.phoneNumber = phone; }
    public void setCurrentAddress(String address) { this.currentAddress = address; }
    public void setEmergencyContact(String contact) { this.emergencyContact = contact; }

    @Override
    public String toString() {
        return "Student[" + currentName +]()
