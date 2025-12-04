package Exer0011_CriticalSections;

import static Exer0012_CriticalSections_Full_Syncronization.CriticalSections.Accounts;
// This static import lets us use "Accounts" directly,
// without writing "CriticalSections.Accounts" every time.

import java.util.HashSet;
// HashSet is imported but NOT used in this class.
// (It could be removed, but we keep it here and just explain.)

public class Credit_Process implements Runnable
{
    // ---------------------------------------------------------
    // ATTRIBUTES
    // ---------------------------------------------------------

    // Destination_Account_Number:
    // This tells us WHICH account in the Accounts list
    // will receive (be credited with) the money.
    private Integer Destination_Account_Number;

    // amount:
    // This is HOW MUCH money we want to add to the destination account.
    private float amount;
    
    // ---------------------------------------------------------
    // CONSTRUCTOR
    // ---------------------------------------------------------
    public Credit_Process(Integer des_acc, float amount)
    {
        // Save the destination account index (e.g., 0 = MyAccount, 1 = Lazada, 2 = Seller)
        this.Destination_Account_Number = des_acc;

        // Save the transfer amount (e.g., 100.0f)
        this.amount = amount;
    }
    
    @Override
    public void run()
    {
        // -----------------------------------------------------
        // When the thread starts, it will execute this run() method.
        // -----------------------------------------------------

        // First, do the actual credit operation (add money to the account)
        this.credit();

        // Then, print the balances.
        // We put this PRINTING inside a synchronized(this) block,
        // so that two threads that use the SAME Credit_Process object
        // will not print at the exact same time.
        synchronized(this)
        {
            System.out.println(Thread.currentThread().getName() 
                    + " Credit Process Executed - New Balances are:");
            System.out.println("My's Account: " + Accounts.get(0));
            System.out.println("Lazada's Account: " + Accounts.get(1));
            System.out.println("Seller's Account: " + Accounts.get(2));
        }
        // IMPORTANT NOTE:
        // synchronized(this) here ONLY protects code for THIS object.
        // If other threads use DIFFERENT Credit_Process objects,
        // they are NOT blocked by this lock.
    }
        
    // ---------------------------------------------------------
    // Deposit (Credit) Operation
    // ---------------------------------------------------------
    private void credit()
    {
        // This block is the CRITICAL SECTION for updating the account balance.
        // We use synchronized(this) so that no two threads using the SAME
        // Credit_Process object can modify at the same time.
        synchronized(this)
        {
            // Get the current balance from the destination account
            float New_Amount = Accounts.get(this.Destination_Account_Number) + this.amount;

            // CRITICAL SECTION:
            // We are updating (writing) the shared Accounts list.
            // If multiple threads do this at the same time without proper locking,
            // the final balances can become WRONG.
            Accounts.set(this.Destination_Account_Number, New_Amount);
        }
        // After we exit synchronized(this), the lock is released.
    }
}
