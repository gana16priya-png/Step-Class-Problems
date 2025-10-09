package com.company.main;

import com.company.security.AccessModifierDemo;

public class PackageTestMain {
    public static void main(String[] args) {
        System.out.println("\nInside PackageTestMain (different package, no inheritance):");

        AccessModifierDemo obj = new AccessModifierDemo(5, "Hello", 12.3, true);

        // Fields
        // System.out.println(obj.privateField); 
        // System.out.println(obj.defaultField);   
        // System.out.println(obj.protectedField);
        System.out.println("publicField = " + obj.publicField); 

        // Methods
        // obj.privateMethod();   
        // obj.defaultMethod();   
        // obj.protectedMethod(); 
        obj.publicMethod();       
    }
}
