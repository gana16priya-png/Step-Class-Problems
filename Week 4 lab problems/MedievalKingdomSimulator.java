import java.util.*;

// --- Abstract Base Class ---
abstract class MagicalStructure {
    protected String structureName;
    protected int magicPower;
    protected String location;
    protected boolean isActive;

    // Full constructor (main one)
    public MagicalStructure(String structureName, int magicPower, String location, boolean isActive) {
        this.structureName = structureName;
        this.magicPower = magicPower;
        this.location = location;
        this.isActive = isActive;
    }

    // Overloaded constructor (defaults)
    public MagicalStructure(String structureName, int magicPower) {
        this(structureName, magicPower, "Unknown Land", true);
    }

    public MagicalStructure(String structureName) {
        this(structureName, 50, "Unknown Land", true);
    }

    // Abstract method
    public abstract void castMagicSpell();

    @Override
    public String toString() {
        return structureName + " | Power: " + magicPower + " | Location: " + location + " | Active: " + isActive;
    }
}

// --- Derived Structures ---

class WizardTower extends MagicalStructure {
    private int spellCapacity;
    private List<String> knownSpells;

    // Fully equipped tower
    public WizardTower(String name, int power, String location, int spellCapacity, List<String> spells) {
        super(name, power, location, true);
        this.spellCapacity = spellCapacity;
        this.knownSpells = new ArrayList<>(spells);
    }

    // Basic tower with spells
    public WizardTower(String name, List<String> spells) {
        this(name, 100, "Wizard Valley", 10, spells);
    }

    // Empty tower
    public WizardTower(String name) {
        this(name, 80, "Unknown", 5, new ArrayList<>());
    }

    @Override
    public void castMagicSpell() {
        if (!knownSpells.isEmpty()) {
            System.out.println(structureName + " casts " + knownSpells.get(0) + "!");
        } else {
            System.out.println(structureName + " has no spells to cast!");
        }
    }

    public void doubleSpellCapacity() {
        this.spellCapacity *= 2;
        System.out.println(structureName + " gains knowledge boost! Capacity doubled to " + spellCapacity);
    }
}

class EnchantedCastle extends MagicalStructure {
    private int defenseRating;
    private boolean hasDrawbridge;

    // Fortress
    public EnchantedCastle(String name, int power, String location, int defenseRating, boolean drawbridge) {
        super(name, power, location, true);
        this.defenseRating = defenseRating;
        this.hasDrawbridge = drawbridge;
    }

    // Royal castle
    public EnchantedCastle(String name, int defenseRating) {
        this(name, 150, "Kingdom Center", defenseRating, true);
    }

    // Simple fort
    public EnchantedCastle(String name) {
        this(name, 100, "Borderlands", 50, false);
    }

    @Override
    public void castMagicSpell() {
        System.out.println(structureName + " fortifies defenses magically! Defense: " + defenseRating);
    }

    public void tripleDefense() {
        this.defenseRating *= 3;
        System.out.println(structureName + " is guarded by a Dragon! Defense tripled to " + defenseRating);
    }
}

class MysticLibrary extends MagicalStructure {
    private int bookCount;
    private String ancientLanguage;

    public MysticLibrary(String name, int power, String location, int bookCount, String lang) {
        super(name, power, location, true);
        this.bookCount = bookCount;
        this.ancientLanguage = lang;
    }

    public MysticLibrary(String name, int bookCount) {
        this(name, 120, "Sacred Grove", bookCount, "Unknown");
    }

    public MysticLibrary(String name) {
        this(name, 100, "Hidden Valley", 20, "Ancient Elvish");
    }

    @Override
    public void castMagicSpell() {
        System.out.println(structureName + " unlocks ancient magic from books! Knowledge stored in " + ancientLanguage);
    }
}

class DragonLair extends MagicalStructure {
    private String dragonType;
    private int treasureValue;

    public DragonLair(String name, int power, String location, String dragonType, int treasureValue) {
        super(name, power, location, true);
        this.dragonType = dragonType;
        this.treasureValue = treasureValue;
    }

    public DragonLair(String dragonType) {
        this(dragonType + " Lair", 200, "Volcanic Mountain", dragonType, 5000);
    }

    @Override
    public void castMagicSpell() {
        System.out.println(dragonType + " dragon breathes fire! Treasure guarded: " + treasureValue);
    }
}

// --- Magical Interactions ---
class MagicInteractions {
    public static boolean canStructuresInteract(MagicalStructure s1, MagicalStructure s2) {
        return s1.isActive && s2.isActive;
    }

