package week5.hospital;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class MedicalRecord {
    private final String recordId;
    private final String patientDNA;
    private final Set<String> allergies; // Using Set for unique allergies
    private final String[] medicalHistory; // Array for ordered history
    private final LocalDate birthDate;
    private final String bloodType;

    public MedicalRecord(String recordId, String patientDNA, String[] allergies, String[] medicalHistory, LocalDate birthDate, String bloodType) {
        if (recordId == null || recordId.trim().isEmpty()) {
            throw new IllegalArgumentException("Record ID cannot be null or empty.");
        }
        if (patientDNA == null || patientDNA.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient DNA cannot be null or empty.");
        }
        if (birthDate == null) {
            throw new IllegalArgumentException("Birth date cannot be null.");
        }
        if (bloodType == null || bloodType.trim().isEmpty()) {
            throw new IllegalArgumentException("Blood type cannot be null or empty.");
        }
        // HIPAA compliance validation (simplified for this example)
        if (!isValidHIPAA(patientDNA)) {
            throw new IllegalArgumentException("HIPAA compliance validation failed for patient DNA.");
        }

        this.recordId = recordId;
        this.patientDNA = patientDNA;
        this.allergies = (allergies != null) ? Collections.unmodifiableSet(new HashSet<>(Arrays.asList(allergies))) : Collections.emptySet();
        this.medicalHistory = (medicalHistory != null) ? Arrays.copyOf(medicalHistory, medicalHistory.length) : new String[0];
        this.birthDate = birthDate;
        this.bloodType = bloodType;
    }

    private boolean isValidHIPAA(String dna) {
        // Simplified HIPAA validation: just checks for non-empty DNA.
        // In a real system, this would involve complex data masking, encryption, and access control.
        return dna != null && !dna.trim().isEmpty();
    }

    public String getRecordId() {
        return recordId;
    }

    public String getPatientDNA() {
        return patientDNA;
    }

    public Set<String> getAllergies() {
        return allergies; // Already unmodifiable set
    }

    public String[] getMedicalHistory() {
        return Arrays.copyOf(medicalHistory, medicalHistory.length); // Defensive copy
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getBloodType() {
        return bloodType;
    }

    public final boolean isAllergicTo(String substance) {
        return allergies.contains(substance);
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
               "recordId='" + recordId + '\'' +
               ", patientDNA='" + patientDNA.substring(0, Math.min(patientDNA.length(), 5)) + "...(masked)" + '\'' + // Masking for audit trail
               ", allergies=" + allergies +
               ", medicalHistory=" + Arrays.toString(medicalHistory) +
               ", birthDate=" + birthDate +
               ", bloodType='" + bloodType + '\'' +
               '}';
    }
}
