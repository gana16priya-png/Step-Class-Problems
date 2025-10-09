// Base class
class Content {
    String title;

    public Content(String title) {
        this.title = title;
    }

    public void play() {
        System.out.println("Playing content: " + title);
    }
}

// Movie subclass
class Movie extends Content {
    int duration;
    double rating;

    public Movie(String title, int duration, double rating) {
        super(title);
        this.duration = duration;
        this.rating = rating;
    }

    public void showSubtitles() {
        System.out.println(title + " subtitles enabled.");
    }
}

// TVSeries subclass
class TVSeries extends Content {
    int seasons;
    int episodes;

    public TVSeries(String title, int seasons, int episodes) {
        super(title);
        this.seasons = seasons;
        this.episodes = episodes;
    }

    public void suggestNextEpisode() {
        System.out.println("Next episode suggestion for " + title);
    }
}

// Documentary subclass
class Documentary extends Content {
    String educationalTag;

    public Documentary(String title, String educationalTag) {
        super(title);
        this.educationalTag = educationalTag;
    }

    public void showRelatedContent() {
        System.out.println("Showing related content for documentary: " + educationalTag);
    }
}

public class MovieStreamingPlatform {
    public static void main(String[] args) {
        // General reference
        Content content;

        // Assign a Movie
        content = new Movie("Inception", 148, 8.8);
        content.play();

        // Downcast to access Movie-specific features
        if (content instanceof Movie) {
            Movie m = (Movie) content;
            m.showSubtitles();
            System.out.println("Duration: " + m.duration + " minutes, Rating: " + m.rating);
        }

        // Assign a TV Series
        content = new TVSeries("Breaking Bad", 5, 62);
        content.play();

        // Downcast to access TVSeries-specific features
        if (content instanceof TVSeries) {
            TVSeries t = (TVSeries) content;
            t.suggestNextEpisode();
        }

        // Assign a Documentary
        content = new Documentary("Planet Earth", "Nature");
        content.play();

        // Downcast to access Documentary-specific features
        if (content instanceof Documentary) {
            Documentary d = (Documentary) content;
            d.showRelatedContent();
        }
    }
}
