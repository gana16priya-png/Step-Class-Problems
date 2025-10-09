package station;

import java.util.UUID;

public class SpaceCrew {
    private final String crewId;
    private final String homeplanet;
    private final SecurityClearance clearance;
    private final CrewRank initialRank;

    private CrewRank currentRank;
    private int missionCount;
    private double spaceHours;

    public static final String STATION_NAME = "Stellar Odyssey";
    public static final int MAX_CREW_CAPACITY = 50;

    // Emergency recruitment
    public SpaceCrew() {
        this("Unknown", "Mars", CrewRank.createCadet(), new SecurityClearance(UUID.randomUUID().toString(), "LOW", new String[]{"hangar"}, System.currentTimeMillis()+86400000));
    }

    // Standard
    public SpaceCrew(String name, String homeplanet, CrewRank initialRank) {
        this(name, homeplanet, initialRank, new SecurityClearance(UUID.randomUUID().toString(), "MEDIUM", new String[]{"lab","bridge"}, System.currentTimeMillis()+604800000));
    }

    // Experienced
    public SpaceCrew(String name, String homeplanet, CrewRank initialRank, int missionCount, double spaceHours) {
        this(name, homeplanet, initialRank, new SecurityClearance(UUID.randomUUID().toString(), "HIGH", new String[]{"lab","bridge","engine"}, System.currentTimeMillis()+1209600000));
        this.missionCount = missionCount;
        this.spaceHours = spaceHours;
    }

    // Full
    public SpaceCrew(String name, String homeplanet, CrewRank initialRank, SecurityClearance clearance) {
        this.crewId = UUID.randomUUID().toString();
        this.homeplanet = homeplanet;
        this.initialRank = initialRank;
        this.currentRank = initialRank;
        this.clearance = clearance;
    }

    public final String getCrewIdentification() {
        return crewId + "@" + STATION_NAME;
    }

    public final boolean canBePromoted() {
        return missionCount > 5 && currentRank.getLevel() < 5;
    }

    public final int calculateSecurityRating() {
        return clearance.getAccessHash() + currentRank.getLevel()*10;
    }

    private final boolean validateClearanceLevel() {
        return !clearance.isExpired();
    }

    protected boolean canAccessSection(String section) {
        return clearance.canAccess(section);
    }

    // Getters/Setters
    public CrewRank getCurrentRank() { return currentRank; }
    public void setCurrentRank(CrewRank rank) { this.currentRank = rank; }

    public int getMissionCount() { return missionCount; }
    public void setMissionCount(int missionCount) { this.missionCount = missionCount; }

    public double getSpaceHours() { return spaceHours; }
    public void setSpaceHours(double spaceHours) { this.spaceHours = spaceHours; }
}
