package Exer0003_Multithreading_Using_Runnable;
// This tells Java which package/folder this file belongs to.

public class MultiThreading_Using_Runnable {

    public static void main(String[] args) {
        // The main method is the starting point of the program.

        // ---------------------------------------------------------
        // Step 1: Create an instance of the Runnable class
        // ---------------------------------------------------------
        MyRunnableThread myRunnable = new MyRunnableThread();
        // This does NOT create a thread.
        // This ONLY creates the "job" or "task" that the thread should run.
        // The run() method in MyRunnableThread is the actual work.

        // ---------------------------------------------------------
        // Step 2: Create Thread objects AND give them the runnable job
        // ---------------------------------------------------------
        Thread thread1 = new Thread(myRunnable);
        // thread1 will run the run() method from myRunnable in a new thread.

        Thread thread2 = new Thread(myRunnable);
        // thread2 will ALSO run the SAME myRunnable job, but in a separate thread.
        // Both thread1 and thread2 share the SAME Runnable object.
        // This means both threads will execute the exact same run() code.

        // ---------------------------------------------------------
        // Step 3: Start both threads
        // ---------------------------------------------------------
        thread1.start();
        // This tells Java to start running run() in a NEW thread (Thread-0 or Thread-1 depending on the JVM)

        thread2.start();
        // This also starts another NEW thread.
        // Now TWO threads are running at the same time.
        // They may print messages in mixed, random order because threads run concurrently.

        // IMPORTANT:
        // Calling start() will call run() AUTOMATICALLY inside a new thread.
        // DO NOT call run() directly → that will NOT create a thread.
    }
}


/*
What Happens When You Run This?
    - You will see something like:
        Thread is running: Thread-0
        Thread is running: Thread-1
        Thread progress: 1
        Thread progress: 1
        Thread progress: 2
        Thread progress: 2
        ...
        Thread finished: Thread-0
        Thread finished: Thread-1

    - But the order will be mixed because both threads run at the same time.



What You Built
    - You created:
        - One Runnable object → This contains the instructions (the job).
        - Two Thread workers → Both workers run the same job at the same time.


    - Think of it like:
        - One "recipe"
        - Two cooks following the same recipe at the same time
        - Both cooks finish at different times and print progress as they go

*/