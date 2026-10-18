public class Main {
    public static void main(String[] args) {

        Order order = new Order();

        // PENDING
        System.out.println("\n--- PENDING ---");
        order.place();   // Valid
        order.ship();    // Invalid
        order.deliver(); // Invalid
        order.pay();     // Valid -> PAID

        // PAID
        System.out.println("\n--- PAID ---");
        order.place();   // Invalid
        order.pay();     // Invalid
        order.deliver(); // Invalid
        order.ship();    // Valid -> SHIPPED

        // SHIPPED
        System.out.println("\n--- SHIPPED ---");
        order.place();   // Invalid
        order.pay();     // Invalid
        order.ship();    // Invalid
        order.cancel();  // Invalid
        order.deliver(); // Valid -> DELIVERED

        // DELIVERED
        System.out.println("\n--- DELIVERED ---");
        order.place();   // Invalid
        order.pay();     // Invalid
        order.ship();    // Invalid
        order.deliver(); // Invalid
        order.cancel();  // Invalid

        // Separate canceled-order scenario
        Order canceledOrder = new Order();

        System.out.println("\n--- CANCELED ---");
        canceledOrder.cancel(); // Valid -> CANCELED
        canceledOrder.place();  // Invalid
        canceledOrder.pay();    // Invalid
        canceledOrder.ship();   // Invalid
        canceledOrder.deliver();// Invalid
        canceledOrder.cancel();// Invalid
    }
}
