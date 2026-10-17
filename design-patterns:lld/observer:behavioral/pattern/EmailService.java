


public class EmailService implements Observer{

    @Override public void update(Order order){
        this.send(order);
    }

    public void send(Order order){
        System.out.println("Sending Email for Order #" + order.id);
    }
}
