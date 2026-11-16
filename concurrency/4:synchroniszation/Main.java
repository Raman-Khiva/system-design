
//Non Synchronized
class NSCounter{
    private int count = 0;

    public void increment(){
        count++;
    }

    public int getCount(){
        return count;
    }
}

// Synchronized Block(More granular than normal synchroized)

class SBCounter{
    private int count = 0;
    private final Object lock = new Object();

    public void increment(){
        synchronized (lock) { // lock only critical part
            count++;
        }
        // non-critical code runs without lock
    }

}

// Static Synchronized
// this give whole class only one lock, different instances of class will have this same lock (not lock per instance but only one lock for all instances)
//
class SSCounter{
    private static int count = 0;

    public static synchronized void increment(){
        count++;
    }
}

public class Main{
    public static void main(String[] args) throws InterruptedException {

        {
            System.out.println("This is non synchronized counter(10 iterations)");

            for(int j=0;j<10;j++){
        NSCounter counter = new NSCounter();
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
                    t1.join();
                    t2.join();
                } catch (InterruptedException e) {
                    System.out.println("Threads interupted!");
                }

               System.out.println("Final count (expected 4000) : " + counter.getCount());
            }      }

{
    System.out.println("This is synchronized counter");
    for(int j=0;j<10;j++){
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
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Threads interupted!");
        }

       System.out.println("Final count (expected 4000) : " + counter.getCount());
    }}
    }
}
