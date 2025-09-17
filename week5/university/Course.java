package university;

import java.util.Arrays;
import java.util.Objects;

public class Course {
    private final String courseCode;
    private final String title;
    private final int creditHours;
    private final String[] prerequisites;
    
    // Modifiable schedule information
    private String schedule;
    private String semester;
    
    public Course(String courseCode, String title, int creditHours, String[] prerequisites) {
        this.courseCode = Objects.requireNonNull(courseCode, "Course code cannot be null");
        this.title = Objects.requireNonNull(title, "Title cannot be null");
        this.creditHours = creditHours;
        this.prerequisites = prerequisites != null ? prerequisites.clone() : new String[0];
    }
    
    // Getters for immutable properties (no setters)
    public String getCourseCode() {
        return courseCode;
    }
    
    public String getTitle() {
        return title;
    }
    
    public int getCreditHours() {
        return creditHours;
    }
    
    public String[] getPrerequisites() {
        return prerequisites.clone();
    }
    
    // JavaBean compliant getters and setters for modifiable schedule
    public String getSchedule() {
        return schedule;
    }
    
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
    
    public String getSemester() {
        return semester;
    }
    
    public void setSemester(String semester) {
        this.semester = semester;
    }
    
    @Override
    public String toString() {
        return "Course{" +
                "courseCode='" + courseCode + '\'' +
                ", title='" + title + '\'' +
                ", creditHours=" + creditHours +
                ", prerequisites=" + Arrays.toString(prerequisites) +
                ", schedule='" + schedule + '\'' +
                ", semester='" + semester + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseCode, course.courseCode);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(courseCode);
    }
}