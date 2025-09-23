import java.util.Scanner;


public class VirtualPet {
    private final String petId;
    private String petName;
    private String species;
    private int age;
    private int happiness;
    private int health;
    private int evolutionStageIndex;
    private boolean isGhost = false;

    private static final String[] EVOLUTION_STAGES = {"Egg", "Baby", "Child", "Teen", "Adult", "Elder", "Ghost"};
    private static final String[] SPECIES_LIST = {"Dragon", "Cat", "Dog", "Unicorn", "Phoenix"};
    private static int totalPetsCreated = 0;
    private static final Random rand = new Random();

    // Default constructor: mysterious egg with random species
    public VirtualPet() {
        this("Mysterious Egg", SPECIES_LIST[rand.nextInt(SPECIES_LIST.length)], 0, 50, 50, 0);
    }

    // Constructor with name only: starts as baby
    public VirtualPet(String petName) {
        this(petName, SPECIES_LIST[rand.nextInt(SPECIES_LIST.length)], 0, 60, 60, 1);
    }

    // Constructor with name and species: starts as child
    public VirtualPet(String petName, String species) {
        this(petName, species, 1, 70, 70, 2);
    }

    // Full constructor
    public VirtualPet(String petName, String species, int age, int happiness, int health, int evolutionStageIndex) {
        this.petId = generatePetId();
        this.petName = petName;
        this.species = species;
        this.age = age;
        this.happiness = happiness;
        this.health = health;
        this.evolutionStageIndex = evolutionStageIndex;
        totalPetsCreated++;
    }

    // Static method to generate unique IDs
    public static String generatePetId() {
        return UUID.randomUUID().toString();
    }

    // Evolve pet based on age and care
    public void evolvePet() {
        if (isGhost) return;
        if (evolutionStageIndex < EVOLUTION_STAGES.length - 2) { // Don't evolve to Ghost here
            if (age > 15 && evolutionStageIndex < 5) evolutionStageIndex = 5; // Elder
            else if (age > 10 && evolutionStageIndex < 4) evolutionStageIndex = 4; // Adult
            else if (age > 7 && evolutionStageIndex < 3) evolutionStageIndex = 3; // Teen
            else if (age > 4 && evolutionStageIndex < 2) evolutionStageIndex = 2; // Child
            else if (age > 1 && evolutionStageIndex < 1) evolutionStageIndex = 1; // Baby
        }
    }

    // Feed pet: increase health and happiness
    public void feedPet() {
        if (isGhost) return;
        health = Math.min(health + 10, 100);
        happiness = Math.min(happiness + 5, 100);
    }

    // Play with pet: increase happiness, may decrease health if overplayed
    public void playWithPet() {
        if (isGhost) return;
        happiness = Math.min(happiness + 15, 100);
        health = Math.max(health - 2, 0);
    }

    // Heal pet: increase health
    public void healPet() {
        if (isGhost) return;
        health = Math.min(health + 20, 100);
    }

    // Simulate a day: age pet, random events
    public void simulateDay() {
        if (isGhost) return;
        age++;
        happiness = Math.max(happiness + rand.nextInt(11) - 5, 0); // -5 to +5
        health = Math.max(health + rand.nextInt(7) - 3, 0); // -3 to +3

        if (health <= 0) {
            becomeGhost();
        } else {
            evolvePet();
        }
    }

    // Pet becomes a ghost
    private void becomeGhost() {
        isGhost = true;
        species = "Ghost";
        evolutionStageIndex = EVOLUTION_STAGES.length - 1;
        happiness = 0;
        health = 0;
    }

    // Ghost can haunt another pet (decrease their happiness)
    public void haunt(VirtualPet other) {
        if (this.isGhost && !other.isGhost) {
            other.happiness = Math.max(other.happiness - 10, 0);
        }
    }

    // Get current evolution stage
    public String getPetStatus() {
        return String.format("%s (%s) - Stage: %s | Age: %d | HP: %d | Happy: %d", 
            petName, species, EVOLUTION_STAGES[evolutionStageIndex], age, health, happiness);
    }

    // Static method to get total pets created
    public static int getTotalPetsCreated() {
        return totalPetsCreated;
    }
}