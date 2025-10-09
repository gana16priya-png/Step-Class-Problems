import java.time.LocalDate;
import java.util.Map;

public class MainDemo {
    public static void main(String[] args) {
        AcademicRecord record = new AcademicRecord(
                "S1001", "Computer Science", LocalDate.of(2023, 9, 1),
                Map.of("CS101", "A", "MATH101", "B+"), 3.6,
                new String[]{"Dean's List"}
        );

        Student student = new Student("S1001", "Alice", "alice@example.com", record);
        Course algorithms = new Course("CS201", "Algorithms", 4, new String[]{"CS101"});

        RegistrationSystem reg = new RegistrationSystem();
        boolean enrolled = reg.enrollStudent(student, algorithms);

        System.out.println("Enrolled: " + enrolled);
        reg.printAcademicAdminReport();
    }
}
