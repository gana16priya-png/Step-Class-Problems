interface Notifier {
    void send(String message);
}

public class Service {
    public void triggerAlert() {
        Notifier notifier = new Notifier() {
            @Override
            public void send(String message) {
                System.out.println("ALERT: " + message);
            }
        };
        notifier.send("Server down! Immediate action required!");
    }

    public static void main(String[] args) {
        new Service().triggerAlert();
    }
}
