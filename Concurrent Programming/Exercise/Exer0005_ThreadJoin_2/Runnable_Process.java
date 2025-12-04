package Exer0005_Threadjoin_2;
// This tells Java which folder this class belongs to.

import java.util.logging.Level;
import java.util.logging.Logger;
// (These imports are not actually used in this file.)

public class Runnable_Process implements Runnable {
    // This class implements Runnable, meaning:
    // This class only describes the WORK to be done by a thread.
    // The real thread will be created in ThreadJoin.java.


    public void run()
    {
        // Print a message when THIS thread starts running.
        // Example output:
        //   Thread-0 : Started...
        System.out.println(
            Thread.currentThread().getName() + " : Started..."
        );
        

        // --------------------------------------------------------------
        // Step 1: Create SubThread tasks (two Runnable objects)
        // --------------------------------------------------------------
        SubThread P2_1 = new SubThread();  
        SubThread P2_2 = new SubThread();
        // These two objects contain the run() method from SubThread.java.
        // They are simple tasks that just print a message when executed.


        // --------------------------------------------------------------
        // Step 2: Wrap each SubThread task inside a Thread
        // --------------------------------------------------------------
        Thread T4 = new Thread(P2_1);  // Thread that will run P2_1
        Thread T5 = new Thread(P2_2);  // Thread that will run P2_2
        // IMPORTANT:
        // Runnable = the job
        // Thread = the worker
        // So T4 and T5 are the actual workers that will run the SubThread tasks.


        // --------------------------------------------------------------
        // Step 3: Start both sub-threads (T4 and T5)
        // --------------------------------------------------------------
        T4.start();   // T4 runs SubThread.run()
        T5.start();   // T5 runs SubThread.run()
        // Both threads run at the SAME TIME (concurrent execution).
        //
        // Example output:
        //     SubThread is Executed: Thread-2
        //     SubThread is Executed: Thread-3


        // --------------------------------------------------------------
        // Step 4: Wait ONLY for T5 to finish (Join on T5)
        // --------------------------------------------------------------
        try 
        {
            T5.join();
            // join() means:
            //   "This current thread MUST WAIT here until T5 finishes running."
            //
            // VERY IMPORTANT:
            // - The CURRENT thread waiting is the parent thread (Thread-0 or Thread-1...)
            // - T4 is NOT joined, so T4 may finish earlier or later — does not matter.
            //
            // Why this is safe:
            // - T5 is not waiting on anything.
            // - There is no circular waiting.
            // - So NO deadlock risk.

        }
        catch (InterruptedException e) 
        {
            // If someone interrupts the waiting, this will run.
        }


        // --------------------------------------------------------------
        // Step 5: Print final message when the parent thread is done
        // --------------------------------------------------------------
        // This will print only AFTER T5 has finished.
        // T4 may or may not be completed — depends on timing.
        System.out.println(
            Thread.currentThread().getName() + " : Completed!"
        );
    }
}



/*
WHAT HAPPENS
    - When a thread runs this Runnable:
        1. It prints --> Thread-X : Started...
        2. It creates 2 SubThread tasks.
        3. It creates T4 and T5 (two new threads).
        4. It starts T4 and T5 → they run at the same time.
        5. It waits for T5 only using T5.join().
        6. After T5 finishes, it prints --> Thread-X : Completed!



Important Behavior (Beginner Friendly)
    1. T4 does NOT have join()
    → It may finish before or after T5
    → It does NOT block or delay the parent thread

    2. join() on T5 only blocks the parent thread
    → The parent thread will NOT print "Completed!" until T5 is done

    3. NO DEADLOCK
    Because:
        - The parent thread waits for T5
        - T5 does NOT wait for anyone
        - So execution always continues



Example Output (order varies because of concurrency)
- Order can be different because threads run concurrently.

    Thread-0 : Started...
        SubThread is Executed: Thread-2
        SubThread is Executed: Thread-3
    Thread-0 : Completed!
*/