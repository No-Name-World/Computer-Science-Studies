package Exer0005_Threadjoin_2;
// This tells Java which package/folder this class belongs to.

public class SubThread implements Runnable
{
    // This class implements Runnable,
    // meaning this class describes the WORK that a thread will do.
    // Runnable = only the job/task. It does NOT create a thread by itself.

    @Override
    public void run()
    {
        // The run() method contains the code that will run inside the thread.
        // When we create a Thread object and pass this SubThread object to it,
        // the Thread object will execute THIS method on start().

        System.out.println(
            "\tSubThread is Executed: " 
            + Thread.currentThread().getName()
        );
        // \t = a tab space for nicer formatting.
        //
        // Thread.currentThread().getName():
        //     This prints the name of the currently running thread.
        //
        // Example output might look like:
        //     SubThread is Executed: Thread-0
        //     SubThread is Executed: Thread-1
        //
        // This helps us see which specific thread is running this code.
    }
}



/*
Simple Explanation
    - This class does ONE thing: When a thread runs it, it prints
        - “SubThread is Executed: [thread name]”
        - That’s all.
        - It is a tiny Runnable that prints a message.



How it is usually used:
- Example:
    SubThread job = new SubThread();  // Create the job
    Thread t = new Thread(job);       // Give the job to a thread
    t.start();                        // Start the thread → run() executes

- Output: SubThread is Executed: Thread-0
*/