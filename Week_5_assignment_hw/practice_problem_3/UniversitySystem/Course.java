public class Course {
    private final String courseCode;
    private final String title;
    private final int creditHours;
    private final String[] prerequisites;

    public Course(String courseCode, String title, int creditHours, String[] prerequisites) {
        this.courseCode = courseCode;
        this.title = title;
        this.creditHours = creditHours;
        this.prerequisites = prerequisites.clone();
    }

    public String getCourseCode() { return courseCode; }
    public String[] getPrerequisites() { return prerequisites.clone(); }

    @Override
    public String toString() {
        return courseCode + " - " + title;
    }
}
