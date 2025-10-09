// File: EmployeeTest.java

abstract class Employee {
    protected String name;
    protected double salary;

    // Constructor
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Abstract method
    public abstract double calculateBonus();

    // Concrete method
    public void displayDetails() {
        System.out.println("Employee Name: " + name);
        System.out.println("Salary: " + salary);
    }
}

class Manager extends Employee {
    public Manager(String name, double salary) {
        super(name, salary);
    }

    @Override
    public double calculateBonus() {
        return salary * 0.20;  // 20% bonus
    }
}

class Developer extends Employee {
    public Developer(String name, double salary) {
        super(name, salary);
    }

    @Override
    public double calculateBonus() {
        return salary * 0.10;  // 10% bonus
    }
}

public class EmployeeTest {
    public static void main(String[] args) {
        Employee e1 = new Manager("Tanmay", 50000);
        Employee e2 = new Developer("Rahul", 40000);

        e1.displayDetails();
        System.out.println("Bonus: " + e1.calculateBonus());
        System.out.println();

        e2.displayDetails();
        System.out.println("Bonus: " + e2.calculateBonus());
    }
}
