package station;

import java.util.*;

public final class SpaceStationRegistry {
    private static final Map<String, Object> crewRegistry = new HashMap<>();

    public static boolean registerCrew(Object crew) {
        String id = UUID.randomUUID().toString();
        crewRegistry.put(id, crew);
        return true;
    }

    public static List<Object> getCrewByType(String type) {
        List<Object> result = new ArrayList<>();
        for (Object crew : crewRegistry.values()) {
            if (crew.getClass().getSimpleName().equalsIgnoreCase(type)) {
                result.add(crew);
            }
        }
        return result;
    }
}
