package station;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        SpaceCrew c1 = new SpaceCrew("Alice", "Earth", CrewRank.createOfficer());
        SpaceCrew c2 = new SpaceCrew("Bob", "Mars", CrewRank.createCommander(), 10, 200.5);

        CommandCrew cmd = new CommandCrew(Set.of("Bridge Control","Tactical"));
        PilotCrew pilot = new PilotCrew(Set.of("Orbital Flight","Docking"));

        SpaceStationRegistry.registerCrew(c1);
        SpaceStationRegistry.registerCrew(c2);
        SpaceStationRegistry.registerCrew(cmd);
        SpaceStationRegistry.registerCrew(pilot);

        System.out.println(c1.getCrewIdentification());
        System.out.println("Can be promoted: " + c2.canBePromoted());
        System.out.println("Command certs: " + cmd.getCommandCertifications());
    }
}
