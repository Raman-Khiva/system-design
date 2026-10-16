import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();

        System.out.print("Choose payment method (credit_card, paypal, crypto): ");
        String method = sc.next();

        PaymentStrategy strategy;

        switch (method) {
            case "credit_card":
                strategy = new CreditCardPayment();
                break;
            case "paypal":
                strategy = new PayPalPayment();
                break;
            case "crypto":
                strategy = new CryptoPayment();
                break;
            default:
                System.out.println("Invalid payment method.");
                sc.close();
                return;
        }

        Order order = new Order(strategy);
        order.checkout(amount);

        sc.close();
    }
}
