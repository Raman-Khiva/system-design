


public class ExpressOrder implements Order{

    @Override public void process(){

        //Here comes all the services (I'm just writing and indicator of execution in actually they performs complex tasks which is understandble so skiping it)
        System.out.println("Validating order by checking order and if priority fees is  paid...");
        System.out.println("Calculating tax at local rate + subcharges...");
        System.out.println("Sending notification email and sms......");
        System.out.println("Updating inventory by deducting items from express store");
    }
}
