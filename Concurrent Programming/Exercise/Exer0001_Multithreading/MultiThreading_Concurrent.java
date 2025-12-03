package Exer0001_Multithreading;  // This tells Java which folder/package this file belongs to.

public class MultiThreading_Concurrent {

    public static void main(String[] args) 
    {
        // This is the main method.
        // This method is the "starting point" of the entire Java program.
        // When you click RUN, Java will start executing from here.

        Threading thread_1 = new Threading();
        Threading thread_2 = new Threading();
        // Here we are creating TWO thread objects.
        // thread_1 and thread_2 are both using the Threading class you created earlier.
        // Each object represents a separate thread that can run at the same time.

        thread_1.setName("T1");
        thread_2.setName("T2");
        // We give each thread a name.
        // This makes the printed output easier to understand.
        // When they run, they will show "T1:0", "T1:1", "T2:0", "T2:1", etc.
        // These names help you see which thread is printing.

        // ----------------------------
        // Concurrent Execution (Simple English)
        // "Concurrent" means the two threads run in an overlapping way.
        // They BOTH start running almost at the same time.
        // Java's thread scheduler decides which one prints first.
        // Sometimes T1 prints first, sometimes T2 prints first.
        // The order is NOT guaranteed — that's what concurrency means.
        // ----------------------------

        thread_1.start();  // This starts thread_1 (it will run its run() method on a new thread)
        thread_2.start();  // This starts thread_2 (it will also run run() in another thread)
        // IMPORTANT:
        // start() creates a new thread in memory and then calls run() automatically.
        // DO NOT call run() manually — that will not create a new thread.
        // Using start() makes both threads run at the SAME TIME.
    }
}




/*
What happens when you run this program?
    1. The main method starts.
    2. You create 2 thread objects: thread_1 and thread_2.
    3. You give them names: T1 and T2.
    4. You call .start() for both threads.
    5. Java creates 2 separate threads.
    6. Both threads start running their run() method almost at the same time.
    7. The output will be mixed, like this:
        T1:0
        T2:0
        T1:1
        T2:1
        ...

    8. The order is random because both threads run concurrently.



Why do threads behave like this?
    - Because the CPU is deciding which thread runs at each moment.
    - Java does NOT guarantee which thread prints first.
*/