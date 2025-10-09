public class Student {
    private final String studentId;
    private final AcademicRecord academicRecord;

    private String currentName;
    private String email;
    private String phoneNumber;
    private String currentAddress;
    private String emergencyContact;

    // Constructor for new freshman
    public Student(String studentId, String currentName, String email,
                   AcademicRecord record) {
        this.studentId = studentId;
        this.currentName = currentName;
        this.email = email;
        this.academicRecord = record;
    }

    // Getters/Setters for contact info
    public String getCurrentName() { return currentName; }
    public void setCurrentName(String name) { this.currentName = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phone) { this.phoneNumber = phone; }

    public String getCurrentAddress() { return currentAddress; }
    public void setCurrentAddress(String address) { this.currentAddress = address; }

    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String contact) { this.emergencyContact = contact; }

    // Package-private academic standing
    String getAcademicStanding() {
        return academicRecord.getCumulativeGPA() >= 3.5 ? "Good Standing" : "Probation";
    }

    // Public safe info
    public String getContactInfo() {
        return currentName + " | " + email;
    }

    public AcademicRecord getAcademicRecord() { return academicRecord; }

    @Override
    public String toString() {
        return studentId + " : " + currentName;
    }
}
