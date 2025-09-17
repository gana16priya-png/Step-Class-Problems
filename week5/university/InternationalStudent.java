package university;

import java.time.LocalDate;
import java.util.Map;

public class InternationalStudent extends Student {
    private String visaStatus;
    private String languageProficiency;
    private String countryOfOrigin;
    private LocalDate visaExpiryDate;
    
    public InternationalStudent(String studentId, String currentName, String major,
                               Map<String, String> completedCourses, String visaStatus, 
                               String languageProficiency, String countryOfOrigin, LocalDate visaExpiryDate) {
        super(studentId, createInternationalAcademicRecord(studentId, major, completedCourses), currentName);
        this.visaStatus = visaStatus;
        this.languageProficiency = languageProficiency;
        this.countryOfOrigin = countryOfOrigin;
        this.visaExpiryDate = visaExpiryDate;
    }
    
    private static AcademicRecord createInternationalAcademicRecord(String studentId, String major, Map<String, String> courses) {
        return new AcademicRecord(studentId, major, LocalDate.now(), courses, new String[0]);
    }
    
    public String getVisaStatus() { return visaStatus; }
    public void setVisaStatus(String visaStatus) { this.visaStatus = visaStatus; }
    
    public String getLanguageProficiency() { return languageProficiency; }
    public void setLanguageProficiency(String languageProficiency) { this.languageProficiency = languageProficiency; }
    
    public String getCountryOfOrigin() { return countryOfOrigin; }
    public void setCountryOfOrigin(String countryOfOrigin) { this.countryOfOrigin = countryOfOrigin; }
    
    public LocalDate getVisaExpiryDate() { return visaExpiryDate; }
    public void setVisaExpiryDate(LocalDate visaExpiryDate) { this.visaExpiryDate = visaExpiryDate; }
    
    public boolean isVisaValid() {
        return visaExpiryDate.isAfter(LocalDate.now());
    }
}