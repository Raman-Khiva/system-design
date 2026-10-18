public class PaidState implements State {

    @Override
    public void place(Order order) {
        System.out.println("Cannot place order: already paid.");
    }

    @Override
    public void pay(Order order) {
        System.out.println("Order is already paid.");
    }

    @Override
    public void ship(Order order) {
        System.out.println("Order shipped successfully!");
        order.setState(new ShippedState());
    }

    @Override
    public void deliver(Order order) {
        System.out.println("Cannot deliver an order that hasn't shipped.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Order canceled successfully!");
        order.setState(new CanceledState());
    }
}
