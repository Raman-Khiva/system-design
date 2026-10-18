public class Main {
    public static void main(String[] args) {
        Order simpleOrder = new Order("order1", 10);
        Order orderWithGiftWrap = new Order("order2", 20, true);
        Order orderWithGiftWrapAndMessage =
                new Order("order3", 20, true, "Happy Birthday");
        Order orderWithPromo = new Order("order4", 5, 4831);

        simpleOrder.printOrder();
        orderWithGiftWrap.printOrder();
        orderWithGiftWrapAndMessage.printOrder();
        orderWithPromo.printOrder();
    }
}
