package Exer0002_Multithreading;  
// This tells Java which package/folder this file belongs to.

public class MultiThreading_Concurrent {

    public static void main(String[] args) {
        // The main method is the starting point of the program.

        Threading thread_1 = new Threading(1);
        Threading thread_2 = new Threading(2);
        // We create two Threading objects.
        // Threading(1) → This will become "Thread #1"
        // Threading(2) → This will become "Thread #2"
        // REMEMBER: Thread #2 will intentionally crash (fake error) inside run().

        // ---------------------------------------------------------
        // Creating more threads using a loop
        // ---------------------------------------------------------
        // This loop will create threads with numbers 3, 4, and 5.
        // i = 3, 4, 5 → total 3 threads
        // For each value of i:
        //    we create a new Threading(i)
        //    then immediately start() it → thread begins running
        // ---------------------------------------------------------

        for (int i = 3; i < 6; i++)   // Loop runs for i = 3, 4, 5
        {
            Threading thread = new Threading(i);  
            // This creates a thread with the number i (3, 4, 5).

            thread.start();  
            // This starts the thread immediately.
            // Each thread will run its own run() method separately.
            // All these threads run CONCURRENTLY (overlapping).
        }   

        // ---------------------------------------------------------
        // Concurrent Execution of Thread #1 and Thread #2
        // ---------------------------------------------------------
        // We now start Thread #1 and Thread #2.
        // These will run at the SAME TIME as threads #3, #4, and #5 above.
        // So total threads running = 5 threads:
        //    Thread #1
        //    Thread #2 (this one will CRASH on purpose)
        //    Thread #3
        //    Thread #4
        //    Thread #5
        // ---------------------------------------------------------

        thread_1.start();   // Starts Thread #1 → runs its run() method in a new thread.
        thread_2.start();   // Starts Thread #2 → runs its run() method but will throw RuntimeException.

        // IMPORTANT:
        // When Thread #2 crashes (fake RuntimeException), ONLY Thread #2 stops.
        // The other threads (#1, #3, #4, #5) keep running normally because threads are independent.
    }
}



/*
What happens when you run this program?
    - You will have 5 threads running concurrently:
        Thread Number	Status
        Thread #1	Runs normally
        Thread #2	Crashes on purpose (fake RuntimeException)
        Thread #3	Runs normally
        Thread #4	Runs normally
        Thread #5	Runs normally



Example Output (NOT exact, because thread order is random)
    0 from Thread #3
    0 from Thread #1
    0 from Thread #2
    Exception in thread "Thread-1" java.lang.RuntimeException
    0 from Thread #4
    0 from Thread #5
    1 from Thread #3
    1 from Thread #1
    ...

    - The output order is mixed, because threads run concurrently (at the same time).



WHY does Thread #2 crash but others continue?
    Because:
        - Each thread runs independently
        - One thread crashing does NOT stop the others
        - Exactly like multiple workers doing their own jobs → even if one worker suddenly stops, the others continue
        - This is called thread isolation.
*/