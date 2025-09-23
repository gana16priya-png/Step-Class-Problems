import java.util.*;

class Patient {
    private String patientId;
    private String patientName;
    private int age;
    private String gender;
    private String contactInfo;
    private String[] medicalHistory;
    private String[] currentTreatments;
    private int historyCount = 0;
    private int treatmentCount = 0;

    private static int totalPatients = 0;

    public Patient(String patientId, String patientName, int age, String gender, String contactInfo) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.age = age;
        this.gender = gender;
        this.contactInfo = contactInfo;
        this.medicalHistory = new String[10];
        this.currentTreatments = new String[5];
        totalPatients++;
    }

    public String getPatientId() { return patientId; }
    public String getPatientName() { return patientName; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getContactInfo() { return contactInfo; }

    public void addMedicalHistory(String history) {
        if (historyCount < medicalHistory.length) medicalHistory[historyCount++] = history;
    }

    public void addTreatment(String treatment) {
        if (treatmentCount < currentTreatments.length) currentTreatments[treatmentCount++] = treatment;
    }

    public void updateTreatment(String treatment) {
        addTreatment(treatment);
        System.out.println("Treatment updated for " + patientName + ": " + treatment);
    }

    public void dischargePatient() {
        currentTreatments = new String[5];
        treatmentCount = 0;
        System.out.println(patientName + " has been discharged.");
    }

    public void displayPatientInfo() {
        System.out.println("Patient ID: " + patientId + ", Name: " + patientName + ", Age: " + age + ", Gender: " + gender + ", Contact: " + contactInfo);
        System.out.print("Medical History: ");
        for (int i = 0; i < historyCount; i++) System.out.print(medicalHistory[i] + " | ");
        System.out.print("\nCurrent Treatments: ");
        for (int i = 0; i < treatmentCount; i++) System.out.print(currentTreatments[i] + " | ");
        System.out.println();
    }

    public static int getTotalPatients() { return totalPatients; }
}

class Doctor {
    private String doctorId;
    private String doctorName;
    private String specialization;
    private String[] availableSlots;
    private int patientsHandled;
    private double consultationFee;

    public Doctor(String doctorId, String doctorName, String specialization, String[] availableSlots, double consultationFee) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.availableSlots = availableSlots;
        this.patientsHandled = 0;
        this.consultationFee = consultationFee;
    }

    public String getDoctorId() { return doctorId; }
    public String getDoctorName() { return doctorName; }
    public String getSpecialization() { return specialization; }
    public String[] getAvailableSlots() { return availableSlots; }
    public double getConsultationFee() { return consultationFee; }
    public int getPatientsHandled() { return patientsHandled; }

    public void incrementPatientsHandled() { patientsHandled++; }

    public void displayDoctorInfo() {
        System.out.println("Doctor ID: " + doctorId + ", Name: " + doctorName + ", Specialization: " + specialization + ", Fee: Rs." + consultationFee);
        System.out.print("Available Slots: ");
        for (String slot : availableSlots) System.out.print(slot + " | ");
        System.out.println("Patients Handled: " + patientsHandled);
    }
}

class Appointment {
    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private String appointmentDate;
    private String appointmentTime;
    private String status;
    private String type; // Consultation, Follow-up, Emergency

    private static int totalAppointments = 0;
    private static String hospitalName = "CityCare Hospital";
    private static double totalRevenue = 0;

    public Appointment(String appointmentId, Patient patient, Doctor doctor, String appointmentDate, String appointmentTime, String type) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = "Scheduled";
        this.type = type;
        totalAppointments++;
    }

    public String getAppointmentId() { return appointmentId; }
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public String getAppointmentDate() { return appointmentDate; }
    public String getAppointmentTime() { return appointmentTime; }
    public String getStatus() { return status; }
    public String getType() { return type; }

    public void scheduleAppointment() {
        status = "Scheduled";
        doctor.incrementPatientsHandled();
        System.out.println("Appointment scheduled: " + appointmentId + " for " + patient.getPatientName() + " with Dr. " + doctor.getDoctorName());
    }

    public void cancelAppointment() {
        status = "Cancelled";
        System.out.println("Appointment " + appointmentId + " cancelled.");
    }

    public double generateBill() {
        double bill = 0;
        switch (type) {
            case "Consultation": bill = doctor.getConsultationFee(); break;
            case "Follow-up": bill = doctor.getConsultationFee() * 0.5; break;
            case "Emergency": bill = doctor.getConsultationFee() * 2; break;
            default: bill = doctor.getConsultationFee();
        }
        totalRevenue += bill;
        System.out.println("Bill for appointment " + appointmentId + ": Rs." + bill);
        return bill;
    }

    public void displayAppointmentInfo() {
        System.out.println("Appointment ID: " + appointmentId + ", Patient: " + patient.getPatientName() + ", Doctor: " + doctor.getDoctorName() +
                ", Date: " + appointmentDate + ", Time: " + appointmentTime + ", Type: " + type + ", Status: " + status);
    }

    // Static methods
    public static void generateHospitalReport(Appointment[] appointments, Doctor[] doctors, Patient[] patients) {
        System.out.println("Hospital: " + hospitalName);
        System.out.println("Total Patients: " + Patient.getTotalPatients());
        System.out.println("Total Appointments: " + totalAppointments);
        System.out.println("Total Revenue: Rs." + totalRevenue);
        getDoctorUtilization(doctors);
        getPatientStatistics(patients);
        System.out.println("---------------------------");
    }

    public static void getDoctorUtilization(Doctor[] doctors) {
        System.out.println("Doctor Utilization:");
        for (Doctor d : doctors) {
            System.out.println(d.getDoctorName() + ": " + d.getPatientsHandled() + " patients handled.");
        }
    }

    public static void getPatientStatistics(Patient[] patients) {
        System.out.println("Patient Statistics:");
        for (Patient p : patients) {
            p.displayPatientInfo();
        }
    }
}

