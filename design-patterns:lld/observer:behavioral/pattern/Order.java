import java.util.List;
import java.util.ArrayList;



public class Order{


    String id;
    private List<Observer> observers = new ArrayList<>();

    public Order(String _id){
        id = _id;
    }

    public void attach(Observer o) {observers.add(o);}
    public void detach(Observer o) {observers.remove(o);}

    public void checkout(){
        notifyObservers();
    }

    private void notifyObservers(){
        for(Observer o : observers){
            o.update(this);
        }
    }
}
