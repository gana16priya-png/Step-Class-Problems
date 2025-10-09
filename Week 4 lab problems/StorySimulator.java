import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * StorySimulator.java
 * Single-file example implementing the Practice Problem 4 requirements.
 */

// Personality enum (final-ish semantics via enum)
enum PersonalityType { BRAVE, CUNNING, MELANCHOLIC, JOYFUL, MYSTERIOUS, SARDONIC }

// Base class for all story characters
abstract class StoryCharacter implements Serializable {
    private static final long serialVersionUID = 1L;

    // Final (immutable) attributes
    public final String characterId;
    public final String backstory;
    public final PersonalityType corePersonality;

    // Dynamic attributes
    protected String currentMood;
    protected Map<String, Integer> relationshipMap; // otherId -> affinity score
    protected Set<String> skillSet;
    protected String currentLocation;
    protected boolean selfAware = false; // can become true at runtime

    // Static / shared
    public static final String[] STORY_GENRES = {"Fantasy", "Sci-Fi", "Mystery", "Romance", "Adventure"};
    private static final Random RAND = new Random();

    // ID generator (simple)
    private static final AtomicInteger ID_COUNTER = new AtomicInteger(1000);
    public static String generateCharacterId() {
        return "C" + ID_COUNTER.getAndIncrement();
    }

    // --- Constructors (chaining) ---
    public StoryCharacter(String backstory, PersonalityType corePersonality, String initialLocation) {
        this.characterId = generateCharacterId();
        this.backstory = backstory;
        this.corePersonality = corePersonality;
        this.currentMood = corePersonalityToMood(corePersonality);
        this.relationshipMap = new HashMap<>();
        this.skillSet = new HashSet<>();
        this.currentLocation = initialLocation;
    }

    // Create from prompt (simple parsing stub)
    public StoryCharacter(String prompt) {
        this(extractBackstoryFromPrompt(prompt), extractPersonalityFromPrompt(prompt), defaultLocationForGenre(prompt));
    }

    // Random generator
    public StoryCharacter() {
        this("A mystery from nowhere", PersonalityType.MYSTERIOUS, "Unknown");
    }

    // helpers used in constructors
    private static String extractBackstoryFromPrompt(String p) {
        if (p == null || p.isEmpty()) return "A life shrouded in mystery";
        return "Born of prompt: " + (p.length() <= 30 ? p : p.substring(0,30) + "...");
    }
    private static PersonalityType extractPersonalityFromPrompt(String p) {
        if (p == null) return PersonalityType.MYSTERIOUS;
        p = p.toLowerCase();
        if (p.contains("brave")) return PersonalityType.BRAVE;
        if (p.contains("sad") || p.contains("melanch")) return PersonalityType.MELANCHOLIC;
        if (p.contains("fun") || p.contains("joke")) return PersonalityType.JOYFUL;
        if (p.contains("cunning") || p.contains("trick")) return PersonalityType.CUNNING;
        return PersonalityType.MYSTERIOUS;
    }
    private static String defaultLocationForGenre(String p) {
        if (p == null) return "Unknown";
        if (p.toLowerCase().contains("space")) return "Orbital Dock";
        if (p.toLowerCase().contains("kingdom")) return "Capital Keep";
        return "Wandering Road";
    }

    // Utility
    private String corePersonalityToMood(PersonalityType t) {
        switch (t) {
            case BRAVE: return "Determined";
            case JOYFUL: return "Cheerful";
            case CUNNING: return "Scheming";
            case MELANCHOLIC: return "Brooding";
            default: return "Neutral";
        }
    }

    // Final methods that cannot be overridden by subclasses
    public final String getIdentity() {
        return String.format("%s | [%s] | Personality:%s", characterId, backstory, corePersonality);
    }

    public final int getRelationshipScore(String otherId) {
        return relationshipMap.getOrDefault(otherId, 0);
    }

    public final void rememberInteraction(String otherId, int delta) {
        relationshipMap.put(otherId, getRelationshipScore(otherId) + delta);
    }

    public final int calculateCharacterProgress() {
        // deterministic algorithm that subclasses cannot override
        return skillSet.size() * 10 + relationshipMap.values().stream().mapToInt(Integer::intValue).sum();
    }

    // Abstract behaviour to be implemented by concrete types
    public abstract void act(SceneContext ctx);

    // Dynamic operations
    public void changeMood(String mood) { this.currentMood = mood; }
    public void learnSkill(String skill) { this.skillSet.add(skill); }
    public void moveTo(String location) { this.currentLocation = location; }

