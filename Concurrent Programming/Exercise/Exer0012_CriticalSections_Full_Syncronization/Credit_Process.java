package Exer0012_CriticalSections_Full_Syncronization;

import static Exer0012_CriticalSections_Full_Syncronization.CriticalSections.Accounts;
// This lets us use "Accounts" directly without typing CriticalSections.Accounts.

import java.util.HashSet;
// NOTE: HashSet is imported but never used. (We keep it, just explaining.)

public class Credit_Process implements Runnable
{
    // ---------------------------------------------------------
    // ATTRIBUTES
    // ---------------------------------------------------------

    // Destination_Account_Number:
    // This tells us which account index will RECEIVE the money.
    // Example:
    //   0 = My Account
    //   1 = Lazada Account
    //   2 = Seller Account
    private Integer Destination_Account_Number;

    // amount:
    // This is the amount of money to add (credit) to the account.
    private float amount;
    
    // ---------------------------------------------------------
    // CONSTRUCTOR
    // ---------------------------------------------------------
    public Credit_Process(Integer des_acc, float amount)
    {
        this.Destination_Account_Number = des_acc;
        this.amount = amount;
    }

    // ---------------------------------------------------------
    // run() → executed when the thread starts
    // ---------------------------------------------------------
    @Override    
    public void run()
    {
        // -----------------------------------------------------
        // FULL SYNCHRONIZATION:
        // -----------------------------------------------------
        // synchronized(Accounts)
        //
        // This means:
        //   - We lock the SHARED resource (Accounts list itself).
        //   - ANY thread trying to modify Accounts MUST WAIT
        //     until the current thread finishes.
        //
        // This prevents:
        //   - Race conditions
        //   - Wrong results
        //   - Overlapping read/write operations
        //
        // This is BETTER than synchronized(this),
        // because ALL Debit and Credit threads share the SAME lock.
        // So they must take turns.
        synchronized (Accounts)
        {
            // -------------------------------------------------
            // CRITICAL SECTION
            // -------------------------------------------------
            // Read the current balance
            float New_Amount = Accounts.get(this.Destination_Account_Number) + this.amount;

            // Update the balance in the shared Accounts list
            Accounts.set(this.Destination_Account_Number, New_Amount);
            // IMPORTANT:
            // Both the READ and the WRITE MUST happen inside the SAME lock.
            // Otherwise two threads could read the same old balance
            // and overwrite each other's results incorrectly.


            // -------------------------------------------------
            // PRINT THE UPDATED BALANCES (still inside the lock)
            // -------------------------------------------------
            System.out.println(Thread.currentThread().getName()
                    + " Credit Process Executed - New Balances are:");

            // Print all accounts to show final values after transaction completes
            System.out.println("My Account: " + Accounts.get(0));
            System.out.println("Lazada's Account: " + Accounts.get(1));
            System.out.println("Seller's Account: " + Accounts.get(2));
        } // Lock is released here
    }
}


/*
SUMMARY
- This version is correct because:
    - All Debit and Credit threads lock the same object: Accounts
    - Only ONE thread can modify balances at a time
    - Every transaction runs from start → finish without interruption
    - No more mixed-up sequence or wrong balances

- No more Lazada randomly becoming $1100

- Best practice for multi-threaded banking = Lock shared resource
    - In this case: synchronized(Accounts)
    - Is the best and safest way.

*/