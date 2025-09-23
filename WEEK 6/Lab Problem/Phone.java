class Phone {
    protected String brand;
    protected String model;

    public Phone(String brand, String model) {
        this.brand = brand;
        this.model = model;
        System.out.println("Phone constructor called: " + brand + " " + model);
    }
}

class SmartPhone extends Phone {
    protected String operatingSystem;

    public SmartPhone(String brand, String model, String operatingSystem) {
        super(brand, model); // Calls Phone constructor
        this.operatingSystem = operatingSystem;
        System.out.println("SmartPhone constructor called: " + operatingSystem);
    }
}

public class PhoneTest {
    public static void main(String[] args) {
        Phone p = new Phone("Nokia", "3310");
        SmartPhone sp = new SmartPhone("Apple", "iPhone 13", "iOS");
    }
}