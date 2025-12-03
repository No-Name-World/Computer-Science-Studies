package Exer0003_Multithreading_Using_Runnable;


public class MyRunnableThread implements Runnable{
    // Implement the run() method required by the Runnable interface
    @Override
    public void run() {
        // ***SOME RUNNABLE PROCESS***
        
        // Print a message indicating that the thread is running
        System.out.println("Thread is running: " + Thread.currentThread().getName());
        
        // Simulate some work being done by the thread
        for (int i = 1; i <= 5; i++) {
            try {
                // Pause the thread for a short period to simulate work being done
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Handle any interruptions to the thread
                System.out.println("Thread interrupted: " + Thread.currentThread().getName());
            }
            // Print a message indicating the progress of the thread
            System.out.println("Thread progress: " + i);
        }
        
        // Print a message indicating that the thread has finished
        System.out.println("Thread finished: " + Thread.currentThread().getName());
  
    }
}