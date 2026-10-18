






public class CheckoutController{
    public void checkout(){
        EmailService email = new EmailService();
        ShippingService shipping = new ShippingService();
        InventoryService inventory = new InventoryService();

        email.sendEmail();
        shipping.scheduleDelivery();
        inventory.deductItems();

    }
}
