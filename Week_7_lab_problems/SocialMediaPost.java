class Post {
    String author;
    String content;
    String time;

    public Post(String author, String content, String time) {
        this.author = author;
        this.content = content;
        this.time = time;
    }

    public void display() {
        System.out.println(author + " posted: " + content + " at " + time);
    }
}

class InstagramPost extends Post {
    int likes;
    String hashtags;

    public InstagramPost(String author, String content, String time, int likes, String hashtags) {
        super(author, content, time);
        this.likes = likes;
        this.hashtags = hashtags;
    }

    @Override
    public void display() {
        System.out.println("Instagram Post by " + author + ": " + content);
        System.out.println("Hashtags: " + hashtags + " Likes: " + likes);
    }
}

class TwitterPost extends Post {
    int retweets;

    public TwitterPost(String author, String content, String time, int retweets) {
        super(author, content, time);
        this.retweets = retweets;
    }

    @Override
    public void display() {
        System.out.println(" Twitter Post by " + author + " (" + content.length() + " chars): " + content);
        System.out.println("Retweets: " + retweets);
    }
}

class LinkedInPost extends Post {
    int connections;

    public LinkedInPost(String author, String content, String time, int connections) {
        super(author, content, time);
        this.connections = connections;
    }

    @Override
    public void display() {
        System.out.println(" LinkedIn Post by " + author + " | Connections: " + connections);
        System.out.println(content + "\nPosted at: " + time);
    }
}

public class SocialMediaPost {
    public static void main(String[] args) {
        Post p1 = new InstagramPost("Alice", "Enjoying vacation!", "10:00AM", 120, "#fun #travel");
        Post p2 = new TwitterPost("Bob", "Learning Java inheritance!", "11:00AM", 25);
        Post p3 = new LinkedInPost("Charlie", "Promoted to Software Engineer!", "12:00PM", 500);

        p1.display();
        p2.display();
        p3.display();
    }
}
