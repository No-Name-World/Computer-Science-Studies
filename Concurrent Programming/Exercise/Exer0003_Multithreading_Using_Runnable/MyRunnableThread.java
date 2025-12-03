package Exer0003_Multithreading_Using_Runnable;
// This tells Java which folder/package this file belongs to.

// IMPORTANT NOTE (Simple English):
// This example uses "Runnable" instead of extending Thread.
// Runnable is another way to create threads in Java.
// Runnable = describes the job a thread should do.
// Thread = the actual worker that executes the job.

public class MyRunnableThread implements Runnable {
    // By writing "implements Runnable", we are saying:
    // "This class promises to provide a run() method."
    // Runnable does NOT create a thread by itself.
    // It only defines the WORK that a thread will do.
    // A separate Thread object is needed to run this work.

    // ---------------------------------------------------
    // Implement the run() method required by Runnable
    // ---------------------------------------------------
    @Override
    public void run() {
        // The run() method contains the task/job the thread will run.
        // When we pass this object into a Thread and call start(),
        // Java will execute THIS run() method in a new thread.

        // ***SOME RUNNABLE PROCESS***
        // This is just a comment showing that this is where the work begins.

        // Print the name of the thread that is running this code.
        // Thread.currentThread() = which thread is currently executing.
        System.out.println("Thread is running: " + Thread.currentThread().getName());

        // ---------------------------------------------------
        // Simulate some work being done inside the thread
        // ---------------------------------------------------
        // We use a for-loop to repeat a task 5 times (i = 1 to 5)
        for (int i = 1; i <= 5; i++) {
            try {
                // Pause this thread for 1 second (1000 ms)
                // This simulates real work (e.g., loading, calculation)
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                // If the thread is interrupted while sleeping,
                // this block will run.
                // We simply print a message to show the interruption.
                System.out.println("Thread interrupted: " + Thread.currentThread().getName());
            }

            // Show the step/progress number
            // Example: "Thread progress: 1", "Thread progress: 2", ...
            System.out.println("Thread progress: " + i);
        }

        // ---------------------------------------------------
        // This prints when the thread finishes all its work
        // ---------------------------------------------------
        System.out.println("Thread finished: " + Thread.currentThread().getName());
    }
}



/*
How Runnable Works
    - Runnable = job description
    - Thread = worker

    - To run this class in a thread, you must do:
        MyRunnableThread obj = new MyRunnableThread();   // create the job
        Thread t1 = new Thread(obj);                     // give the job to a thread worker
        t1.start();                                      // start the thread

    - This means:
        Runnable = “What to do”
        Thread = “Who will do it”
*/