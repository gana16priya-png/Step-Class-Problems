
public class Employee {
    protected String empId;
    protected String empName;
    protected String department;
    protected String designation;
    protected double baseSalary;
    protected String joinDate;
    protected boolean[] attendanceRecord; // 30 days
    protected int leavesRequested;

    protected static int totalEmployees = 0;
    protected static String companyName = "Tech Solutions";
    protected static double totalSalaryExpense = 0;
    protected static int workingDaysPerMonth = 30;

    public Employee(String empId, String empName, String department, String designation, double baseSalary, String joinDate) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.designation = designation;
        this.baseSalary = baseSalary;
        this.joinDate = joinDate;
        this.attendanceRecord = new boolean[workingDaysPerMonth];
        this.leavesRequested = 0;
        totalEmployees++;
    }

    public void markAttendance(int day, boolean present) {
        if (day >= 1 && day <= workingDaysPerMonth) {
            attendanceRecord[day - 1] = present;
        }
    }

    public void requestLeave(int day) {
        if (day >= 1 && day <= workingDaysPerMonth && !attendanceRecord[day - 1]) {
            leavesRequested++;
            System.out.println(empName + " requested leave for day " + day);
        }
    }

    public int getPresentDays() {
        int count = 0;
        for (boolean present : attendanceRecord) if (present) count++;
        return count;
    }

    public double calculateSalary() {
        double salary = baseSalary;
        // Deduct salary for absent days (except approved leaves)
        int absentDays = workingDaysPerMonth - getPresentDays() - leavesRequested;
        salary -= (baseSalary / workingDaysPerMonth) * absentDays;
        totalSalaryExpense += salary;
        return salary;
    }

    public double calculateBonus() {
        // Simple performance-based bonus: 10% if attendance > 27 days
        int presentDays = getPresentDays();
        if (presentDays >= 28) return baseSalary * 0.10;
        else if (presentDays >= 25) return baseSalary * 0.05;
        else return 0;
    }

    public void generatePaySlip() {
        double salary = calculateSalary();
        double bonus = calculateBonus();
        System.out.println("Pay Slip for " + empName + " (" + empId + ")");
        System.out.println("Department: " + department + ", Designation: " + designation);
        System.out.println("Base Salary: " + baseSalary);
        System.out.println("Attendance: " + getPresentDays() + "/" + workingDaysPerMonth);
        System.out.println("Leaves Requested: " + leavesRequested);
        System.out.println("Salary after deductions: " + salary);
        System.out.println("Performance Bonus: " + bonus);
        System.out.println("Total Pay: " + (salary + bonus));
        System.out.println("---------------------------");
    }
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String empId, String empName, String department, String designation, double baseSalary, String joinDate) {
        super(empId, empName, department, designation, baseSalary, joinDate);
    }
    @Override
    public double calculateSalary() {
        // Full-time: base salary, deduction for absents
        return super.calculateSalary();
    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String empId, String empName, String department, String designation, double hourlyRate, String joinDate, int hoursWorked) {
        super(empId, empName, department, designation, hourlyRate * hoursWorked, joinDate);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        // Part-time: paid only for hours worked
        double salary = hourlyRate * hoursWorked;
        totalSalaryExpense += salary;
        return salary;
    }
}

class ContractEmployee extends Employee {
    private double contractAmount;

    public ContractEmployee(String empId, String empName, String department, String designation, double contractAmount, String joinDate) {
        super(empId, empName, department, designation, contractAmount, joinDate);
        this.contractAmount = contractAmount;
    }

    @Override
    public double calculateSalary() {
        // Contract: fixed amount, no attendance deduction
        totalSalaryExpense += contractAmount;
        return contractAmount;
    }
}

class Department {
    private String deptId;
    private String deptName;
    private Employee manager;
    private Employee[] employees;
    private double budget;

    public Department(String deptId, String deptName, Employee manager, Employee[] employees, double budget) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.manager = manager;
        this.employees = employees;
        this.budget = budget;
    }

    public double getDepartmentExpense() {
        double expense = 0;
        for (Employee e : employees) {
            if (e != null) expense += e.calculateSalary() + e.calculateBonus();
        }
        return expense;
    }

    public void displayDepartmentInfo() {
        System.out.println("Department: " + deptName + " (ID: " + deptId + ")");
        System.out.println("Manager: " + manager.empName);
        System.out.println("Budget: " + budget);
        System.out.println("Employees:");
        for (Employee e : employees) {
            if (e != null) System.out.println("- " + e.empName + " (" + e.designation + ")");
        }
        System.out.println("Department Expense: " + getDepartmentExpense());
        System.out.println("---------------------------");
    }
    public Employee[] getEmployees() { return employees; }
}

public class EmployeeManagement {
    // Static methods
    public static double calculateCompanyPayroll(Employee[] employees) {
        double total = 0;
        for (Employee e : employees) {
            if (e != null) total += e.calculateSalary() + e.calculateBonus();
        }
        return total;
    }

    public static void getDepartmentWiseExpenses(Department[] departments) {
        for (Department d : departments) {
            d.displayDepartmentInfo();
        }
    }

    public static void getAttendanceReport(Employee[] employees) {
        System.out.println("Attendance Report:");
        for (Employee e : employees) {
            if (e != null)
                System.out.println(e.empName + ": " + e.getPresentDays() + "/" + Employee.workingDaysPerMonth);
        }
        System.out.println("---------------------------");
    }

    public static void main(String[] args) {
        // Create employees
        Employee e1 = new FullTimeEmployee("E001", "Alice", "IT", "Developer", 50000, "01-01-2022");
        Employee e2 = new PartTimeEmployee("E002", "Bob", "IT", "Support", 500, "01-01-2022", 80);
        Employee e3 = new ContractEmployee("E003", "Charlie", "HR", "Consultant", 30000, "01-01-2022");

        // Mark attendance
        for (int i = 1; i <= 30; i++) {
            e1.markAttendance(i, i != 5 && i != 12); // Absent on 5, 12
            e2.markAttendance(i, i % 2 == 0); // Present on even days
            e3.markAttendance(i, true); // Always present
        }
        e1.requestLeave(5);
        e1.requestLeave(12);

        // Create departments
        Employee[] itEmployees = {e1, e2};
        Employee[] hrEmployees = {e3};
        Department d1 = new Department("D01", "IT", e1, itEmployees, 200000);
        Department d2 = new Department("D02", "HR", e3, hrEmployees, 100000);

        // Generate pay slips
        e1.generatePaySlip();
        e2.generatePaySlip();
        e3.generatePaySlip();

        // Department info and expenses
        Department[] departments = {d1, d2};
        getDepartmentWiseExpenses(departments);

        // Attendance report
        Employee[] allEmployees = {e1, e2, e3};
        getAttendanceReport(allEmployees);

        // Company payroll
        System.out.println("Company Payroll Expense: " + calculateCompanyPayroll(allEmployees));
        System.out.println("Total Employees: " + Employee.totalEmployees);
        System.out.println("Company Name: " + Employee.companyName);
    }
}