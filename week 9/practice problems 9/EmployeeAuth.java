import java.util.HashSet;

class Employee {
    private String empCode;
    private String name;

    public Employee(String empCode, String name) {
        this.empCode = empCode;
        this.name = name;
    }

    // Override equals() — same empCode means same employee
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // same object in memory
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee other = (Employee) obj;
        return empCode.equals(other.empCode);
    }

    // Override hashCode() — must match equals() logic
    @Override
    public int hashCode() {
        return empCode.hashCode();
    }

    // toString() — show both fields
    @Override
    public String toString() {
        return "Employee Code: " + empCode + ", Name: " + name;
    }
}

public class EmployeeAuth {
    public static void main(String[] args) {
        // 1. Create employees
        Employee e1 = new Employee("BL001", "Ritika");
        Employee e2 = new Employee("BL001", "Ritika S.");

        // 2. Compare using '==' and equals()
        System.out.println("Using == : " + (e1 == e2));
        System.out.println("Using equals(): " + e1.equals(e2));

        // 3. Print both
        System.out.println(e1);
        System.out.println(e2);

        // 4. Test in HashSet
        HashSet<Employee> set = new HashSet<>();
        set.add(e1);
        set.add(e2);

        System.out.println("HashSet contents:");
        for (Employee e : set)
            System.out.println(e);
    }
}
