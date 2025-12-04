package Exer0006_Threadjoin_3;
// This tells Java which folder/package this class belongs to.

import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentBehaviour implements Runnable
{
    // -------------------------------------------------------------
    // Attribute: stores which student number this object represents
    // -------------------------------------------------------------
    private int StudentNumber;
    
    // -------------------------------------------------------------
    // Constructor: sets the student number when creating the object
    // -------------------------------------------------------------
    public StudentBehaviour(int studentNumber)
    {
        this.StudentNumber = studentNumber;
    }

    public void run()
    {        
        // This prints when it is THIS student's turn to behave.
        System.out.println("Student " + this.StudentNumber + "'s turn has arrived.");

        // -------------------------------------------------------------
        // Step 1: Create the tasks (Runnables) for this student
        // -------------------------------------------------------------
        // Each task represents ONE action the student must do.
        GreetingTeacher GT = new GreetingTeacher(this.StudentNumber); // Student greets the teacher
        ShoesOff SH = new ShoesOff(this.StudentNumber);               // Student takes off shoes
        SittingDown SD = new SittingDown(this.StudentNumber);         // Student sits down in class
        
        // -------------------------------------------------------------
        // Step 2: Turn each task into a Thread
        // -------------------------------------------------------------
        Thread T1 = new Thread(GT);  // This thread will run the "greet teacher" action
        Thread T2 = new Thread(SH);  // This thread will run the "shoes off" action
        Thread T3 = new Thread(SD);  // This thread will run the "sitting down" action
        
        // -------------------------------------------------------------
        // Step 3: Start T1 (greeting) and WAIT until it finishes
        // -------------------------------------------------------------
        T1.start();  
        try {
            T1.join();  // Student must finish greeting before doing anything else
        } catch (InterruptedException ex) {
            Logger.getLogger(StudentBehaviour.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // -------------------------------------------------------------
        // Step 4: Start T2 (shoes off) and WAIT until it finishes
        // -------------------------------------------------------------
        T2.start();
        try {
            T2.join();  // Student must finish removing shoes before sitting
        } catch (InterruptedException ex) {
            Logger.getLogger(StudentBehaviour.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // -------------------------------------------------------------
        // Step 5: Start T3 (sit down) and WAIT until it finishes
        // -------------------------------------------------------------
        T3.start();
        try {
            T3.join();  // Student completes the final action
        } catch (InterruptedException ex) {
            Logger.getLogger(StudentBehaviour.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // After T3 finishes, THIS student's behaviour is complete.
        // Control returns to ThreadJoin_3, where the next student arrives.
    }    
}




/*
EXPLANATION OF THE WHOLE PROCESS
    - Each student does 3 things in order:
        1. Greet teacher
        2. Take off shoes
        3. Sit down

    - Each action is run inside its own thread:
        Thread T1 → greet
        Thread T2 → remove shoes
        Thread T3 → sit down

    - Because you use join() after each start:
        T1 must finish → then T2 starts  
        T2 must finish → then T3 starts  
        T3 must finish → student is ready

    - This is perfect sequential ordering, controlled by threads.



How this fits into the main class
    - ThreadJoin_3 runs:
        Student 1 enters → performs all 3 steps → finishes
        Student 2 enters → performs all 3 steps → finishes
        …
        Student 10 enters → finishes

    - Then: All the 10 students are in the class Already!
*/