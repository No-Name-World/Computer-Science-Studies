package Exer0006_Threadjoin_3;

import java.util.logging.Level;
import java.util.logging.Logger;


public class StudentBehaviour implements Runnable
{
    // Attributes
    private int StudentNumber;
    
    // Constructor
    public StudentBehaviour(int studentNumber)
    {
        this.StudentNumber = studentNumber;
    }

    public void run()
    {        
        System.out.println("Student " + this.StudentNumber + "'s turn has arrived.");

        // Runnables
        GreetingTeacher GT = new GreetingTeacher(this.StudentNumber);
        ShoesOff SH = new ShoesOff(this.StudentNumber);
        SittingDown SD = new SittingDown(this.StudentNumber);
        
        // Threads
        Thread T1 = new Thread(GT);
        Thread T2 = new Thread(SH);
        Thread T3 = new Thread(SD);
        
        T1.start();
        try {
            T1.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(StudentBehaviour.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        T2.start();
        try {
            T2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(StudentBehaviour.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        T3.start();
        try {
            T3.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(StudentBehaviour.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
