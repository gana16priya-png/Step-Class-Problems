package station;

import java.util.Set;

public class PilotCrew {
    private final Set<String> flightCertifications;

    public PilotCrew(Set<String> certs) {
        this.flightCertifications = Set.copyOf(certs);
    }

    public Set<String> getFlightCertifications() {
        return Set.copyOf(flightCertifications);
    }
}
