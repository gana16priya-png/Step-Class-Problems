// File: SmartHome.java

// Base class
class SmartDevice {
    protected String name;

    public SmartDevice(String name) {
        this.name = name;
    }

    public void turnOn() {
        System.out.println(name + " is now ON.");
    }

    public void turnOff() {
        System.out.println(name + " is now OFF.");
    }
}

// Subclass: SmartLight
class SmartLight extends SmartDevice {
    private int brightness;

    public SmartLight(String name, int brightness) {
        super(name);
        this.brightness = brightness;
    }

    public void adjustBrightness(int level) {
        this.brightness = level;
        System.out.println(name + " brightness set to " + brightness + "%.");
    }
}

// Subclass: SmartThermostat
class SmartThermostat extends SmartDevice {
    private int temperature;

    public SmartThermostat(String name, int temperature) {
        super(name);
        this.temperature = temperature;
    }

    public void setTemperature(int temp) {
        this.temperature = temp;
        System.out.println(name + " temperature set to " + temperature + "°C.");
    }
}

// Demo class
public class SmartHome {
    public static void main(String[] args) {
        // Create an array of SmartDevice references
        SmartDevice[] devices = {
            new SmartLight("Living Room Light", 75),
            new SmartThermostat("Bedroom Thermostat", 22),
            new SmartLight("Kitchen Light", 50)
        };

        // Iterate and safely downcast using instanceof
        for (SmartDevice device : devices) {
            device.turnOn();

            if (device instanceof SmartLight) {
                SmartLight light = (SmartLight) device;
                light.adjustBrightness(90); // safe downcast
            } else if (device instanceof SmartThermostat) {
                SmartThermostat thermostat = (SmartThermostat) device;
                thermostat.setTemperature(24); // safe downcast
            }

            device.turnOff();
            System.out.println();
        }
    }
}
