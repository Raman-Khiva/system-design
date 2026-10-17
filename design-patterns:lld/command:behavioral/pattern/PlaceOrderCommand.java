







public class PlaceOrderCommand implements Command{

    Order order;

    public PlaceOrderCommand(Order _order){
        order = _order;
    }

    @Override public void execute(){
        order.place();
    }
    @Override public void undo(){
        order.cancel();
    }
}
