package Exer0007_Threadjoin_4;
// This tells Java which package/folder this class belongs to.

public class GreetingTeacher implements Runnable {
    
    // ---------------------------------------------------------
    // Attribute: stores the student number for this action
    // ---------------------------------------------------------
    int StudentNumber;
    
    // ---------------------------------------------------------
    // Constructor: runs when we create a GreetingTeacher object
    // ---------------------------------------------------------
    GreetingTeacher(int StdNum)
    {
        // Save the student number in this object
        this.StudentNumber = StdNum;
        // Example:
        //   new GreetingTeacher(3)  → StudentNumber = 3
        //   new GreetingTeacher(18) → StudentNumber = 18
    }
    
    @Override
    public void run()
    {
        // ---------------------------------------------------------
        // This thread prints a message that the student is greeting
        // the teacher. This represents Step 1 of the student's sequence.
        // ---------------------------------------------------------
        //
        // CLASS NUMBER CALCULATION (very simple):
        // Students  1–10 → Class 1
        // Students 11–20 → Class 2
        //
        // Formula:
        //   (StudentNumber - 1) / 10
        // gives:
        //    1–10  → 0
        //    11–20 → 1
        // +1 makes them Class 1 or Class 2.
        //
        // "\t" adds a tab so output looks tidy.
        System.out.println(
            "\tClass " 
            + ((int)((this.StudentNumber - 1) / 10) + 1) 
            + " - Student " 
            + this.StudentNumber 
            + ": Greeting Teacher."
        );

        // This is a simple action. After printing, the thread ends.
    }
}



/*
Summary
    - This class represents Step 1 of each student's arrival:
        1. Greeting Teacher
        2. Taking Shoes Off
        3. Sitting Down

    - This specific thread prints: Class X - Student Y: Greeting Teacher.

    - Where:
        X = class number (1 or 2)
        Y = student number (1–20)



How it fits into StudentBehaviour
    - Inside StudentBehaviour.run():
        GreetingTeacher G = new GreetingTeacher(StudentNumber);
        Thread T1 = new Thread(G);
        T1.start();
        T1.join();  // must finish greeting before next step

    - So each student MUST complete this greeting step before moving on.

*/