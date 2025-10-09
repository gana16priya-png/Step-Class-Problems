import java.util.*;

class VirtualPet {
    // --- Fields ---
    private final String petId; // Unique, final ID
    private String petName;
    private String species;
    private int age;
    private int happiness;
    private int health;
    private int stageIndex; // Tracks current evolution stage
    private boolean isGhost;

    // --- Static Fields ---
    static final String[] EVOLUTION_STAGES = {"Egg", "Baby", "Child", "Teen", "Adult", "Elder"};
    static int totalPetsCreated = 0;
    private static final Random rand = new Random();

    // --- Constructors ---
    // Full constructor (main one)
    public VirtualPet(String petName, String species, int age, int happiness, int health, int stageIndex) {
        this.petId = generatePetId();
        this.petName = petName;
        this.species = species;
        this.age = age;
        this.happiness = happiness;
        this.health = health;
        this.stageIndex = stageIndex;
        this.isGhost = false;
        totalPetsCreated++;
    }

    // Constructor with name and species (starts as Child)
    public VirtualPet(String petName, String species) {
        this(petName, species, 0, 50, 100, 2); // stageIndex 2 → Child
    }

    // Constructor with only name (starts as Baby)
    public VirtualPet(String petName) {
        this(petName, getRandomSpecies(), 0, 50, 100, 1); // stageIndex 1 → Baby
    }

    // Default constructor (Egg with random species)
    public VirtualPet() {
        this("Unknown", getRandomSpecies(), 0, 50, 100, 0); // stageIndex 0 → Egg
    }

    // --- Methods ---
    public void evolvePet() {
        if (isGhost) {
            System.out.println(petName + " is a Ghost and cannot evolve!");
            return;
        }
        if (stageIndex < EVOLUTION_STAGES.length - 1 && age > stageIndex * 2) { 
            stageIndex++;
            System.out.println(petName + " evolved into " + EVOLUTION_STAGES[stageIndex] + "!");
        }
    }

    public void feedPet() {
        if (!isGhost) {
            health += 10;
            System.out.println(petName + " was fed. Health: " + health);
        }
    }

    public void playWithPet() {
        if (!isGhost) {
            happiness += 10;
            System.out.println(petName + " played. Happiness: " + happiness);
        }
    }

    public void healPet() {
        if (!isGhost) {
            health += 20;
            System.out.println(petName + " healed. Health: " + health);
        }
    }

    public void simulateDay() {
        if (isGhost) {
            System.out.println(petName + " haunts the daycare...");
            return;
        }

        age++;
        happiness -= rand.nextInt(5);
        health -= rand.nextInt(5);

        if (health <= 0) {
            health = 0;
            isGhost = true;
            species = "Ghost";
            System.out.println(petName + " has died and become a Ghost!");
        } else {
            evolvePet();
        }
    }

    public String getPetStatus() {
        return petName + " (" + species + ") | Age: " + age + 
               " | Stage: " + (isGhost ? "Ghost" : EVOLUTION_STAGES[stageIndex]) +
               " | Happiness: " + happiness + " | Health: " + health;
    }

    // --- Static helpers ---
    public static String generatePetId() {
        return UUID.randomUUID().toString();
    }

    private static String getRandomSpecies() {
        String[] speciesOptions = {"Dragon", "Unicorn", "Cat", "Dog", "Phoenix"};
        return speciesOptions[rand.nextInt(speciesOptions.length)];
    }
}

// --- Main Simulation ---
public class VirtualPetSimulator {
    public static void main(String[] args) {
        // Pet daycare
        List<VirtualPet> daycare = new ArrayList<>();

        daycare.add(new VirtualPet()); // Egg
        daycare.add(new VirtualPet("Fluffy")); // Baby
        daycare.add(new VirtualPet("Sparky", "Dragon")); // Child
        daycare.add(new VirtualPet("Shadow", "Wolf", 5, 60, 70, 3)); // Full control

        // Simulate multiple days
        for (int day = 1; day <= 5; day++) {
            System.out.println("\n--- Day " + day + " ---");
            for (VirtualPet pet : daycare) {
                pet.simulateDay();
                System.out.println(pet.getPetStatus());
            }
        }

        System.out.println("\nTotal Pets Created: " + VirtualPet.totalPetsCreated);
    }
}
