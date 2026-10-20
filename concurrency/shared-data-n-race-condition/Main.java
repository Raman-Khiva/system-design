



public class Main{
    public static void main(String[] args) throws InterruptedException{


       Counter counter = new Counter();

      Thread t1 = new Thread(()->{
          for(int i=0;i<2000;i++){
              counter.increment();
          }
      });
      Thread t2 = new Thread(()->{
          for(int i=0;i<2000;i++){
              counter.increment();
          }
      });

      t1.start();
      t2.start();
      try {
          t1.join(); // stop main thread till t1 exectues
          t2.join(); //stop main thread till t1 exectues
          //it makes sure these threads runs before programs ends

      } catch (InterruptedException e) {
          System.out.println("Threads interupted!");
      }
      System.out.println("Final count (expected 4000) : " + counter.getCount());
    }
}
