package university;

import java.util.Objects;

public class Student {
    private final String studentId;
    private final AcademicRecord academicRecord;
    
    // Modifiable contact information
    private String currentName;
    private String email;
    private String phoneNumber;
    
    // Personal information
    private String currentAddress;
    private String emergencyContact;
    
    public Student(String studentId, AcademicRecord academicRecord, String currentName) {
        this.studentId = Objects.requireNonNull(studentId, "Student ID cannot be null");
        this.academicRecord = Objects.requireNonNull(academicRecord, "Academic record cannot be null");
        this.currentName = Objects.requireNonNull(currentName, "Name cannot be null");
    }
    
    // Package-private for faculty access
    String getAcademicStanding() {
        double gpa = academicRecord.getCumulativeGPA();
        if (gpa >= 3.5) return "Dean's List";
        else if (gpa >= 3.0) return "Good Standing";
        else if (gpa >= 2.0) return "Academic Warning";
        else return "Academic Probation";
    }
    
    // Public method for safe information sharing
    public ContactInfo getContactInfo() {
        return new ContactInfo(currentName, email, phoneNumber);
    }
    
    // JavaBean compliant getters and setters for modifiable data
    public String getCurrentName() {
        return currentName;
    }
    
    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getCurrentAddress() {
        return currentAddress;
    }
    
    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }
    
    public String getEmergencyContact() {
        return emergencyContact;
    }
    
    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }
    
    // Getters for immutable data (no setters)
    public String getStudentId() {
        return studentId;
    }
    
    public AcademicRecord getAcademicRecord() {
        return academicRecord;
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", currentName='" + currentName + '\'' +
                ", email='" + email + '\'' +
                ", academicStanding='" + getAcademicStanding() + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
    
    // Inner class for safe contact information sharing
    public static class ContactInfo {
        private final String name;
        private final String email;
        private final String phoneNumber;
        
        public ContactInfo(String name, String email, String phoneNumber) {
            this.name = name;
            this.email = email;
            this.phoneNumber = phoneNumber;
        }
        
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getPhoneNumber() { return phoneNumber; }
        
        @Override
        public String toString() {
            return "ContactInfo{name='" + name + "', email='" + email + "', phone='" + phoneNumber + "'}";
        }
    }
}