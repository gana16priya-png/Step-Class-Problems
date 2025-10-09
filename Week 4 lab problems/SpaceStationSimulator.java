import java.util.*;

// --- Enum with final ---
enum CrewRank {
    CADET(1), OFFICER(2), COMMANDER(3), CAPTAIN(4), ADMIRAL(5);

    private final int level;

    CrewRank(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}

// --- Base Crew Class ---
class SpaceCrew {
    // Final fields (permanent)
    private final String crewId;
    private final String homePlanet;
    private final CrewRank initialRank;

    // Mutable fields
    protected CrewRank currentRank;
    protected int skillLevel;
    protected int missionCount;
    protected int spaceHours;

    // Static fields
    static final String STATION_NAME = "Stellar Odyssey";
    static final int MAX_CREW_CAPACITY = 50;
    private static final Random rand = new Random();

    // --- Constructors ---
    // Full detailed profile
    public SpaceCrew(String crewId, String homePlanet, CrewRank initialRank,
                     CrewRank currentRank, int skillLevel, int missionCount, int spaceHours) {
        this.crewId = crewId;
        this.homePlanet = homePlanet;
        this.initialRank = initialRank;
        this.currentRank = currentRank;
        this.skillLevel = skillLevel;
        this.missionCount = missionCount;
        this.spaceHours = spaceHours;
    }

    // Experienced transfer
    public SpaceCrew(String crewId, String homePlanet, CrewRank rank,
                     int missionCount, int skillLevel) {
        this(crewId, homePlanet, rank, rank, skillLevel, missionCount, missionCount * 100);
    }

    // Standard recruitment
    public SpaceCrew(String crewId, String homePlanet, CrewRank rank) {
        this(crewId, homePlanet, rank, rank, 10, 0, 0);
    }

    // Emergency recruitment (random planet)
    public SpaceCrew(String crewId, CrewRank rank) {
        this(crewId, getRandomPlanet(), rank, rank, 5, 0, 0);
    }

    // --- Final methods ---
    public final String getCrewIdentification() {
        return "ID: " + crewId + " | Planet: " + homePlanet + " | Initial Rank: " + initialRank;
    }

    public final boolean canBePromoted() {
        return currentRank.getLevel() < CrewRank.ADMIRAL.getLevel();
    }

    public final int calculateSpaceExperience() {
        return (missionCount * 10) + (spaceHours / 50) + skillLevel;
    }

    // Helper
    private static String getRandomPlanet() {
        String[] planets = {"Mars", "Europa", "Titan", "Ganymede", "Venus"};
        return planets[rand.nextInt(planets.length)];
    }

    @Override
    public String toString() {
        return getCrewIdentification() +
                " | Current Rank: " + currentRank +
                " | Skills: " + skillLevel +
                " | Missions: " + missionCount +
                " | Hours: " + spaceHours;
    }
}

// --- Specialized Crew Types ---
class PilotCrew extends SpaceCrew {
    private final String flightCertification;

    public PilotCrew(String crewId, String homePlanet, CrewRank rank, String certification) {
        super(crewId, homePlanet, rank);
        this.flightCertification = certification;
    }

    @Override
    public String toString() {
        return super.toString() + " | Pilot Certification: " + flightCertification;
    }
}

class ScienceCrew extends SpaceCrew {
    private final String researchSpecialization;

    public ScienceCrew(String crewId, CrewRank rank, String specialization) {
        super(crewId, rank);
        this.researchSpecialization = specialization;
    }

    @Override
    public String toString() {
        return super.toString() + " | Research: " + researchSpecialization;
    }
}

class EngineerCrew extends SpaceCrew {
    private final String engineeringType;

    public EngineerCrew(String crewId, String homePlanet, CrewRank rank, String type) {
        super(crewId, homePlanet, rank);
        this.engineeringType = type;
    }

    @Override
    public String toString() {
        return super.toString() + " | Engineer Type: " + engineeringType;
    }
}

// --- Final Registry Class ---
final class SpaceStationRegistry {
    private static List<SpaceCrew> crewRegistry = new ArrayList<>();

    public static boolean addCrew(SpaceCrew crew) {
        if (crewRegistry.size() < SpaceCrew.MAX_CREW_CAPACITY) {
            crewRegistry.add(crew);
            return true;
        }
        return false;
    }

    public static void listAllCrew() {
        System.out.println("\n--- Crew Registry (" + crewRegistry.size() + ") ---");
        for (SpaceCrew c : crewRegistry) {
            System.out.println(c);
        }
    }

    public static int totalExperience() {
        int sum = 0;
        for (SpaceCrew c : crewRegistry) {
            sum += c.calculateSpaceExperience();
        }
        return sum;
    }

    public static void emergencyScenario() {
        System.out.println("\n🚨 Emergency! Critical crew required!");
        boolean hasPilot = false, hasEngineer = false, hasScientist = false;

        for (SpaceCrew c : crewRegistry) {
            if (c instanceof PilotCrew) hasPilot = true;
            if (c instanceof EngineerCrew) hasEngineer = true;
            if (c instanceof ScienceCrew) hasScientist = true;
        }

        if (hasPilot && hasEngineer && hasScientist) {
            System.out.println("✅ Emergency team ready: Pilot + Engineer + Scientist combination saves the station!");
        } else {
            System.out.println("❌ Not enough specialized crew for the crisis!");
        }
    }
}

// --- Main Simulation ---
public class SpaceStationSimulator {
    public static void main(String[] args) {
        PilotCrew pilot = new PilotCrew("P-001", "Earth", CrewRank.OFFICER, "Advanced Spaceflight");
        ScienceCrew scientist = new ScienceCrew("S-010", CrewRank.CADET, "Astrobiology");
        EngineerCrew engineer = new EngineerCrew("E-007", "Mars", CrewRank.COMMANDER, "Structural Systems");

        SpaceCrew generic = new SpaceCrew("G-999", CrewRank.CADET);

        SpaceStationRegistry.addCrew(pilot);
        SpaceStationRegistry.addCrew(scientist);
        SpaceStationRegistry.addCrew(engineer);
        SpaceStationRegistry.addCrew(generic);

        SpaceStationRegistry.listAllCrew();

        System.out.println("\nTotal Station Experience: " + SpaceStationRegistry.totalExperience());

        SpaceStationRegistry.emergencyScenario();
    }
}
