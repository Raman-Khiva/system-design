
// two ways to create thread


// by extending Thread class

class MyThread extends Thread{

    @Override public void run(){
        System.out.println("Running in: " + Thread.currentThread().getName());
    }
}


// 2. By implement3.ng Runnable interface

class MyTask implements Runnable{
    @Override public void run(){
        System.out.println("Running in: " + Thread.currentThread().getName());
    }
}





public class Main{

    public static void main(String[] args){

        Thread thread1 = new MyThread();
        Thread thread2 = new Thread(new MyTask());

        System.out.println("This is thread with Extend Thread class method : ");
        thread1.start();
        System.out.println("This is thread with Implement Runnable interface method"  );
        thread2.start();


        System.out.println("Creating new thread");
        Thread t3 = new Thread(()->{
            System.out.println("State while running: "+ Thread.currentThread().getState());
        });
        System.out.println("New thread created");

        System.out.println("State while running: "+ t3.getState());
        t3.start();

        System.out.println("Thread started");
        System.out.println("State while running: "+ t3.getState());



        System.out.println("Thread with custom name = SpecialThread");

        Thread specialThread = new Thread(()->{},"SpecialThread");

        System.out.println("Name of cutom named thread : " + specialThread.getName());


        System.out.println("Testing what happens on running .run()");
        System.out.println("State before running .run() : " + specialThread.getState());
        specialThread.run();
        System.out.println("State after running .run() : " + specialThread.getState());

    }
}
