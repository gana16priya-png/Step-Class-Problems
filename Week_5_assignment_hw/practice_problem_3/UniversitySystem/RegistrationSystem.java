import java.util.*;

public class RegistrationSystem {
    private final Map<String, Object> enrolledStudents = new HashMap<>();

    // Static final rules
    public static final int MAX_CREDITS = 18;

    public boolean enrollStudent(Object student, Object course) {
        if (student instanceof Student s && course instanceof Course c) {
            if (validatePrerequisites(s, c)) {
                enrolledStudents.put(s.toString(), c);
                return true;
            }
        }
        return false;
    }

    private boolean validatePrerequisites(Student s, Course c) {
        for (String pre : c.getPrerequisites()) {
            if (!s.getAcademicRecord().meetsPrerequisites(pre)) return false;
        }
        return true;
    }

    // Package-private admin method
    void printAcademicAdminReport() {
        System.out.println("Total enrolled: " + enrolledStudents.size());
    }
}
