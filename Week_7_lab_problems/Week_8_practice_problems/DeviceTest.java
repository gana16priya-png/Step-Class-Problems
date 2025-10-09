// File: DeviceTest.java

interface Camera {
    void takePhoto();
}

interface MusicPlayer {
    void playMusic();
}

class SmartPhone implements Camera, MusicPlayer {
    @Override
    public void takePhoto() {
        System.out.println("Taking photo with smartphone");
    }

    @Override
    public void playMusic() {
        System.out.println("Playing music on smartphone");
    }
}

public class DeviceTest {
    public static void main(String[] args) {
        SmartPhone sp = new SmartPhone();
        sp.takePhoto();
        sp.playMusic();
    }
}
