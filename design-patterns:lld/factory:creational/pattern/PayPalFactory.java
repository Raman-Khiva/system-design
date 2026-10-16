




public class PayPalFactory implements PaymentFactory{
    @Override public PaymentStrategy createPayment(){
        return new PayPalPayment();
    }
}
