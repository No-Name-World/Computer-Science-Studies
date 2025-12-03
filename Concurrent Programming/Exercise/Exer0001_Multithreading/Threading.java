package Exer0001_Multithreading;  // This tells Java the folder/group name of this file.
// A "package" helps organize your files inside a project.

public class Threading extends Thread {  
    // This class is named "Threading".
    // The keyword "extends Thread" means we are creating our own custom thread.
    // A thread = a small, separate path of execution (something that runs at the same time as main program).
    // By extending Thread, we are saying "this class can run code in a separate thread".

    @Override   // This means we are replacing the original run() method from the Thread class.
    public void run()
    {
        // The run() method contains the instructions that the thread will execute.
        // When we call start() from another place, the run() method will run automatically in a new thread.
        // Think of run() as: "What should this thread do?"

        for(int i = 0; i < 5; i++)  
        {
            // This for-loop will repeat 5 times: i = 0, 1, 2, 3, 4
            // Each loop, we print the thread name and the number i.
            // This helps us see that the thread is running multiple times.

            System.out.println(this.getName() + ":" + i);
            // this.getName() gets the name of the current thread (e.g., Thread-0).
            // Printing this lets you see which thread is running.
            // ":" + i shows the current count.

            try {
                this.sleep(1000);
                // sleep(1000) tells the thread to pause for 1000 milliseconds (1 second).
                // This is useful for showing the output slowly.
                // Only the thread pauses, not the entire program.

            } catch (InterruptedException ex) {
                // This catch block runs if something interrupts the sleeping thread.
                // For example, another thread could interrupt it.
                // In this case, we do nothing, but normally you might handle the error.
            }
        }

        // When the loop ends, the run() method ends.
        // After run() ends, the thread will finish and stop automatically.
    }
}




/*
What is the process when this thread runs?
    1. You create an object of the class Threading.

    2. You call .start() on that object.
    → This tells Java: “Create a new thread and run the run() method inside it.”

    3. The new thread begins and enters the run() method.

    4. It runs the for loop 5 times.

    5. Each time it:
        - prints the thread name and number
        - sleeps for 1 second

    6. After finishing all 5 loops, the thread stops.
*/