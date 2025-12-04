package Exer0005_Threadjoin_2;
// This tells Java which folder/package this class belongs to.

public class ThreadJoin 
{
    // ---------------------------------------------------------
    // Shared Attribute (seen by all threads)
    // ---------------------------------------------------------
    public static int sharedCounter = 0;
    // This number is shared among all threads.
    // Each time a thread runs, it might increase this number.
    // Because it's static → all threads see the SAME variable.

    
    public static void main(String[] args) 
    {
        // Print the main thread's name and ID.
        // Every Java program always starts with ONE main thread.
        System.out.println(
            "The Main Thread Name: " 
            + Thread.currentThread().getName() 
            + " : " 
            + Thread.currentThread().getId()
        );
        

        // ---------------------------------------------------------
        // Step 1: Create ONE Runnable job (P1)
        // ---------------------------------------------------------
        Runnable_Process P1 = new Runnable_Process();
        // This object has the run() method that our threads will execute.
        // All threads will run the SAME task.


        // ---------------------------------------------------------
        // Step 2: Create threads that will run the same job
        // ---------------------------------------------------------
        Thread T1 = new Thread(P1);   // Thread #1
        Thread T2 = new Thread(P1);   // Thread #2
        Thread T3 = new Thread(P1);   // Thread #3
        

        // ---------------------------------------------------------
        // Step 3: Start T1 (Thread #1)
        // ---------------------------------------------------------
        T1.start();   // T1 begins running immediately


        // ---------------------------------------------------------
        // Step 4: Wait for T1 to FINISH before moving on
        // ---------------------------------------------------------
        try 
        {
            T1.join();  
            // join() makes the MAIN thread WAIT here.
            // Meaning:
            // Main thread PAUSES until T1 completely finishes.
            //
            // After T1 is done → main thread continues.
        }
        catch (InterruptedException e) 
        {
            // If the waiting is interrupted, this block runs.
        }
        

        // ---------------------------------------------------------
        // Step 5: Start T2 (Thread #2) ONLY AFTER T1 has finished
        // ---------------------------------------------------------
        T2.start();   


        // ---------------------------------------------------------
        // Step 6: Main thread waits for T2 to finish
        // ---------------------------------------------------------
        try 
        {
            T2.join();  
            // Main thread stays here until T2 finishes.
            // So the order so far:
            // T1 (finish) → T2 (finish)
        }
        catch (InterruptedException e) 
        {
            // ignored
        }


        // ---------------------------------------------------------
        // Step 7: Start T3 (Thread #3)
        // ---------------------------------------------------------
        T3.start();  
        // T3 now runs.
        // Unlike the previous exercise, we ONLY use ONE join() here,
        // so there is NO risk of deadlock.


        // ---------------------------------------------------------
        // Step 8: Wait for T3 to finish (ONLY ONE JOIN)
        // ---------------------------------------------------------
        try {
            // NOTE (Simple English):
            // Deadlock happens when:
            // - Thread A waits for Thread B
            // - Thread B waits for Thread A
            // - Both get stuck forever
            //
            // In this example, we wait ONLY for T3.
            // T3 is NOT waiting for anything.
            // So we are SAFE — no deadlock here!
            T3.join();  

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // ---------------------------------------------------------
        // Step 9: Main thread ends AFTER all threads are done
        // ---------------------------------------------------------
        System.out.println("Main Thread is Terminated!");
        // At this point:
        // - T1 finished
        // - T2 finished
        // - T3 finished
        // Now the program ends cleanly.
    }
}




/*
- This program forces the threads to run in this order:
    T1 → finish  
    T2 → finish  
    T3 → finish  
    Main thread ends


- Because:
    T1.join() → Main thread waits for T1
    T2.join() → Main thread waits for T2
    T3.join() → Main thread waits for T3


- All threads run one-by-one, in order.
- There is NO concurrency here.
- Each thread runs only after the previous one finishes.
- This is SEQUENTIAL execution using join().
*/