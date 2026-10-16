





public class CreditCardPayment implements PaymentStrategy{

    @Override public void pay(double amount){
        System.out.println("Processing $" + amount + " using Credit Card");
    }

}
