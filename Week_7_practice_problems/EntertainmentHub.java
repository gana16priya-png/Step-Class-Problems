// File: EntertainmentHub.java

// Base class
class Entertainment {
    protected String title;

    public Entertainment(String title) {
        this.title = title;
    }

    public void start() {
        System.out.println("Starting " + title);
    }

    public void stop() {
        System.out.println("Stopping " + title);
    }
}

// Subclass: Movie
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

// Subclass: Game
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

// Demo class
public class EntertainmentHub {
    public static void main(String[] args) {
        // Step 1: Create Entertainment reference with Movie
        Entertainment entertainment = new Movie("Avengers", "Action");

        // Step 2: Call base method
        entertainment.start();

        // Step 3: Downcast to Movie
        if (entertainment instanceof Movie) {
            Movie m = (Movie) entertainment;
            m.showSubtitles();
            m.adjustQuality();
        }

        // Step 5: Repeat with Game
        entertainment = new Game("FIFA 24", "PlayStation");
        entertainment.start();

        if (entertainment instanceof Game) {
            Game g = (Game) entertainment;
            g.saveProgress();
            g.showLeaderboard();
        }

        // Step 6: Wrong downcast (will throw ClassCastException at runtime)
        try {
            Movie wrongCast = (Movie) entertainment; // entertainment is actually Game
            wrongCast.showSubtitles(); // Runtime error
        } catch (ClassCastException e) {
            System.out.println("\nWrong downcast attempted! " + e);
        }
    }
}
