import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/* ----------------- Employee Class ----------------- */
abstract class Employee {
    String empId;
    String empName;
    String department;
    String designation;
    double baseSalary;
    LocalDate joinDate;
    boolean[] attendanceRecord;
    int leavesTaken;
    double totalSalary;

    // Static variables
    static int totalEmployees = 0;
    static String companyName = "TechCorp Solutions";
    static double totalSalaryExpense = 0.0;
    static int workingDaysPerMonth = 30;

    public Employee(String empId, String empName, String department, String designation, double baseSalary, String joinDate) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.designation = designation;
        this.baseSalary = baseSalary;
        this.joinDate = LocalDate.parse(joinDate);
        this.attendanceRecord = new boolean[workingDaysPerMonth];
        this.leavesTaken = 0;
        this.totalSalary = 0.0;
        totalEmployees++;
    }

    // Mark attendance
    public void markAttendance(int day, boolean present) {
        if (day < 1 || day > workingDaysPerMonth) {
            System.out.println("Invalid day. Must be between 1 and " + workingDaysPerMonth);
            return;
        }
        attendanceRecord[day - 1] = present;
    }

    // Request leave
    public void requestLeave(int day) {
        if (day < 1 || day > workingDaysPerMonth) {
            System.out.println("Invalid leave day.");
            return;
        }
        attendanceRecord[day - 1] = false;
        leavesTaken++;
        System.out.println(empName + " requested leave on day " + day);
    }

    // Calculate salary (to be overridden)
    public abstract double calculateSalary();

    // Performance-based bonus
    public double calculateBonus() {
        int presentDays = 0;
        for (boolean present : attendanceRecord) {
            if (present) presentDays++;
        }
        double attendanceRate = (double) presentDays / workingDaysPerMonth;
        if (attendanceRate >= 0.9) {
            return 0.1 * baseSalary; // 10% bonus
        } else if (attendanceRate >= 0.75) {
            return 0.05 * baseSalary; // 5% bonus
        }
        return 0.0;
    }

    // Generate pay slip
    public void generatePaySlip() {
        double salary = calculateSalary();
        double bonus = calculateBonus();
        totalSalary = salary + bonus;
        totalSalaryExpense += totalSalary;

        System.out.println("\nPay Slip for " + empName);
        System.out.println("   Employee ID: " + empId);
        System.out.println("   Department: " + department);
        System.out.println("   Designation: " + designation);
        System.out.println("   Base Salary: ₹" + baseSalary);
        System.out.println("   Salary this month: ₹" + salary);
        System.out.println("   Performance Bonus: ₹" + bonus);
        System.out.println("   Total Pay: ₹" + totalSalary);
    }
}

/* ----------------- Employee Types ----------------- */
class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String empId, String empName, String department, String designation, double baseSalary, String joinDate) {
        super(empId, empName, department, designation, baseSalary, joinDate);
    }

    @Override
    public double calculateSalary() {
        int presentDays = 0;
        for (boolean present : attendanceRecord) {
            if (present) presentDays++;
        }
        return (baseSalary / workingDaysPerMonth) * presentDays;
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String empId, String empName, String department, String designation, double baseSalary, String joinDate) {
        super(empId, empName, department, designation, baseSalary, joinDate);
    }

    @Override
    public double calculateSalary() {
        int presentDays = 0;
        for (boolean present : attendanceRecord) {
            if (present) presentDays++;
        }
        // Part-time paid half base salary rate
        return ((baseSalary / 2) / workingDaysPerMonth) * presentDays;
    }
}

class ContractEmployee extends Employee {
    public ContractEmployee(String empId, String empName, String department, String designation, double baseSalary, String joinDate) {
        super(empId, empName, department, designation, baseSalary, joinDate);
    }

    @Override
    public double calculateSalary() {
        int presentDays = 0;
        for (boolean present : attendanceRecord) {
            if (present) presentDays++;
        }
        // Contract employees get daily wage directly
        return (baseSalary / workingDaysPerMonth) * presentDays;
    }
}

/* ----------------- Department Class ----------------- */
class Department {
    String deptId;
    String deptName;
    Employee manager;
    List<Employee> employees;
    double budget;

    public Department(String deptId, String deptName, Employee manager, double budget) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.manager = manager;
        this.employees = new ArrayList<>();
        this.budget = budget;
    }

    public void addEmployee(Employee emp) {
        employees.add(emp);
    }

    public double getDepartmentExpense() {
        double sum = 0;
        for (Employee e : employees) {
            sum += e.totalSalary;
        }
        return sum;
    }
}

/* ----------------- HR Management System ----------------- */
public class EmployeePayrollSystem {
    public static void calculateCompanyPayroll(List<Employee> employees) {
        System.out.println("\nCompany Payroll Report (" + Employee.companyName + ")");
        for (Employee e : employees) {
            e.generatePaySlip();
        }
        System.out.println("\nTotal Employees: " + Employee.totalEmployees);
        System.out.println("Total Salary Expense: ₹" + Employee.totalSalaryExpense);
    }

    public static void getDepartmentWiseExpenses(List<Department> departments) {
        System.out.println("\nDepartment-wise Expenses:");
        for (Department d : departments) {
            System.out.println("   " + d.deptName + ": ₹" + d.getDepartmentExpense());
        }
    }

    public static void getAttendanceReport(List<Employee> employees) {
        System.out.println("\nAttendance Report:");
        for (Employee e : employees) {
            int presentDays = 0;
            for (boolean present : e.attendanceRecord) {
                if (present) presentDays++;
            }
            System.out.println("   " + e.empName + " (" + e.empId + "): " + presentDays + "/" + Employee.workingDaysPerMonth + " days present");
        }
    }

    public static void main(String[] args) {
        // Create Employees
        Employee e1 = new FullTimeEmployee("E1", "Alice", "IT", "Developer", 60000, "2023-01-01");
        Employee e2 = new PartTimeEmployee("E2", "Bob", "IT", "Support", 30000, "2024-05-01");
        Employee e3 = new ContractEmployee("E3", "Charlie", "HR", "Consultant", 40000, "2022-09-01");

        List<Employee> employees = Arrays.asList(e1, e2, e3);

        // Departments
        Department d1 = new Department("D1", "IT", e1, 500000);
        d1.addEmployee(e1);
        d1.addEmployee(e2);

        Department d2 = new Department("D2", "HR", e3, 200000);
        d2.addEmployee(e3);

        List<Department> departments = Arrays.asList(d1, d2);

        // Mark attendance
        for (int i = 1; i <= 25; i++) {
            e1.markAttendance(i, true);
            e2.markAttendance(i, i % 2 == 0); // part-time attends alternate days
            e3.markAttendance(i, i % 3 != 0); // contract attends 2 of 3 days
        }
        e1.requestLeave(26);

        // Payroll processing
        calculateCompanyPayroll(employees);

        // Department expenses
        getDepartmentWiseExpenses(departments);

        // Attendance report
        getAttendanceReport(employees);
    }
}
