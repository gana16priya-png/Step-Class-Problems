package station;

import java.util.Set;

public class CommandCrew {
    private final Set<String> commandCertifications;

    public CommandCrew(Set<String> certs) {
        this.commandCertifications = Set.copyOf(certs);
    }

    public Set<String> getCommandCertifications() {
        return Set.copyOf(commandCertifications);
    }
}
