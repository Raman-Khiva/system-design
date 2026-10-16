
public class Main {
    public static void main(String[] args) {
        PaymentGatewayConfig config1 = new PaymentGatewayConfig();
        config1.setIsSandboxMode(true);

        PaymentGatewayConfig config2 = new PaymentGatewayConfig();

        PaymentGatewayConfig[] configs = {config1, config2};

        for (int i = 0; i < configs.length; i++) {
            PaymentGatewayConfig config = configs[i];

            System.out.println("====== CONFIG " + i + " ======");
            System.out.println("apiEndpoints: " + config.getApiEndpoints());
            System.out.println("defaultCurrency: " + config.getDefaultCurrency());
            System.out.println("timeoutSeconds: " + config.getTimeoutSeconds());
            System.out.println("isSandboxMode: " + config.isSandboxMode());
            System.out.println();
        }
    }
}
