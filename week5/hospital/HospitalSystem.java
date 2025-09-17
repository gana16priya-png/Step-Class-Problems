package week5.hospital;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class HospitalSystem { // final class to maintain business rule integrity
    private final Map<String, Object> patientRegistry; // stores different patient types

    // Static final constants for hospital policies and privacy rules
    public static final String POLICY_HIPAA_COMPLIANCE = "Strict adherence to HIPAA regulations.";
    public static final String POLICY_STAFF_ACCESS_LEVELS = "Role-based access control for patient data.";
    public static final String RULE_EMERGENCY_ADMISSION_PROTOCOL = "Emergency admissions require minimal data and temporary ID generation.";

    public HospitalSystem() {
        this.patientRegistry = new HashMap<>();
    }

    public boolean admitPatient(Object patient, Object staff) {
        if (!(patient instanceof Patient)) {
            System.out.println("Admission failed: Invalid patient object.");
            return false;
        }
        if (!validateStaffAccess(staff, patient)) {
            System.out.println("Admission failed: Staff does not have appropriate access permissions.");
            return false;
        }

        Patient p = (Patient) patient;
        if (patientRegistry.containsKey(p.getPatientId())) {
            System.out.println("Admission failed: Patient with ID " + p.getPatientId() + " already exists.");
            return false;
        }

        patientRegistry.put(p.getPatientId(), p);
        System.out.println("Patient " + p.getCurrentName() + " admitted successfully with ID: " + p.getPatientId());
        return true;
    }

    private boolean validateStaffAccess(Object staff, Object patient) {
        // Simplified privacy protection based on staff role
        if (staff instanceof Doctor) {
            // Doctors can admit patients
            return true;
        } else if (staff instanceof Nurse) {
            // Nurses can admit patients
            return true;
        } else if (staff instanceof Administrator) {
            // Administrators can admit patients
            return true;
        }
        System.out.println("Staff validation failed: Unknown staff type or insufficient permissions.");
        return false;
    }

    // Package-private method for internal hospital operations
    Patient getPatientInternal(String patientId) {
        return (Patient) patientRegistry.get(patientId);
    }

    // Public method to get an unmodifiable view of the patient registry
    public Map<String, Object> getPatientRegistry() {
        return Collections.unmodifiableMap(patientRegistry);
    }

    @Override
    public String toString() {
        return "HospitalSystem{" +
               "patientRegistrySize=" + patientRegistry.size() +
               ", policies=[" + POLICY_HIPAA_COMPLIANCE + ", " + POLICY_STAFF_ACCESS_LEVELS + "]" +
               '}';
    }
}
