class DeliveryService {

    // Basic delivery: cost depends only on distance
    public void calculateCharges(double distance) {
        double cost = distance * 5; // 5 per km
        System.out.println("Basic Delivery: Distance = " + distance +
                           " km, Cost = " + cost);
    }

    // Premium delivery: distance + priority fee
    public void calculateCharges(double distance, double priorityFee) {
        double cost = distance * 5 + priorityFee;
        System.out.println("Premium Delivery: Distance = " + distance +
                           " km, Priority Fee = " + priorityFee +
                           ", Total = " + cost);
    }

    // Group delivery: distance + number of orders discount
    public void calculateCharges(double distance, int numberOfOrders) {
        double baseCost = distance * 5;
        double discount = numberOfOrders * 2; // 2 off per extra order
        double total = baseCost - discount;
        System.out.println("Group Delivery: Distance = " + distance +
                           " km, Orders = " + numberOfOrders +
                           ", Discount = " + discount +
                           ", Total = " + total);
    }

    // Festival special: distance + discount percentage + free delivery option
    public void calculateCharges(double distance, double discountPercent, double orderAmount) {
        double baseCost = distance * 5;
        if (orderAmount > 500) {
            System.out.println("Festival Special: Order above 500  Free Delivery!");
        } else {
            double discount = baseCost * (discountPercent / 100);
            double total = baseCost - discount;
            System.out.println("Festival Special: Distance = " + distance +
                               " km, Discount = " + discountPercent + "%" +
                               ", Final Cost = " + total);
        }
    }
}

public class FoodDeliverySystem {
    public static void main(String[] args) {
        DeliveryService service = new DeliveryService();

        service.calculateCharges(10);                         // Basic
        service.calculateCharges(10, 50);                     // Premium
        service.calculateCharges(10, 3);                      // Group
        service.calculateCharges(10, 20, 400);                // Festival
        service.calculateCharges(10, 20, 600);                // Festival Free
    }
}
