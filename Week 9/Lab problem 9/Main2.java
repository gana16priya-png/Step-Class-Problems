class Car {
    String brand, model;
    double price;

    Car(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car [Brand=" + brand + ", Model=" + model + ", Price=" + price + "]";
    }
}

public class Main2 {
    public static void main(String[] args) {
        Car c = new Car("Tesla", "Model S", 90000);
        System.out.println(c); // invokes toString()
        System.out.println("Class Name: " + c.getClass().getName());
    }
}
