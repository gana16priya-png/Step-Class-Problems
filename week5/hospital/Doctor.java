package week5.hospital;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Doctor {
    private final String licenseNumber;
    private final String specialty;
    private final Set<String> certifications;

    public Doctor(String licenseNumber, String specialty, Set<String> certifications) {
        if (licenseNumber == null || licenseNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("License number cannot be null or empty.");
        }
        if (specialty == null || specialty.trim().isEmpty()) {
            throw new IllegalArgumentException("Specialty cannot be null or empty.");
        }
        this.licenseNumber = licenseNumber;
        this.specialty = specialty;
        this.certifications = (certifications != null) ? Collections.unmodifiableSet(new HashSet<>(certifications)) : Collections.emptySet();
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getSpecialty() {
        return specialty;
    }

    public Set<String> getCertifications() {
        return certifications; // Already unmodifiable
    }

    // Example of a method that might access patient data (with proper access control in HospitalSystem)
    public void examinePatient(Patient patient) {
        System.out.println("Doctor " + specialty + " (" + licenseNumber + ") is examining patient " + patient.getPatientId());
        // In a real system, this would involve more complex interactions and data access
    }

    @Override
    public String toString() {
        return "Doctor{" +
               "licenseNumber='" + licenseNumber + '\'' +
               ", specialty='" + specialty + '\'' +
               ", certifications=" + certifications +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(licenseNumber, doctor.licenseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licenseNumber);
    }
}
