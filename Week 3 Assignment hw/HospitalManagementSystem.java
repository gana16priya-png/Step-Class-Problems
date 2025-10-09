// Patient Class
class Patient {
    String patientId, patientName, gender, contactInfo;
    int age;
    String[] medicalHistory;
    String[] currentTreatments;

    Patient(String id, String name, int age, String gender, String contact, 
            String[] history, String[] treatments) {
        this.patientId = id;
        this.patientName = name;
        this.age = age;
        this.gender = gender;
        this.contactInfo = contact;
        this.medicalHistory = history;
        this.currentTreatments = treatments;
    }

    public void updateTreatment(String newTreatment) {
        System.out.println(patientName + " added treatment: " + newTreatment);
    }

    public void dischargePatient() {
        System.out.println("Patient " + patientName + " discharged.");
    }
}

// Doctor Class
class Doctor {
    String doctorId, doctorName, specialization;
    String[] availableSlots;
    int patientsHandled;
    double consultationFee;

    Doctor(String id, String name, String specialization, String[] slots, double fee) {
        this.doctorId = id;
        this.doctorName = name;
        this.specialization = specialization;
        this.availableSlots = slots;
        this.consultationFee = fee;
        this.patientsHandled = 0;
    }

    public void addPatient(Patient p) {
        patientsHandled++;
        System.out.println("Doctor " + doctorName + " is now treating patient " + p.patientName);
    }

    public void removeSlot(String time) {
        for (int i = 0; i < availableSlots.length; i++) {
            if (availableSlots[i].equals(time)) {
                availableSlots[i] = "BOOKED";
                break;
            }
        }
    }
}

// Appointment Base Class
abstract class Appointment {
    String appointmentId, appointmentDate, appointmentTime, status;
    Patient patient;
    Doctor doctor;

    static int totalPatients = 0;
    static int totalAppointments = 0;
    static String hospitalName = "CityCare Hospital";
    static double totalRevenue = 0;

    Appointment(String id, Patient p, Doctor d, String date, String time) {
        this.appointmentId = id;
        this.patient = p;
        this.doctor = d;
        this.appointmentDate = date;
        this.appointmentTime = time;
        this.status = "Scheduled";
        totalAppointments++;
        totalPatients++;
    }

    public void cancelAppointment() {
        this.status = "Cancelled";
        System.out.println("Appointment " + appointmentId + " cancelled.");
    }

    public abstract double generateBill();

    // Static utility methods
    public static void generateHospitalReport() {
        System.out.println("==== " + hospitalName + " Report ====");
        System.out.println("Total Patients: " + totalPatients);
        System.out.println("Total Appointments: " + totalAppointments);
        System.out.println("Total Revenue: " + totalRevenue);
    }

    public static void getDoctorUtilization(Doctor d) {
        System.out.println("Doctor " + d.doctorName + " handled " + d.patientsHandled + " patients.");
    }

    public static void getPatientStatistics(Patient[] patients) {
        int male = 0, female = 0;
        for (Patient p : patients) {
            if (p.gender.equalsIgnoreCase("Male")) male++;
            else if (p.gender.equalsIgnoreCase("Female")) female++;
        }
        System.out.println("Patient Statistics -> Male: " + male + ", Female: " + female);
    }
}

// Appointment Types
class Consultation extends Appointment {
    Consultation(String id, Patient p, Doctor d, String date, String time) {
        super(id, p, d, date, time);
    }
    @Override
    public double generateBill() {
        double bill = doctor.consultationFee;
        totalRevenue += bill;
        return bill;
    }
}

class FollowUp extends Appointment {
    FollowUp(String id, Patient p, Doctor d, String date, String time) {
        super(id, p, d, date, time);
    }
    @Override
    public double generateBill() {
        double bill = doctor.consultationFee * 0.5; // Half fee
        totalRevenue += bill;
        return bill;
    }
}

class Emergency extends Appointment {
    Emergency(String id, Patient p, Doctor d, String date, String time) {
        super(id, p, d, date, time);
    }
    @Override
    public double generateBill() {
        double bill = doctor.consultationFee * 2; // Double fee
        totalRevenue += bill;
        return bill;
    }
}

// Main Simulation
public class HospitalManagementSystem {
    public static void main(String[] args) {
        Patient p1 = new Patient("P101", "Ravi Kumar", 35, "Male", "9876543210",
                new String[]{"Diabetes"}, new String[]{"Insulin"});
        Doctor d1 = new Doctor("D201", "Dr. Sharma", "Cardiology",
                new String[]{"10AM", "11AM", "12PM"}, 1000);

        Appointment a1 = new Consultation("A301", p1, d1, "2025-09-02", "10AM");
        d1.addPatient(p1);
        d1.removeSlot("10AM");

        System.out.println("Bill Generated: " + a1.generateBill());
        Appointment.generateHospitalReport();
        Appointment.getDoctorUtilization(d1);
        Appointment.getPatientStatistics(new Patient[]{p1});

        p1.updateTreatment("Blood Pressure Monitoring");
        p1.dischargePatient();
    }
}
