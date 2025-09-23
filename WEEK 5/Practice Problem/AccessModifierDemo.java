package com.company.security;

public class AccessModifierDemo {
    // Fields with different access modifiers
    private int privateField;           // Only accessible within this class
    String defaultField;                // Default (package-private): accessible within same package
    protected double protectedField;    // Accessible in package + subclasses
    public boolean publicField;         // Accessible everywhere

    // Constructor initializing all fields
    public AccessModifierDemo(int privateField, String defaultField, double protectedField, boolean publicField) {
        this.privateField = privateField;
        this.defaultField = defaultField;
        this.protectedField = protectedField;
        this.publicField = publicField;
    }

    // Methods with different access modifiers
    private void privateMethod() {
        System.out.println("Private method called");
    }

    void defaultMethod() {
        System.out.println("Default method called");
    }

    protected void protectedMethod() {
        System.out.println("Protected method called");
    }

    public void publicMethod() {
        System.out.println("Public method called");
    }

    // Public method to test internal access
    public void testInternalAccess() {
        // Access and print all fields
        System.out.println("privateField: " + privateField);
        System.out.println("defaultField: " + defaultField);
        System.out.println("protectedField: " + protectedField);
        System.out.println("publicField: " + publicField);

        // Call all methods
        privateMethod();
        defaultMethod();
        protectedMethod();
        publicMethod();

        // Demonstrates that private members are accessible within the same class
    }

    public static void main(String[] args) {
        AccessModifierDemo demo = new AccessModifierDemo(10, "default", 3.14, true);

        // System.out.println(demo.privateField); // ERROR: privateField not accessible
        System.out.println(demo.defaultField);      // OK: default access within same package
        System.out.println(demo.protectedField);    // OK: protected access within same package
        System.out.println(demo.publicField);       // OK: public access everywhere

        // demo.privateMethod(); // ERROR: privateMethod not accessible
        demo.defaultMethod();      // OK: default access within same package
        demo.protectedMethod();    // OK: protected access within same package
        demo.publicMethod();       // OK: public access everywhere

        demo.testInternalAccess(); // Shows all internal accesses (including private)
    }
}

// Second class in the SAME package
class SamePackageTest {
    public static void testAccess() {
        AccessModifierDemo demo = new AccessModifierDemo(20, "package", 2.71, false);

        // System.out.println(demo.privateField); // ERROR: privateField not accessible
        System.out.println(demo.defaultField);      // OK: default access within same package
        System.out.println(demo.protectedField);    // OK: protected access within same package
        System.out.println(demo.publicField);       // OK: public access everywhere

        // demo.privateMethod(); // ERROR: privateMethod not accessible
        demo.defaultMethod();      // OK: default access within same package
        demo.protectedMethod();    // OK: protected access within same package
        demo.publicMethod();       // OK: public access everywhere
    }
}