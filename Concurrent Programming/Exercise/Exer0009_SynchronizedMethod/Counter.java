/*
    - We want to create a counter that increases and decreases.
    - We want that Every time the counter starts to increase, it has to finish increasing 5 times.
    - We want that Every time the counter starts to Decrease, it has to finish Decreasing 5 times.
*/

package Exer0009_SynchronizedMethod;

public class Counter {
     private int count = 0; // Shared variable
    
    // Synchronized method to increment the count
    public synchronized void increment() {
        for (int i=0;i<5;i++)
        {
            count++; // Critical section
            System.out.println("Increased: " + count);
        }
    }
    
    // Synchronized method to decrement the count
    public synchronized void decrement() {
        for (int i=0;i<5;i++)
        {
            count--; // Critical section
            System.out.println("Decreased: " + count);
        }
    }
    
    // Method to retrieve the current count
    public int getCount() {
        return count;
    }
}
