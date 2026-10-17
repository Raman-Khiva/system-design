






public class CancelOrderCommand implements Command{

    Order order;
    public CancelOrderCommand(Order _order){
        order = _order;
    }

    @Override public void execute(){
        order.cancel();
    }
    @Override public void undo(){
        order.place();
    }
}
