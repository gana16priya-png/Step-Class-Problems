package week5.hospital;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Administrator {
    private final String adminId;
    private final List<String> accessPermissions;

    public Administrator(String adminId, List<String> accessPermissions) {
        if (adminId == null || adminId.trim().isEmpty()) {
            throw new IllegalArgumentException("Administrator ID cannot be null or empty.");
        }
        this.adminId = adminId;
        this.accessPermissions = (accessPermissions != null) ? Collections.unmodifiableList(new ArrayList<>(accessPermissions)) : Collections.emptyList();
    }

    public String getAdminId() {
        return adminId;
    }

    public List<String> getAccessPermissions() {
        return accessPermissions; // Already unmodifiable
    }

    // Example of a method for administrative tasks
    public void manageHospitalPolicy(String policyName, String newPolicyDetails) {
        System.out.println("Administrator " + adminId + " is updating policy '" + policyName + "' with details: " + newPolicyDetails);
    }

    @Override
    public String toString() {
        return "Administrator{" +
               "adminId='" + adminId + '\'' +
               ", accessPermissions=" + accessPermissions +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator that = (Administrator) o;
        return Objects.equals(adminId, that.adminId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId);
    }
}
