public class AudioMixer {
    private String mixerModel;
    private int numberOfChannels;
    private boolean hasBluetoothConnectivity;
    private double maxVolumeDecibels;
    private String[] connectedDevices;
    private int deviceCount;

    // No-argument constructor using this() chaining
    public AudioMixer() {
        this("StandardMix-8", 8, false); // calls 3-parameter constructor
    }

    // Two-parameter constructor using this() chaining
    public AudioMixer(String mixerModel, int numberOfChannels) {
        this(mixerModel, numberOfChannels, false); // calls 3-parameter constructor
    }

    // Three-parameter constructor using this() chaining
    public AudioMixer(String mixerModel, int numberOfChannels, boolean hasBluetoothConnectivity) {
        this(mixerModel, numberOfChannels, hasBluetoothConnectivity, 120.0); // calls main constructor
    }

    // Main constructor - all parameters
    public AudioMixer(String mixerModel, int numberOfChannels,
                      boolean hasBluetoothConnectivity, double maxVolumeDecibels) {
        this.mixerModel = mixerModel;
        this.numberOfChannels = numberOfChannels;
        this.hasBluetoothConnectivity = hasBluetoothConnectivity;
        this.maxVolumeDecibels = maxVolumeDecibels;
        this.connectedDevices = new String[numberOfChannels]; // array size = channels
        this.deviceCount = 0;

        System.out.println("Constructor executed for: " + mixerModel);
    }

    // Method to connect devices
    public void connectDevice(String deviceName) {
        if (deviceCount < connectedDevices.length) {
            connectedDevices[deviceCount] = deviceName;
            deviceCount++;
            System.out.println("Connected: " + deviceName);
        } else {
            System.out.println("All channels occupied!");
        }
    }

    // Method to display status
    public void displayMixerStatus() {
        System.out.println("\n=== " + mixerModel + " STATUS ===");
        System.out.println("Channels: " + numberOfChannels);
        System.out.println("Bluetooth: " + (hasBluetoothConnectivity ? "Enabled" : "Disabled"));
        System.out.println("Max Volume: " + maxVolumeDecibels + " dB");
        System.out.println("Connected Devices: " + deviceCount + "/" + numberOfChannels);
        for (int i = 0; i < deviceCount; i++) {
            System.out.println(" Channel " + (i + 1) + ": " + connectedDevices[i]);
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        System.out.println("=== MUSIC STUDIO SETUP ===");

        // Mixer using no-argument constructor
        AudioMixer mixer1 = new AudioMixer();

        // Mixer using two-parameter constructor
        AudioMixer mixer2 = new AudioMixer("CompactMix-4", 4);

        // Mixer using three-parameter constructor
        AudioMixer mixer3 = new AudioMixer("BluetoothMix-12", 12, true);

        // Mixer using full constructor
        AudioMixer mixer4 = new AudioMixer("MegaMix-24", 24, true, 150.0);

        // Connect devices
        mixer1.connectDevice("Keyboard");
        mixer1.connectDevice("Drum Machine");

        mixer2.connectDevice("Guitar");
        mixer2.connectDevice("Bass");

        mixer3.connectDevice("Laptop");
        mixer3.connectDevice("Phone");

        mixer4.connectDevice("Microphone");
        mixer4.connectDevice("Synthesizer");
        mixer4.connectDevice("Electric Guitar");

        // Display status
        mixer1.displayMixerStatus();
        mixer2.displayMixerStatus();
        mixer3.displayMixerStatus();
        mixer4.displayMixerStatus();

        System.out.println("\n=== Constructor Chaining Order ===");
        System.out.println("No-arg -> 3-param -> Main");
        System.out.println("2-param -> 3-param -> Main");
        System.out.println("3-param -> Main");
        System.out.println("Full constructor called directly");
    }
}
