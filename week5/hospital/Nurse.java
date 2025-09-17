package week5.hospital;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Nurse {
    private final String nurseId;
    private String shift;
    private final List<String> qualifications;

    public Nurse(String nurseId, String shift, List<String> qualifications) {
        if (nurseId == null || nurseId.trim().isEmpty()) {
            throw new IllegalArgumentException("Nurse ID cannot be null or empty.");
        }
        if (shift == null || shift.trim().isEmpty()) {
            throw new IllegalArgumentException("Shift cannot be null or empty.");
        }
        this.nurseId = nurseId;
        this.shift = shift;
        this.qualifications = (qualifications != null) ? Collections.unmodifiableList(new ArrayList<>(qualifications)) : Collections.emptyList();
    }

    public String getNurseId() {
        return nurseId;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        if (shift == null || shift.trim().isEmpty()) {
            throw new IllegalArgumentException("Shift cannot be null or empty.");
        }
        this.shift = shift;
    }

    public List<String> getQualifications() {
        return qualifications; // Already unmodifiable
    }

    // Example of a method that might interact with patient data
    public void administerMedication(Patient patient, String medication) {
        System.out.println("Nurse " + nurseId + " is administering " + medication + " to patient " + patient.getPatientId());
    }

    @Override
    public String toString() {
        return "Nurse{" +
               "nurseId='" + nurseId + '\'' +
               ", shift='" + shift + '\'' +
               ", qualifications=" + qualifications +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nurse nurse = (Nurse) o;
        return Objects.equals(nurseId, nurse.nurseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nurseId);
    }
}
