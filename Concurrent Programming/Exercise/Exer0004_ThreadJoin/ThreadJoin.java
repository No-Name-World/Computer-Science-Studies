package Exer0004_ThreadJoin;
// This tells Java which folder/package this file belongs to.

public class ThreadJoin 
{
    // ---------------------------------------------------------------
    // Shared Attribute (Simple Explanation)
    // ---------------------------------------------------------------
    // This is a shared variable that ALL threads can access and change.
    // Because it is static, every thread sees the same variable.
    public static int sharedCounter = 0;
    
    public static void main(String[] args) 
    {
        // Print the name and ID of the MAIN thread.
        // The main thread is the FIRST thread started by every Java program.
        System.out.println("The Main Thread Name: " + 
                Thread.currentThread().getName() + " : " + 
                Thread.currentThread().getId());
        
        // ---------------------------------------------------------------
        // Step 1: Create ONE Runnable object (the job that threads will run)
        // ---------------------------------------------------------------
        Runnable_Process P1 = new Runnable_Process();
        // This object contains the run() method logic.
        // All threads created below will run the SAME job.

      
        // ---------------------------------------------------------------
        // Step 2: Create Thread objects and give them the job (P1)
        // ---------------------------------------------------------------
        Thread T1 = new Thread(P1);
        Thread T2 = new Thread(P1);
        Thread T3 = new Thread(P1);
        Thread T4 = new Thread(P1);
        // Each thread will run the same work, but independently.

        
        // ---------------------------------------------------------------
        // Step 3: Start Thread-1
        // ---------------------------------------------------------------
        T1.start();  
        // T1 begins executing its run() method in a new separate thread.


        // ---------------------------------------------------------------
        // Step 4: Main thread WAITS for T1 to finish using join()
        // ---------------------------------------------------------------
        try 
        {
            T1.join();  
            // join() means:
            // "Main thread, STOP here and WAIT until T1 finishes running."
            // So the program will not continue until T1 is done.
        }
        catch (InterruptedException e) 
        {
            // If the waiting is interrupted, this block will run.
        }
        

        // ---------------------------------------------------------------
        // Step 5: Start Thread-2 (only after T1 is finished)
        // ---------------------------------------------------------------
        T2.start();
        // Thread-2 begins running.


        // ---------------------------------------------------------------
        // Step 6: Main thread WAITS for T2 to finish
        // ---------------------------------------------------------------
        try 
        {
            T2.join();
            // Main thread waits here until T2 finishes.
            // This forces T1 to run fully → then T2 runs fully → then continue.
        }
        catch (InterruptedException e) 
        {
            // Ignored for this example.
        }


        // ---------------------------------------------------------------
        // Step 7: Start Thread-3 and Thread-4 at the same time
        // ---------------------------------------------------------------
        T3.start();
        T4.start();
        // These two threads run concurrently (at the same time).
        

        // ---------------------------------------------------------------
        // Step 8: Main thread WAITS for both T3 and T4
        // ---------------------------------------------------------------
        try {
            // join both threads
            T3.join();
            T4.join();

            // NOTE ABOUT DEADLOCK (Simple English):
            // A deadlock can happen if:
            // - T3 is waiting for T4 AND 
            // - T4 is waiting for T3 
            // at the same time.
            //
            // In this example, it DOES NOT occur because:
            // - Main thread is the only one doing the waiting
            // - Threads T3 and T4 are NOT waiting for each other
            //
            // But if threads were waiting for each other, it would freeze forever.
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // ---------------------------------------------------------------
        // Step 9: Main thread ends after all threads have finished
        // ---------------------------------------------------------------
        System.out.println("Main Thread is Terminated!");
        // By this point:
        // - T1 finished
        // - T2 finished
        // - T3 finished
        // - T4 finished
        // And the main thread prints its final message.
    }   
}


/*
Summary of What This Program Does
    1. Start Thread 1
    2. Main thread waits → until Thread 1 finishes
    3. Start Thread 2
    4. Main thread waits → until Thread 2 finishes
    5. Start Thread 3 and Thread 4 at the same time
    6. Main thread waits → until both are finished
    7. Then main thread prints final message and ends



Why Do We Use join()?
    - Without join(), all threads run randomly.
    - With join(), you force a specific order:
        T1 → finish  
        T2 → finish  
        T3 + T4 (together) → finish  
        Main thread ends

    - join() is like saying: “I will not move on until this thread is done.”
*/