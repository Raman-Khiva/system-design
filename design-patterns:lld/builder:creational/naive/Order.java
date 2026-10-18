

public class Order{


    public String id;
    public int amt;
    //optional
    public int promocode= 0;
    public boolean giftWrap = false;
    public String giftMessage = "";

    public  Order(String _id, int _amt){
        id = _id;
        amt = _amt;
    }

    public  Order(String _id, int _amt, boolean _giftWrap){
        id = _id;
        amt = _amt;
        giftWrap = _giftWrap;
    }
    public  Order(String _id, int _amt, boolean _giftWrap, String _giftMessage){
        id = _id;
        amt = _amt;
        giftWrap = _giftWrap;
        giftMessage = _giftMessage;
    }
    public  Order(String _id, int _amt, int _promoCode){
        id = _id;
        amt = _amt;
        promocode = _promoCode;

    }

    public void  printOrder() {
           System.out.println(
               "Order{id='" + id +
               "', amt=" + amt +
               ", giftWrap=" + giftWrap +
               ", giftMessage='" + giftMessage +
               "', promo=" + promocode
           );
}
}
