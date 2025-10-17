// File: EntertainmentHub.java

// Base class
public class Entertainment {
    protected String title;

    public Entertainment(String title) {
        this.title = title;
    }

    // Start method
    public void start() {
        System.out.println("Starting " + title);
    }

    // Stop method
    public void stop() {
        System.out.println("Stopping " + title);
    }
}

// Movie subclass
class Movie extends Entertainment {
    private String genre;

    public Movie(String title, String genre) {
        super(title);
        this.genre = genre;
    }

    public void showSubtitles() {
        System.out.println("Showing subtitles for " + title + " (" + genre + ")");
    }

    public void adjustQuality() {
        System.out.println("Adjusting video quality for " + title);
    }
}

// Game subclass
class Game extends Entertainment {
    private String platform;

    public Game(String title, String platform) {
        super(title);
        this.platform = platform;
    }

    public void saveProgress() {
        System.out.println("Saving " + title + " progress on " + platform);
    }

    public void showLeaderboard() {
        System.out.println(title + " leaderboard on " + platform);
    }
}

// Main Hub class
public class EntertainmentHub {
    public static void main(String[] args) {
        // 1. Create Entertainment reference for Movie
        Entertainment entertainment = new Movie("Avengers", "Action");

        // 2. Call start() through Entertainment reference
        entertainment.start();

        // 3. Downcast to Movie
        Movie m = (Movie) entertainment;

        // 4. Call Movie-specific methods
        m.showSubtitles();
        m.adjustQuality();

        System.out.println();

        // 5. Repeat with Game
        entertainment = new Game("FIFA 24", "PlayStation");
        entertainment.start();

        Game g = (Game) entertainment; // Downcast to Game
        g.saveProgress();
        g.showLeaderboard();

        System.out.println();

        // 6. Wrong downcast (will cause runtime error: ClassCastException)
        entertainment = new Movie("Inception", "Sci-Fi");
        try {
            Game wrongCast = (Game) entertainment; // Invalid cast
            wrongCast.showLeaderboard();
        } catch (ClassCastException e) {
            System.out.println("Error: Tried to cast Movie to Game → " + e);
        }
    }
}
