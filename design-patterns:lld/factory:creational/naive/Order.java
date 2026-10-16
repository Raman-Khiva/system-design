




public class Order{

    PaymentStrategy paymentStrategy;

    public Order(PaymentStrategy strategy){
        paymentStrategy = strategy;
    }

    public void checkout(double amount){
        paymentStrategy.pay(amount);
    }
}
