// Base class
class Instrument {
    protected String name;
    protected String material;

    Instrument(String name, String material) {
        this.name = name;
        this.material = material;
    }

    void play() {
        System.out.println(name + " is playing...");
    }
}

// Child class 1
class Piano extends Instrument {
    private int keys;

    Piano(String name, String material, int keys) {
        super(name, material);
        this.keys = keys;
    }

    @Override
    void play() {
        System.out.println(name + " with " + keys + " keys is playing melodiously!");
    }
}

// Child class 2
class Guitar extends Instrument {
    private int strings;

    Guitar(String name, String material, int strings) {
        super(name, material);
        this.strings = strings;
    }

    @Override
    void play() {
        System.out.println(name + " with " + strings + " strings is strumming!");
    }
}

// Child class 3
class Drum extends Instrument {
    private int size;

    Drum(String name, String material, int size) {
        super(name, material);
        this.size = size;
    }

    @Override
    void play() {
        System.out.println(name + " of size " + size + " is beating loudly!");
    }
}

// Test class
public class LabProblem5 {
    public static void main(String[] args) {
        Instrument[] instruments = {
            new Piano("Piano", "Wood", 88),
            new Guitar("Guitar", "Steel", 6),
            new Drum("Drum", "Plastic", 22)
        };

        for (Instrument i : instruments) {
            i.play(); // runtime polymorphism
        }
    }
}
