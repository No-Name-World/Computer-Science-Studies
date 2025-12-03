package Exer0009_SynchronizedMethod;
public class SynchronizedMethod {

    public static void main(String[] args) {
         Counter counter = new Counter(); // Create a Counter object
        
        // Create multiple threads to increment and decrement the count
        Thread incrementThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                counter.increment(); // Increment the count
            }
        });
        
        Thread decrementThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                counter.decrement(); // Decrement the count
            }
        });
        
        // Start the threads
        incrementThread.start();
        decrementThread.start();
        
        // Wait for the threads to finish
        try {
            incrementThread.join();
            decrementThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Display the final count
        System.out.println("Final Count: " + counter.getCount());
    }
    
}
