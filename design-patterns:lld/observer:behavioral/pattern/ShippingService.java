



public class ShippingService implements Observer{

    @Override public void update(Order order){
        this.schedule(order);
    }

    public void schedule(Order order){
        System.out.println("Scheduling Shipping for Order #" + order.id);
    }
}
