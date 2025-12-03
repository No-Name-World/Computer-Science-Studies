package Exer0001_Multithreading;  // This tells Java which package/folder this class belongs to.

public class MultiThreading_Sequential {

    public static void main(String[] args) 
    {
        // The main method is the starting point of the program.

        Threading thread_1 = new Threading();
        Threading thread_2 = new Threading();
        // We create two objects from the Threading class.
        // But IMPORTANT: these objects will NOT run as real threads
        // because we will NOT use .start() in this example.

        thread_1.setName("T1");  // Give thread_1 the name "T1".
        thread_2.setName("T2");  // Give thread_2 the name "T2".
        // These names are only for printing (they help us see which is which).

        // ---------------------------------------------------------
        //  Sequential Execution (VERY SIMPLE EXPLANATION)
        // ---------------------------------------------------------
        // "Sequential" means the code runs ONE AFTER ANOTHER.
        // There is NO concurrency and NO overlapping execution.
        // The second run() will ONLY start AFTER the first run() finishes.
        // This is because we are calling run() directly.
        //
        // IMPORTANT NOTE:
        // thread_1.run() does NOT start a new thread.
        // It just behaves like a normal method call inside main().
        // It runs fully → finishes → then thread_2.run() starts.
        //
        // This is NOT multithreading.
        // This is normal, single-thread execution (only the main thread is running).
        // ---------------------------------------------------------

        thread_1.run();  // This calls run() like a normal function. It does NOT create a new thread.
                         // The entire loop inside run() will finish here first.
                         // Only after finishing, the next line will run.

        thread_2.run();  // This starts ONLY AFTER thread_1.run() finishes.
                         // So the output will show all T1 lines first, then all T2 lines.
    }
}
