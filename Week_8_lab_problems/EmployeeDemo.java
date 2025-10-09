// Abstract Class
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
}

// Interface
interface Payable {
    void generatePaySlip();
}

// Concrete Class
class Manager extends Employee implements Payable {
    private String department;

    // Constructor
    public Manager(String name, double salary, String department) {
        super(name, salary);
        this.department = department;
    }

    // Implement abstract method
    @Override
    public double calculateBonus() {
        return salary * 0.20; // 20% bonus
    }

    // Implement interface method
    @Override
    public void generatePaySlip() {
        System.out.println("------ PAY SLIP ------");
        System.out.println("Employee: " + name);
        System.out.println("Department: " + department);
        System.out.println("Base Salary: " + salary);
        System.out.println("Bonus: " + calculateBonus());
        System.out.println("Total Pay: " + (salary + calculateBonus()));
        System.out.println("----------------------");
    }
}

// Main Class
public class EmployeeDemo {
    public static void main(String[] args) {
        Manager m = new Manager("Alice", 80000, "Finance");
        m.generatePaySlip();
    }
}
