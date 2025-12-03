package Exer0007_Threadjoin_4;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadJoin_4 
{
    public static void main(String[] args) 
    {
        System.out.println("Teachers are standing at their classes' doors. Students are arriving:");
      
        // For each class, Creating an Array that can Take 10 Threads
        Thread[] stdQueue_1 = new Thread[10];
        Thread[] stdQueue_2 = new Thread[10];
        
        // Creating Threads for stdQueue_1
        for (int i = 0; i < stdQueue_1.length; i++)
        {
            stdQueue_1[i] = new Thread(new StudentBehaviour(i+1));
        }
        
        // Creating Threads for stdQueue_2
        for (int i = 0; i < stdQueue_2.length; i++)
        {
            stdQueue_2[i] = new Thread(new StudentBehaviour(i+11));
        }
        
        // Students are arriving
        for (int i=0;i<10;i++)
        {
            // A Student of Class_1 Arrives to the queue and ready to start his activities.
            stdQueue_1[i].start();
            // A Student of Class_2 Arrives to the queue and ready to start his activities.
            stdQueue_2[i].start();
            
            // Each Student, must be waited to finish ALL his activities .
            try {
                stdQueue_1[i].join();
                stdQueue_2[i].join();
            } catch (InterruptedException ex) {
            }
        }
        
        // Only when ALL students finish their activities, this statement will be printed on screen.
        System.out.println("All the 20 students are in their seats Already!");
  
    }
    
}
