import java.util.Scanner;

class Employee {
    private static String companyName;
    private static int totalEmployees = 0;

    private String empId;
    private String name;
    private String department;
    private double salary;

    // Constructors
    public Employee() {
        totalEmployees++;
    }

    public Employee(String empId, String name, String department, double salary) {
        this.empId = empId;
        this.name = name;
        this.department = department;
        this.salary = salary;
        totalEmployees++;
    }

    // Static methods
    public static void setCompanyName(String name) {
        companyName = name;
    }

    public static int getTotalEmployees() {
        return totalEmployees;
    }

    // Instance methods
    public double calculateAnnualSalary() {
        return salary * 12;
    }

    public void updateSalary(double newSalary) {
        this.salary = newSalary;
    }

    public void displayEmployee() {
        System.out.println("ID: " + empId + " | Name: " + name +
                           " | Department: " + department + " | Salary: " + salary);
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }
}

class Department {
    String deptName;
    Employee[] employees;
    int employeeCount;

    public Department(String deptName, int capacity) {
        this.deptName = deptName;
        this.employees = new Employee[capacity];
        this.employeeCount = 0;
    }

    public void addEmployee(Employee emp) {
        if (employeeCount < employees.length) {
            employees[employeeCount++] = emp;
            System.out.println(emp.getName() + " added to " + deptName);
        }
    }

    public Employee findHighestPaid() {
        if (employeeCount == 0) return null;
        Employee highest = employees[0];
        for (int i = 1; i < employeeCount; i++) {
            if (employees[i].getSalary() > highest.getSalary()) {
                highest = employees[i];
            }
        }
        return highest;
    }

    public double calculateTotalPayroll() {
        double total = 0;
        for (int i = 0; i < employeeCount; i++) {
            total += employees[i].getSalary();
        }
        return total;
    }

    public void displayDepartmentEmployees() {
        System.out.println("Employees in " + deptName + ":");
        for (int i = 0; i < employeeCount; i++) {
            employees[i].displayEmployee();
        }
    }
}

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Employee.setCompanyName("TechCorp");

        Department dept1 = new Department("IT", 5);
        Department dept2 = new Department("HR", 5);

        Employee e1 = new Employee("E001", "Alice", "IT", 50000);
        Employee e2 = new Employee("E002", "Bob", "IT", 60000);
        Employee e3 = new Employee("E003", "Charlie", "HR", 45000);

        dept1.addEmployee(e1);
        dept1.addEmployee(e2);
        dept2.addEmployee(e3);

        dept1.displayDepartmentEmployees();
        dept2.displayDepartmentEmployees();

        System.out.println("Highest Paid in IT: " + dept1.findHighestPaid().getName());
        System.out.println("IT Payroll: " + dept1.calculateTotalPayroll());
        System.out.println("HR Payroll: " + dept2.calculateTotalPayroll());
        System.out.println("Total Employees: " + Employee.getTotalEmployees());

        scanner.close();
    }
}
