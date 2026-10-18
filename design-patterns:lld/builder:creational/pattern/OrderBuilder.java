


public class OrderBuilder{

    public  String id;
    public  int    amt;
    public  boolean giftWrap;
    public  String giftMessage;
    public  int promoCode;

    public OrderBuilder id(String _id){
        this.id = _id;
        return this;
    }

    public OrderBuilder amt(int _amt){

        this.amt = _amt;
        return this;
    }

    public OrderBuilder giftWrap(boolean wrap){
        this.giftWrap = wrap;
        return this;
    }

    public OrderBuilder giftMessage(String message){
        this.giftMessage = message;
        return this;
    }

    public OrderBuilder promoCode(int code){
        this.promoCode = code;
        return this;
    }

    public Order build(){
        return new Order(this);
    }


}
