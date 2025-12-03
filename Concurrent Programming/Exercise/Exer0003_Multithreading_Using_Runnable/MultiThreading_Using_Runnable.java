package Exer0003_Multithreading_Using_Runnable;
public class MultiThreading_Using_Runnable {
    public static void main(String[] args) {
        // Create an instance of the MyRunnable class
        MyRunnableThread myRunnable = new MyRunnableThread();
        
        // Create two threads using the MyRunnable instance
        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable);
        
        // Start both threads
        thread1.start();
        thread2.start();
    }
    
}
