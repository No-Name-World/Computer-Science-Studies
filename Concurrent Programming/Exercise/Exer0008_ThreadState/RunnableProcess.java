package Exer0008_Threadstate;

public class RunnableProcess extends Thread {

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + 
                    " is Running: " +Thread.currentThread().getState());
            Thread.sleep(2000); // Sleep for 2 seconds Watch the monitor and check the state of Thread 1.

            System.out.println(Thread.currentThread().getName() + 
                    " Slept for 2 Seconds.");
            System.out.println(Thread.currentThread().getName() + 
                    " is awake: " + " " +Thread.currentThread().getState());

        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + 
                    " interrupted.");
        }
    }
}
