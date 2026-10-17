
import java.util.Scanner;






public class Main{
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        while(true){

            System.out.println("Choose your action [p=place order / c = cancel order]");
            String action = sc.next();

            OrderProcessor processor = new OrderProcessor();

            if(action.equals("p")){
                processor.placeOrder();
            }
            else if(action.equals("c")){
                processor.cancelOrder();
            }else{
                System.out.println("Please choose a valid option");
            }
        }
        }
    }
