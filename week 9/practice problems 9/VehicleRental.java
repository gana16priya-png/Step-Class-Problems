class Vehicle {
    private String registrationNo;
    private String type;
    private double ratePerDay;

    // Constructor initializing all fields
    public Vehicle(String registrationNo, String type, double ratePerDay) {
        this.registrationNo = registrationNo;
        this.type = type;
        this.ratePerDay = ratePerDay;
    }

    // Getters
    public String getRegistrationNo() {
        return registrationNo;
    }

    public String getType() {
        return type;
    }

    public double getRatePerDay() {
        return ratePerDay;
    }

    // Override toString()
    @Override
    public String toString() {
        return "Vehicle: " + registrationNo + ", Type: " + type + ", Rate: $" + ratePerDay + "/day";
    }
}

public class VehicleRental {
    public static void main(String[] args) {
        // 1. Create Vehicle object
        Vehicle v1 = new Vehicle("MH12AB1234", "Sedan", 1500);

        // 2. Print the Vehicle object
        System.out.println(v1);

        // 3. Create another vehicle
        Vehicle v2 = new Vehicle("MH14XY5678", "SUV", 2000);

        // Print and compare
        System.out.println(v2);

        // Compare registration numbers (for example)
        if (v1.getRegistrationNo().equals(v2.getRegistrationNo())) {
            System.out.println("Both vehicles have the same registration number.");
        } else {
            System.out.println("Different vehicles.");
        }
    }
}
