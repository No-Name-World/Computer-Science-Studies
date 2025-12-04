package Exer0006_Threadjoin_3;
// This tells Java which package/folder this file belongs to.

public class SittingDown implements Runnable {
    // ---------------------------------------------------------
    // Attribute: stores the student number for this action
    // ---------------------------------------------------------
    int StudentNumber;
    
    // ---------------------------------------------------------
    // Constructor: called when we create a SittingDown object
    // ---------------------------------------------------------
    SittingDown(int StdNum)
    {
        // Save the student number inside this object
        this.StudentNumber = StdNum;
        // Example:
        // new SittingDown(5) → StudentNumber = 5
    }

    @Override
    public void run()
    {
        // ---------------------------------------------------------
        // This is the action the student performs:
        // "Sitting Down"
        // ---------------------------------------------------------
        //
        // When a Thread runs this run() method,
        // it prints that the student is now sitting down.
        //
        // The "\t" is a tab to indent the message nicely.
        System.out.println(
            "\tStudent " + this.StudentNumber + ": Sitting Down."
        );

        // This action is simple and finishes immediately.
        // When run() ends, the thread completes its job.
    }
}


/*
Explanation
    - This class represents Step 3 of a student entering the classroom:
        1. Greet teacher → T1
        2. Take off shoes → T2
        3. Sit down → THIS class (T3)

    - Each student runs this class in their own thread as part of the StudentBehaviour.



How this action fits in the full flow
    - From StudentBehaviour.java:
        SittingDown SD = new SittingDown(this.StudentNumber);
        Thread T3 = new Thread(SD);
        T3.start();
        T3.join();

    - Meaning:
        1. Create the sitting action for this student
        2. Put the action into a thread
        3. Start the thread
        4. Wait for it to finish before moving to the next student
*/