
public class CryptoFactory implements PaymentFactory{
    @Override public PaymentStrategy createPayment(){
        return new CryptoPayment();
    }
}
