package Exer0004_ThreadJoin;
// This tells Java which package/folder this file belongs to.

import static Exer0004_ThreadJoin.ThreadJoin.sharedCounter;
// This line imports the sharedCounter so we can use it directly here.
// sharedCounter is a static variable, meaning it is shared by ALL threads.
// All threads see and modify the SAME number.

public class Runnable_Process implements Runnable {
    // We use "implements Runnable" which means:
    // This class provides a run() method that will be executed by a thread.
    // The actual thread object will be created in ThreadJoin.java

    public void run() 
    {
        // ---------------------------------------------------------
        // Step 1: Increase the shared counter by 1
        // ---------------------------------------------------------
        sharedCounter++;
        // Because sharedCounter is SHARED among all threads:
        // Every time a thread enters this run() method, it increases the counter.
        // Example:
        // First thread → counter becomes 1
        // Second thread → counter becomes 2
        // Third thread → counter becomes 3
        // ...
        // NOTE: Without synchronization, threads may read/write at the same time,
        //       causing inconsistent results in real-world applications.
        //       But for this example, it's okay because it's only demonstration.

        
        // ---------------------------------------------------------
        // Step 2: Print which thread is currently executing
        // ---------------------------------------------------------
        System.out.println(
            Thread.currentThread().getName()  + " : " 
          + Thread.currentThread().getId() + " is Executing Statement #" 
          + sharedCounter
        );
        // Thread.currentThread().getName():
        //     This prints the name of the thread (e.g., Thread-0, Thread-1)
        //
        // Thread.currentThread().getId():
        //     This prints the unique ID number of the thread.
        //
        // sharedCounter:
        //     This shows which "statement number" is being executed
        //     based on how many threads entered run().
        //
        // Example output:
        //   Thread-0 : 21 is Executing Statement #1
        //   Thread-1 : 22 is Executing Statement #2
        //   Thread-2 : 23 is Executing Statement #3
        //
        // NOTE:
        // Because join() was used in the main program, 
        // the order will be controlled:
        // T1 then T2 then T3 and T4 (together).


        // ---------------------------------------------------------
        // Step 3: Sleep (Pause the thread)
        // ---------------------------------------------------------
        try {
            Thread.sleep(500);
            // Make the thread pause for 500 milliseconds (0.5 seconds).
            // This simulates some work being done.
            // Other threads continue running while this one sleeps.

        } catch (InterruptedException ex) {
            // If this thread gets interrupted during sleep,
            // this block will run. For now, we ignore it.
        }
    }    
}



/*
Summary of What This Class Does
    - When a thread runs this run() method:
        1. It increases sharedCounter by 1
        2. It prints its name, ID, and the updated counter
        3. It sleeps for 0.5 seconds
        4. Then it finishes



How It Works With ThreadJoin.java
    - Because the main program uses:
        T1.join();
        T2.join();
        T3.join();
        T4.join();

    - Your execution order becomes:
        1. T1 runs → prints
        2. T2 runs → prints
        3. T3 and T4 run together → prints in random order
        4. Main method finishes
*/
