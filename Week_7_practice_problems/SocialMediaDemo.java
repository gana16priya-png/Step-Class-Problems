// File: SocialMediaDemo.java

// Base class
class SocialMediaPost {
    protected String content;
    protected String author;

    public SocialMediaPost(String content, String author) {
        this.content = content;
        this.author = author;
    }

    // Base share method
    public void share() {
        System.out.println("Sharing: " + content + " by " + author);
    }
}

// InstagramPost class overriding share()
class InstagramPost extends SocialMediaPost {
    private int likes;

    public InstagramPost(String content, String author, int likes) {
        super(content, author);
        this.likes = likes;
    }

    @Override
    public void share() {
        System.out.println("Instagram: " + content + " by @" + author + " - " + likes + " likes");
    }
}

// TwitterPost class overriding share()
class TwitterPost extends SocialMediaPost {
    private int retweets;

    public TwitterPost(String content, String author, int retweets) {
        super(content, author);
        this.retweets = retweets;
    }

    @Override
    public void share() {
        System.out.println("Tweet: " + content + " by @" + author + " - " + retweets + " retweets");
    }
}

// Demo class
public class SocialMediaDemo {
    public static void main(String[] args) {
        // Social Media Feed Simulation
        SocialMediaPost[] feed = new SocialMediaPost[3];

        feed[0] = new InstagramPost("Sunset vibes!", "john_doe", 245);
        feed[1] = new TwitterPost("Java is awesome!", "code_ninja", 89);
        feed[2] = new SocialMediaPost("Hello world!", "beginner");

        // Polymorphism in action: same method, different behaviors
        for (SocialMediaPost post : feed) {
            post.share();
        }
    }
}
