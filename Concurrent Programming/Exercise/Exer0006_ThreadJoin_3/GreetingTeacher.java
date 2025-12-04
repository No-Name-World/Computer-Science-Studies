package Exer0006_Threadjoin_3;
// This tells Java which package/folder this file belongs to.

public class GreetingTeacher implements Runnable {
    
    // ---------------------------------------------------------
    // Attribute: stores which student is performing this action
    // ---------------------------------------------------------
    int StudentNumber;
    
    // ---------------------------------------------------------
    // Constructor: sets the student number when creating this object
    // ---------------------------------------------------------
    GreetingTeacher(int StdNum)
    {
        // Save the student number inside this object
        this.StudentNumber = StdNum;
        // Example:
        // new GreetingTeacher(4) → StudentNumber = 4
    }
    
    @Override
    public void run()
    {
        // ---------------------------------------------------------
        // THIS is the action of greeting the teacher.
        // Whenever a thread runs this Runnable, this message prints.
        // ---------------------------------------------------------
        //
        // \t is a tab indent for nicer formatting.
        System.out.println(
            "\tStudent " + this.StudentNumber + ": Greeting Teacher."
        );

        // There is no delay or extra logic here.
        // Once the message prints, the thread finishes this task.
    }
}




/*
Explanation
    - This class represents Step 1 of a student arriving:
        1. Greet teacher → Thread T1
        2. Take off shoes → Thread T2
        3. Sit down → Thread T3
    
    - Each of these steps runs as a separate thread inside the StudentBehaviour class.



How this works inside StudentBehaviour
    - Inside StudentBehaviour.run():
        GreetingTeacher GT = new GreetingTeacher(this.StudentNumber);
        Thread T1 = new Thread(GT);
        T1.start();
        T1.join();   // student MUST greet teacher first

    - So each student:
        ✔ Greets the teacher
        ✔ Then takes off shoes
        ✔ Then sits down

    - All controlled through threads + join().
*/