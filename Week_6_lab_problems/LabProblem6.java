// Parent class
class Box {
    void pack() {
        System.out.println("Box is being packed.");
    }

    void unpack() {
        System.out.println("Box is being unpacked.");
    }
}

// Child class
class GiftBox extends Box {
    @Override
    void pack() {
        super.pack(); // call parent method
        System.out.println("Adding decorative wrapping for the gift.");
        System.out.println("Tying a ribbon around the box.");
    }

    @Override
    void unpack() {
        super.unpack(); // call parent method
        System.out.println("Unwrapping decorative paper.");
        System.out.println("Removing the ribbon.");
    }
}

// Test class
public class LabProblem6 {
    public static void main(String[] args) {
        GiftBox gbox = new GiftBox();

        System.out.println("Packing process:");
        gbox.pack();

        System.out.println("\nUnpacking process:");
        gbox.unpack();
    }
}
