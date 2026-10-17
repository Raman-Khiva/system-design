




public class StandardOrder implements Order{

    @Override public void process(){

        //Here comes all the services (I'm just writing and indicator of execution in actually they performs complex tasks which is understandble so skiping it)
        System.out.println("Validating order by checking items.....");
        System.out.println("Calculating tax at local rate......");
        System.out.println("Sending notification email....");
        System.out.println("Updating inventory by deducting items from local warehouse");
    }
}
