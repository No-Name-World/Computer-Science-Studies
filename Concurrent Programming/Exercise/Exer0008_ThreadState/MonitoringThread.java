package Exer0008_Threadstate;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MonitoringThread{
    public static void main(String[] args) {
        RunnableProcess thread1 = new RunnableProcess();
        ShortRunnableProcess P2 = new ShortRunnableProcess();
        Thread thread2 = new Thread(P2);
       
        System.out.println("T1's Default Name: " + thread1.getName());
        System.out.println("T2's Default Name: " + thread2.getName());
        
        thread1.setName("Thread 1");
        thread2.setName("Thread 2");

        System.out.println("T0's Name: " + Thread.currentThread().getName());
        System.out.println("T1's Name: " + thread1.getName());
        System.out.println("T2's Name: " + thread2.getName());
        
        System.out.println( thread1.getName()+ " state Before Calling start() is: " + thread1.getState());
        System.out.println( thread2.getName()+ " state Before Calling start() is: " + thread2.getState());

        // Start the threads
        thread1.start();
        thread2.start();
        System.out.println( thread1.getName()+ " state After Calling start() is: " + thread1.getState());
        System.out.println( thread1.getName()+ " state After Calling start() is: " + thread1.getState());
 
        System.out.println(thread1.getName()+ " is Alive: "+ thread1.isAlive());
        System.out.println(thread2.getName()+ " is Alive: "+ thread2.isAlive());


// Customise the Thread name and print it 
        thread1.setName("T1");
        thread2.setName("T2");
        System.out.println("Thread's Name AFTER changing it: " + thread1.getName());
        System.out.println("Thread's Name AFTER changing it: " + thread2.getName());
 
        // Getting the Unique ID for the Thread
        System.out.println("Thread's Unique ID: " + thread1.getId());
        System.out.println("Thread's Unique ID: " + thread2.getId());
        
        
        try {
            thread1.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MonitoringThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Thread " + thread1.getName() + ": " + thread1.getState());
        
//        while(thread1.isAlive())
//        {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(MonitoringThread.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            System.out.println("Monitor: "+ thread1.getName()+ " state: " + thread1.getState());
//        }
//        System.out.println("Monitor: "+ thread2.getName()+ " state: " + thread2.getState());
        
    }
}