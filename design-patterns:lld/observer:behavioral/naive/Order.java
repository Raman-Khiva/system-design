



public class Order{



    EmailService emailService;
    InventoryService inventoryService;
    ShippingService shippingService;

    public Order(){
        emailService = new EmailService();
        inventoryService = new InventoryService();
        shippingService = new ShippingService();
    }

    public void checkout(){
        emailService.send();
        inventoryService.update();
        shippingService.schedule();
    }
}
