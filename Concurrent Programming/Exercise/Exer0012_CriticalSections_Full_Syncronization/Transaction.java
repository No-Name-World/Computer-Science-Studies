package Exer0012_CriticalSections_Full_Syncronization;

import static Exer0012_CriticalSections_Full_Syncronization.CriticalSections.Accounts;
// We import the shared Accounts list so we can use it directly here if needed.

import java.util.logging.Level;
import java.util.logging.Logger;

public class Transaction implements Runnable
{
    // ---------------------------------------------------------
    // ATTRIBUTES (Data stored inside the Transaction object)
    // ---------------------------------------------------------

    // Source_Account_Number:
    // This is the account we will take money FROM.
    // Example: 0 = My Account.
    private Integer Source_Account_Number;

    // Destination_Account_Number:
    // This is the account we will send money TO.
    // Example: 2 = Seller's Account.
    private Integer Destination_Account_Number;

    // amount:
    // This is the amount of money we want to transfer in this transaction.
    private float amount;

    // Lazada_Account_Number:
    // This account is used as a middle account (like a platform):
    //   - Money moves: My Account → Lazada → Seller.
    private Integer Lazada_Account_Number = 1;
    
    
    // ---------------------------------------------------------
    // CONSTRUCTOR
    // ---------------------------------------------------------
    public Transaction(Integer src_acc, Integer des_acc, float amount)
    {
        // Save the source account (where money starts)
        this.Source_Account_Number = src_acc;

        // Save the destination account (where money should end)
        this.Destination_Account_Number = des_acc;

        // Save the transfer amount (e.g., 100)
        this.amount = amount;
    }
    
    
    // ---------------------------------------------------------
    // run() → This is what happens when THIS Transaction is executed in a thread
    // ---------------------------------------------------------
    @Override
    public void run()
    {
        // IMPORTANT NOTE (from comment in code):
        // This part does NOT directly access shared data (Accounts)
        // inside this method, so we do NOT need to synchronize here.
        // The actual modification of Accounts is done INSIDE
        // Debit_Process and Credit_Process, which are synchronized already.

        // -----------------------------------------------------
        // Step 1: Create the 4 threads that will simulate:
        //
        //   1) Debit from My Account  (0)   → Lazada (1)
        //   2) Credit to Lazada       (1)
        //   3) Debit from Lazada      (1)   → Seller (2)
        //   4) Credit to Seller       (2)
        //
        // All money movements use $100 for this example.
        // -----------------------------------------------------

        // Debit_1:
        // Take $100 from the Source Account (e.g., My Account).
        Thread Debit_1 = new Thread(
                new Debit_Process(this.Source_Account_Number, (float)100)
        );
         
        // Credit_1:
        // Add $100 to the Lazada Account (account index 1).
        Thread Credit_1 = new Thread(
                new Credit_Process(this.Lazada_Account_Number, (float)100)
        );

        // Debit_2:
        // Take $100 from Lazada Account (account index 1).
        Thread Debit_2 = new Thread(
                new Debit_Process(this.Lazada_Account_Number, (float)100)
        );

        // Credit_2:
        // Add $100 to the final Destination Account (e.g., Seller).
        Thread Credit_2 = new Thread(
                new Credit_Process(this.Destination_Account_Number, (float)100)
        );
        

        // -----------------------------------------------------
        // Step 2: Execute the FIRST transfer pair:
        //         My Account → Lazada
        // -----------------------------------------------------

        // Start both the debit and credit for the first step.
        // So these two threads will:
        //   - Debit_1: subtract 100 from My Account
        //   - Credit_1: add 100 to Lazada
        // Internally, these threads will use
        // synchronized(Accounts) to prevent race conditions.
        Debit_1.start();
        Credit_1.start();

        try {
            // Wait for BOTH debit and credit operations to complete
            // before going to the next step.
            //
            // join() makes the Transaction thread wait here
            // until Debit_1 finishes AND Credit_1 finishes.
            Debit_1.join();
            Credit_1.join();
        } 
        catch (InterruptedException ex) {
            // If the waiting is interrupted, we ignore it in this example.
            // In a real system, we might log or handle this properly.
        }
        
        // -----------------------------------------------------
        // Step 3: Execute the SECOND transfer pair:
        //         Lazada → Seller
        // -----------------------------------------------------

        // Start:
        //   - Debit_2: subtract 100 from Lazada
        //   - Credit_2: add 100 to Seller
        //
        // NOTE:
        // We start both threads, but we do NOT call join() here.
        // That means the Transaction thread will finish quickly
        // and these two threads will continue running in the background
        // until they complete their work.
        Debit_2.start();
        Credit_2.start();

        // This part does NOT need synchronization because:
        //   - We are only starting threads here.
        //   - The real access to shared "Accounts" is done inside
        //     Debit_Process and Credit_Process, which are already synchronized.
    }
}
