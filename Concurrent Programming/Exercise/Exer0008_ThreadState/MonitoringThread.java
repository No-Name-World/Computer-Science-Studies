package Exer0008_Threadstate;

// Importing logging tools to print error messages if something goes wrong
import java.util.logging.Level;
import java.util.logging.Logger;

// This class contains the main() method.
// It is used to MONITOR the state (life cycle) of threads.
public class MonitoringThread {
    public static void main(String[] args) {

        // ---------------------------------------------------------
        // Create the thread objects
        // ---------------------------------------------------------

        RunnableProcess thread1 = new RunnableProcess();
        // NOTE (important):
        // RunnableProcess is most likely a class that EXTENDS Thread.
        // That is why we can call .start(), .getName(), .getState(), etc. on it.
        // This object represents a LONGER running thread.

        ShortRunnableProcess P2 = new ShortRunnableProcess();
        // This is probably a class that IMPLEMENTS Runnable.
        // It only defines the JOB to be done by the thread, not the thread itself.

        Thread thread2 = new Thread(P2);
        // Here we wrap the Runnable (P2) inside a Thread object.
        // Now thread2 is a real Thread that can be started with .start().


        // ---------------------------------------------------------
        // Thread NAMES (default names before we change them)
        // ---------------------------------------------------------

        System.out.println("T1's Default Name: " + thread1.getName());
        // This prints the default name assigned by Java to thread1.
        // Example: "Thread-0"

        System.out.println("T2's Default Name: " + thread2.getName());
        // This prints the default name assigned by Java to thread2.
        // Example: "Thread-1"


        // ---------------------------------------------------------
        // Change the thread names to something more meaningful
        // ---------------------------------------------------------

        thread1.setName("Thread 1");
        thread2.setName("Thread 2");
        // setName() lets us give more readable names to threads.
        // This is useful in debugging and monitoring.


        // ---------------------------------------------------------
        // Print the names of the main thread and the two worker threads
        // ---------------------------------------------------------

        System.out.println("T0's Name: " + Thread.currentThread().getName());
        // This prints the name of the CURRENT thread, which is the MAIN thread.
        // By default, the main thread's name is usually "main".

        System.out.println("T1's Name: " + thread1.getName());
        System.out.println("T2's Name: " + thread2.getName());
        // These print the new names we just assigned:
        // "Thread 1" and "Thread 2".


        // ---------------------------------------------------------
        // Check the state of the threads BEFORE starting them
        // ---------------------------------------------------------
        // Thread states (simple version):
        // NEW          → Created but not started yet
        // RUNNABLE     → Started and may be running
        // BLOCKED      → Waiting to acquire a lock
        // WAITING      → Waiting indefinitely for another thread
        // TIMED_WAITING→ Waiting for a specific time (e.g., sleep)
        // TERMINATED   → Finished

        System.out.println(thread1.getName()
                + " state Before Calling start() is: " + thread1.getState());
        // At this point, thread1 has been created but not started.
        // So its state should be NEW.

        System.out.println(thread2.getName()
                + " state Before Calling start() is: " + thread2.getState());
        // Same for thread2 – it has not been started yet, so state should be NEW.


        // ---------------------------------------------------------
        // Start both threads so they begin running their run() methods
        // ---------------------------------------------------------

        // Start the threads
        thread1.start();
        thread2.start();
        // After calling start(), the threads move from NEW → RUNNABLE.
        // They will run their run() methods in parallel with the main thread.


        // ---------------------------------------------------------
        // Check their state right AFTER starting
        // ---------------------------------------------------------
        System.out.println(thread1.getName()
                + " state After Calling start() is: " + thread1.getState());
        System.out.println(thread1.getName()
                + " state After Calling start() is: " + thread1.getState());
        // NOTE:
        // These two lines both print the state of thread1.
        // Probably the second line was meant to print thread2's state, for example:
        //   System.out.println(thread2.getName()
        //           + " state After Calling start() is: " + thread2.getState());
        // But we are leaving the code as it is, just explaining it.
        //
        // Also note:
        // Right after start(), the state may be RUNNABLE or sometimes
        // it might quickly change into TIMED_WAITING if the thread is sleeping.
        // Thread states can change very fast.


        // ---------------------------------------------------------
        // Check if the threads are still alive
        // ---------------------------------------------------------
        System.out.println(thread1.getName() + " is Alive: " + thread1.isAlive());
        System.out.println(thread2.getName() + " is Alive: " + thread2.isAlive());
        // isAlive() returns:
        //   true  → thread has been started and has not finished yet
        //   false → thread has not started yet or has already finished


        // ---------------------------------------------------------
        // Change the thread names again (custom short names)
        // ---------------------------------------------------------

        // Customise the Thread name and print it 
        thread1.setName("T1");
        thread2.setName("T2");
        // Now the threads will be known as "T1" and "T2".
        // This may help simplify monitoring output.

        System.out.println("Thread's Name AFTER changing it: " + thread1.getName());
        System.out.println("Thread's Name AFTER changing it: " + thread2.getName());
        // These show the NEW custom names.


        // ---------------------------------------------------------
        // Print the unique ID of each thread
        // ---------------------------------------------------------
        // Each thread has a unique numeric ID assigned by the JVM.
        // It never changes for that thread.
        System.out.println("Thread's Unique ID: " + thread1.getId());
        System.out.println("Thread's Unique ID: " + thread2.getId());


        // ---------------------------------------------------------
        // Wait for thread1 to finish by using join()
        // ---------------------------------------------------------
        try {
            thread1.join();
            // join() makes the MAIN thread wait until thread1 finishes.
            // After this point, thread1 should be in TERMINATED state.
        } catch (InterruptedException ex) {
            Logger.getLogger(MonitoringThread.class.getName())
                  .log(Level.SEVERE, null, ex);
        }
        
        // Print the final state of thread1 (it should be TERMINATED now)
        System.out.println("Thread " + thread1.getName() + ": " + thread1.getState());


        // ---------------------------------------------------------
        // Monitor loop: keep checking thread1's state while it is alive
        // ---------------------------------------------------------
        // NOTE:
        // Because we already called join() above, by the time we reach this loop,
        // thread1 is probably already FINISHED (TERMINATED).
        // So in real execution, this while loop may not run at all.
        //
        // But logically, this loop is an example of how you can continuously
        // monitor a thread's state while it is still alive.
        while (thread1.isAlive())
        {
            try {
                Thread.sleep(100);
                // Sleep 100ms before checking again.
                // This avoids printing too many lines too quickly.
            } catch (InterruptedException ex) {
                Logger.getLogger(MonitoringThread.class.getName())
                      .log(Level.SEVERE, null, ex);
            }

            System.out.println("Monitor: " + thread1.getName()
                    + " state: " + thread1.getState());
            // This would repeatedly print the current state of thread1
            // (RUNNABLE, TIMED_WAITING, TERMINATED, etc.) until it finishes.
        }


        // ---------------------------------------------------------
        // Finally, print the state of thread2
        // ---------------------------------------------------------
        System.out.println("Monitor: " + thread2.getName()
                + " state: " + thread2.getState());
        // By this time, thread2 has also likely finished and should be TERMINATED.
        // If thread2 does longer work, it might still be RUNNABLE or WAITING,
        // depending on what ShortRunnableProcess does.
    }
}