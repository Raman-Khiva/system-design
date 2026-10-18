public class CanceledState implements State {

    @Override
    public void place(Order order) {
        System.out.println("Cannot place: order is canceled.");
    }

    @Override
    public void pay(Order order) {
        System.out.println("Cannot pay: order is canceled.");
    }

    @Override
    public void ship(Order order) {
        System.out.println("Cannot ship: order is canceled.");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("Cannot deliver: order is canceled.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Order is already canceled.");
    }
}