    // Meta-story: become self-aware
    public void awakenSelfAwareness() {
        if (!selfAware) {
            selfAware = true;
            System.out.println("[" + characterId + "] suddenly becomes self-aware and whispers: " +
                    "\"Why was I constructed this way? My backstory is final!\"");
        }
    }

    // Attempt to hack a final attribute -> should always fail (simulated)
    public void attemptHackFinals() {
        System.out.println("[" + characterId + "] attempts to hack their final backstory...");
        System.out.println("...error: final attributes immutable. They make a comedic sigh.");
        // Creates humor by adding a temporary mood but not changing final fields
        changeMood("Defeated (but witty)");
    }

    // Dialogue generation using inheritance info
    public String speak(String line) {
        // Base implementation uses personality to flavor lines
        switch (corePersonality) {
            case BRAVE: return line + " (said bravely)";
            case CUNNING: return line + " (murmured with a sly grin)";
            case JOYFUL: return line + " (laughed cheerfully)";
            case MELANCHOLIC: return line + " (said sadly)";
            default: return line + " (in mysterious tone)";
        }
    }

    @Override
    public String toString() {
        return String.format("%s | Mood:%s | Loc:%s | Skills:%s", getIdentity(), currentMood, currentLocation, skillSet);
    }
}

// Concrete Hero
class Hero extends StoryCharacter {
    private final String origin; // final origin story detail that determines final ability
    private final String finalAbility; // final attribute derived from origin

    // Hero constructors showing chaining
    public Hero(String origin, PersonalityType p, String location) {
        super("Origin: " + origin, p, location);
        this.origin = origin;
        this.finalAbility = deriveAbilityFromOrigin(origin);
        // heroes start knowing one ability
        learnSkill(finalAbility);
    }
    public Hero(String origin) { this(origin, PersonalityType.BRAVE, "Home Village"); }
    public Hero() { this("Unknown Heritage"); }

    private String deriveAbilityFromOrigin(String o) {
        if (o.toLowerCase().contains("star")) return "Starlight Slash";
        if (o.toLowerCase().contains("forge")) return "Forge Might";
        return "Courage Strike";
    }

    @Override
    public void act(SceneContext ctx) {
        // Heroes prefer direct action
        System.out.println(speak("I will face the danger head-on!"));
        // interacts with others
        for (StoryCharacter other : ctx.getCharacters()) {
            if (!other.characterId.equals(this.characterId)) {
                rememberInteraction(other.characterId, +2);
            }
        }
    }

    public String getFinalAbility() { return finalAbility; }
}

// Concrete Villain
class Villain extends StoryCharacter {
    private final String evilMotivation; // final, permanent
    private int menaceLevel; // mutable

    public Villain(String motivation, PersonalityType p, String lair) {
        super("Motivation: " + motivation, p, lair);
        this.evilMotivation = motivation;
        this.menaceLevel = 5;
    }
    public Villain(String motivation) { this(motivation, PersonalityType.CUNNING, "Hidden Lair"); }

    @Override
    public void act(SceneContext ctx) {
        System.out.println(speak("All will bend to my plan: " + evilMotivation));
        menaceLevel++;
        // manipulate relationships (reduce others' opinion)
        for (StoryCharacter other : ctx.getCharacters()) {
            if (!other.characterId.equals(this.characterId)) {
                other.rememberInteraction(this.characterId, -1);
            }
        }
    }

    public void taunt() {
        System.out.println("[" + characterId + "] taunts: \"You cannot change what I was made to do.\"");
    }
}

// MysteriousStranger: hides most attributes until revealed
class MysteriousStranger extends StoryCharacter {
    private transient boolean revealed = false; // transient: not serialized as permanent reveal state

    public MysteriousStranger(String prompt) { super(prompt); }

    @Override
    public void act(SceneContext ctx) {
        if (!revealed && RAND.nextDouble() < 0.3) {
            revealed = true;
            learnSkill("Hidden Lore");
            System.out.println(speak("I was not what I seemed...revealed!"));
        } else {
            System.out.println(speak("...I drift in silence."));
        }
    }

    public void forceReveal() {
        revealed = true;
    }
    private static final Random RAND = new Random();
}

// Comic Relief
class ComicRelief extends StoryCharacter {
    public final String humorStyle; // final

    public ComicRelief(String style) {
        super("Born on a stage", PersonalityType.JOYFUL, "Traveling Troupe");
        this.humorStyle = style;
    }

