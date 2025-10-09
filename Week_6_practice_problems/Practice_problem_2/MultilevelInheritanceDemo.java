// File: MultilevelInheritanceDemo.java

// ================= Base Class =================
class Animal {
    protected String species;
    protected String habitat;
    protected int lifespan;
    protected boolean isWildlife;

    // Constructor
    public Animal(String species, String habitat, int lifespan, boolean isWildlife) {
        this.species = species;
        this.habitat = habitat;
        this.lifespan = lifespan;
        this.isWildlife = isWildlife;
        System.out.println("Animal constructor: Creating " + species);
    }

    // Methods
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
        return "Species: " + species + ", Habitat: " + habitat +
               ", Lifespan: " + lifespan + " years, Wildlife: " + isWildlife;
    }
}

// ================= Intermediate Class =================
class Mammal extends Animal {
    protected String furColor;
    protected boolean hasWarmBlood;
    protected int gestationPeriod;

    // Constructor
    public Mammal(String species, String habitat, int lifespan, boolean isWildlife,
                  String furColor, int gestationPeriod) {
        super(species, habitat, lifespan, isWildlife);
        this.furColor = furColor;
        this.hasWarmBlood = true; // always true for mammals
        this.gestationPeriod = gestationPeriod;
        System.out.println("Mammal constructor: Adding mammal traits");
    }

    // Override move()
    @Override
    public void move() {
        super.move();
        System.out.println("Mammal is walking/running");
    }

    // Mammal-specific methods
    public void nurse() {
        System.out.println("Mammal is nursing offspring");
    }

    public void regulateTemperature() {
        System.out.println("Maintaining body temperature");
    }
}

// ================= Specific Class =================
class Dog extends Mammal {
    private String breed;
    private boolean isDomesticated;
    private int loyaltyLevel;
    private String favoriteActivity;

    // Constructor 1: Basic Dog
    public Dog() {
        super("Dog", "Domestic", 12, false, "Varies", 60);
        this.breed = "Mixed";
        this.isDomesticated = true;
        this.loyaltyLevel = 7;
        this.favoriteActivity = "Playing fetch";
        System.out.println("Dog constructor: Creating a basic dog");
    }

    // Constructor 2: Detailed Dog
    public Dog(String species, String habitat, int lifespan, boolean isWildlife,
               String furColor, int gestationPeriod,
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
        this(other.species, other.habitat, other.lifespan, other.isWildlife,
             other.furColor, other.gestationPeriod,
             other.breed, other.isDomesticated, other.loyaltyLevel, other.favoriteActivity);
        System.out.println("Dog constructor: Copying dog " + other.breed);
    }

    // Overridden methods
    @Override
    public void eat() {
        super.eat();
        System.out.println("Dog is wagging tail while eating");
    }

    @Override
    public void sleep() {
        System.out.println("Dog is sleeping in doghouse");
    }

    @Override
    public void move() {
        System.out.println("Dog is running and playing");
    }

    // Dog-specific methods
    public void bark() {
        System.out.println("Woof! Woof!");
    }

    public void fetch() {
        System.out.println("Dog is fetching the ball");
    }

    public void showLoyalty() {
        System.out.println("Dog's loyalty level: " + loyaltyLevel + "/10");
    }

    // Demonstration method
    public void demonstrateInheritance() {
        System.out.println("\n--- Demonstrating Inheritance ---");
        super.eat(); // Animal-level behavior
        super.move(); // Mammal move + Animal move
        super.sleep(); // Animal-level sleep
        this.bark();
        this.showLoyalty();
        System.out.println("--- End Demonstration ---\n");
    }
}

// ================= Demo =================
public class MultilevelInheritanceDemo {
    public static void main(String[] args) {
        System.out.println("\n=== Constructor Chaining Test ===");
        Dog dog1 = new Dog();  // Basic constructor

        Dog dog2 = new Dog("Dog", "Domestic", 15, false,
                           "Brown", 63,
                           "Labrador", true, 9, "Swimming");

        Dog dog3 = new Dog(dog2); // Copy constructor

        System.out.println("\n=== Method Overriding Test ===");
        dog2.eat();
        dog2.move();
        dog2.sleep();

        System.out.println("\n=== Inheritance Demonstration ===");
        dog2.demonstrateInheritance();

        System.out.println("\n=== Access to Inherited Members ===");
        System.out.println(dog2.getAnimalInfo());
        dog2.nurse();
        dog2.regulateTemperature();

        System.out.println("\n=== instanceof Test ===");
        System.out.println("dog2 instanceof Dog: " + (dog2 instanceof Dog));
        System.out.println("dog2 instanceof Mammal: " + (dog2 instanceof Mammal));
        System.out.println("dog2 instanceof Animal: " + (dog2 instanceof Animal));
    }
}