    public static String performMagicBattle(MagicalStructure attacker, MagicalStructure defender) {
        if (!canStructuresInteract(attacker, defender)) return "Interaction failed!";
        return attacker.structureName + " attacks " + defender.structureName + " → " +
                (attacker.magicPower > defender.magicPower ? attacker.structureName + " wins!" : defender.structureName + " defends!");
    }

    public static int calculateKingdomMagicPower(MagicalStructure[] structures) {
        int sum = 0;
        for (MagicalStructure s : structures) sum += s.magicPower;
        return sum;
    }

    public static void checkSpecialEffects(MagicalStructure s1, MagicalStructure s2) {
        if (s1 instanceof WizardTower && s2 instanceof MysticLibrary) {
            ((WizardTower) s1).doubleSpellCapacity();
        } else if (s1 instanceof MysticLibrary && s2 instanceof WizardTower) {
            ((WizardTower) s2).doubleSpellCapacity();
        } else if (s1 instanceof EnchantedCastle && s2 instanceof DragonLair) {
            ((EnchantedCastle) s1).tripleDefense();
        } else if (s1 instanceof DragonLair && s2 instanceof EnchantedCastle) {
            ((EnchantedCastle) s2).tripleDefense();
        }
    }
}

// --- Kingdom Manager ---
class KingdomManager {
    private List<MagicalStructure> structures = new ArrayList<>();

    public void addStructure(MagicalStructure s) {
        structures.add(s);
    }

    public void categorizeStructures() {
        for (MagicalStructure s : structures) {
            if (s instanceof WizardTower) System.out.println(s.structureName + " → Wizard Tower");
            else if (s instanceof EnchantedCastle) System.out.println(s.structureName + " → Enchanted Castle");
            else if (s instanceof MysticLibrary) System.out.println(s.structureName + " → Mystic Library");
            else if (s instanceof DragonLair) System.out.println(s.structureName + " → Dragon Lair");
        }
    }

    public void calculateTaxes() {
        for (MagicalStructure s : structures) {
            int tax = 0;
            if (s instanceof WizardTower) tax = 50;
            else if (s instanceof EnchantedCastle) tax = 100;
            else if (s instanceof MysticLibrary) tax = 70;
            else if (s instanceof DragonLair) tax = 200;

            System.out.println(s.structureName + " owes " + tax + " gold in taxes.");
        }
    }

    public void determineSpecialization() {
        int magic = 0, defense = 0, knowledge = 0, dragon = 0;
        for (MagicalStructure s : structures) {
            if (s instanceof WizardTower) magic++;
            else if (s instanceof EnchantedCastle) defense++;
            else if (s instanceof MysticLibrary) knowledge++;
            else if (s instanceof DragonLair) dragon++;
        }

        if (magic >= defense && magic >= knowledge && magic >= dragon) {
            System.out.println("Kingdom Specialization: Magic-Focused!");
        } else if (defense >= magic && defense >= knowledge && defense >= dragon) {
            System.out.println("Kingdom Specialization: Defense-Focused!");
        } else if (knowledge >= magic && knowledge >= defense && knowledge >= dragon) {
            System.out.println("Kingdom Specialization: Knowledge-Focused!");
        } else {
            System.out.println("Kingdom Specialization: Dragon-Focused!");
        }
    }
}

// --- Main Simulation ---
public class MedievalKingdomSimulator {
    public static void main(String[] args) {
        KingdomManager kingdom = new KingdomManager();

        WizardTower tower1 = new WizardTower("Arcane Spire", Arrays.asList("Fireball", "Lightning"));
        EnchantedCastle castle = new EnchantedCastle("Stonehold", 80);
        MysticLibrary library = new MysticLibrary("Grand Archive", 200);
        DragonLair lair = new DragonLair("Fire Dragon");

        kingdom.addStructure(tower1);
        kingdom.addStructure(castle);
        kingdom.addStructure(library);
        kingdom.addStructure(lair);

        System.out.println("\n--- Categorization ---");
        kingdom.categorizeStructures();

        System.out.println("\n--- Taxes ---");
        kingdom.calculateTaxes();

        System.out.println("\n--- Special Effects ---");
        MagicInteractions.checkSpecialEffects(tower1, library);
        MagicInteractions.checkSpecialEffects(castle, lair);

        System.out.println("\n--- Battle Simulation ---");
        System.out.println(MagicInteractions.performMagicBattle(tower1, lair));
        System.out.println(MagicInteractions.performMagicBattle(castle, tower1));

        System.out.println("\n--- Specialization ---");
        kingdom.determineSpecialization();

        System.out.println("\nTotal Kingdom Magic Power: " +
                MagicInteractions.calculateKingdomMagicPower(new MagicalStructure[]{tower1, castle, library, lair}));
    }
}
