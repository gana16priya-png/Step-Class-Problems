class SmartDevice {
    protected String name;

    public SmartDevice(String name) {
        this.name = name;
    }

    public void deviceInfo() {
        System.out.println("Device: " + name);
    }
}

// Smart TV
class SmartTV extends SmartDevice {
    public SmartTV(String name) {
        super(name);
    }

    public void changeChannel(int channel) {
        System.out.println(name + " switched to channel " + channel);
    }

    public void setVolume(int volume) {
        System.out.println(name + " volume set to " + volume);
    }

    public void openStreamingApp(String app) {
        System.out.println(name + " opened " + app);
    }
}

// Smart Thermostat
class SmartThermostat extends SmartDevice {
    public SmartThermostat(String name) {
        super(name);
    }

    public void setTemperature(int temp) {
        System.out.println(name + " temperature set to " + temp + "°C");
    }

    public void setHumidity(int humidity) {
        System.out.println(name + " humidity set to " + humidity + "%");
    }

    public void enableEnergySavingMode() {
        System.out.println(name + " enabled energy-saving mode");
    }
}

// Smart Security System
class SmartSecurity extends SmartDevice {
    public SmartSecurity(String name) {
        super(name);
    }

    public void activateAlarm() {
        System.out.println(name + " alarm activated!");
    }

    public void viewCameraFeed() {
        System.out.println(name + " camera feed streaming...");
    }

    public void controlAccess(String user) {
        System.out.println(name + " granted access to " + user);
    }
}

// Smart Kitchen Appliance
class SmartKitchen extends SmartDevice {
    public SmartKitchen(String name) {
        super(name);
    }

    public void setCookingTime(int minutes) {
        System.out.println(name + " cooking time set to " + minutes + " minutes");
    }

    public void setCookingTemperature(int temp) {
        System.out.println(name + " cooking temperature set to " + temp + "°C");
    }

    public void loadRecipe(String recipe) {
        System.out.println(name + " loaded recipe: " + recipe);
    }
}

// Main Program
public class Problem6_SmartHome {
    public static void main(String[] args) {
        // Mixed collection of devices
        SmartDevice[] devices = {
            new SmartTV("Living Room TV"),
            new SmartThermostat("Bedroom Thermostat"),
            new SmartSecurity("Home Security"),
            new SmartKitchen("Smart Oven")
        };

        // Safely identify and downcast before using
        for (SmartDevice device : devices) {
            device.deviceInfo();

            if (device instanceof SmartTV) {
                SmartTV tv = (SmartTV) device;
                tv.changeChannel(5);
                tv.setVolume(20);
                tv.openStreamingApp("Netflix");
            } else if (device instanceof SmartThermostat) {
                SmartThermostat thermostat = (SmartThermostat) device;
                thermostat.setTemperature(22);
                thermostat.setHumidity(40);
                thermostat.enableEnergySavingMode();
            } else if (device instanceof SmartSecurity) {
                SmartSecurity security = (SmartSecurity) device;
                security.activateAlarm();
                security.viewCameraFeed();
                security.controlAccess("Alice");
            } else if (device instanceof SmartKitchen) {
                SmartKitchen kitchen = (SmartKitchen) device;
                kitchen.setCookingTime(45);
                kitchen.setCookingTemperature(180);
                kitchen.loadRecipe("Lasagna");
            }
            System.out.println();
        }
    }
}
