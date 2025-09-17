package university;

import java.time.LocalDate;
import java.util.HashMap;

public class FreshmanStudent extends Student {
    private String highSchool;
    private double highSchoolGPA;
    
    public FreshmanStudent(String studentId, String currentName, String major, String highSchool, double highSchoolGPA) {
        super(studentId, createFreshAcademicRecord(studentId, major), currentName);
        this.highSchool = highSchool;
        this.highSchoolGPA = highSchoolGPA;
    }
    
    private static AcademicRecord createFreshAcademicRecord(String studentId, String major) {
        return new AcademicRecord(studentId, major, LocalDate.now(), new HashMap<>(), new String[0]);
    }
    
    public String getHighSchool() { return highSchool; }
    public void setHighSchool(String highSchool) { this.highSchool = highSchool; }
    
    public double getHighSchoolGPA() { return highSchoolGPA; }
    public void setHighSchoolGPA(double highSchoolGPA) { this.highSchoolGPA = highSchoolGPA; }
}