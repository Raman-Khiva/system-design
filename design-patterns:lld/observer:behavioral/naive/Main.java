
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Order order = new Order();

        boolean state = true;

        while (state) {
            System.out.println("Confirm your order... [y/n/e]");
            String res = sc.next().toLowerCase();

            if (res.equals("y")) {
                System.out.println("Order placed successfully!");
                order.checkout();
                state = false;
            } else if (res.equals("n")) {
                System.out.println("Order canceled!");
                state = false;
            } else if (res.equals("e")) {
                state = false;
            } else {
                System.out.println("Please select a valid option!");
            }
        }

        sc.close();
    }
}
