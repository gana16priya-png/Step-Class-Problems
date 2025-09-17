package university;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Professor {
    private final String facultyId;
    private final String department;
    private final List<String> qualifications;
    
    // Modifiable schedule information
    private List<String> currentCourses;
    private String officeHours;
    
    public Professor(String facultyId, String department, List<String> qualifications) {
        this.facultyId = Objects.requireNonNull(facultyId, "Faculty ID cannot be null");
        this.department = Objects.requireNonNull(department, "Department cannot be null");
        this.qualifications = qualifications != null ? 
            new ArrayList<>(qualifications) : new ArrayList<>();
        this.currentCourses = new ArrayList<>();
    }
    
    // Getters for immutable properties (no setters)
    public String getFacultyId() {
        return facultyId;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public List<String> getQualifications() {
        return new ArrayList<>(qualifications);
    }
    
    // JavaBean compliant getters and setters for modifiable data
    public List<String> getCurrentCourses() {
        return new ArrayList<>(currentCourses);
    }
    
    public void setCurrentCourses(List<String> currentCourses) {
        this.currentCourses = currentCourses != null ? 
            new ArrayList<>(currentCourses) : new ArrayList<>();
    }
    
    public String getOfficeHours() {
        return officeHours;
    }
    
    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }
    
    @Override
    public String toString() {
        return "Professor{" +
                "facultyId='" + facultyId + '\'' +
                ", department='" + department + '\'' +
                ", qualifications=" + qualifications +
                ", currentCourses=" + currentCourses +
                ", officeHours='" + officeHours + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return Objects.equals(facultyId, professor.facultyId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(facultyId);
    }
}