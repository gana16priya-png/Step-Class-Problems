class Vehicle {
    private String brand;
    private int year;

    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    public void start() {
        System.out.println(brand + " vehicle started.");
    }

    public void stop() {
        System.out.println(brand + " vehicle stopped.");
    }

    public String getBrand() {
        return brand;
    }

    public int getYear() {
        return year;
    }
}

class Car extends Vehicle {
    private int numberOfDoors;
    private boolean sunroof;

    public Car(String brand, int year, int numberOfDoors, boolean sunroof) {
        super(brand, year);
        this.numberOfDoors = numberOfDoors;
        this.sunroof = sunroof;
    }

    public void openSunroof() {
        if (sunroof) {
            System.out.println(getBrand() + " sunroof opened.");
        } else {
            System.out.println(getBrand() + " does not have a sunroof.");
        }
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public boolean hasSunroof() {
        return sunroof;
    }
}