// Parent class
class Bird {
    void fly() {
        System.out.println("Bird is flying...");
    }
}

// Child class 1
class Penguin extends Bird {
    @Override
    void fly() {
        System.out.println("Penguin cannot fly, it swims instead!");
    }
}

// Child class 2
class Eagle extends Bird {
    @Override
    void fly() {
        System.out.println("Eagle soars high in the sky!");
    }
}

// Test class
public class LabProblem3 {
    public static void main(String[] args) {
        Bird[] birds = { new Bird(), new Penguin(), new Eagle() };

        for (Bird b : birds) {
            b.fly(); // polymorphism in action
        }
    }
}
