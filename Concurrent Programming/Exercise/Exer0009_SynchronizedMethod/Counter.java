/*
    - We want to create a counter that increases and decreases.
    - Each time a thread calls increment(), it must increase the counter 5 TIMES IN A ROW.
    - Each time a thread calls decrement(), it must decrease the counter 5 TIMES IN A ROW.
    - No other thread is allowed to interrupt or enter the counter while
      one thread is doing its full 5-step increment or 5-step decrement.
*/

package Exer0009_SynchronizedMethod;

public class Counter {

    // ---------------------------------------------------------
    // Shared variable that BOTH threads will modify
    // ---------------------------------------------------------
    private int count = 0; 
    // Because it is SHARED, this variable is a CRITICAL resource.
    // More than one thread will try to change it at the same time.


    // ---------------------------------------------------------
    // Synchronized METHOD → increment the counter 5 times
    // ---------------------------------------------------------
    public synchronized void increment() {
        // synchronized means:
        //   Only ONE thread can run THIS METHOD at a time.
        //   If another thread tries to call increment() or decrement(),
        //   it will be forced to WAIT.
        //
        // This guarantees:
        //   - The full 5-step increment happens WITHOUT interruption.
        //   - No race condition.
        //   - No thread switches in the middle of the loop.

        for (int i = 0; i < 5; i++) {
            count++;  // Critical section: modifying shared data
            System.out.println("Increased: " + count);
        }
        // When the loop finishes, the lock is released
        // so other threads can enter increment() or decrement().
    }


    // ---------------------------------------------------------
    // Synchronized METHOD → decrement the counter 5 times
    // ---------------------------------------------------------
    public synchronized void decrement() {
        // synchronized again ensures:
        //   - A thread that calls decrement() MUST finish all 5 decreases
        //     before any other thread can touch 'count'.
        //   - Prevents interleaving like:
        //       T1: decrease
        //       T2: increase
        //       T1: decrease
        //       T2: increase
        //     (This is NOT allowed because we want 5 decreases in a row)

        for (int i = 0; i < 5; i++) {
            count--;  // Critical section
            System.out.println("Decreased: " + count);
        }
        // After finishing 5 steps, the lock is released.
    }


    // ---------------------------------------------------------
    // Method to get the current value of the counter
    // ---------------------------------------------------------
    public int getCount() {
        // This method is NOT synchronized because it only reads,
        // and reading the value is safe.
        return count;
    }
}


/*
SUMMARY
- Two threads run at the same time:
    - 1 thread repeatedly calls increment()
    - 1 thread repeatedly calls decrement()

- Each method MUST:
    ✔ Lock the counter
    ✔ Perform 5 full steps (increase or decrease)
    ✔ Unlock the counter

- This is why we use:
    public synchronized void increment()
    public synchronized void decrement()


- Otherwise, threads might interfere like:
    T1: +1
    T2: -1
    T1: +1
    T2: -1
    - Which is NOT allowed according to your requirement.

*/