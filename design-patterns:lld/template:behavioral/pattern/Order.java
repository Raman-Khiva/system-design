




public abstract class Order{



    protected void validate(){
        System.out.println("Validating Order by checking items ");
    }


    protected void calculateTax(){
        System.out.println("Calcualting tax with local rate ");
    }

    protected void updateInventory(){
        System.out.println("Updating inventory by");
    }

    protected void notifyCustomer(){
        System.out.println("Notifying customer by sending email");
    }

    protected void sendBill(){
        System.out.println("Sending bill");
    }


    public final void process(){
        validate();
        calculateTax();
        notifyCustomer();
        updateInventory();
        sendBill();
    }
}
