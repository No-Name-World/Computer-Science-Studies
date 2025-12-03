package Exer0005_Threadjoin_2;

public class ThreadJoin 
{
    // Attributes
    public static int sharedCounter =0;
    
    public static void main(String[] args) 
    {
        System.out.println("The Main Thread Name: " + Thread.currentThread().getName() + " : " + Thread.currentThread().getId());
        // Creaating the Thread Objects
        Runnable_Process P1 = new Runnable_Process();
      
        Thread T1 = new Thread(P1);
        Thread T2 = new Thread(P1);
        Thread T3 = new Thread(P1);
        
        // Start both threads
        T1.start();
        
        // Join point for T1
        try 
        {
            T1.join();
        }
        catch (InterruptedException e) 
        {
        }
        
        T2.start();

        // Join point for T2
        try 
        {
            T2.join();
        }
        catch (InterruptedException e) 
        {
        }

        T3.start();

        try {
            // NOTE: Adding two joining points, might lead to DEADLOCK.
            // Keeping ONLY ONE Join point is Suggested.
            T3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Main Thread is Terminated!");
    }
    
}
