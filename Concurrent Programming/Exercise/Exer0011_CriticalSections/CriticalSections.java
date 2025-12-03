/*
    In this example of Critical sections, We want to transfer $100 from the MyAccount's account to the Lazada Account's account then eventually to the Seller's Account.
    If we do not use 'synchronization' for the Critical Sections, then an error might happen in the results due to theinterleaving of multiple threads or processes.
    This is called Race condition, which occurs when two or more threads or processes access shared data or resources in an uncontrolled manner

    In the example, The MyAccount got $ 500, Lazada Account's account got $ 1000, and The daughter got $ 0.
    SUPPOSEDLY, afte the execution of the 4 threads, the final balance for the My accout should be 400, The Lazada 1000 and the Seller's 100;

    If we keep running the program for few times, we will see the balance of the Lazada Account's Account might be $ 1100, which is WRONG!
    The error happened because of the wrong execution sequence WITHIN the threads, specifically within the Critical Sections.

    So we need to makethe processes ISOLATED by the use of Synchronization or Locks.
*/
package criticalsections;

import Exer0012_CriticalSections_Full_Syncronization.Credit_Process;
import Exer0012_CriticalSections_Full_Syncronization.Debit_Process;
import java.util.ArrayList;

public class CriticalSections 
{
    public static ArrayList<Float> Accounts = new ArrayList<>();
    
    public static void main(String[] args) 
    {
        // Index 0 - My Accout
        Accounts.add((float)500);
        // Index 1 - Lazada Account's Accout
        Accounts.add((float)1000);
        // Index 2 - Seller's Accout
        Accounts.add((float)0);
                
        // Transfer from My Account (Number 0) to Lazada's Account (Number 1).
        Thread Debit_1 = new Thread(new Debit_Process(0,(float)100));
        Thread Credit_1 = new Thread(new Credit_Process(1,(float)100));
        
        // Transfer from Lazada's Account (Number 1) to Seller's Account (Number 2)
        Thread Debit_2 = new Thread(new Debit_Process(1,(float)100));
        Thread Credit_2 = new Thread(new Credit_Process(2,(float)100));
        
        // running the threads
        Debit_1.start();
        
        // IF Sequence is Important: Making Sure that the Threads will be executed in Sequence.
        try {
                Debit_1.join();
            } 
        catch (InterruptedException ex) 
        {
        }
        
        Credit_1.start();
        // IF Sequence is Important: Making Sure that the Threads will be executed in Sequence.
        try {
                Credit_1.join();
            } 
        catch (InterruptedException ex) 
        {
        }

        Debit_2.start();
        
        // IF Sequence is Important: Making Sure that the Threads will be executed in Sequence.
        try {
                Debit_2.join();
            } 
        catch (InterruptedException ex) 
        {
        }

        Credit_2.start();
        
    }
    
}
