public class Animal {
    protected String species;
    protected String habitat;
    protected int lifespan;
    protected boolean isWildlife;

    public Animal(String species, String habitat, int lifespan, boolean isWildlife) {
        this.species = species;
        this.habitat = habitat;
        this.lifespan = lifespan;
        this.isWildlife = isWildlife;
        System.out.println("Animal constructor: Creating " + species);
    }

    public void eat() {
        System.out.println("Animal is eating");
    }

    public void sleep() {
        System.out.println("Animal is sleeping");
    }

    public void move() {
        System.out.println("Animal is moving");
    }

    public String getAnimalInfo() {
        return "Species: " + species + ", Habitat: " + habitat + ", Lifespan: " + lifespan + " years, Wildlife: " + isWildlife;
    }
}

class Mammal extends Animal {
    protected String furColor;
    protected boolean hasWarmBlood;
    protected int gestationPeriod;

    public Mammal(String species, String habitat, int lifespan, boolean isWildlife, String furColor, int gestationPeriod) {
        super(species, habitat, lifespan, isWildlife);
        this.furColor = furColor;
        this.hasWarmBlood = true;
        this.gestationPeriod = gestationPeriod;
        System.out.println("Mammal constructor: Adding mammal traits");
    }

    @Override
    public void move() {
        super.move();
        System.out.println("Mammal is walking/running");
    }

    public void nurse() {
        System.out.println("Mammal is nursing offspring");
    }

    public void regulateTemperature() {
        System.out.println("Maintaining body temperature");
    }
}

class Dog extends Mammal {
    private String breed;
    private boolean isDomesticated;
    private int loyaltyLevel;
    private String favoriteActivity;

    // Constructor 1: Basic dog with minimal parameters
    public Dog() {
        super("Dog", "Domestic", 13, false, "Varied", 60);
        this.breed = "Unknown";
        this.isDomesticated = true;
        this.loyaltyLevel = 5;
        this.favoriteActivity = "Playing";
    }

    // Constructor 2: Detailed dog with all parameters
    public Dog(String species, String habitat, int lifespan, boolean isWildlife, String furColor, int gestationPeriod,
               String breed, boolean isDomesticated, int loyaltyLevel, String favoriteActivity) {
        super(species, habitat, lifespan, isWildlife, furColor, gestationPeriod);
        this.breed = breed;
        this.isDomesticated = isDomesticated;
        this.loyaltyLevel = loyaltyLevel;
        this.favoriteActivity = favoriteActivity;
        System.out.println("Dog constructor: Creating " + breed + " dog");
    }

    // Constructor 3: Copy constructor
    public Dog(Dog other) {
        this(other.species, other.habitat, other.lifespan, other.isWildlife, other.furColor, other.gestationPeriod,
             other.breed, other.isDomesticated, other.loyaltyLevel, other.favoriteActivity);
    }

    @Override
    public void eat() {
        super.eat();
        System.out.println("Dog is wagging tail while eating");
    }

    @Override
    public void move() {
        System.out.println("Dog is running and playing");
    }

    @Override
    public void sleep() {
        System.out.println("Dog is sleeping in doghouse");
    }

    public void bark() {
        System.out.println("Woof! Woof!");
    }

    public void fetch() {
        System.out.println("Dog is fetching the ball");
    }

    public void showLoyalty() {
        System.out.println("Loyalty level: " + loyaltyLevel + "/10");
    }

    public void demonstrateInheritance() {
        eat();
        move();
        sleep();
        nurse();
        regulateTemperature();
        System.out.println(getAnimalInfo());
    }

    public static void main(String[] args) {
        // Test multilevel constructor chaining
        Dog d1 = new Dog();
        Dog d2 = new Dog("Dog", "Domestic", 15, false, "Brown", 65, "Labrador", true, 9, "Swimming");
        Dog d3 = new Dog(d2);

        // Test method overriding
        d1.eat();
        d2.move();
        d3.sleep();

        // Access inherited members
        System.out.println(d2.species); // Animal
        System.out.println(d2.furColor); // Mammal

        // Call methods from all levels
        d2.nurse();
        d2.regulateTemperature();
        d2.bark();
        d2.fetch();
        d2.showLoyalty();

        // Demonstrate chain of inheritance
        System.out.println(d2 instanceof Dog);     // true
        System.out.println(d2 instanceof Mammal);  // true
        System.out.println(d2 instanceof Animal);  // true

        // Demonstrate inheritance method