import java.util.Random;
import java.util.UUID;

public class SpaceCrew {
    // Permanent attributes
    private final String crewId;         // unique permanent ID
    private final String homePlanet;     // cannot change
    private final CrewRank initialRank;  // starting rank (for promotion checks)

    // Flexible attributes
    protected CrewRank currentRank;
    protected int skillLevel;
    protected int missionCount;
    protected int spaceHours;

    // Static constants
    public static final String STATION_NAME = "Stellar Odyssey";
    public static final int MAX_CREW_CAPACITY = 50;

    // ---- Constructors ----
    // Emergency recruitment
    public SpaceCrew(CrewRank rank) {
        this.crewId = UUID.randomUUID().toString();
        this.homePlanet = generateRandomPlanet();
        this.initialRank = rank;
        this.currentRank = rank;
    }

    // Standard recruitment
    public SpaceCrew(String homePlanet, CrewRank rank) {
        this.crewId = UUID.randomUUID().toString();
        this.homePlanet = homePlanet;
        this.initialRank = rank;
        this.currentRank = rank;
    }

    // Experienced transfer
    public SpaceCrew(String homePlanet, CrewRank rank, int missionCount, int skillLevel) {
        this(homePlanet, rank); // calls the above constructor
        this.missionCount = missionCount;
        this.skillLevel = skillLevel;
    }

    // Full detailed profile
    public SpaceCrew(String homePlanet, CrewRank rank, int missionCount, int skillLevel, int spaceHours) {
        this(homePlanet, rank, missionCount, skillLevel);
        this.spaceHours = spaceHours;
    }

    private String generateRandomPlanet() {
        String[] planets = {"Mars", "Venus", "Titan", "Europa", "Kepler-22b"};
        return planets[new Random().nextInt(planets.length)];
    }

    // ---- Final Methods ----
    public final String getCrewIdentification() {
        return "ID: " + crewId + " | Home: " + homePlanet + " | Initial Rank: " + initialRank;
    }

    public final boolean canBePromoted() {
        return currentRank.higherThan(initialRank) == false && currentRank != CrewRank.ADMIRAL;
    }

    public final int calculateSpaceExperience() {
        return missionCount * 10 + skillLevel * 5 + spaceHours;
    }
}