    @Override
    public void act(SceneContext ctx) {
        System.out.println(speak("Here's a joke about constructors: \"Why can't I change my backstory? I tried!\""));
        // timing dynamic: jokes may increase others' mood
        for (StoryCharacter c : ctx.getCharacters()) {
            if (!c.characterId.equals(this.characterId)) c.rememberInteraction(this.characterId, +1);
        }
    }
}

// SceneContext used when characters act
class SceneContext {
    private final List<StoryCharacter> characters;
    private final String sceneGenre;

    public SceneContext(List<StoryCharacter> characters, String genre) {
        this.characters = characters;
        this.sceneGenre = genre;
    }
    public List<StoryCharacter> getCharacters() { return characters; }
    public String getSceneGenre() { return sceneGenre; }
}

// StoryEngine: static rules + generation + saving/loading + fusion
class StoryEngine {
    private final List<StoryCharacter> cast = new ArrayList<>();

    // Add characters
    public void add(StoryCharacter c) { cast.add(c); }

    // Categorize by instanceof
    public void categorizeCast() {
        System.out.println("--- Cast Categorization ---");
        for (StoryCharacter c : cast) {
            if (c instanceof Hero) System.out.println(c.characterId + " → Hero");
            else if (c instanceof Villain) System.out.println(c.characterId + " → Villain");
            else if (c instanceof MysteriousStranger) System.out.println(c.characterId + " → MysteriousStranger");
            else if (c instanceof ComicRelief) System.out.println(c.characterId + " → ComicRelief");
            else System.out.println(c.characterId + " → UnknownType");
        }
    }

    // generateStoryArc uses instanceof combos to decide arc type
    public String generateStoryArc() {
        boolean hasHero = false, hasVillain = false, hasMyst = false;
        for (StoryCharacter c : cast) {
            if (c instanceof Hero) hasHero = true;
            if (c instanceof Villain) hasVillain = true;
            if (c instanceof MysteriousStranger) hasMyst = true;
        }
        if (hasHero && hasVillain && hasMyst) return "Epic Redemption Arc (high drama + twists)";
        if (hasHero && hasVillain) return "Classic Hero vs Villain Showdown";
        if (hasMyst) return "Mystery-Driven Character Study";
        return "Slice-of-Life Adventure";
    }

    // resolveConflict: simplistic battle of wits/power using instanceof + calculateCharacterProgress
    public String resolveConflict(StoryCharacter a, StoryCharacter b) {
        int aScore = a.calculateCharacterProgress();
        int bScore = b.calculateCharacterProgress();

        // type advantage: heroes get bonus vs villains; villains cunning bonus vs mysterious
        if (a instanceof Hero && b instanceof Villain) aScore += 10;
        if (a instanceof Villain && b instanceof MysteriousStranger) aScore += 8;
        if (b instanceof ComicRelief) bScore += 2; // morale buff

        String res = aScore > bScore ? a.characterId + " prevails" : b.characterId + " prevails";
        return "Conflict: " + a.characterId + " ("+aScore+") vs " + b.characterId + " ("+bScore+") → " + res;
    }

    // Dialogue creation that reflects inheritance
    public void createDialogue(StoryCharacter speaker, String line) {
        System.out.println(speaker.characterId + " says: " + speaker.speak(line));
    }

    // Fusion: combine two characters into a new one (final attributes combined into a new final backstory)
    public StoryCharacter fusion(StoryCharacter a, StoryCharacter b) {
        String fusedBackstory = "Fusion of " + a.characterId + " & " + b.characterId + ": " + a.backstory + " + " + b.backstory;
        PersonalityType fusedPersonality = RAND.nextBoolean() ? a.corePersonality : b.corePersonality;
        // Simple rule: if either is Hero, new character is Hero; else if either is Villain, Villain; else MysteriousStranger
        StoryCharacter fused;
        if (a instanceof Hero || b instanceof Hero) {
            Hero h = new Hero("Fused Origin", fusedPersonality, a.currentLocation);
            fused = h;
        } else if (a instanceof Villain || b instanceof Villain) {
            Villain v = new Villain("Fused Motivation", fusedPersonality, a.currentLocation);
            fused = v;
        } else {
            fused = new MysteriousStranger(fusedBackstory);
        }
        // carry over some skills (non-final)
        fused.skillSet.addAll(a.skillSet);
        fused.skillSet.addAll(b.skillSet);
        fused.rememberInteraction(a.characterId, 5);
        fused.rememberInteraction(b.characterId, 5);
        System.out.println("Created fused character: " + fused.characterId + " with backstory: " + fused.backstory);
        add(fused);
        return fused;
    }

