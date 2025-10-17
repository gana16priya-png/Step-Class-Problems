public class AppConfig {
    private static String appName = "MyApp";

    public static class NetworkConfig {
        private String host;
        private int port;

        public NetworkConfig(String host, int port) {
            this.host = host;
            this.port = port;
        }

        public void showConfig() {
            System.out.println("App: " + appName + ", Host: " + host + ", Port: " + port);
        }
    }
}

public class AppConfigurator {
    public static void main(String[] args) {
        AppConfig.NetworkConfig config = new AppConfig.NetworkConfig("localhost", 8080);
        config.showConfig();
    }
}
