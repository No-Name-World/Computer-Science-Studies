package Exer0007_Threadjoin_4;
// This tells Java which package/folder this class belongs to.

import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentBehaviour implements Runnable
{
    // ---------------------------------------------------------
    // Attribute: stores which student number this object represents
    // ---------------------------------------------------------
    private int StudentNumber;
    
    // ---------------------------------------------------------
    // Constructor: sets the student number
    // ---------------------------------------------------------
    public StudentBehaviour(int studentNumber)
    {
        this.StudentNumber = studentNumber;
    }

    public void run()
    {        
        // ---------------------------------------------------------
        // Print which student is arriving AND which class they belong to.
        //
        // Formula (Simple English):
        // Students 1–10 → Class 1
        // Students 11–20 → Class 2
        //
        // (StudentNumber - 1) / 10:
        //   1–10 → 0
        //   11–20 → 1
        // +1 to convert from 0/1 to Class 1/Class 2
        // ---------------------------------------------------------
        System.out.println(
            "Student " + this.StudentNumber 
            + "'s turn has arrived to Class " 
            + ((int)((this.StudentNumber - 1) / 10) + 1)
        );


        // ---------------------------------------------------------
        // Step 1: Create the tasks (Runnables)
        // ---------------------------------------------------------
        // These Runnables represent the 3 actions a student must perform.
        // Each student performs these actions IN ORDER:
        //   1. Greet teacher
        //   2. Take off shoes
        //   3. Sit down
        GreetingTeacher G = new GreetingTeacher(this.StudentNumber);
        ShoesOff SH       = new ShoesOff(this.StudentNumber);
        SittingDown S     = new SittingDown(this.StudentNumber);
        

        // ---------------------------------------------------------
        // Step 2: Wrap each Runnable inside its own Thread
        // ---------------------------------------------------------
        // Thread T1 → greeting action
        // Thread T2 → removing shoes
        // Thread T3 → sitting down
        Thread T1 = new Thread(G);
        Thread T2 = new Thread(SH);
        Thread T3 = new Thread(S);
        

        // ---------------------------------------------------------
        // Step 3: Start T1 (Greeting Teacher) and WAIT until finished
        // ---------------------------------------------------------
        T1.start();  
        try {
            T1.join();  // Must greet teacher FIRST
        } 
        catch (InterruptedException ex) {
            Logger.getLogger(StudentBehaviour.class.getName())
                  .log(Level.SEVERE, null, ex);
        }
        

        // ---------------------------------------------------------
        // Step 4: Start T2 (Shoes Off) and WAIT until finished
        // ---------------------------------------------------------
        T2.start();
        try {
            T2.join();  // Must take shoes off NEXT
        } 
        catch (InterruptedException ex) {
            Logger.getLogger(StudentBehaviour.class.getName())
                  .log(Level.SEVERE, null, ex);
        }
        

        // ---------------------------------------------------------
        // Step 5: Start T3 (Sitting Down) and WAIT until finished
        // ---------------------------------------------------------
        T3.start();
        try {
            T3.join();  // Must sit down LAST
        } 
        catch (InterruptedException ex) {
            Logger.getLogger(StudentBehaviour.class.getName())
                  .log(Level.SEVERE, null, ex);
        }

        // After this, the student is fully settled in class.
        // The main thread (from ThreadJoin_4) can now allow the next pair of students.
    }    
}



/*
Summary (for learners)
    - Each student does these 3 actions in order, each using its own thread:
        1. Greetings Teacher → must finish
        2. Taking Shoes Off → must finish
        3. Sitting Down → must finish

    - Because each action uses join():
        T1 → finish  
        T2 → finish  
        T3 → finish  

    - Only after T3 finishes → the student is fully seated.



How this fits into ThreadJoin_4
    - ThreadJoin_4:
        - Starts student from Class 1
        - Starts student from Class 2
        - Waits for BOTH to finish all 3 actions
        - Then moves to next pair

    So the program acts like two synchronized queues:
        Student 1  + Student 11  → finish  
        Student 2  + Student 12  → finish  
        ...  
        Student 10 + Student 20 → finish  

*/