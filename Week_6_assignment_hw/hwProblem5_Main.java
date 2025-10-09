// hwProblem5.java

class BasicMath {
    // Overloaded calculate methods
    public int calculate(int a, int b) {
        return a + b; // addition
    }

    public double calculate(double a, double b) {
        return a * b; // multiplication
    }

    public int calculate(int a) {
        return a * a; // square
    }
}

class AdvancedMath extends BasicMath {
    // More overloaded methods
    public double calculate(double a, int power) {
        return Math.pow(a, power); // power
    }

    public double calculate(int base, double root) {
        return Math.pow(base, 1.0 / root); // nth root
    }
}

public class hwProblem5_Main {
    public static void main(String[] args) {
        AdvancedMath math = new AdvancedMath();

        // Using inherited methods
        System.out.println("Sum: " + math.calculate(5, 3));          // BasicMath
        System.out.println("Multiply: " + math.calculate(2.0, 4.0)); // BasicMath
        System.out.println("Square: " + math.calculate(6));          // BasicMath

        // Using new overloaded methods
        System.out.println("Power: " + math.calculate(2.0, 3));      // AdvancedMath
        System.out.println("Root: " + math.calculate(27, 3.0));      // AdvancedMath
    }
}
