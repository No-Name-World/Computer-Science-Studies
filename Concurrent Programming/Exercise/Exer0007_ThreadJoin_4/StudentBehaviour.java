package Exer0007_Threadjoin_4;

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
        System.out.println("Student " + this.StudentNumber + "'s turn has arrived to Class " + ((int)((this.StudentNumber-1)/10)+1));

        // Runnables
        GreetingTeacher G = new GreetingTeacher(this.StudentNumber);
        ShoesOff SH = new ShoesOff(this.StudentNumber);
        SittingDown S = new SittingDown(this.StudentNumber);
        
        // Threads
        Thread T1 = new Thread(G);
        Thread T2 = new Thread(SH);
        Thread T3 = new Thread(S);
        
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
