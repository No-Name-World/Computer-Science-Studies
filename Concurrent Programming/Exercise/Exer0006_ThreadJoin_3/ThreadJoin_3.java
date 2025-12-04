package Exer0006_Threadjoin_3;
// This tells Java which folder/package this class belongs to.

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadJoin_3 
{
    public static void main(String[] args) 
    {
        // This message represents the teacher waiting at the classroom door.
        System.out.println("Teacher standing at the class's door. Students are arriving:");
      

        // -------------------------------------------------------------
        // Create an ARRAY that holds 10 Thread objects
        // -------------------------------------------------------------
        // Each thread will represent ONE student.
        Thread[] students = new Thread[10];
        

        // -------------------------------------------------------------
        // Step 1: Create 10 students (as Thread objects)
        // -------------------------------------------------------------
        for (int i = 0; i < students.length; i++)
        {
            // Each student is represented by a StudentBehaviour object,
            // which determines how the student behaves when they "arrive".
            //
            // i+1 makes student numbers start from 1 to 10.
            students[i] = new Thread(new StudentBehaviour(i + 1));
        }
        

        // -------------------------------------------------------------
        // Step 2: Students arrive one by one (sequential arrival)
        // -------------------------------------------------------------
        // IMPORTANT:
        // Because join() is INSIDE this loop,
        // the teacher waits for EACH student to fully arrive
        // before letting the next student come in.
        //
        // This results in SEQUENTIAL arrival.
        for (Thread Std : students)
        {
            Std.start();  
            // Start this student's thread → student "arrives".

            try {
                Std.join();
                // join() means:
                // "Teacher waits here until THIS student finishes arriving."
                //
                // Because join() is inside the loop, the next student
                // will NOT start until the previous student's thread is done.
                //
                // So the arrival order becomes:
                // Student 1 arrives → finish
                // Student 2 arrives → finish
                // ...
                // Student 10 arrives → finish

            } catch (InterruptedException ex) {
                // If the waiting is interrupted, log the error.
                Logger.getLogger(ThreadJoin_3.class.getName())
                      .log(Level.SEVERE, null, ex);
            }
        }

        // After all 10 threads have finished, this message prints:
        System.out.println("All the 10 students are in the class Already!");
    }
}



/*
SUMMARY
    This program simulates:
        - Teacher standing at door → lets students enter ONE BY ONE.

        - Because join() forces:
            Student 1 → finish
            Student 2 → finish
            Student 3 → finish
            ...
            Student 10 → finish

        - This is sequential execution, NOT concurrency.



If join() were removed
    - Then:
        All 10 students would arrive at the same time
        Teacher would NOT wait
        The arrivals would be mixed/random
        Output order would be unpredictable
    
    - This would represent concurrent arrival.
*/