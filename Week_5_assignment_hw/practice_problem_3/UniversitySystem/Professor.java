import java.util.List;

public class Professor {
    private final String facultyId;
    private final String department;
    private final List<String> qualifications;

    public Professor(String facultyId, String department, List<String> qualifications) {
        this.facultyId = facultyId;
        this.department = department;
        this.qualifications = List.copyOf(qualifications);
    }

    @Override
    public String toString() {
        return facultyId + " (" + department + ")";
    }
}
