

public class InventoryService implements Observer{

    @Override public void update(Order order){
        this.updateInv(order);
    }

    public void updateInv(Order order){
        System.out.println("Updating inventory for Order #" + order.id);
    }
}