    // Save cast to disk (serialization)
    public void saveCast(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(cast);
            System.out.println("Saved cast to " + filename);
        }
    }

    // Load cast from disk
    @SuppressWarnings("unchecked")
    public void loadCast(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            List<StoryCharacter> loaded = (List<StoryCharacter>) ois.readObject();
            cast.clear();
            cast.addAll(loaded);
            System.out.println("Loaded cast from " + filename);
        }
    }

    // Simple scene simulation
    public void simulateScene(String genre) {
        System.out.println("\n=== Scene (" + genre + ") ===");
        SceneContext ctx = new SceneContext(new ArrayList<>(cast), genre);
        // each casts acts; order matters -> meta features may trigger
        for (StoryCharacter c : new ArrayList<>(cast)) {
            c.act(ctx);
            // random chance for self-awareness
            if (!c.selfAware && RAND.nextDouble() < 0.08) {
                c.awakenSelfAwareness();
            }
            // random attempt to hack finals (for humor)
            if (RAND.nextDouble() < 0.03) c.attemptHackFinals();
        }

        // After acting, show relationships snapshot
        System.out.println("--- Relationship Snapshot ---");
        for (StoryCharacter c : cast) {
            System.out.println(c.characterId + " relationships: " + c.relationshipMap);
        }
    }

    public void listCast() {
        System.out.println("\n--- Current Cast ---");
        for (StoryCharacter c : cast) System.out.println(c);
    }

    private static final Random RAND = new Random();
}

// Final class StoryStats cannot be extended
final class StoryStats {
    public static Map<String, Integer> trackConstructorUsage = new HashMap<>();

    public static void registerConstructor(String constructorName) {
        trackConstructorUsage.put(constructorName, trackConstructorUsage.getOrDefault(constructorName, 0) + 1);
    }

    public static void printStats() {
        System.out.println("\n--- Story Engine Stats ---");
        trackConstructorUsage.forEach((k,v) -> System.out.println(k + " used: " + v + " times"));
    }
}

// Main driver
public class StorySimulator {
    public static void main(String[] args) {
        StoryEngine engine = new StoryEngine();

        // Create characters in different ways (constructor patterns)
        Hero hero1 = new Hero("Starborn", PersonalityType.BRAVE, "Skyward Hamlet"); StoryStats.registerConstructor("Hero(full)");
        Villain villain1 = new Villain("World Domination", PersonalityType.CUNNING, "Obsidian Keep"); StoryStats.registerConstructor("Villain(full)");
        MysteriousStranger stranger1 = new MysteriousStranger("A whisper in space"); StoryStats.registerConstructor("Mysterious(prompt)");
        ComicRelief comic1 = new ComicRelief("Slapstick"); StoryStats.registerConstructor("Comic(style)");

        // generate from prompt
        StoryCharacter fromPrompt = new MysteriousStranger("brave traveler from kingdom of fog"); StoryStats.registerConstructor("FromPrompt");

        // random generator (default constructor)
        StoryCharacter randomChar = new Hero(); StoryStats.registerConstructor("Hero(default)");

        // Add to engine
        engine.add(hero1); engine.add(villain1); engine.add(stranger1); engine.add(comic1); engine.add(fromPrompt); engine.add(randomChar);

        // categorize
        engine.categorizeCast();

        // create dialogue
        engine.createDialogue(hero1, "We must go now!");
        engine.createDialogue(villain1, "You are too naïve.");

        // fusion (twist)
        StoryCharacter fused = engine.fusion(hero1, stranger1);

        // simulate a few scenes
        engine.simulateScene("Fantasy");
        engine.simulateScene("Mystery");

        // conflict resolution demo
        System.out.println("\n" + engine.resolveConflict(hero1, villain1));

        // list cast and stats
        engine.listCast();
        StoryStats.printStats();

        // demonstrate save/load (serialization)
        String savefile = "saved_cast.bin";
        try {
            engine.saveCast(savefile);
            // load back (to show preserved finals)
            engine.loadCast(savefile);
            engine.listCast();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Save/Load failed: " + e.getMessage());
        }

        // show meta features: characters attempt to hack final trait
        hero1.attemptHackFinals();
        villain1.awakenSelfAwareness();

        System.out.println("\nStory Arc Suggestion: " + engine.generateStoryArc());

        System.out.println("\n--- End of simulation ---");
    }
}
