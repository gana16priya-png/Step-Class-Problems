package com.company.security;

public class AccessModifierDemo {
    private final int privateField;
              String defaultField;
    protected double protectedField;
    public boolean publicField;

    public AccessModifierDemo(int privateField, String defaultField, double protectedField, boolean publicField) {
        this.privateField = privateField;
        this.defaultField = defaultField;
        this.protectedField = protectedField;
        this.publicField = publicField;
    }

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

    public void testInternalAccess() {
        System.out.println("\nInside testInternalAccess():");
        System.out.println("privateField = " + privateField);
        System.out.println("defaultField = " + defaultField);
        System.out.println("protectedField = " + protectedField);
        System.out.println("publicField = " + publicField);

        privateMethod();
        defaultMethod();
        protectedMethod();
        publicMethod();
    }

    public static void main(String[] args) {
        AccessModifierDemo obj = new AccessModifierDemo(10, "Hello", 99.9, true);

        System.out.println("Access from main():");

        // Fields
        // System.out.println(obj.privateField);  
        System.out.println("defaultField = " + obj.defaultField);    
        System.out.println("protectedField = " + obj.protectedField);
        System.out.println("publicField = " + obj.publicField);      

        // Methods
        // obj.privateMethod();   
        obj.defaultMethod();      
        obj.protectedMethod();    
        obj.publicMethod();       

        obj.testInternalAccess();

        SamePackageTest.testAccess();
    }
}
