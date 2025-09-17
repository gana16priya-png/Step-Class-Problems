package week5.hospital;

import java.util.UUID;

public class Patient {
    private final String patientId;
    private final MedicalRecord medicalRecord; // Protected health information

    private String currentName;
    private String emergencyContact;
    private String insuranceInfo; // Modifiable personal data

    private int roomNumber;
    private String attendingPhysician; // Current treatment info

    // Emergency admission constructor (minimal data, generates temporary ID)
    public Patient(String currentName, String emergencyContact, MedicalRecord medicalRecord) {
        this.patientId = "TEMP-" + UUID.randomUUID().toString(); // Generate temporary ID
        this.currentName = currentName;
        this.emergencyContact = emergencyContact;
        this.medicalRecord = medicalRecord;
        this.insuranceInfo = "N/A";
        this.roomNumber = -1; // Unassigned
        this.attendingPhysician = "On-Call";
        validatePrivacyPermissions();
    }

    // Standard admission constructor (full patient information)
    public Patient(String patientId, String currentName, String emergencyContact, String insuranceInfo, int roomNumber, String attendingPhysician, MedicalRecord medicalRecord) {
        if (patientId == null || patientId.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient ID cannot be null or empty.");
        }
        this.patientId = patientId;
        this.currentName = currentName;
        this.emergencyContact = emergencyContact;
        this.insuranceInfo = insuranceInfo;
        this.roomNumber = roomNumber;
        this.attendingPhysician = attendingPhysician;
        this.medicalRecord = medicalRecord;
        validatePrivacyPermissions();
    }

    // Transfer admission constructor (imports existing medical record)
    public Patient(String patientId, String currentName, String emergencyContact, String insuranceInfo, int roomNumber, String attendingPhysician, MedicalRecord medicalRecord, boolean isTransfer) {
        this(patientId, currentName, emergencyContact, insuranceInfo, roomNumber, attendingPhysician, medicalRecord);
        if (!isTransfer) {
            throw new IllegalArgumentException("This constructor is for transfer admissions only.");
        }
        // Additional transfer-specific validation could go here
    }

    private void validatePrivacyPermissions() {
        // Simplified privacy validation: ensures medical record is not null
        if (this.medicalRecord == null) {
            throw new IllegalArgumentException("Medical record is required for patient admission.");
        }
        // In a real system, this would involve checking staff roles, consent forms, etc.
    }

    // Package-private method for hospital staff access
    String getBasicInfo() {
        return "Patient ID: " + patientId + ", Name: " + currentName + ", Room: " + roomNumber + ", Attending Physician: " + attendingPhysician;
    }

    // Public method for non-sensitive data
    public String getPublicInfo() {
        return "Name: " + currentName + ", Room Number: " + roomNumber;
    }

    // Getters for final fields
    public String getPatientId() {
        return patientId;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    // Getters and setters for modifiable fields
    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        if (currentName == null || currentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Current name cannot be null or empty.");
        }
        this.currentName = currentName;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        if (emergencyContact == null || emergencyContact.trim().isEmpty()) {
            throw new IllegalArgumentException("Emergency contact cannot be null or empty.");
        }
        this.emergencyContact = emergencyContact;
    }

    public String getInsuranceInfo() {
        return insuranceInfo;
    }

    public void setInsuranceInfo(String insuranceInfo) {
        this.insuranceInfo = insuranceInfo;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        if (roomNumber < 0) {
            throw new IllegalArgumentException("Room number cannot be negative.");
        }
        this.roomNumber = roomNumber;
    }

    public String getAttendingPhysician() {
        return attendingPhysician;
    }

    public void setAttendingPhysician(String attendingPhysician) {
        if (attendingPhysician == null || attendingPhysician.trim().isEmpty()) {
            throw new IllegalArgumentException("Attending physician cannot be null or empty.");
        }
        this.attendingPhysician = attendingPhysician;
    }

    @Override
    public String toString() {
        return "Patient{" +
               "patientId='" + patientId + '\'' +
               ", currentName='" + currentName + '\'' +
               ", roomNumber=" + roomNumber +
               ", attendingPhysician='" + attendingPhysician + '\'' +
               ", medicalRecord=" + medicalRecord.toString() + // Audit trail for medical record
               '}';
    }
}
