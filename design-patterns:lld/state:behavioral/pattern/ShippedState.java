public class ShippedState implements State {

    @Override
    public void place(Order order) {
        System.out.println("Cannot place an already shipped order.");
    }

    @Override
    public void pay(Order order) {
        System.out.println("Cannot pay: order already shipped.");
    }

    @Override
    public void ship(Order order) {
        System.out.println("Order has already shipped.");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("Order delivered successfully!");
        order.setState(new DeliveredState());
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Cannot cancel an order that has shipped.");
    }
}
