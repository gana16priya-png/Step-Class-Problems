import java.util.*;

// ======================= IMMUTABLE CONFIG CLASSES ======================= //
final class CastleConfig {
    private final String name;
    private final int defenseLevel;
    private final int guardCount;

    public CastleConfig(String name, int defenseLevel, int guardCount) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Castle name cannot be empty");
        if (defenseLevel < 0 || defenseLevel > 100) throw new IllegalArgumentException("Defense level 0-100 only");
        if (guardCount < 0) throw new IllegalArgumentException("Guard count cannot be negative");
        this.name = name;
        this.defenseLevel = defenseLevel;
        this.guardCount = guardCount;
    }

    public String getName() { return name; }
    public int getDefenseLevel() { return defenseLevel; }
    public int getGuardCount() { return guardCount; }

    @Override
    public String toString() {
        return "Castle[" + name + ", Defense=" + defenseLevel + ", Guards=" + guardCount + "]";
    }
}

final class MarketConfig {
    private final String name;
    private final int stallCount;
    private final String specialty;

    public MarketConfig(String name, int stallCount, String specialty) {
        if (stallCount < 0) throw new IllegalArgumentException("Stall count cannot be negative");
        this.name = Objects.requireNonNullElse(name, "Default Market");
        this.stallCount = stallCount;
        this.specialty = Objects.requireNonNullElse(specialty, "General Goods");
    }

    public String getName() { return name; }
    public int getStallCount() { return stallCount; }
    public String getSpecialty() { return specialty; }

    @Override
    public String toString() {
        return "Market[" + name + ", Stalls=" + stallCount + ", Specialty=" + specialty + "]";
    }
}

final class BarrackConfig {
    private final String name;
    private final int soldierCapacity;
    private final boolean trainingGround;

    public BarrackConfig(String name, int soldierCapacity, boolean trainingGround) {
        if (soldierCapacity < 0) throw new IllegalArgumentException("Capacity must be positive");
        this.name = name;
        this.soldierCapacity = soldierCapacity;
        this.trainingGround = trainingGround;
    }

    public String getName() { return name; }
    public int getSoldierCapacity() { return soldierCapacity; }
    public boolean hasTrainingGround() { return trainingGround; }

    @Override
    public String toString() {
        return "Barrack[" + name + ", Capacity=" + soldierCapacity + ", TrainingGround=" + trainingGround + "]";
    }
}

// ======================= KINGDOM STRUCTURES ======================= //
abstract class Structure {
    private final String structureId;
    private final long createdAt;

    protected Structure() {
        this.structureId = UUID.randomUUID().toString();
        this.createdAt = System.currentTimeMillis();
    }

    public String getStructureId() { return structureId; }
    public long getCreatedAt() { return createdAt; }

    public abstract String getDetails();
}

class Castle extends Structure {
    private final CastleConfig config;

    // Constructor Overloading
    public Castle(CastleConfig config) {
        super();
        this.config = config;
    }
    public Castle(String name) {
        this(new CastleConfig(name, 50, 100));
    }

    @Override
    public String getDetails() {
        return "Castle Details: " + config.toString();
    }
}

class Market extends Structure {
    private final MarketConfig config;

    public Market(MarketConfig config) {
        super();
        this.config = config;
    }
    public Market(String name) {
        this(new MarketConfig(name, 20, "Food"));
    }

    @Override
    public String getDetails() {
        return "Market Details: " + config.toString();
    }
}

class Barrack extends Structure {
    private final BarrackConfig config;

    public Barrack(BarrackConfig config) {
        super();
        this.config = config;
    }
    public Barrack(String name) {
        this(new BarrackConfig(name, 200, true));
    }

    @Override
    public String getDetails() {
        return "Barrack Details: " + config.toString();
    }
}

// ======================= KINGDOM MANAGER ======================= //
class Kingdom {
    private final String kingdomName;
    private final List<Structure> structures = new ArrayList<>();

    public Kingdom(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Kingdom must have a name");
        this.kingdomName = name;
    }

    public void addStructure(Structure s) {
        structures.add(s);
    }

    public void showStructures() {
        System.out.println("🏰 Kingdom: " + kingdomName);
        for (Structure s : structures) {
            System.out.println(" - " + s.getDetails());

            // instanceof usage
            if (s instanceof Castle c) {
                System.out.println("   🔒 Stronghold defense ready.");
            } else if (s instanceof Market m) {
                System.out.println("   🛒 Trade hub active.");
            } else if (s instanceof Barrack b) {
                System.out.println("   ⚔ Soldiers trained.");
            }
        }
    }
}

// ======================= MAIN DEMO ======================= //
public class MedievalKingdomDemo {
    public static void main(String[] args) {
        Kingdom kingdom = new Kingdom("Avaloria");

        CastleConfig cConfig = new CastleConfig("Stonehold", 90, 500);
        MarketConfig mConfig = new MarketConfig("Central Bazaar", 50, "Exotic Spices");
        BarrackConfig bConfig = new BarrackConfig("Iron Legion Barracks", 300, true);

        // Using different constructors
        Castle castle = new Castle(cConfig);
        Market market = new Market(mConfig);
        Barrack barrack = new Barrack("Default Barrack");

        kingdom.addStructure(castle);
        kingdom.addStructure(market);
        kingdom.addStructure(barrack);

        kingdom.showStructures();
    }
}
