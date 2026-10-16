














public class PaymentGatewayConfig {
    private String apiEndpoints;
    private String defaultCurrency;
    private int timeoutSeconds;
    private boolean isSandboxMode;

    private static PaymentGatewayConfig instance;

    private PaymentGatewayConfig() {
        apiEndpoints = "/some/api/endpoint";
        defaultCurrency = "USD";
        timeoutSeconds = 120;
        isSandboxMode = false;
    }


    public static PaymentGatewayConfig getInstance(){
        if(instance==null){
            instance = new PaymentGatewayConfig();
        }

        return instance;
    }



    public void setApiEndpoints(String endpoints) {
        instance.apiEndpoints = endpoints;
    }

    public void setDefaultCurrency(String currency) {
        instance.defaultCurrency = currency;
    }

    public void setTimeoutSeconds(int seconds) {
        instance.timeoutSeconds = seconds;
    }

    public void setIsSandboxMode(boolean mode) {
        instance.isSandboxMode = mode;
    }

    public String getApiEndpoints() {
        return instance.apiEndpoints;
    }

    public String getDefaultCurrency() {
        return instance.defaultCurrency;
    }

    public int getTimeoutSeconds() {
        return instance.timeoutSeconds;
    }

    public boolean isSandboxMode() {
        return instance.isSandboxMode;
    }
}
