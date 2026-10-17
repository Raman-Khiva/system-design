
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        while (true) {
            System.out.println("Please enter order id..");
            String id = sc.next().toLowerCase();


            Order order = new Order(id);
            EmailService o1 = new EmailService();
            InventoryService o2 = new InventoryService();
            ShippingService o3 = new ShippingService();
            order.attach(o1);
            order.attach(o2);
            order.attach(o3);

            System.out.println("Do you want email confirmation...(y/n)");
            String preference = sc.next();
            if(preference.equals("n")){
                order.detach(o1);
            }

            order.checkout();
        }

    }
}
