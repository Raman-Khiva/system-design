public class PendingState implements State {

    @Override
    public void place(Order order) {
        System.out.println("Order placed successfully!");
    }

    @Override
    public void pay(Order order) {
        System.out.println("Payment successful!");
        order.setState(new PaidState());
    }

    @Override
    public void ship(Order order) {
        System.out.println("Cannot ship order while pending.");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("Cannot deliver order while pending.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Order canceled successfully!");
        order.setState(new CanceledState());
    }
}
