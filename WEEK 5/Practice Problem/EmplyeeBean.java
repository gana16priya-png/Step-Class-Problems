import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class EmployeeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    // ==========================
    // Private fields (JavaBean standard)
    // ==========================
    private String employeeId;
    private String firstName;
    private String lastName;
    private double salary;
    private String department;
    private Date hireDate;
    private boolean isActive;

    // ==========================
    // Constructors
    // ==========================
    public EmployeeBean() {
        // No-arg constructor (JavaBean requirement)
    }

    public EmployeeBean(String employeeId, String firstName, String lastName,
                        double salary, String department, Date hireDate, boolean isActive) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        setSalary(salary); // validation inside setter
        this.department = department;
        this.hireDate = hireDate;
        this.isActive = isActive;
    }

    // ==========================
    // Standard Getters
    // ==========================
    public String getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public boolean isActive() {
        return isActive;
    }

    // ==========================
    // Standard Setters
    // ==========================
    public void setEmployeeId(String employeeId) {
        if (employeeId == null || employeeId.isBlank()) {
            throw new IllegalArgumentException("Employee ID cannot be null or blank.");
        }
        this.employeeId = employeeId;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be empty.");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be empty.");
        }
        this.lastName = lastName;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary must be positive.");
        }
        this.salary = salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // ==========================
    // Computed Properties
    // ==========================
    public String getFullName() {
        return (firstName != null ? firstName : "") + " " +
               (lastName != null ? lastName : "");
    }

    public int getYearsOfService() {
        if (hireDate == null) return 0;
        long millis = new Date().getTime() - hireDate.getTime();
        return (int) (millis / (1000L * 60 * 60 * 24 * 365));
    }

    public String getFormattedSalary() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        return nf.format(salary);
    }

    // ==========================
    // Derived Properties with Validation
    // ==========================
    public void setFullName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Full name cannot be empty.");
        }
        String[] parts = fullName.trim().split(" ", 2);
        this.firstName = parts[0];
        this.lastName = (parts.length > 1) ? parts[1] : "";
    }

    // ==========================
    // Object Methods
    // ==========================
    @Override
    public String toString() {
        return "EmployeeBean{" +
                "employeeId='" + employeeId + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", salary=" + getFormattedSalary() +
                ", department='" + department + '\'' +
                ", hireDate=" + hireDate +
                ", yearsOfService=" + getYearsOfService() +
                ", active=" + isActive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeBean)) return false;
        EmployeeBean that = (EmployeeBean) o;
        return Objects.equals(employeeId, that.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

    // ==========================
    // Main Demo
    // ==========================
    public static void main(String[] args) {
        // Create EmployeeBean using default constructor + setters
        EmployeeBean emp1 = new EmployeeBean();
        emp1.setEmployeeId("E001");
        emp1.setFirstName("Alice");
        emp1.setLastName("Smith");
        emp1.setSalary(60000);
        emp1.setDepartment("IT");
        emp1.setHireDate(new Date(120, 0, 1)); // Jan 1, 2020
        emp1.setActive(true);

        // Create EmployeeBean using parameterized constructor
        EmployeeBean emp2 = new EmployeeBean("E002", "Bob", "Johnson",
                80000, "Finance", new Date(118, 6, 1), true);

        // Demonstrate getters
        System.out.println("Emp1 Full Name: " + emp1.getFullName());
        System.out.println("Emp2 Salary: " + emp2.getFormattedSalary());

        // Test validation
        try {
            emp1.setSalary(-5000);
        } catch (Exception e) {
            System.out.println("Validation caught: " + e.getMessage());
        }

        // Test computed properties
        System.out.println("Emp1 Years of Service: " + emp1.getYearsOfService());

        // Show JavaBean in collections
        EmployeeBean[] employees = {emp1, emp2};
        Arrays.sort(employees, Comparator.comparingDouble(EmployeeBean::getSalary));

        System.out.println("\nSorted Employees by Salary:");
        for (EmployeeBean e : employees) {
            System.out.println(e);
        }

        // Filter active employees
        System.out.println("\nActive Employees:");
        Arrays.stream(employees)
                .filter(EmployeeBean::isActive)
                .forEach(System.out::println);

        // Use reflection utilities
        System.out.println("\n--- JavaBeanProcessor Demo ---");
        JavaBeanProcessor.printAllProperties(emp1);

        EmployeeBean emp3 = new EmployeeBean();
        JavaBeanProcessor.copyProperties(emp2, emp3);
        System.out.println("Copied Employee: " + emp3);
    }
}
