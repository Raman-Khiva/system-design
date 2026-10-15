

public class Order {

static void checkout(String paymentType, double amount){

      switch (paymentType){
         case "credit_card" :
             System.out.println("Processing $" + amount + " via Credit Card");
             break;
        case "paypal" :
             System.out.println("Processing $" + amount + " via  Paypal");
             break;
        case "crypto" :
             System.out.println("Processing $" + amount + " via  crypto");
             break;
        default :
             System.out.println("Payment failed! No valid payment method selected.");

}

}
}
