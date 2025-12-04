/*
    In this example of Critical Sections, we want to transfer $100 from:
        - MyAccount's account   → Lazada Account's account
        - Lazada Account's      → Seller's Account

    If we do NOT use 'synchronization' for the Critical Sections,
    then an error might happen in the results because multiple threads
    can run at the SAME TIME and touch the SAME data.

    This problem is called a Race Condition:
        - It happens when two or more threads/processes access and update
          shared data in an uncontrolled way.
        - The final result depends on which thread runs first or faster,
          which is NOT safe or predictable.

    In this example:
        - MyAccount has    $500
        - Lazada Account has $1000
        - Seller's Account has $0

    SUPPOSEDLY, after the execution of the 4 threads, the final balance should be:
        - MyAccount:   400  (500 - 100)
        - Lazada:     1000  (+100 from MyAccount, then -100 to Seller → no net change)
        - Seller:      100  (0 + 100 from Lazada)

    But if we keep running this program WITHOUT full synchronization,
    we may see Lazada's account sometimes end with $1100, which is WRONG.

    This error happens because:
        - The threads run in the wrong sequence
        - Critical sections (money updates) are not fully protected
        - Two threads might read/update the same account at the same time

    Therefore, we need to make the processes ISOLATED and SAFE
    by using Synchronization or Locks when we update the shared Accounts list.
*/

package Exer0011_CriticalSections;

import Exer0012_CriticalSections_Full_Syncronization.Credit_Process;
import Exer0012_CriticalSections_Full_Syncronization.Debit_Process;
import java.util.ArrayList;

public class CriticalSections 
{
    // ---------------------------------------------------------
    // Shared Data Structure: All Accounts in One List
    // ---------------------------------------------------------
    // This ArrayList holds the balances for all accounts.
    // Index 0 → My Account
    // Index 1 → Lazada's Account
    // Index 2 → Seller's Account
    //
    // IMPORTANT:
    // All threads (Debit_Process and Credit_Process) will read/write
    // to this SAME list. So this is our SHARED RESOURCE.
    public static ArrayList<Float> Accounts = new ArrayList<>();
    
    public static void main(String[] args) 
    {
        // -----------------------------------------------------
        // Step 1: Initialize account balances
        // -----------------------------------------------------

        // Index 0 - My Account has $500
        Accounts.add((float)500);

        // Index 1 - Lazada Account's Account has $1000
        Accounts.add((float)1000);

        // Index 2 - Seller's Account has $0
        Accounts.add((float)0);
                
        // -----------------------------------------------------
        // Step 2: Create Threads for Money Transfers
        // -----------------------------------------------------
        // We have 2 transfer operations:

        // 1) Transfer from My Account (0) → Lazada's Account (1)
        //    - Debit_1:  take $100 from My Account
        //    - Credit_1: add $100 to Lazada's Account
        Thread Debit_1  = new Thread(new Debit_Process(0, (float)100));
        Thread Credit_1 = new Thread(new Credit_Process(1, (float)100));
        
        // 2) Transfer from Lazada's Account (1) → Seller's Account (2)
        //    - Debit_2:  take $100 from Lazada's Account
        //    - Credit_2: add $100 to Seller's Account
        Thread Debit_2  = new Thread(new Debit_Process(1, (float)100));
        Thread Credit_2 = new Thread(new Credit_Process(2, (float)100));
        
        // -----------------------------------------------------
        // Step 3: Run the Threads in a CONTROLLED SEQUENCE
        // -----------------------------------------------------
        // Even though we are using threads, here we are forcing
        // them to execute one after another using join().
        //
        // This is to:
        //   - Avoid some race conditions
        //   - Make the sequence easier to understand:
        //
        //   1. Debit MyAccount → Lazada (Debit_1)
        //   2. Credit Lazada   ← MyAccount (Credit_1)
        //   3. Debit Lazada    → Seller    (Debit_2)
        //   4. Credit Seller   ← Lazada    (Credit_2)

        // ---- Step 3.1: Start Debit_1 (My Account → Lazada: taking $100) ----
        Debit_1.start();
        
        // IF sequence is important:
        // Wait for Debit_1 to FINISH before moving to the next step.
        try {
            Debit_1.join();  // Main thread waits here until Debit_1 completes
        } 
        catch (InterruptedException ex) 
        {
            // In this example we ignore interruption handling.
        }
        
        // ---- Step 3.2: Start Credit_1 (My Account → Lazada: receiving $100) ----
        Credit_1.start();

        // Wait for Credit_1 to FINISH to keep the order safe.
        try {
            Credit_1.join();
        } 
        catch (InterruptedException ex) 
        {
        }

        // ---- Step 3.3: Start Debit_2 (Lazada → Seller: taking $100) ----
        Debit_2.start();
        
        // Again, wait for Debit_2 to finish before going to Credit_2
        try {
            Debit_2.join();
        } 
        catch (InterruptedException ex) 
        {
        }

        // ---- Step 3.4: Start Credit_2 (Lazada → Seller: giving $100) ----
        Credit_2.start();
        // NOTE:
        // There is no final join() here, but in a real program,
        // if we want to GUARANTEE that all operations finish before
        // printing the final balances, we should also call:
        //
        //     Credit_2.join();
        //
        // Otherwise, the program might end before Credit_2 actually finishes.
    }
}
