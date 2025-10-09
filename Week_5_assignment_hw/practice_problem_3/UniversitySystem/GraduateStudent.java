public class GraduateStudent extends Student {
    private final AcademicRecord undergradTranscript;

    public GraduateStudent(String studentId, String name, String email,
                           AcademicRecord undergradTranscript, AcademicRecord gradRecord) {
        super(studentId, name, email, gradRecord);
        this.undergradTranscript = undergradTranscript;
    }

    public AcademicRecord getUndergradTranscript() {
        return undergradTranscript;
    }
}
