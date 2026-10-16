






public class CryptoPayment implements PaymentStrategy{

    @Override public void pay(double amount){
        System.out.println("Processing $" + amount + " using  CryptoPayment");
    }

}
