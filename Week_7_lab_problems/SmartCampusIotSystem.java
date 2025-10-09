// Base class
abstract class SmartDevice {
    String deviceId;

    public SmartDevice(String deviceId) {
        this.deviceId = deviceId;
    }

    public void connect() {
        System.out.println("Device " + deviceId + " connected.");
    }

    public abstract void status();
}

// Smart Classroom
class SmartClassroom extends SmartDevice {
    public SmartClassroom(String deviceId) {
        super(deviceId);
    }

    @Override
    public void status() {
        System.out.println("Smart Classroom " + deviceId + " is online.");
    }

    public void controlLighting() {
        System.out.println("Lighting controlled in classroom " + deviceId);
    }

    public void controlProjector() {
        System.out.println("Projector turned on in classroom " + deviceId);
    }
}

// Smart Lab
class SmartLab extends SmartDevice {
    public SmartLab(String deviceId) {
        super(deviceId);
    }

    @Override
    public void status() {
        System.out.println("Smart Lab " + deviceId + " is operational.");
    }

    public void manageEquipment() {
        System.out.println("Equipment managed in lab " + deviceId);
    }

    public void activateSafetySystem() {
        System.out.println("Safety system activated in lab " + deviceId);
    }
}

// Smart Library
class SmartLibrary extends SmartDevice {
    public SmartLibrary(String deviceId) {
        super(deviceId);
    }

    @Override
    public void status() {
        System.out.println("Smart Library " + deviceId + " is running.");
    }

    public void trackOccupancy() {
        System.out.println("Occupancy tracked in library " + deviceId);
    }

    public void checkBookAvailability() {
        System.out.println("Book availability checked in library " + deviceId);
    }
}

public class SmartCampusIotSystem {
    public static void main(String[] args) {
        SmartDevice[] devices = {
            new SmartClassroom("C101"),
            new SmartLab("L201"),
            new SmartLibrary("Lib01")
        };

        for (SmartDevice d : devices) {
            d.connect();
            d.status();

            // Safe downcasting with instanceof
            if (d instanceof SmartClassroom) {
                SmartClassroom sc = (SmartClassroom) d;
                sc.controlLighting();
                sc.controlProjector();
            } else if (d instanceof SmartLab) {
                SmartLab sl = (SmartLab) d;
                sl.manageEquipment();
                sl.activateSafetySystem();
            } else if (d instanceof SmartLibrary) {
                SmartLibrary sLib = (SmartLibrary) d;
                sLib.trackOccupancy();
                sLib.checkBookAvailability();
            }
        }
    }
}
