package Exer0007_Threadjoin_4;
// This tells Java which folder/package this class belongs to.

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadJoin_4 
{
    public static void main(String[] args) 
    {
        // Teacher's announcement before students begin arriving
        System.out.println("Teachers are standing at their classes' doors. Students are arriving:");
      

        // -------------------------------------------------------------
        // Step 1: Create two ARRAYS of threads, one for each classroom
        // -------------------------------------------------------------
        // Class 1 has 10 students → will use stdQueue_1
        // Class 2 has 10 students → will use stdQueue_2
        Thread[] stdQueue_1 = new Thread[10];
        Thread[] stdQueue_2 = new Thread[10];
        

        // -------------------------------------------------------------
        // Step 2: Fill stdQueue_1 with Threads for Students 1–10
        // -------------------------------------------------------------
        for (int i = 0; i < stdQueue_1.length; i++)
        {
            // StudentBehaviour controls the student's actions:
            // greet → remove shoes → sit down
            stdQueue_1[i] = new Thread(new StudentBehaviour(i + 1));
            // Example:
            // i = 0 → student #1
            // i = 1 → student #2
            // ...
            // i = 9 → student #10
        }
        

        // -------------------------------------------------------------
        // Step 3: Fill stdQueue_2 with Threads for Students 11–20
        // -------------------------------------------------------------
        for (int i = 0; i < stdQueue_2.length; i++)
        {
            // Student numbers continue from 11 to 20
            stdQueue_2[i] = new Thread(new StudentBehaviour(i + 11));
        }


        // -------------------------------------------------------------
        // Step 4: Students from BOTH classes arrive in parallel
        // -------------------------------------------------------------
        // The loop runs 10 times:
        //   At each i:
        //     - One student from Class 1 arrives
        //     - One student from Class 2 arrives
        //
        // They both start their activity sequence.
        // BUT join() forces each pair to completely finish
        // before moving to the next pair.
        //
        // This simulates:
        //    Class 1, Student #i finishes
        //    Class 2, Student #i finishes
        // THEN the next pair arrives.
        for (int i = 0; i < 10; i++)
        {
            // -----------------------------
            // Class 1 student arrives
            // -----------------------------
            stdQueue_1[i].start();

            // -----------------------------
            // Class 2 student arrives
            // -----------------------------
            stdQueue_2[i].start();
            

            // ---------------------------------------------------------
            // VERY IMPORTANT:
            // Wait for BOTH students (one from each class)
            // to finish ALL their activities.
            //
            // join() means:
            //   "The main thread MUST WAIT here until THIS student finishes."
            //
            // So:
            //   - Student 1 (Class 1) finishes fully
            //   - Student 11 (Class 2) finishes fully
            // THEN the next pair begins.
            // ---------------------------------------------------------
            try {
                stdQueue_1[i].join();
                stdQueue_2[i].join();

                // This creates TWO synchronised queues:
                // Class 1 → student #1 → student #2 → ... → student #10
                // Class 2 → student #11 → student #12 → ... → student #20
                //
                // Each pair completes BEFORE the next pair begins.
            } 
            catch (InterruptedException ex) {
                // Ignore interruptions in this example.
            }
        }


        // -------------------------------------------------------------
        // Step 5: All students are seated → main thread prints this
        // -------------------------------------------------------------
        System.out.println("All the 20 students are in their seats Already!");
        // This prints ONLY when ALL 20 threads have finished.
    }
}



/*
SUMMARY
- You have 2 classes, each with 10 students:
    - Class 1 students: 1 → 10
    - Class 2 students: 11 → 20


- Each student does:
    1. greet teacher
    2. take off shoes
    3. sit down
    - All controlled by join().


- The main thread:
    - starts one student from Class 1
    - starts one student from Class 2
    - waits for BOTH to finish
    - then moves to the next pair


- So the arrival pattern is:
    (1, 11) → finish
    (2, 12) → finish
    (3, 13) → finish
    ...
    (10, 20) → finish

- Only after all 20 students finish: All the 20 students are in their seats Already!

*/