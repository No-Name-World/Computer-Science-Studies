package Exer0008_Threadstate;
// This tells Java which folder/package this class belongs to.

public class RunnableProcess extends Thread {
    // This class EXTENDS Thread.
    // That means this object itself IS a thread.
    // We can call start(), getName(), getState(), isAlive(), etc. directly on it.

    @Override
    public void run() {
        // The run() method contains the code that will be executed
        // when we call .start() on a RunnableProcess object.

        try {
            // -----------------------------------------------------
            // First message: when the thread starts running
            // -----------------------------------------------------
            System.out.println(
                Thread.currentThread().getName() 
                + " is Running: " 
                + Thread.currentThread().getState()
            );
            // Thread.currentThread():
            //   Returns the thread that is currently executing this code.
            // getName():
            //   Returns the name of this thread.
            // getState():
            //   Returns its current state (usually RUNNABLE here).
            //
            // Example output:
            //   T1 is Running: RUNNABLE


            // -----------------------------------------------------
            // Put the thread to sleep for 2 seconds
            // -----------------------------------------------------
            Thread.sleep(2000);
            // This makes ONLY THIS THREAD pause for 2000 milliseconds (2 seconds).
            // While this thread is sleeping:
            //   - Its state becomes TIMED_WAITING.
            //   - Other threads (including main, thread2, etc.) can still run.
            //
            // In your MonitoringThread class, you can watch and see
            // thread1's state change during this time.


            // -----------------------------------------------------
            // After waking up from sleep
            // -----------------------------------------------------
            System.out.println(
                Thread.currentThread().getName() 
                + " Slept for 2 Seconds."
            );
            // This message tells us that the thread has finished its 2-second sleep.

            System.out.println(
                Thread.currentThread().getName() 
                + " is awake: " 
                + Thread.currentThread().getState()
            );
            // Now the thread is no longer in TIMED_WAITING.
            // Its state is likely RUNNABLE again (or soon TERMINATED if run() finishes).
            //
            // Example output:
            //   T1 Slept for 2 Seconds.
            //   T1 is awake: RUNNABLE

        } catch (InterruptedException e) {
            // This block will run if the thread gets interrupted
            // while it is sleeping.
            // For example, another thread might call thread1.interrupt().
            System.out.println(
                Thread.currentThread().getName() 
                + " interrupted."
            );
        }

        // When this run() method finishes, the thread's state becomes TERMINATED.
    }
}
