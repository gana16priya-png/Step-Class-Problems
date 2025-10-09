// File: GameBattle.java
class GameBattle {

    // Basic attack
    public void attack(int damage) {
        System.out.println("Basic attack for " + damage + " points!");
    }

    // Attack with weapon
    public void attack(int damage, String weapon) {
        System.out.println("Attacking with " + weapon + " for " + damage + " points!");
    }

    // Attack with weapon + critical hit possibility
    public void attack(int damage, String weapon, boolean isCritical) {
        if (isCritical) {
            System.out.println("CRITICAL HIT! " + weapon + " deals " + (damage * 2) + " points!");
        } else {
            // fallback to the 2-argument method
            attack(damage, weapon);
        }
    }

    // Team attack
    public void attack(int damage, String[] teammates) {
        int totalDamage = damage * teammates.length;
        System.out.print("Team attack with ");
        for (int i = 0; i < teammates.length; i++) {
            System.out.print(teammates[i]);
            if (i < teammates.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" for " + totalDamage + " total damage!");
    }

    public static void main(String[] args) {
        // Gaming Battle Simulation
        GameBattle gb = new GameBattle();

        // Basic attack with 50 damage
        gb.attack(50);

        // Sword attack with 75 damage
        gb.attack(75, "Sword");

        // Critical bow attack with 60 damage
        gb.attack(60, "Bow", true);

        // Team attack with {"Alice", "Bob"} for 40 base damage
        String[] teammates = {"Alice", "Bob"};
        gb.attack(40, teammates);
    }
}