public class HospitalManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create doctors
        Doctor[] doctors = {
            new Doctor("D001", "Dr. Sharma", "Cardiology", new String[]{"10:00", "11:00", "12:00"}, 500),
            new Doctor("D002", "Dr. Gupta", "Orthopedics", new String[]{"14:00", "15:00"}, 400),
            new Doctor("D003", "Dr. Singh", "General", new String[]{"09:00", "13:00"}, 300)
        };

        // Create patients
        Patient[] patients = {
            new Patient("P001", "Alice", 30, "Female", "9876543210"),
            new Patient("P002", "Bob", 45, "Male", "9123456780"),
            new Patient("P003", "Charlie", 25, "Male", "9988776655")
        };

        // Appointment records
        Appointment[] appointments = new Appointment[10];
        int appIndex = 0;

        while (true) {
            System.out.println("\n--- Hospital Management Menu ---");
            System.out.println("1. Schedule Appointment");
            System.out.println("2. Cancel Appointment");
            System.out.println("3. Update Treatment");
            System.out.println("4. Discharge Patient");
            System.out.println("5. Generate Bill");
            System.out.println("6. View Doctor Info");
            System.out.println("7. View Patient Info");
            System.out.println("8. Hospital Report");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Patient ID: ");
                    String pid = sc.nextLine();
                    Patient patient = null;
                    for (Patient p : patients) if (p.getPatientId().equals(pid)) patient = p;
                    if (patient == null) { System.out.println("Patient not found."); break; }
                    System.out.print("Enter Doctor ID: ");
                    String did = sc.nextLine();
                    Doctor doctor = null;
                    for (Doctor d : doctors) if (d.getDoctorId().equals(did)) doctor = d;
                    if (doctor == null) { System.out.println("Doctor not found."); break; }
                    System.out.print("Enter Appointment Date (dd-mm-yyyy): ");
                    String date = sc.nextLine();
                    System.out.print("Enter Appointment Time: ");
                    String time = sc.nextLine();
                    System.out.print("Enter Appointment Type (Consultation/Follow-up/Emergency): ");
                    String type = sc.nextLine();
                    String appId = "A" + (appIndex + 1);
                    appointments[appIndex] = new Appointment(appId, patient, doctor, date, time, type);
                    appointments[appIndex].scheduleAppointment();
                    appIndex++;
                    break;
                case 2:
                    System.out.print("Enter Appointment ID to cancel: ");
                    String cancelId = sc.nextLine();
                    boolean found = false;
                    for (int i = 0; i < appIndex; i++) {
                        if (appointments[i] != null && appointments[i].getAppointmentId().equals(cancelId)) {
                            appointments[i].cancelAppointment();
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("Appointment not found.");
                    break;
                case 3:
                    System.out.print("Enter Patient ID: ");
                    pid = sc.nextLine();
                    patient = null;
                    for (Patient p : patients) if (p.getPatientId().equals(pid)) patient = p;
                    if (patient == null) { System.out.println("Patient not found."); break; }
                    System.out.print("Enter Treatment to add: ");
                    String treatment = sc.nextLine();
                    patient.updateTreatment(treatment);
                    break;
                case 4:
                    System.out.print("Enter Patient ID: ");
                    pid = sc.nextLine();
                    patient = null;
                    for (Patient p : patients) if (p.getPatientId().equals(pid)) patient = p;
                    if (patient == null) { System.out.println("Patient not found."); break; }
                    patient.dischargePatient();
                    break;
                case 5:
                    System.out.print("Enter Appointment ID: ");
                    String billId = sc.nextLine();
                    for (int i = 0; i < appIndex; i++) {
                        if (appointments[i] != null && appointments[i].getAppointmentId().equals(billId)) {
                            appointments[i].generateBill();
                        }
                    }
                    break;
                case 6:
                    System.out.print("Enter Doctor ID: ");
                    did = sc.nextLine();
                    for (Doctor d : doctors) {
                        if (d.getDoctorId().equals(did)) d.displayDoctorInfo();
                    }
                    break;
                case 7:
                    System.out.print("Enter Patient ID: ");
                    pid = sc.nextLine();
                    for (Patient p : patients) {
                        if (p.getPatientId().equals(pid)) p.displayPatientInfo();
                    }
                    break;
                case 8:
                    Appointment.generateHospitalReport(appointments, doctors, patients);
                    break;
                case 9:
                    System.out.println("Thank you for using the Hospital Management System!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}