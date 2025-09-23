import java.util.*;

// ========== IMMUTABLE SPECIES CLASS ==========
final class PetSpecies {
    private final String speciesName;
    private final String[] evolutionStages;
    private final int maxLifespan;
    private final String habitat;

    public PetSpecies(String speciesName, String[] evolutionStages, int maxLifespan, String habitat) {
        if (speciesName == null || speciesName.isBlank())
            throw new IllegalArgumentException("Species name cannot be blank");
        if (evolutionStages == null || evolutionStages.length == 0)
            throw new IllegalArgumentException("Evolution stages cannot be empty");
        if (maxLifespan <= 0)
            throw new IllegalArgumentException("Max lifespan must be positive");
        if (habitat == null || habitat.isBlank())
            throw new IllegalArgumentException("Habitat cannot be blank");

        this.speciesName = speciesName;
        this.evolutionStages = Arrays.copyOf(evolutionStages, evolutionStages.length);
        this.maxLifespan = maxLifespan;
        this.habitat = habitat;
    }

    public String getSpeciesName() { return speciesName; }
    public String[] getEvolutionStages() { return Arrays.copyOf(evolutionStages, evolutionStages.length); }
    public int getMaxLifespan() { return maxLifespan; }
    public String getHabitat() { return habitat; }

    @Override
    public String toString() {
        return "PetSpecies{" +
                "speciesName='" + speciesName + '\'' +
                ", maxLifespan=" + maxLifespan +
                ", habitat='" + habitat + '\'' +
                ", evolutionStages=" + Arrays.toString(evolutionStages) +
                '}';
    }
}

// ========== VIRTUAL PET CLASS ==========
class VirtualPet {
    // Immutable core
    private final String petId;
    private final PetSpecies species;
    private final long birthTimestamp;

    // Mutable controlled state
    private String petName;
    private int age;
    private int happiness;
    private int health;

    // Access control
    protected static final String[] DEFAULT_EVOLUTION_STAGES = {"Baby", "Teen", "Adult", "Elder"};
    static final int MAX_HAPPINESS = 100, MAX_HEALTH = 100;
    public static final String PET_SYSTEM_VERSION = "2.0";

    private String currentStage;

    // ----- Constructors -----
    public VirtualPet() {
        this("Unnamed", new PetSpecies("DefaultPet", DEFAULT_EVOLUTION_STAGES, 20, "Forest"));
    }

    public VirtualPet(String name) {
        this(name, new PetSpecies("DefaultPet", DEFAULT_EVOLUTION_STAGES, 20, "Forest"));
    }

    public VirtualPet(String name, PetSpecies species) {
        this(name, species, 0, 50, 50);
    }

    public VirtualPet(String name, PetSpecies species, int age, int happiness, int health) {
        this.petId = UUID.randomUUID().toString();
        this.species = Objects.requireNonNull(species);
        this.birthTimestamp = System.currentTimeMillis();
        setPetName(name);
        setAge(age);
        setHappiness(happiness);
        setHealth(health);
        this.currentStage = species.getEvolutionStages()[0];
    }

    // ----- Getters -----
    public String getPetId() { return petId; }
    public PetSpecies getSpecies() { return species; }
    public long getBirthTimestamp() { return birthTimestamp; }
    public String getPetName() { return petName; }
    public int getAge() { return age; }
    public int getHappiness() { return happiness; }
    public int getHealth() { return health; }
    public String getCurrentStage() { return currentStage; }

    // ----- Setters with validation -----
    public void setPetName(String petName) {
        if (petName == null || petName.isBlank())
            throw new IllegalArgumentException("Pet name cannot be blank");
        this.petName = petName;
    }

    public void setAge(int age) {
        if (age < 0 || age > species.getMaxLifespan())
            throw new IllegalArgumentException("Age out of range");
        this.age = age;
        checkEvolution();
    }

    public void setHappiness(int happiness) { this.happiness = validateStat(happiness, MAX_HAPPINESS); }
    public void setHealth(int health) { this.health = validateStat(health, MAX_HEALTH); }

    // ----- Public Methods -----
    public void feedPet(String foodType) {
        int bonus = calculateFoodBonus(foodType);
        modifyHealth(bonus);
        System.out.println(petName + " enjoyed " + foodType + "! Health + " + bonus);
    }

    public void playWithPet(String gameType) {
        int effect = calculateGameEffect(gameType);
        modifyHappiness(effect);
        System.out.println(petName + " loved playing " + gameType + "! Happiness + " + effect);
    }

    // ----- Protected Internal -----
    protected int calculateFoodBonus(String foodType) {
        return switch (foodType.toLowerCase()) {
            case "meat" -> 15;
            case "veggies" -> 10;
            default -> 5;
        };
    }

    protected int calculateGameEffect(String gameType) {
        return switch (gameType.toLowerCase()) {
            case "fetch" -> 20;
            case "dance" -> 15;
            default -> 10;
        };
    }

    // ----- Private Helpers -----
    private int validateStat(int value, int max) {
        if (value < 0) return 0;
        return Math.min(value, max);
    }

    private void modifyHappiness(int delta) { setHappiness(this.happiness + delta); }
    private void modifyHealth(int delta) { setHealth(this.health + delta); }

    private void checkEvolution() {
        int stageIndex = Math.min(age / 5, species.getEvolutionStages().length - 1);
        currentStage = species.getEvolutionStages()[stageIndex];
    }

    // Package-private debug
    String getInternalState() {
        return "PetID=" + petId + " | Name=" + petName +
                " | Age=" + age + " | Happiness=" + happiness +
                " | Health=" + health + " | Stage=" + currentStage;
    }

    @Override
    public String toString() {
        return "VirtualPet{" +
                "petId='" + petId + '\'' +
                ", petName='" + petName + '\'' +
                ", age=" + age +
                ", happiness=" + happiness +
                ", health=" + health +
                ", currentSta
