package pets;

import java.util.Objects;

public class DragonPet {

    private final String dragonType;
    private final String breathWeapon;
    private final VirtualPet basePet;

    public DragonPet(String name, String dragonType, String breathWeapon) {
        this.basePet = new VirtualPet(name, new PetSpecies("Dragon",
                new String[]{"Hatchling","Young","Adult","Ancient"}, 10000, "Volcano"),
                0, 70, 80);
        this.dragonType = dragonType;
        this.breathWeapon = breathWeapon;
    }

    public String getDragonType() { return dragonType; }
    public String getBreathWeapon() { return breathWeapon; }
    public VirtualPet getBasePet() { return basePet; }

    @Override
    public String toString() {
        return "DragonPet{" +
                "type='" + dragonType + '\'' +
                ", breath='" + breathWeapon + '\'' +
                ", base=" + basePet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DragonPet)) return false;
        DragonPet that = (DragonPet) o;
        return dragonType.equals(that.dragonType) && basePet.equals(that.basePet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dragonType, basePet);
    }
}
