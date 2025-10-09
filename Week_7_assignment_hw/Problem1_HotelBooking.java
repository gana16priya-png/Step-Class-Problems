class HotelBooking {
    void calculateCost(String roomType, int nights) {
        double baseRate = 2000;
        double cost = baseRate * nights;
        System.out.println("[Standard Booking]");
        System.out.println("Room: " + roomType + ", Nights: " + nights + ", Total: ₹" + cost);
    }

    void calculateCost(String roomType, int nights, double seasonalMultiplier) {
        double baseRate = 2000;
        double cost = baseRate * nights * seasonalMultiplier;
        System.out.println("[Seasonal Booking]");
        System.out.println("Room: " + roomType + ", Nights: " + nights + ", Total: ₹" + cost);
    }

    void calculateCost(String roomType, int nights, double corporateDiscount, boolean mealPackage) {
        double baseRate = 2000;
        double cost = baseRate * nights;
        if (mealPackage) cost += 1000;
        cost = cost - (cost * corporateDiscount);
        System.out.println("[Corporate Booking]");
        System.out.println("Room: " + roomType + ", Nights: " + nights + ", Total: ₹" + cost);
    }

    void calculateCost(String roomType, int nights, int guestCount, double decorationFee, String cateringOptions) {
        double baseRate = 2000;
        double cost = baseRate * nights + decorationFee + (guestCount * 500);
        System.out.println("[Wedding Booking]");
        System.out.println("Room: " + roomType + ", Nights: " + nights + ", Total: ₹" + cost);
    }
}

public class Problem1_HotelBooking {
    public static void main(String[] args) {
        HotelBooking h = new HotelBooking();
        h.calculateCost("Deluxe", 3);
        h.calculateCost("Suite", 2, 1.5);
        h.calculateCost("Executive", 5, 0.2, true);
        h.calculateCost("Hall", 1, 200, 5000, "Buffet");
    }
}
