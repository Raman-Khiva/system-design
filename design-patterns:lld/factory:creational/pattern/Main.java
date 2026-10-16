import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();

        System.out.print("Choose payment method (credit_card, paypal, crypto): ");
        String method = sc.next();

        PaymentFactory factory;

        switch (method) {
            case "credit_card":
                factory = new CreditCardFactory();
                break;
            case "paypal":
                factory = new PayPalFactory();
                break;
            case "crypto":
                factory = new CryptoFactory();
                break;
            default:
                System.out.println("Invalid payment method.");
                sc.close();
                return;
        }

        PaymentStrategy strategy = factory.createPayment();
        Order order = new Order(strategy);
        order.checkout(amount);

        sc.close();
    }
}
