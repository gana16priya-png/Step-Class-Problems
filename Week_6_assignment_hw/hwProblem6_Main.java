// hwProblem6.java

class Weather {
    String type;

    // Constructor
    public Weather(String type) {
        this.type = type;
        System.out.println("Weather constructor called for " + type);
    }

    public void display() {
        System.out.println("General Weather: " + type);
    }
}

class Storm extends Weather {
    int windSpeed;

    public Storm(String type, int windSpeed) {
        super(type);
        this.windSpeed = windSpeed;
        System.out.println("Storm constructor called");
    }

    @Override
    public void display() {
        System.out.println("Storm with wind speed: " + windSpeed + " km/h");
    }
}

class Thunderstorm extends Storm {
    boolean lightning;

    public Thunderstorm(String type, int windSpeed, boolean lightning) {
        super(type, windSpeed);
        this.lightning = lightning;
        System.out.println("Thunderstorm constructor called");
    }

    @Override
    public void display() {
        System.out.println("Thunderstorm: wind " + windSpeed + " km/h, lightning=" + lightning);
    }
}

class Sunshine extends Weather {
    int uvIndex;

    public Sunshine(String type, int uvIndex) {
        super(type);
        this.uvIndex = uvIndex;
        System.out.println("Sunshine constructor called");
    }

    @Override
    public void display() {
        System.out.println("Sunshine with UV Index: " + uvIndex);
    }
}

public class hwProblem6_Main {
    public static void main(String[] args) {
        Weather[] weatherArray = new Weather[3];

        weatherArray[0] = new Storm("Cyclone", 120);
        weatherArray[1] = new Thunderstorm("Monsoon Thunderstorm", 80, true);
        weatherArray[2] = new Sunshine("Bright Day", 9);

        System.out.println("\n--- Weather Reports ---");
        for (Weather w : weatherArray) {
            w.display(); // polymorphism at work
        }
    }
}
