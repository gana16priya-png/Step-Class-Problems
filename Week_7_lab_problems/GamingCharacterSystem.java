abstract class Character {
    String name;
    public Character(String name) {
        this.name = name;
    }
    abstract void attack();
}

class Warrior extends Character {
    public Warrior(String name) { super(name); }
    @Override
    void attack() {
        System.out.println(name + " swings a mighty sword!  High defense mode!");
    }
}

class Mage extends Character {
    public Mage(String name) { super(name); }
    @Override
    void attack() {
        System.out.println(name + " casts a powerful spell!  Using mana points!");
    }
}

class Archer extends Character {
    public Archer(String name) { super(name); }
    @Override
    void attack() {
        System.out.println(name + " shoots a precise arrow!  Long-range attack!");
    }
}

public class GamingCharacterSystem {
    public static void main(String[] args) {
        Character[] army = {
            new Warrior("Thor"),
            new Mage("Gandalf"),
            new Archer("Legolas")
        };

        // Dynamic Method Dispatch in action!
        for (Character c : army) {
            c.attack();  // runtime decides actual method
        }
    }
}
