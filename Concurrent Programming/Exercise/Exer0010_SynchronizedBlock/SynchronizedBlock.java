package Exer0010_SynchronizedBlock;

public class SynchronizedBlock {

    public static void main(String[] args) {

        // ---------------------------------------------------------
        // Step 1: Create ONE shared Counter object
        // ---------------------------------------------------------
        // Both threads will use this same Counter.
        // This means BOTH threads will modify the SAME shared variable "count".
        Counter counter = new Counter(); 
        

        // ---------------------------------------------------------
        // Step 2: Create a thread that INCREMENTS the counter
        // ---------------------------------------------------------
        Thread incrementThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                counter.increment();  
                // Calls increment() → adds 1 (or adds 5 times if loop inside)
                //
                // Because this version uses *synchronized BLOCK* inside Counter,
                // only ONE thread enters the critical block at a time.
            }
        });
        

        // ---------------------------------------------------------
        // Step 3: Create a thread that DECREMENTS the counter
        // ---------------------------------------------------------
        Thread decrementThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                counter.decrement();  
                // Calls decrement() → subtracts 1 (or 5 times if loop inside)
                //
                // Also protected by synchronized block inside Counter.
            }
        });
        

        // ---------------------------------------------------------
        // Step 4: Start both threads → run at the SAME TIME
        // ---------------------------------------------------------
        // These two threads run concurrently (overlapping execution).
        // That means both will try to modify the SAME shared "count" variable.
        //
        // Without synchronization, this would cause a RACE CONDITION
        // such as:
        //   - Jumping numbers
        //   - Wrong final count
        //
        // With synchronized BLOCK, only ONE thread can modify the count at a time.
        incrementThread.start();
        decrementThread.start();
        

        // ---------------------------------------------------------
        // Step 5: Make the MAIN thread wait until BOTH threads finish
        // ---------------------------------------------------------
        try {
            incrementThread.join();  // Main waits until increment thread finishes
            decrementThread.join();  // Main waits until decrement thread finishes
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }


        // ---------------------------------------------------------
        // Step 6: Print the FINAL result
        // ---------------------------------------------------------
        // Because incrementThread and decrementThread each run 10 times,
        // and each method performs controlled synchronized updates,
        // the final count SHOULD be correct.
        System.out.println("Final Count: " + counter.getCount());
    }
}


/*
SUMMARY
- You have:
    - One shared counter
    - Two threads:
        - One increases the count
        - One decreases the count

- Because they run at the same time, they can clash (race condition).

- To prevent this, your Counter class uses: Synchronized BLOCK
    synchronized(this) {
        // only ONE thread allowed inside here at a time
    }

- So:
    1. Thread A enters → lock acquired → modify count
    2. Thread B must wait outside
    3. When A finishes → lock released
    4. Then B can enter
- This guarantees the count is updated safely.

*/