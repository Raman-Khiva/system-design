





public class CreditCardFactory implements PaymentFactory{
    @Override public PaymentStrategy createPayment(){
        return new CreditCardPayment();
    }
}
