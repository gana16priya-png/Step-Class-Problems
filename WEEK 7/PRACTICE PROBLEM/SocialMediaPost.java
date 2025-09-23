public class SocialMediaPost {
    protected String content;
    protected String author;

    public SocialMediaPost(String content, String author) {
        this.content = content;
        this.author = author;
    }

    // Prints "Sharing: [content] by [author]"
    public void share() {
        System.out.println("Sharing: " + content + " by " + author);
    }
}

class InstagramPost extends SocialMediaPost {
    private int likes;

    public InstagramPost(String content, String author, int likes) {
        super(content, author);
        this.likes = likes;
    }

    // Override share() for Instagram
    @Override
    public void share() {
        System.out.println("Instagram: " + content + " by @" + author + " - " + likes + " likes");
    }
}

class TwitterPost extends SocialMediaPost {
    private int retweets;

    public TwitterPost(String content, String author, int retweets) {
        super(content, author);
        this.retweets = retweets;
    }

    // Override share() for Twitter
    @Override
    public void share() {
        System.out.println("Tweet: " + content + " by @" + author + " - " + retweets + " retweets");
    }
}

class SocialMediaDemo {
    public static void main(String[] args) {
        // Array of SocialMediaPost references
        SocialMediaPost[] feed = new SocialMediaPost[3];
        feed[0] = new InstagramPost("Sunset vibes!", "john_doe", 245);
        feed[1] = new TwitterPost("Java is awesome!", "code_ninja", 89);
        feed[2] = new SocialMediaPost("Hello world!", "beginner");

        // Loop and call share() on each
        for (SocialMediaPost post : feed) {
            post.share();
        }
    }
}