
public class PaymentGatewayConfig {
    private String apiEndpoints;
    private String defaultCurrency;
    private int timeoutSeconds;
    private boolean isSandboxMode;

    public PaymentGatewayConfig() {
        apiEndpoints = "/some/api/endpoint";
        defaultCurrency = "USD";
        timeoutSeconds = 120;
        isSandboxMode = false;
    }

    public void setApiEndpoints(String endpoints) {
        apiEndpoints = endpoints;
    }

    public void setDefaultCurrency(String currency) {
        defaultCurrency = currency;
    }

    public void setTimeoutSeconds(int seconds) {
        timeoutSeconds = seconds;
    }

    public void setIsSandboxMode(boolean mode) {
        isSandboxMode = mode;
    }

    public String getApiEndpoints() {
        return apiEndpoints;
    }

    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    public int getTimeoutSeconds() {
        return timeoutSeconds;
    }

    public boolean isSandboxMode() {
        return isSandboxMode;
    }
}
