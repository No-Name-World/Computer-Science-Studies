package Exer0007_Threadjoin_4;
// This tells Java which folder/package this class belongs to.

public class ShoesOff implements Runnable {

    // ---------------------------------------------------------
    // Attribute: stores the student number
    // ---------------------------------------------------------
    int StudentNumber;
    
    // ---------------------------------------------------------
    // Constructor: runs when we create a ShoesOff object
    // ---------------------------------------------------------
    ShoesOff(int StdNum)
    {
        // Save the student number inside this object
        this.StudentNumber = StdNum;
        // Example:
        //   new ShoesOff(8)  → StudentNumber = 8
        //   new ShoesOff(15) → StudentNumber = 15
    }
    
    @Override
    public void run()
    {
        // ---------------------------------------------------------
        // This is the action of the student taking their shoes off.
        // It runs inside a separate thread created in StudentBehaviour.
        // ---------------------------------------------------------

        // Determine the student's class number using simple math:
        // (StudentNumber - 1) / 10:
        //   1–10  → 0
        //   11–20 → 1
        // +1 converts these to Class 1, Class 2, etc.
        //
        // The "\t" creates a tab space for nicer, indented output.
        System.out.println(
            "\tClass " 
            + ((int)((this.StudentNumber - 1) / 10) + 1)
            + " - Student " 
            + this.StudentNumber
            + ": Taking Shoes Off."
        );

        // After printing, this thread finishes immediately.
        // No extra processing happens here.
    }
}



/*
Summary
    - This class represents Step 2 of each student's arrival:
        1. Greet teacher
        2. Take shoes off ← (THIS CLASS)
        3. Sit down

    - Each student's “take shoes off” step runs in its own thread, and StudentBehaviour uses join() to ensure:
        Step 1 → finish  
        Step 2 → finish  
        Step 3 → finish  

    - Before moving to the next student.



Class number logic (easy explanation)
    - Students 1–10 → Class 1
    - Students 11–20 → Class 2
    - Formula: ((StudentNumber - 1) / 10) + 1

*/