

public class Main{

    public static void main(String[] args){


        Order order = Order.Builder()
            .id("order1")
            .amt(40)
            .giftWrap(true)
            .promoCode(4958)
            .build();

        System.out.print("Order 1 details \n"
            + "id : " + order.id + "\n"
            + "amount : " + order.amt + "\n"
            + "gift wraped : " + order.giftWrap + "\n"
            + "promo code : " + order.promoCode + "\n"

        );
    }
}
