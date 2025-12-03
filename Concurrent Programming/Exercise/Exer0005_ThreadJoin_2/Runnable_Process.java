package Exer0005_Threadjoin_2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Runnable_Process implements Runnable{
    
    public void run()
    {
        System.out.println(Thread.currentThread().getName()  + " : Started...");
        
        // Creaating the Thread Objects
        SubThread P2_1 = new SubThread();
        SubThread P2_2 = new SubThread();
    
        Thread T4 = new Thread(P2_1);
        Thread T5 = new Thread(P2_2);
    
        T4.start();
        T5.start();
        // Join point for T1
        try 
        {
            T5.join();
        }
        catch (InterruptedException e) 
        {
        }
        System.out.println(Thread.currentThread().getName()  + " : " +"Completed!");
    }
}

