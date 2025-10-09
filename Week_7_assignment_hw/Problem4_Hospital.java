// File: HospitalSystem.java

// Base class
class MedicalStaff {
    protected String name;
    protected int staffId;

    public MedicalStaff(String name, int staffId) {
        this.name = name;
        this.staffId = staffId;
    }

    public void scheduleShift(String shift) {
        System.out.println(name + " scheduled for " + shift + " shift");
    }

    public void accessIDCard() {
        System.out.println(name + " (ID: " + staffId + ") accessed hospital premises");
    }

    public void processPayroll() {
        System.out.println("Payroll processed for " + name);
    }
}

// Doctor
class Doctor extends MedicalStaff {
    public Doctor(String name, int staffId) {
        super(name, staffId);
    }

    public void diagnose() {
        System.out.println(name + " diagnosed a patient");
    }

    public void prescribeMedicine() {
        System.out.println(name + " prescribed medicine");
    }

    public void performSurgery() {
        System.out.println(name + " performed surgery");
    }
}

// Nurse
class Nurse extends MedicalStaff {
    public Nurse(String name, int staffId) {
        super(name, staffId);
    }

    public void administerMedicine() {
        System.out.println(name + " administered medicine");
    }

    public void monitorPatient() {
        System.out.println(name + " monitored patient vitals");
    }

    public void assistProcedure() {
        System.out.println(name + " assisted in medical procedure");
    }
}

// Technician
class Technician extends MedicalStaff {
    public Technician(String name, int staffId) {
        super(name, staffId);
    }

    public void operateEquipment() {
        System.out.println(name + " operated medical equipment");
    }

    public void runTests() {
        System.out.println(name + " ran lab tests");
    }

    public void maintainInstruments() {
        System.out.println(name + " maintained instruments");
    }
}

// Administrator
class Administrator extends MedicalStaff {
    public Administrator(String name, int staffId) {
        super(name, staffId);
    }

    public void scheduleAppointment() {
        System.out.println(name + " scheduled an appointment");
    }

    public void manageRecords() {
        System.out.println(name + " managed patient records");
    }
}

// Main Program
public class Problem4_Hospital
{
    public static void main(String[] args) {
        // Upcasting: all staff stored as MedicalStaff
        MedicalStaff[] staff = {
            new Doctor("Dr. Smith", 101),
            new Nurse("Nurse Alice", 102),
            new Technician("Tech Bob", 103),
            new Administrator("Admin Carol", 104)
        };

        // Common operations via base class reference
        for (MedicalStaff ms : staff) {
            ms.accessIDCard();
            ms.scheduleShift("Day");
            ms.processPayroll();
            System.out.println();
        }

        // Downcasting optional if we want to use specialized methods
        ((Doctor) staff[0]).performSurgery();
        ((Nurse) staff[1]).monitorPatient();
        ((Technician) staff[2]).runTests();
        ((Administrator) staff[3]).manageRecords();
    }
}
