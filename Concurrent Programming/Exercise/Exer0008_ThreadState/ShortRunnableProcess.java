package Exer0008_Threadstate;
// This tells Java which package/folder this class belongs to.

public class ShortRunnableProcess implements Runnable {
    // This class IMPLEMENTS Runnable.
    // That means this class is NOT a thread by itself.
    // It only describes the WORK / JOB that a thread should do.
    //
    // In MonitoringThread.java:
    //    new Thread(new ShortRunnableProcess());
    // is used to create the actual thread.

    @Override
    public void run()
    {
        // ---------------------------------------------------------
        // This is the ONLY action this Runnable performs.
        // It prints a simple message and immediately finishes.
        // ---------------------------------------------------------

        System.out.println(
            Thread.currentThread().getName() +
            ": Short Runnable Process been Fully Executed."
        );
        // Thread.currentThread().getName():
        //     gets the name of the thread that is executing this code.
        //
        // Example output:
        //     Thread 2: Short Runnable Process been Fully Executed.
        //
        // After printing this message, this Runnable is DONE.
        // The thread running it will then enter TERMINATED state.
    }
}


/*
Summary
- ShortRunnableProcess is a tiny task that:
    ✔ prints a message
    ✔ finishes instantly
    ✔ does NOT sleep
    ✔ does NOT loop
    ✔ does NOT block


- It is used in MonitoringThread to:
    - show how different threads have different states
    - demonstrate how quickly a Runnable can finish
    - compare a SHORT process vs a LONG process (RunnableProcess)
*/