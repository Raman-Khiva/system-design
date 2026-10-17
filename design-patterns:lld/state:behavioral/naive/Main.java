public class Main {
    public static void main(String[] args) {

        Order order = new Order();

        // PENDING
        System.out.println("\n--- PENDING ---");
        order.place();
        order.pay();

        // PAID
        System.out.println("\n--- PAID ---");
        order.place();   // Invalid
        order.pay();     // Invalid
        order.deliver(); // Invalid
        order.ship();    // Valid → SHIPPED

        // SHIPPED
        System.out.println("\n--- SHIPPED ---");
        order.place();   // Invalid
        order.pay();     // Invalid
        order.ship();    // Invalid
        order.cancel();  // Invalid
        order.deliver(); // Valid → DELIVERED

        // DELIVERED
        System.out.println("\n--- DELIVERED ---");
        order.place();   // Invalid
        order.pay();     // Invalid
        order.ship();    // Invalid
        order.deliver(); // Invalid
        order.cancel();  // Invalid
    }
}
