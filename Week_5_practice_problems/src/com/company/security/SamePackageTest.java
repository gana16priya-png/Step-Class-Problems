package com.company.security;

class SamePackageTest {
    public static void testAccess() {
        System.out.println("\nInside SamePackageTest:");
        AccessModifierDemo obj = new AccessModifierDemo(20, "World", 55.5, false);

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
    }

    public static void main(String[] args) {
        testAccess();
    }
}
