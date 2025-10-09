import java.util.Scanner;

class Employee {
    private String empId;
    private String empName;
    private String department;
    private double baseSalary;
    private String empType; // Full-time, Part-time, Contract

    private static int empCounter = 0;
    private static int totalEmployees = 0;

    // Constructor for Full-time Employee
    public Employee(String empName, String department, double baseSalary) {
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = department;
        this.baseSalary = baseSalary;
        this.empType = "Full-Time";
        totalEmployees++;
    }

    // Constructor for Part-time Employee
    public Employee(String empName, String department, double hourlyRate, int hours) {
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = department;
        this.baseSalary = hourlyRate * hours; // computed later in salary
        this.empType = "Part-Time";
        totalEmployees++;
    }

    // Constructor for Contract Employee
    public Employee(String empName, String department, double contractAmount, boolean isContract) {
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = department;
        this.baseSalary = contractAmount;
        this.empType = "Contract";
        totalEmployees++;
    }

    // Overloaded calculateSalary()
    public double calculateSalary(double bonus) { // For Full-Time
        return baseSalary + bonus;
    }

    public double calculateSalary(int hours, double hourlyRate) { // For Part-Time
        return hours * hourlyRate;
    }

    public double calculateSalary() { // For Contract
        return baseSalary;
    }

    // Overloaded calculateTax()
    public double calculateTax(double salary, double rate) {
        return salary * rate;
    }

    public double calculateTax(double salary) {
        // default 10% tax
        return salary * 0.10;
    }

    // Generate Payslip
    public void generatePaySlip() {
        double salary = 0, tax = 0;
        if (empType.equals("Full-Time")) {
            salary = calculateSalary(5000); // assuming fixed bonus
            tax = calculateTax(salary, 0.15);
        } else if (empType.equals("Part-Time")) {
            salary = baseSalary; // already hours * rate
            tax = calculateTax(salary, 0.05);
        } else if (empType.equals("Contract")) {
            salary = calculateSalary();
            tax = calculateTax(salary);
        }

        System.out.println("=================================");
        System.out.println("Employee Pay Slip");
        System.out.println("ID: " + empId);
        System.out.println("Name: " + empName);
        System.out.println("Department: " + department);
        System.out.println("Type: " + empType);
        System.out.println("Gross Salary: " + salary);
        System.out.println("Tax: " + tax);
        System.out.println("Net Salary: " + (salary - tax));
        System.out.println("=================================");
    }

    public void displayEmployeeInfo() {
        System.out.println(empId + " | " + empName + " | " + department + " | " + empType);
    }

    public static int getTotalEmployees() {
        return totalEmployees;
    }

    private static String generateEmpId() {
        empCounter++;
        return "E" + String.format("%03d", empCounter);
    }
}

public class EmployeePayrollSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Employee[] employees = new Employee[10];
        int empCount = 0;

        while (true) {
            System.out.println("\n===== Payroll Menu =====");
            System.out.println("1. Add Full-Time Employee");
            System.out.println("2. Add Part-Time Employee");
            System.out.println("3. Add Contract Employee");
            System.out.println("4. Show All Employees");
            System.out.println("5. Generate Pay Slip");
            System.out.println("6. Show Total Employees");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String ftName = sc.nextLine();
                    System.out.print("Enter Department: ");
                    String ftDept = sc.nextLine();
                    System.out.print("Enter Base Salary: ");
                    double base = sc.nextDouble();
                    employees[empCount++] = new Employee(ftName, ftDept, base);
                    System.out.println("Full-time employee added.");
                    break;

                case 2:
                    System.out.print("Enter Name: ");
                    String ptName = sc.nextLine();
                    System.out.print("Enter Department: ");
                    String ptDept = sc.nextLine();
                    System.out.print("Enter Hourly Rate: ");
                    double hr = sc.nextDouble();
                    System.out.print("Enter Hours Worked: ");
                    int hrs = sc.nextInt();
                    employees[empCount++] = new Employee(ptName, ptDept, hr, hrs);
                    System.out.println("Part-time employee added.");
                    break;

                case 3:
                    System.out.print("Enter Name: ");
                    String cName = sc.nextLine();
                    System.out.print("Enter Department: ");
                    String cDept = sc.nextLine();
                    System.out.print("Enter Contract Amount: ");
                    double amt = sc.nextDouble();
                    employees[empCount++] = new Employee(cName, cDept, amt, true);
                    System.out.println("Contract employee added.");
                    break;

                case 4:
                    System.out.println("\n--- Employee List ---");
                    for (int i = 0; i < empCount; i++) {
                        employees[i].displayEmployeeInfo();
                    }
                    break;

                case 5:
                    System.out.print("Enter Employee Index (0-" + (empCount - 1) + "): ");
                    int idx = sc.nextInt();
                    if (idx >= 0 && idx < empCount) {
                        employees[idx].generatePaySlip();
                    } else {
                        System.out.println("Invalid index!");
                    }
                    break;

                case 6:
                    System.out.println("Total Employees: " + Employee.getTotalEmployees());
                    break;

                case 7:
                    System.out.println("Exiting Payroll System...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
