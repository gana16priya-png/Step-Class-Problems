package station;

import java.util.Arrays;

public final class CrewRank {
    private final String rankName;
    private final int level;
    private final String[] permissions;

    private CrewRank(String rankName, int level, String[] permissions) {
        this.rankName = rankName;
        this.level = level;
        this.permissions = Arrays.copyOf(permissions, permissions.length);
    }

    public static CrewRank createCadet() {
        return new CrewRank("Cadet", 1, new String[]{"observe"});
    }

    public static CrewRank createOfficer() {
        return new CrewRank("Officer", 2, new String[]{"observe","report"});
    }

    public static CrewRank createCommander() {
        return new CrewRank("Commander", 3, new String[]{"manage","authorize"});
    }

    public static CrewRank createCaptain() {
        return new CrewRank("Captain", 4, new String[]{"command","authorize","launch"});
    }

    public static CrewRank createAdmiral() {
        return new CrewRank("Admiral", 5, new String[]{"command_all","strategize"});
    }

    public String getRankName() { return rankName; }
    public int getLevel() { return level; }
    public String[] getPermissions() { return Arrays.copyOf(permissions, permissions.length); }
}
