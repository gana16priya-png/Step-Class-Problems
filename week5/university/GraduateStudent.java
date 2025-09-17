package university;

import java.time.LocalDate;
import java.util.Map;

public class GraduateStudent extends Student {
    private String thesisTitle;
    private String advisor;
    private AcademicRecord undergraduateRecord;
    
    public GraduateStudent(String studentId, String currentName, String major, 
                          AcademicRecord undergraduateRecord, String thesisTitle, String advisor) {
        super(studentId, createGraduateAcademicRecord(studentId, major), currentName);
        this.undergraduateRecord = undergraduateRecord;
        this.thesisTitle = thesisTitle;
        this.advisor = advisor;
    }
    
    private static AcademicRecord createGraduateAcademicRecord(String studentId, String major) {
        return new AcademicRecord(studentId, major + " (Graduate)", LocalDate.now(), 
                                 new java.util.HashMap<>(), new String[0]);
    }
    
    public String getThesisTitle() { return thesisTitle; }
    public void setThesisTitle(String thesisTitle) { this.thesisTitle = thesisTitle; }
    
    public String getAdvisor() { return advisor; }
    public void setAdvisor(String advisor) { this.advisor = advisor; }
    
    public AcademicRecord getUndergraduateRecord() { return undergraduateRecord; }
}