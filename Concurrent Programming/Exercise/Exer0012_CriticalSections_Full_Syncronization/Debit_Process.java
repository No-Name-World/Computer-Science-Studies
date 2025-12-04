package Exer0012_CriticalSections_Full_Syncronization;

import static Exer0012_CriticalSections_Full_Syncronization.CriticalSections.Accounts;
// This import allows us to use Accounts directly,
// without typing CriticalSections.Accounts each time.

public class Debit_Process implements Runnable
{
    // ---------------------------------------------------------
    // ATTRIBUTES
    // ---------------------------------------------------------

    // Source_Account_Number:
    // The account index from which money will be WITHDRAWN.
    // Example:
    //   0 = My Account
    //   1 = Lazada Account
    //   2 = Seller Account
    private Integer Source_Account_Number;

    // amount:
    // The amount of money we want to withdraw from the source account.
    private float amount;
    
    // ---------------------------------------------------------
    // CONSTRUCTOR
    // ---------------------------------------------------------
    public Debit_Process(Integer src_acc, float amount)
    {
        // Store the account number we are withdrawing from
        this.Source_Account_Number = src_acc;

        // Store the amount to withdraw
        this.amount = amount;
    }
    
    // ---------------------------------------------------------
    // run() → executed when this thread starts
    // ---------------------------------------------------------
    @Override
    public void run()
    {
        // ---------------------------------------------------------
        // FULL SYNCHRONIZATION
        // ---------------------------------------------------------
        // synchronized(Accounts)
        //
        // We lock the SHARED Accounts list.
        //
        // Meaning:
        //    - Only ONE thread (either Debit_Process or Credit_Process)
        //      can touch the Accounts list at a time.
        //
        // This prevents:
        //    - Race conditions
        //    - Wrong final balances
        //    - Overlapping updates
        //
        // This is the MOST correct way because ALL threads share
        // the SAME lock: the 'Accounts' object.
        synchronized (Accounts)
        {
            // -----------------------------------------------------
            // CRITICAL SECTION:
            // WITHDRAW the money from the selected account
            // -----------------------------------------------------
            // Read the existing balance
            float currentBalance = Accounts.get(this.Source_Account_Number);

            // Subtract the amount
            float newBalance = currentBalance - this.amount;

            // Write the new balance back to the shared list
            Accounts.set(this.Source_Account_Number, newBalance);

            // -----------------------------------------------------
            // PRINT UPDATED BALANCES (still inside the lock)
            // -----------------------------------------------------
            System.out.println(Thread.currentThread().getName() 
                    + " Debit Process Executed - New Balances are:");

            System.out.println("My Account: "      + Accounts.get(0));
            System.out.println("Lazada's Account: "+ Accounts.get(1));
            System.out.println("Seller's Account: "+ Accounts.get(2));
        }
        // The lock on Accounts is released here.
    }
}


/*
SUMMARY
- This version is correct and thread-safe because:
    - We lock the shared Accounts list: synchronized (Accounts)


- This ensures:
    ✔ Only ONE thread (Debit or Credit) updates account balances at a time
    ✔ No interleaving of read/write operations
    ✔ No unpredictable results
    ✔ No more wrong balances like Lazada randomly getting $1100


- Why this version is better than Exer0011
    - Exer0011 used synchronized(this) → each object locked itself
    - But multiple Debit/Credit objects DO NOT share the same lock
    - So threads could still run simultaneously → race condition


- In Exer0012:
    - All operations lock the same shared object → Accounts
    - That is the proper global lock.

*/