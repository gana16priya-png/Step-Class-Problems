class Course {
    String title, instructor;
    Course(String t, String i) { title = t; instructor = i; }
    void displayProgress() {
        System.out.println(title + " | Instructor: " + instructor);
        System.out.println("Generic course progress");
    }
}

class VideoCourse extends Course {
    int completion, watchTime;
    VideoCourse(String t, String i, int c, int w) { super(t, i); completion = c; watchTime = w; }
    @Override void displayProgress() {
        System.out.println("[Video Course] " + title + " | Watch time: " + watchTime + " hrs, Completion: " + completion + "%");
    }
}

class InteractiveCourse extends Course {
    int quizzes, projects;
    InteractiveCourse(String t, String i, int q, int p) { super(t, i); quizzes = q; projects = p; }
    @Override void displayProgress() {
        System.out.println("[Interactive Course] " + title + " | Quizzes: " + quizzes + ", Projects: " + projects);
    }
}

public class Problem2_OnlineLearning {
    public static void main(String[] args) {
        Course c1 = new VideoCourse("Java Basics", "Alice", 80, 10);
        Course c2 = new InteractiveCourse("DSA", "Bob", 5, 2);
        c1.displayProgress();
        c2.displayProgress();
    }
}
