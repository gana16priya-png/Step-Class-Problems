import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SmartDevice {
    // ==========================
    // Read-only properties
    // ==========================
    private final String deviceId;
    private final LocalDateTime manufacturingDate;
    private final String serialNumber;

    // ==========================
    // Write-only properties
    // ==========================
    private int hashedEncryptionKey;
    private int hashedAdminPassword;

    // ==========================
    // Read-write properties
    // ==========================
    private String deviceName;
    private boolean isEnabled;

    // ==========================
    // Internal State
    // ==========================
    private final LocalDateTime startupTime;

    // ==========================
    // Constructor
    // ==========================
    public SmartDevice(String deviceId, LocalDateTime manufacturingDate, String deviceName) {
        this.deviceId = deviceId;
        this.manufacturingDate = manufacturingDate;
        this.serialNumber = UUID.randomUUID().toString();
        this.deviceName = deviceName;
        this.isEnabled = true; // default enabled
        this.startupTime = LocalDateTime.now();
        this.hashedEncryptionKey = 0;
        this.hashedAdminPassword = 0;
    }

    // ==========================
    // Read-Only Property Methods
    // ==========================
    public String getDeviceId() {
        return deviceId;
    }

    public LocalDateTime getManufacturingDate() {
        return manufacturingDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public long getUptime() {
        Duration duration = Duration.between(startupTime, LocalDateTime.now());
        return duration.getSeconds(); // uptime in seconds
    }

    public int getDeviceAge() {
        Period period = Period.between(manufacturingDate.toLocalDate(), LocalDateTime.now().toLocalDate());
        return period.getYears();
    }

    // ==========================
    // Write-Only Property Methods
    // ==========================
    public void setEncryptionKey(String key) {
        if (key != null && key.length() >= 8) {
            this.hashedEncryptionKey = key.hashCode();
            System.out.println("✅ Encryption key set successfully.");
        } else {
            System.out.println("❌ Encryption key must be at least 8 characters.");
        }
    }

    public void setAdminPassword(String password) {
        if (password != null && password.length() >= 6) {
            this.hashedAdminPassword = password.hashCode();
            System.out.println("✅ Admin password set successfully.");
        } else {
            System.out.println("❌ Admin password must be at least 6 characters.");
        }
    }

    public boolean validateEncryptionKey(String key) {
        return key != null && key.hashCode() == this.hashedEncryptionKey;
    }

    public boolean validateAdminPassword(String password) {
        return password != null && password.hashCode() == this.hashedAdminPassword;
    }

    // ==========================
    // Read-Write Property Methods
    // ==========================
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }

    // ==========================
    // Utility Methods
    // ==========================
    public Map<String, String> getPropertyInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("deviceId", "Read-Only");
        info.put("manufacturingDate", "Read-Only");
        info.put("serialNumber", "Read-Only");
        info.put("uptime", "Computed Read-Only");
        info.put("deviceAge", "Computed Read-Only");
        info.put("encryptionKey", "Write-Only");
        info.put("adminPassword", "Write-Only");
        info.put("deviceName", "Read-Write");
        info.put("isEnabled", "Read-Write");
        return info;
    }

    public void resetDevice() {
        this.hashedEncryptionKey = 0;
        this.hashedAdminPassword = 0;
        this.isEnabled = false;
        System.out.println("🔄 Device reset. Security keys cleared, read-only properties preserved.");
    }

    // ==========================
    // Demo in main
    // ==========================
    public static void main(String[] args) {
        SmartDevice device1 = new SmartDevice("DEV001", LocalDateTime.of(2020, 5, 10, 10, 0), "SmartHub");

        //  Demonstrate read-only
        System.out.println("Device ID: " + device1.getDeviceId());
        System.out.println("Manufacturing Date: " + device1.getManufacturingDate());
        System.out.println("Serial Number: " + device1.getSerialNumber());
        System.out.println("Device Age: " + device1.getDeviceAge() + " years");
        System.out.println("Uptime: " + device1.getUptime() + " seconds");

        //  Attempting setters (should not exist for read-only)
        // device1.setDeviceId("NEWID"); // Compilation error

        // Demonstrate write-only
        device1.setEncryptionKey("SuperSecureKey123");
        device1.setAdminPassword("StrongPass!");

        System.out.println("Encryption validation (wrong): " + device1.validateEncryptionKey("wrongKey"));
        System.out.println("Encryption validation (correct): " + device1.validateEncryptionKey("SuperSecureKey123"));

        System.out.println("Admin password validation (wrong): " + device1.validateAdminPassword("badpass"));
        System.out.println("Admin password validation (correct): " + device1.validateAdminPassword("StrongPass!"));

        //  Demonstrate read-write
        System.out.println("Device Name: " + device1.getDeviceName());
        device1.setDeviceName("SmartHub V2");
        System.out.println("Updated Device Name: " + device1.getDeviceName());

        System.out.println("Enabled: " + device1.isEnabled());
        device1.setEnabled(false);
        System.out.println("Enabled after update: " + device1.isEnabled());

        //Multiple devices independence
        SmartDevice device2 = new SmartDevice("DEV002", LocalDateTime.of(2022, 1, 1, 12, 0), "SmartLight");
        System.out.println("\nDevice 2 Info:");
        System.out.println("Device ID: " + device2.getDeviceId());
        System.out.println("Device Name: " + device2.getDeviceName());

        // Property info
        System.out.println("\nProperty Access Info: " + device1.getPropertyInfo());

        //  Reset device
        device1.resetDevice();
        System.out.println("After reset, Enabled: " + device1.isEnabled());
    }
}
