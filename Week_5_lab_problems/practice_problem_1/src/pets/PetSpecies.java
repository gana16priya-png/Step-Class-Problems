package pets;

import java.util.Arrays;
import java.util.Objects;

public final class PetSpecies {

    private final String speciesName;
    private final String[] evolutionStages;
    private final int maxLifespan;
    private final String habitat;

    public PetSpecies(String speciesName, String[] evolutionStages, int maxLifespan, String habitat) {
        if (speciesName == null || speciesName.isBlank())
            throw new IllegalArgumentException("Species name required");
        if (evolutionStages == null || evolutionStages.length == 0)
            throw new IllegalArgumentException("At least one evolution stage is required");
        if (maxLifespan <= 0)
            throw new IllegalArgumentException("Max lifespan must be positive");
        if (habitat == null || habitat.isBlank())
            throw new IllegalArgumentException("Habitat required");

        this.speciesName = speciesName;
        this.evolutionStages = Arrays.copyOf(evolutionStages, evolutionStages.length);
        this.maxLifespan = maxLifespan;
        this.habitat = habitat;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public String[] getEvolutionStages() {
        return Arrays.copyOf(evolutionStages, evolutionStages.length);
    }

    public int getMaxLifespan() {
        return maxLifespan;
    }

    public String getHabitat() {
        return habitat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetSpecies)) return false;
        PetSpecies that = (PetSpecies) o;
        return maxLifespan == that.maxLifespan &&
                speciesName.equals(that.speciesName) &&
                habitat.equals(that.habitat) &&
                Arrays.equals(evolutionStages, that.evolutionStages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(speciesName, habitat, maxLifespan, Arrays.hashCode(evolutionStages));
    }

    @Override
    public String toString() {
        return "PetSpecies{" +
                "speciesName='" + speciesName + '\'' +
                ", maxLifespan=" + maxLifespan +
                ", habitat='" + habitat + '\'' +
                '}';
    }
}
