public class DeliveredState implements State {

    @Override
    public void place(Order order) {
        System.out.println("Cannot place: order already delivered.");
    }

    @Override
    public void pay(Order order) {
        System.out.println("Cannot pay: order already delivered.");
    }

    @Override
    public void ship(Order order) {
        System.out.println("Cannot ship: order already delivered.");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("Order is already delivered.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Cannot cancel a delivered order.");
    }
}
