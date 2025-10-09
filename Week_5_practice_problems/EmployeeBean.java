import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class EmployeeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    // ================== Private Fields ==================
    private String employeeId;
    private String firstName;
    private String lastName;
    private double salary;
    private String department;
    private LocalDate hireDate; // modern LocalDate
    private boolean isActive;

    // ================== Constructors ==================
    public EmployeeBean() {
        // No-argument constructor (required by JavaBean standard)
    }

    public EmployeeBean(String employeeId, String firstName, String lastName,
                        double salary, String department, LocalDate hireDate, boolean isActive) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        setSalary(salary); // validation
        this.department = department;
        this.hireDate = hireDate;
        this.isActive = isActive;
    }

    // ================== Standard Getters ==================
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

    public LocalDate getHireDate() {
        return hireDate;
    }

    public boolean isActive() {
        return isActive;
    }

    // ================== Standard Setters ==================
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary must be positive");
        }
        this.salary = salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // ================== Computed Properties ==================
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getYearsOfService() {
        if (hireDate == null) return 0;
        return Period.between(hireDate, LocalDate.now()).getYears();
    }

    public String getFormattedSalary() {
        return NumberFormat.getCurrencyInstance().format(salary);
    }

    // ================== Derived Setters ==================
    public void setFullName(String fullName) {
        if (fullName != null && fullName.contains(" ")) {
            String[] parts = fullName.split(" ", 2);
            this.firstName = parts[0];
            this.lastName = parts[1];
        }
    }

    // ================== Overrides ==================
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

    // ================== Demo Main ==================
    public static void main(String[] args) {
        // Create EmployeeBean using default constructor + setters
        EmployeeBean emp1 = new EmployeeBean();
        emp1.setEmployeeId("E001");
        emp1.setFirstName("Alice");
        emp1.setLastName("Johnson");
        emp1.setSalary(50000);
        emp1.setDepartment("HR");
        emp1.setHireDate(LocalDate.of(2020, 1, 1)); // modern LocalDate
        emp1.setActive(true);

        // Create EmployeeBean using parameterized constructor
        EmployeeBean emp2 = new EmployeeBean("E002", "Bob", "Smith", 60000,
                "Finance", LocalDate.of(2018, 6, 15), true);

        // Demonstrate getters
        System.out.println(emp1.getEmployeeId() + " - " + emp1.getFullName());
        System.out.println(emp2.getEmployeeId() + " - " + emp2.getFullName());

        // Computed properties
        System.out.println(emp1.getFullName() + " has worked for " + emp1.getYearsOfService() + " years");
        System.out.println(emp2.getFullName() + " salary: " + emp2.getFormattedSalary());

        // Validation test
        try {
            emp1.setSalary(-1000);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation works: " + e.getMessage());
        }

        // toString
        System.out.println(emp1);
        System.out.println(emp2);

        // Reflection utilities
        JavaBeanProcessor.printAllProperties(emp1);

        EmployeeBean emp3 = new EmployeeBean();
        JavaBeanProcessor.copyProperties(emp2, emp3);
        System.out.println("Copied Employee: " + emp3);
    }
}

// ================== Utility Class ==================
class JavaBeanProcessor {
    public static void printAllProperties(EmployeeBean emp) {
        try {
            Method[] methods = EmployeeBean.class.getMethods();
            for (Method method : methods) {
                if ((method.getName().startsWith("get") || method.getName().startsWith("is"))
                        && method.getParameterCount() == 0) {
                    Object value = method.invoke(emp);
                    System.out.println(method.getName() + " = " + value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyProperties(EmployeeBean source, EmployeeBean target) {
        try {
            Method[] methods = EmployeeBean.class.getMethods();
            for (Method method : methods) {
                if (method.getName().startsWith("get") && method.getParameterCount() == 0) {
                    String property = method.getName().substring(3);
                    try {
                        Method setter = EmployeeBean.class.getMethod("set" + property, method.getReturnType());
                        Object value = method.invoke(source);
                        setter.invoke(target, value);
                    } catch (NoSuchMethodException ignored) {
                        // skip if setter not found (e.g. for computed properties)
                    }
                } else if (method.getName().startsWith("is") && method.getParameterCount() == 0) {
                    String property = method.getName().substring(2);
                    try {
                        Method setter = EmployeeBean.class.getMethod("set" + property, method.getReturnType());
                        Object value = method.invoke(source);
                        setter.invoke(target, value);
                    } catch (NoSuchMethodException ignored) {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
