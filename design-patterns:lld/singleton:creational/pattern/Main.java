
public class Main {
    public static void main(String[] args) {
        PaymentGatewayConfig config1 =  PaymentGatewayConfig.getInstance();

        PaymentGatewayConfig config2 =  PaymentGatewayConfig.getInstance();

        PaymentGatewayConfig[] configs = {config1, config2};

        System.out.println("=== configs before change ===");
        for (int i = 0; i < configs.length; i++) {
            PaymentGatewayConfig config = configs[i];

            System.out.println("====== CONFIG " + i + " ======");
            System.out.println("apiEndpoints: " + config.getApiEndpoints());
            System.out.println("defaultCurrency: " + config.getDefaultCurrency());
            System.out.println("timeoutSeconds: " + config.getTimeoutSeconds());
            System.out.println("isSandboxMode: " + config.isSandboxMode());
            System.out.println();
        }

        config1.setIsSandboxMode(true);


        System.out.println("=== configs after changes in one config ===");
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
