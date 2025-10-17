// File: EmployeeDemo.java
abstract class Employee {
    protected String name;
    protected double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public abstract double calculateBonus();
}

interface Payable {
    void generatePaySlip();
}

class Manager extends Employee implements Payable {
    private String department;

    public Manager(String name, double salary, String department) {
        super(name, salary);
        this.department = department;
    }

    @Override
    public double calculateBonus() {
        return salary * 0.2;  // 20% bonus
    }

    @Override
    public void generatePaySlip() {
        System.out.println("Pay Slip for Manager");
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Salary: " + salary);
        System.out.println("Bonus: " + calculateBonus());
        System.out.println("Net Pay: " + (salary + calculateBonus()));
    }
}

public class EmployeeDemo {
    public static void main(String[] args) {
        Manager mgr = new Manager("Alice", 60000, "IT");
        mgr.generatePaySlip();
    }
}
