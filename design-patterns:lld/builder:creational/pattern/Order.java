


public class Order{
    public final String id;
    public final int    amt;
    public final boolean giftWrap;
    public final String giftMessage;
    public final int promoCode;

    public Order(OrderBuilder _builder){
        id = _builder.id;
        amt = _builder.amt;
        giftWrap = _builder.giftWrap;
        giftMessage = _builder.giftMessage;
        promoCode = _builder.promoCode;
    }

    public static OrderBuilder Builder(){
        return new OrderBuilder();
    }

}
