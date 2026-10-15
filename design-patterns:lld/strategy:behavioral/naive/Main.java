
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Please enter the amount:");
            int amount = sc.nextInt();

            System.out.println(
                "Please enter the payment method (credit_card, paypal, crypto):"
            );
            String method = sc.next();

            Order.checkout(method, amount);
        }
    }
}
