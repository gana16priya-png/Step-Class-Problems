package com.company.extended;

import com.company.security.AccessModifierDemo;

public class ExtendedDemo extends AccessModifierDemo {

    // Constructor calls parent constructor
    public ExtendedDemo(int privateVal, String defaultVal, double protectedVal, boolean publicVal) {
        super(privateVal, defaultVal, protectedVal, publicVal);
    }

    public void testInheritedAccess() {
        System.out.println("\nInside ExtendedDemo (different package, WITH inheritance):");

        // System.out.println(privateField);   
        // System.out.println(defaultField);   
        System.out.println("protectedField = " + protectedField); 
        System.out.println("publicField = " + publicField);       

        // Methods
        // privateMethod();   
        // defaultMethod();   
        protectedMethod();   
        publicMethod();      
    }

    // Overriding protected method
    @Override
    protected void protectedMethod() {
        System.out.println("Overridden protected method in ExtendedDemo");
    }

    public static void main(String[] args) {
        ExtendedDemo child = new ExtendedDemo(30, "Child", 7.7, true);
        child.testInheritedAccess();

        // Also test parent object
        AccessModifierDemo parent = new AccessModifierDemo(10, "Parent", 99.9, false);
        System.out.println("\nFrom parent reference:");
        // parent.protectedMethod(); // ❌ Not accessible outside package
        parent.publicMethod();       // ✅ Accessible
    }
}
