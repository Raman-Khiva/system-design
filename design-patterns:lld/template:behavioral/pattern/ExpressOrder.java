
public class ExpressOrder extends Order{



    @Override protected  void validate(){
        System.out.println("Validating Order by checking items and checking if express charges paid");
    }


    @Override protected  void calculateTax(){
        System.out.println("Calcualting tax with local rate + charges");
    }

    @Override protected  void updateInventory(){
        System.out.println("Updating inventory by deducting items from express store");
    }

    @Override protected  void notifyCustomer(){
        System.out.println("Notifying customer by sending email and sms");
    }

}
