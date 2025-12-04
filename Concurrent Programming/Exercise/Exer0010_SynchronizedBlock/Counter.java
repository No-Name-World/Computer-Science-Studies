/*
    - We want to create a counter that increases and decreases.
    - When a thread starts INCREASING, it must finish 5 increases without interruption.
    - When a thread starts DECREASING, it must finish 5 decreases without interruption.
    - This means the INCREASE block and DECREASE block must be "locked" while running.
*/

package Exer0010_SynchronizedBlock;

public class Counter {

    // ---------------------------------------------------------
    // Shared variable
    // ---------------------------------------------------------
    private int count = 0; 
    // Both threads (incrementThread & decrementThread) use this SAME variable.
    // This creates a "critical section" that must be protected.


    // ---------------------------------------------------------
    // increment() using a synchronized BLOCK
    // ---------------------------------------------------------
    public void increment() {

        // synchronized(this) → LOCKS this Counter object
        //
        // Only ONE thread can enter this block at a time.
        // If a thread is already inside this block:
        //     - All other threads MUST WAIT outside.
        //
        // This ensures:
        //     - The 5-step increment loop runs FULLY
        //       before another thread can touch “count”.
        synchronized (this) {

            for (int i = 0; i < 5; i++) {
                count++;   // Critical section
                System.out.println("Increased: " + count);
            }

            // When the block ends, the lock is automatically released.
        }

        // This message prints AFTER the thread leaves the synchronized block.
        // This means the lock has been released and another thread may enter.
        System.out.println("OUT OF INCREMENT SYNCHRONIZATION.");
    }


    // ---------------------------------------------------------
    // decrement() using a synchronized BLOCK
    // ---------------------------------------------------------
    public void decrement() {

        // Again, synchronized(this) locks the SAME object (this Counter).
        //
        // Meaning:
        //   - If increment() is running, decrement() MUST WAIT.
        //   - If decrement() is running, increment() MUST WAIT.
        //
        // Ensures the entire 5-step decrement happens safely.
        synchronized (this) {

            for (int i = 0; i < 5; i++) {
                count--;   // Critical section
                System.out.println("Decreased: " + count);
            }

            // Once loop finishes, the lock is released.
        }

        System.out.println("OUT OF DECREMENT SYNCHRONIZATION.");
        // Prints AFTER leaving the locked block.
    }


    // ---------------------------------------------------------
    // Simple getter to read the current count
    // ---------------------------------------------------------
    public int getCount() {
        // Reading the value is safe, so no synchronization needed here.
        return count;
    }
}


/*
SUMMARY (for beginners)
    - You have:
        - One shared number → count
        - Two threads:
            - One increases count
            - One decreases count

    - Both want to modify the same number.
    - This creates a race condition if not protected.


Synchronized(this)
    - This LOCKS the Counter object so that:
        - Only ONE thread can increase OR decrease at a time
        - No mixing of operations
        - Each thread completes its 5-step loop without interruption
    - So the output is always correct and predictable.


Difference between synchronized Method vs synchronized Block
    - Synchronized Method = Synchronized Block
        - Lock is applied to the whole method = Lock is only applied to the code block we choose
        - Less flexible = More flexible
        - Cleaner & shorter = Can lock specific parts, not whole method
        - Example: public synchronized void increment() = Example: synchronized(this) { ... }
    
    - Both prevent race conditions.

*/