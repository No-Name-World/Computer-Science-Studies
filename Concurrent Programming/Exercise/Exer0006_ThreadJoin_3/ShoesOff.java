package Exer0006_Threadjoin_3;
// This tells Java which package/folder this file belongs to.

public class ShoesOff implements Runnable {
    
    // ---------------------------------------------------------
    // Attribute: stores which student is performing this action
    // ---------------------------------------------------------
    int StudentNumber;
    
    // ---------------------------------------------------------
    // Constructor: called when this ShoesOff object is created
    // ---------------------------------------------------------
    ShoesOff(int StdNum)
    {
        // Save the student's number inside this object
        this.StudentNumber = StdNum;
        // Example:
        // new ShoesOff(3) → StudentNumber = 3
    }
    
    @Override
    public void run()
    {
        // ---------------------------------------------------------
        // This method contains the actual action to be executed
        // when a thread runs this ShoesOff task.
        // ---------------------------------------------------------
        //
        // The \t is just a tab for nicer indentation in the output.
        System.out.println(
            "\tStudent " + this.StudentNumber + ": Taking Shoes Off."
        );

        // This is a simple action that finishes immediately.
        // When run() ends, the thread completes this task.
    }
}


/*
Explanation
    - This class represents Step 2 of each student's process:
        1. Greet teacher → Thread T1
        2. Take off shoes → Thread T2 (THIS CLASS)
        3. Sit down → Thread T3
    
    - Each student runs the ShoesOff task inside a thread as part of the StudentBehaviour.



How this is used in StudentBehaviour
    - Inside StudentBehaviour.run():
        ShoesOff SH = new ShoesOff(this.StudentNumber);
        Thread T2 = new Thread(SH);
        T2.start();
        T2.join();

    - So for each student:
        1. Create the “take shoes off” action
        2. Put it inside Thread T2
        3. Start Thread T2
        4. Wait until the student finishes taking off shoes
        5. Move on to sitting down
*/