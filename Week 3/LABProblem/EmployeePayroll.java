public class EmployeePayroll {
    private String empId;
    private String empName;
    private String department;
    private double baseSalary;
    private String empType;
    private static int totalEmployees = 0;

    // Constructor for full-time employee
    public EmployeePayroll(String empId, String empName, String department, double baseSalary) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.baseSalary = baseSalary;
        this.empType = "Full-Time";
        totalEmployees++;
    }

    // Constructor for part-time employee
    public EmployeePayroll(String empId, String empName, String department, double hourlyRate, int hours) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.baseSalary = hourlyRate * hours;
        this.empType = "Part-Time";
        totalEmployees++;
    }

    // Constructor for contract employee
    public EmployeePayroll(String empId, String empName, String department, double fixedAmount, boolean isContract) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.baseSalary = fixedAmount;
        this.empType = "Contract";
        totalEmployees++;
    }

    // Overloaded calculateSalary methods
    public double calculateSalary(double bonus) { // Full-Time
        if (empType.equals("Full-Time")) {
            return baseSalary + bonus;
        }
        return baseSalary;
    }

    public double calculateSalary(int hours, double hourlyRate) { // Part-Time
        if (empType.equals("Part-Time")) {
            return hours * hourlyRate;
        }
        return baseSalary;
    }

    public double calculateSalary() { // Contract
        if (empType.equals("Contract")) {
            return baseSalary;
        }
        return baseSalary;
    }

    // Overloaded calculateTax methods
    public double calculateTax(double salary) { // Full-Time
        if (empType.equals("Full-Time")) {
            return salary * 0.2; // 20% tax
        }
        return 0;
    }

    public double calculateTax(double salary, double rate) { // Part-Time
        if (empType.equals("Part-Time")) {
            return salary * rate; // e.g., 10% tax
        }
        return 0;
    }

    public double calculateTax() { // Contract
        if (empType.equals("Contract")) {
            return baseSalary * 0.05; // 5% tax
        }
        return 0;
    }

    // Generate Pay Slip
    public void generatePaySlip() {
        double salary;
        double tax;
        if (empType.equals("Full-Time")) {
            salary = calculateSalary(5000); // Example bonus
            tax = calculateTax(salary);
        } else if (empType.equals("Part-Time")) {
            salary = calculateSalary(80, baseSalary / 80); // Example: 80 hours
            tax = calculateTax(salary, 0.1); // 10% tax
        } else {
            salary = calculateSalary();
            tax = calculateTax();
        }
        System.out.println("Pay Slip for " + empName);
        System.out.println("Employee ID: " + empId);
        System.out.println("Department: " + department);
        System.out.println("Type: " + empType);
        System.out.println("Salary: " + salary);
        System.out.println("Tax: " + tax);
        System.out.println("---------------------------");
    }

    // Display Employee Info
    public void displayEmployeeInfo() {
        System.out.println("ID: " + empId + ", Name: " + empName + ", Dept: " + department +
                ", Type: " + empType + ", Base Salary: " + baseSalary);
    }

    // Static method to get total employees
    public static int getTotalEmployees() {
        return totalEmployees;
    }

    // Static method to generate payroll report
    public static void generatePayrollReport(EmployeePayroll[] employees) {
        System.out.println("Company Payroll Report:");
        for (EmployeePayroll emp : employees) {
            emp.displayEmployeeInfo();
        }
        System.out.println("Total Employees: " + getTotalEmployees());
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        EmployeePayroll[] employees = new EmployeePayroll[3];
        employees[0] = new EmployeePayroll("E001", "Alice", "HR", 40000); // Full-Time
        employees[1] = new EmployeePayroll("E002", "Bob", "IT", 500, 80); // Part-Time
        employees[2] = new EmployeePayroll("E003", "Charlie", "Finance", 30000, true); // Contract

        // Demonstrate method overloading
        for (EmployeePayroll emp : employees) {
            emp.generatePaySlip();
        }

        // Generate company-wide payroll report
        generatePayrollReport(employees);
    }
}