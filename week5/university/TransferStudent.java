package university;

import java.time.LocalDate;
import java.util.Map;

public class TransferStudent extends Student {
    private String previousUniversity;
    private int transferCredits;
    
    public TransferStudent(String studentId, String currentName, String major, 
                          Map<String, String> transferCourses, String previousUniversity, int transferCredits) {
        super(studentId, createTransferAcademicRecord(studentId, major, transferCourses), currentName);
        this.previousUniversity = previousUniversity;
        this.transferCredits = transferCredits;
    }
    
    private static AcademicRecord createTransferAcademicRecord(String studentId, String major, Map<String, String> transferCourses) {
        return new AcademicRecord(studentId, major, LocalDate.now(), transferCourses, new String[0]);
    }
    
    public String getPreviousUniversity() { return previousUniversity; }
    public void setPreviousUniversity(String previousUniversity) { this.previousUniversity = previousUniversity; }
    
    public int getTransferCredits() { return transferCredits; }
    public void setTransferCredits(int transferCredits) { this.transferCredits = transferCredits; }
}