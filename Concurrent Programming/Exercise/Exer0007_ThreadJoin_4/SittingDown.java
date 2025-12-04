package Exer0007_Threadjoin_4;
// This tells Java which folder/package this class belongs to.

public class SittingDown implements Runnable {

    // ---------------------------------------------------------
    // Attribute: stores the student number doing this action
    // ---------------------------------------------------------
    int StudentNumber;
    
    // ---------------------------------------------------------
    // Constructor: runs when we create a SittingDown object
    // ---------------------------------------------------------
    SittingDown(int StdNum)
    {
        // Save the student number in this object
        this.StudentNumber = StdNum;
        // Example:
        // new SittingDown(14) → StudentNumber = 14
    }

    @Override
    public void run()
    {
        // ---------------------------------------------------------
        // This method contains the behaviour executed by the thread.
        // When a thread runs this, it prints that the student is now
        // sitting down in their class.
        // ---------------------------------------------------------
        //
        // ((StudentNumber - 1) / 10) + 1
        // SIMPLE EXPLANATION:
        //   Students  1–10 → Class 1
        //   Students 11–20 → Class 2
        //   Students 21–30 → Class 3 (if existed)
        //
        // The "\t" adds indentation to format the output nicely.
        System.out.println(
            "\tClass " 
            + ((int)((this.StudentNumber - 1) / 10) + 1) 
            + " - Student " 
            + this.StudentNumber 
            + ": Sitting Down."
        );

        // There is no delay or extra processing.
        // Once this message prints, this thread's work is completed.
    }
}



/*
Summary
    - This class represents the final step of a student's arrival process.

    - The student has already:
        1. greeted the teacher
        2. taken off shoes
    
    - Now they sit down inside their class.
    - The thread prints: Class X - Student Y: Sitting Down.

    - Where:
        X = class number
        Y = student number



How This Fits Into StudentBehaviour
    - Inside StudentBehaviour.run():
        SittingDown S = new SittingDown(this.StudentNumber);
        Thread T3 = new Thread(S);
        T3.start();
        T3.join();

    - So each student:
        1. Greets teacher
        2. Removes shoes
        3. Sits down (this class)
    
    - All in the correct order because of join().
*/