





public class Main{

    public static void main(String[] args) throws InterruptedException{

        // .sleep(time) method pauses the thread for the given time

        // Thread t = new Thread(()->{
        //     for(int i=1;i<5;i++){
        //        System.out.println("Count : " + i);
        //        try {
        //           Thread.sleep(2000);
        //        } catch (InterruptedException e) {
        //            System.out.println("Thread was interupted!");
        //            return;
        //        }
        //     }
        // });

        // System.out.print("Starting new Thread");
        // t.start();

        // System.out.print("Finished!");




        // JOIN()
        // t.join() it stops the currently running thread untill target(t in this case) thread fineshes it's job
        //

        // Thread t2 = new Thread(()->{
        //     System.out.println("Worker thread started running");
        //     try{
        //         Thread.sleep(5000);
        //     }catch(InterruptedException  e){
        //         System.out.println("Worked thread interupted!");
        //         return;
        //     }

        //     System.out.println("Worked thread has done it's job.");
        // });




        // t2.start();
        // System.out.println("Main thread is running...");
        // System.out.println("Main thread will be blocked and wait untill worked thread(t2) finishes it's job.");
       // try {
        //     t2.join();
        // } catch (InterruptedException e) {
        //     System.out.println("Main was interrupted while waiting");
        //     return;
        // }
        // System.out.println("Main Thread is back now");


        // Interrupt

        // .interrupt() Signals a thread to stop what it's doing. It does not force the thread to stop - it sets a flag that the thread can check

        // Thread t3 = new Thread(()->{
        //     System.out.println("Worker Thread has been started");
        //     System.out.println("Worker Thread will go to sleep mode for 3s now and will say \"Hello\" on return!");

        //     try{
        //         Thread.sleep(3000);

        //         System.out.println("Sleep Over! Hellow from the Worker Thread");
        //     }catch(InterruptedException e){
        //         System.out.println("Worker thread interrupted! Cleaning up...");
        //     }

        // });

        // t3.start();
        // Thread.sleep(1000);
        // t3.interrupt();


        // YIELD

        // .yield() Hints the scheduler that the current thread is willing to give up it's CUP time, scheduler may ignore this hind

        // Thread t4 = new Thread(()->{
        //     System.out.println("Counting till 20");
        //     try{
        //     for(int i=1;i<21;i++){
        //         System.out.println(i);
        //         Thread.sleep(2000);
        //         if(i==2){
        //             Thread.yield();
        //         }
        //     }
        //     System.out.println("Yield command got ignored by Scheduler");
        //     }catch(InterruptedException e){
        //         System.out.println("Counter thread interrupted! Clearning up....")
        //         ;
        //     }
        // });

        // t4.start();


        // PRIORITY
        // Threads have priority from 1(lowest) to 10(highest). default is 5
        // .setPriority is used to set the priority

        Thread t5 = new Thread(()->{
            for(int i=1;i<6;i++){
                System.out.println("Ran by low priority thread : " + i);
            }
        });

        Thread t6 = new Thread(()->{
            for(int i=1;i<6;i++){
                System.out.println("Ran by high priority thread : " + i);
            }
        });

        t5.setPriority(Thread.MIN_PRIORITY);
        t6.setPriority(Thread.MAX_PRIORITY);

        t5.start();
        t6.start();



    }
}
