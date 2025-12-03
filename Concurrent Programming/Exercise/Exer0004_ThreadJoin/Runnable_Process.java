package Exer0004_ThreadJoin;

import static Exer0004_ThreadJoin.ThreadJoin.sharedCounter;

public class Runnable_Process implements Runnable{
    
    public void run()
    {
        sharedCounter++;
        System.out.println(Thread.currentThread().getName()  + " : " 
          + Thread.currentThread().getId()+ " is Executing Statement #" + 
                sharedCounter);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) 
          {
          }
        }    
    }