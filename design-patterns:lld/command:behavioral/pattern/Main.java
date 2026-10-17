
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CommandInvoker invoker = new CommandInvoker();

        // Create the order once, outside the loop
        Order order = new Order();

        Command placeOrder = new PlaceOrderCommand(order);
        Command cancelOrder = new CancelOrderCommand(order);

        while (true) {
            System.out.println(
                "Choose your action [p = place order / c = cancel order / u = undo / q = quit]"
            );

            String action = sc.next();

            if (action.equals("p")) {
                invoker.executeCmd(placeOrder);
            } else if (action.equals("c")) {
                invoker.executeCmd(cancelOrder);
            } else if (action.equals("u")) {
                invoker.undoCmd();
            } else if (action.equals("q")) {
                break;
            } else {
                System.out.println("Please choose a valid option");
            }
        }

        sc.close();
    }
}
