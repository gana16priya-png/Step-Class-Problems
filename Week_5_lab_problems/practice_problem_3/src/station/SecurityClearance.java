package station;

import java.util.Arrays;

public final class SecurityClearance {
    private final String clearanceId;
    private final String level;
    private final String[] authorizedSections;
    private final long expirationDate;

    public SecurityClearance(String clearanceId, String level, String[] authorizedSections, long expirationDate) {
        if (clearanceId == null || level == null || authorizedSections == null)
            throw new IllegalArgumentException("Invalid clearance data");
        this.clearanceId = clearanceId;
        this.level = level;
        this.authorizedSections = Arrays.copyOf(authorizedSections, authorizedSections.length);
        this.expirationDate = expirationDate;
    }

    public String getClearanceId() { return clearanceId; }
    public String getLevel() { return level; }
    public String[] getAuthorizedSections() { return Arrays.copyOf(authorizedSections, authorizedSections.length); }
    public long getExpirationDate() { return expirationDate; }

    public final boolean canAccess(String section) {
        return Arrays.asList(authorizedSections).contains(section);
    }

    public final boolean isExpired() {
        return System.currentTimeMillis() > expirationDate;
    }

    public final int getAccessHash() {
        return (clearanceId + level).hashCode();
    }
}
