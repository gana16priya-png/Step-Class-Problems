public class InternationalStudent extends Student {
    private final String visaStatus;
    private final String languageProficiency;

    public InternationalStudent(String studentId, String name, String email,
                                AcademicRecord record, String visaStatus, String languageProficiency) {
        super(studentId, name, email, record);
        this.visaStatus = visaStatus;
        this.languageProficiency = languageProficiency;
    }

    public String getVisaStatus() { return visaStatus; }
    public String getLanguageProficiency() { return languageProficiency; }
}
