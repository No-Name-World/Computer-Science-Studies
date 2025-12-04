package Exer0009_SynchronizedMethod;

public class SynchronizedMethod {

    public static void main(String[] args) {
        
        // ---------------------------------------------------------
        // Step 1: Create ONE shared Counter object
        // ---------------------------------------------------------
        // This single Counter will be accessed by BOTH threads.
        // Without synchronization, both threads may update the count
        // at the SAME TIME → causing incorrect results.
        Counter counter = new Counter(); 
        

        // ---------------------------------------------------------
        // Step 2: Create a thread that INCREMENTS the counter 10 times
        // ---------------------------------------------------------
        Thread incrementThread = new Thread(() -> {
            // A lambda expression (short way to write a Runnable)
            for (int i = 0; i < 10; i++) {
                counter.increment(); 
                // Increases the shared count by 1
                // This method MUST be synchronized to avoid race conditions.
            }
        });
        

        // ---------------------------------------------------------
        // Step 3: Create another thread that DECREMENTS the counter 10 times
        // ---------------------------------------------------------
        Thread decrementThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                counter.decrement(); 
                // Decreases the shared count by 1
                // Also MUST be synchronized to avoid race conditions.
            }
        });
        

        // ---------------------------------------------------------
        // Step 4: Start both threads
        // ---------------------------------------------------------
        // Now BOTH threads run at the SAME TIME (concurrent execution).
        // One adds +1 repeatedly, the other adds -1 repeatedly.
        incrementThread.start();
        decrementThread.start();
        

        // ---------------------------------------------------------
        // Step 5: Wait for BOTH threads to finish their work
        // ---------------------------------------------------------
        try {
            incrementThread.join();  // Wait until incrementThread is done
            decrementThread.join();  // Wait until decrementThread is done
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // ---------------------------------------------------------
        // Step 6: Print the FINAL count after both threads finish
        // ---------------------------------------------------------
        // Ideally:
        // Increment 10 times (+10)
        // Decrement 10 times (-10)
        // FINAL should be 0
        // But this is ONLY correct if the methods are synchronized.
        System.out.println("Final Count: " + counter.getCount());
    }
}


/*
EXPLANATION
- You have:
    - One shared Counter object
    - Two threads
        - One adds +1 ten times
        - One subtracts -1 ten times

    - So expected result: (+1 * 10) + (-1 * 10) = 0


- But threads run at the same time, so:
    ❌ If not synchronized → race condition
    ❌ Wrong final count (e.g., 1, -2, 3, etc.)

    ✔ If synchronized → safe updates
    ✔ Final count will be exactly 0
*/