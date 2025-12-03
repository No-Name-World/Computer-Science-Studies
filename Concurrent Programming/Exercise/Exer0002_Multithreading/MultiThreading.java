package Exer0002_Multithreading;

public class MultiThreading {
    public static void main(String[] args) {
        
        Threading thread_1 = new Threading(1);
        Threading thread_2 = new Threading(2);
        
        for (int i=3;i<6;i++)
        {
            Threading thread = new Threading(i);
            thread.start();
        }   
        
        // Concurrent Execution
        thread_1.start();
        thread_2.start();

    }
    
}
