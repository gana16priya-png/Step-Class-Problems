package pets;

import java.util.Objects;

public class RobotPet {

    private boolean needsCharging;
    private int batteryLevel;
    private final VirtualPet basePet;

    public RobotPet(String name) {
        this.basePet = new VirtualPet(name, new PetSpecies("Robot",
                new String[]{"Prototype","Model-X","AI Core"}, 20000, "Lab"),
                0, 40, 100);
        this.needsCharging = false;
        this.batteryLevel = 100;
    }

    public boolean isNeedsCharging() { return needsCharging; }
    public void setNeedsCharging(boolean needsCharging) { this.needsCharging = needsCharging; }

    public int getBatteryLevel() { return batteryLevel; }
    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel < 0 || batteryLevel > 100)
            throw new IllegalArgumentException("Battery must be 0-100");
        this.batteryLevel = batteryLevel;
    }

    public VirtualPet getBasePet() { return basePet; }

    @Override
    public String toString() {
        return "RobotPet{" +
                "needsCharging=" + needsCharging +
                ", battery=" + batteryLevel +
                ", base=" + basePet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RobotPet)) return false;
        RobotPet that = (RobotPet) o;
        return basePet.equals(that.basePet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basePet);
    }
}
