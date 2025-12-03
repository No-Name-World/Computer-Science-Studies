// 1 - Blowing one thread will NOT affect the other threads.
//     Meaning: if one thread crashes or has a runtime error,
//     the other threads will continue running normally.
//     This is because each thread runs independently.

// 2 - Even if the main program (main thread) blows up with a runtime error,
//     the already running threads will NOT be affected.
//     They are already running separately in their own "path of execution".

package Exer0002_Multithreading;  
// This tells Java which package/folder this class belongs to.

import java.util.logging.Level;
import java.util.logging.Logger;
// These imports are not used in this example, but normally
// they are used for logging errors or messages instead of printing.

public class Threading extends Thread {
    // We create a class named Threading.
    // It extends Thread, meaning it becomes a thread capable of running code independently.
    // Each object of this class represents one thread.

    // ---------------------
    // Attributes (Variables)
    // ---------------------
    private int ThreadNumber;  
    // This variable stores the number of the thread.
    // Example: Thread #1, Thread #2, Thread #3...
    // We use it to identify which thread is printing or crashing.

    // ---------------------
    // Constructor
    // ---------------------
    public Threading(int threadNumber)
    {
        // This constructor is called when we create a new Threading object.
        // The number we pass in (threadNumber) is stored inside ThreadNumber.
        this.ThreadNumber = threadNumber;    
        // Example: new Threading(2) → ThreadNumber = 2
    }

    @Override
    public void run()
    {
        // The run() method is what the thread will execute.
        // When we call .start(), Java will call this run() method automatically
        // in a NEW thread (a separate path).
        // Each thread will run its own copy of this method.

        for(int i = 0; i < 5; i++)
        {
            // This loop will run 5 times: i = 0,1,2,3,4

            System.out.println(i + " from Thread #" + this.ThreadNumber);
            // This prints the current counter AND which thread it came from.
            // Example output: "0 from Thread #1", "1 from Thread #2", etc.

            // ------------------------------------------------------
            // IMPORTANT: Simulating a FAKE ERROR for Thread #2 only
            // ------------------------------------------------------
            // Here we are pretending that Thread 2 experiences a problem.
            // So when ThreadNumber == 2, we throw a runtime error.
            // This will crash ONLY Thread #2.
            // Thread #1, Thread #3, etc. will continue normally.

            if(this.ThreadNumber == 2)
                throw new RuntimeException();
            // This immediately stops Thread #2.
            // But other threads are NOT stopped because each thread runs independently.
            // This demonstrates "thread isolation".

            try {
                this.sleep(1000);
                // sleep(1000) pauses ONLY this thread for 1 second.
                // Other threads continue running.
                // This helps you clearly see the output step-by-step.

            } catch (InterruptedException ex) {
                // This block runs if someone interrupts the sleeping thread.
                // In this example, we ignore it.
            }
        }

        // When the loop finishes, the thread ends naturally
        // (unless it crashes earlier due to the fake RuntimeException).
    }
}


/*
What is happening?
    - You create multiple threads: Thread #1, Thread #2, Thread #3…
    - Each thread runs the run() method at the same time.
    - Thread #2 purposely throws a fake RuntimeException.
    - Thread #2 crashes and stops.
    - But Thread #1 and Thread #3 continue running normally.


Why do the other threads NOT stop when Thread #2 crashes?
    Because:
        - Each thread runs on its own independent “path”.
        - One path crashing does NOT destroy the others.
        - They are not connected.
        - They share the same program, but not the same execution flow.


It’s like:
    - If one worker in a factory goes home sick,
    - the other workers continue working.
*/