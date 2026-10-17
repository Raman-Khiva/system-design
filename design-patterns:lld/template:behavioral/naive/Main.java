

import java.util.Scanner;



public class Main{
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("Please select order type [e=express/s=standard]");
            String type = sc.next();

            Order order;
            if(type.equals("s")){
                 order = new StandardOrder();
                 order.process();
            }
            else if(type.equals("e")){
                 order = new ExpressOrder();
                 order.process();
            }else{
                System.out.println("Please select a valid type");
            }
        }
    }
}
