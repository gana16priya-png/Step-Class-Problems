package pets;

import java.util.Objects;
import java.util.UUID;

public class VirtualPet {

    // ----- Core immutable -----
    private final String petId;
    private final PetSpecies species;
    private final long birthTimestamp;

    // ----- Controlled mutable -----
    private String petName;
    private int age;
    private int happiness;
    private int health;

    // ----- Constants -----
    protected static final String[] DEFAULT_EVOLUTION_STAGES = {"Egg", "Baby", "Teen", "Adult"};
    static final int MAX_HAPPINESS = 100;
    static final int MAX_HEALTH = 100;
    public static final String PET_SYSTEM_VERSION = "2.0";

    // ----- Constructors (Chaining) -----
    public VirtualPet() {
        this("Pet-" + UUID.randomUUID().toString().substring(0, 5),
             new PetSpecies("Default", DEFAULT_EVOLUTION_STAGES, 5000, "Forest"),
             0, 50, 50);
    }

    public VirtualPet(String name) {
        this(name, new PetSpecies("Default", DEFAULT_EVOLUTION_STAGES, 5000, "Forest"), 0, 60, 60);
    }

    public VirtualPet(String name, PetSpecies species) {
        this(name, species, 0, 60, 60);
    }

    public VirtualPet(String name, PetSpecies species, int age, int happiness, int health) {
        if (species == null) throw new IllegalArgumentException("Species cannot be null");
        this.petId = generatePetId();
        this.species = species;
        this.birthTimestamp = System.currentTimeMillis();
        this.petName = name;
        setAge(age);
        setHappiness(happiness);
        setHealth(health);
    }

    // ----- JavaBean Getters/Setters -----
    public String getPetId() { return petId; }
    public PetSpecies getSpecies() { return species; }
    public long getBirthTimestamp() { return birthTimestamp; }

    public String getPetName() { return petName; }
    public void setPetName(String petName) {
        if (petName == null || petName.isBlank())
            throw new IllegalArgumentException("Name cannot be blank");
        this.petName = petName;
    }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = validateStat(age, 0, species.getMaxLifespan()); }

    public int getHappiness() { return happiness; }
    public void setHappiness(int happiness) { this.happiness = validateStat(happiness, 0, MAX_HAPPINESS); }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = validateStat(health, 0, MAX_HEALTH); }

    // ----- Public interface -----
    public void feedPet(String foodType) {
        int bonus = calculateFoodBonus(foodType);
        modifyHealth(bonus);
    }

    public void playWithPet(String gameType) {
        int effect = calculateGameEffect(gameType);
        modifyHappiness(effect);
        updateEvolutionStage();
    }

    // ----- Protected Internal Calculations -----
    protected int calculateFoodBonus(String foodType) {
        return switch (foodType.toLowerCase()) {
            case "meat" -> 10;
            case "veggies" -> 5;
            default -> 2;
        };
    }

    protected int calculateGameEffect(String gameType) {
        return switch (gameType.toLowerCase()) {
            case "fetch" -> 8;
            case "dance" -> 12;
            default -> 4;
        };
    }

    // ----- Private Internal Logic -----
    private void modifyHappiness(int amount) {
        setHappiness(Math.min(MAX_HAPPINESS, happiness + amount));
    }

    private void modifyHealth(int amount) {
        setHealth(Math.min(MAX_HEALTH, health + amount));
    }

    private void updateEvolutionStage() {
        // (Could check happiness/age and update stage)
    }

    private int validateStat(int value, int min, int max) {
        if (value < min || value > max)
            throw new IllegalArgumentException("Stat must be between " + min + " and " + max);
        return value;
    }

    private String generatePetId() {
        return "PET-" + UUID.randomUUID().toString().substring(0, 8);
    }

    // ----- Package-private debugging -----
    String getInternalState() {
        return String.format("ID=%s Name=%s Age=%d Happy=%d Health=%d",
                petId, petName, age, happiness, health);
    }

    // ----- Overrides -----
    @Override
    public String toString() {
        return "VirtualPet{" +
                "id='" + petId + '\'' +
                ", name='" + petName + '\'' +
                ", species=" + species.getSpeciesName() +
                ", happiness=" + happiness +
                ", health=" + health +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VirtualPet)) return false;
        VirtualPet that = (VirtualPet) o;
        return petId.equals(that.petId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petId);
    }
}
