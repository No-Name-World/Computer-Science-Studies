/*
    In this example of Critical Sections, we want to transfer $100 from:
        - MyAccount's account   → Lazada Account's account
        - Lazada Account's      → Seller's Account

    If we do NOT use 'synchronization' for the Critical Sections,
    then an error might happen in the results because multiple threads
    can touch the SAME data (the account balances) at the SAME time.

    This problem is called a Race Condition:
        - It happens when two or more threads/processes access and update
          shared data in an uncontrolled way.
        - The final result depends on which thread runs first or faster.
        - This makes the program unsafe and unpredictable.

    In the example:
        - MyAccount has    $500
        - Lazada Account has $1000
        - Seller's Account has $0

    SUPPOSEDLY, after the correct execution:
        - MyAccount:  $400  (500 - 100)
        - Lazada:    $1000  (first +100, then -100 → no net change)
        - Seller:     $100  (0 + 100)

    If we keep running a NON-synchronized version many times,
    we may see Lazada's account sometimes end with $1100, which is WRONG.

    The error happens because:
        - The execution order inside different threads is mixed up.
        - The CRITICAL SECTION (reading and writing balances) is not protected.

    In THIS version (Exer0012), we want FULL SYNCHRONIZATION:
        - We isolate the critical sections using synchronization or locks.
        - This ensures each money transfer is done completely and safely
          before another thread can change the same shared accounts.
*/

package Exer0012_CriticalSections_Full_Syncronization;

import java.util.ArrayList;

public class CriticalSections 
{
    // ---------------------------------------------------------
    // Shared Data Structure: List of all account balances
    // ---------------------------------------------------------
    // This ArrayList stores the balances of all accounts:
    // Index 0 → My Account
    // Index 1 → Lazada Account
    // Index 2 → Seller's Account
    //
    // IMPORTANT:
    // Many threads may read/update this SAME list,
    // so this is the SHARED RESOURCE that must be protected.
    public static ArrayList<Float> Accounts = new ArrayList<>();
    
    public static void main(String[] args) 
    {
        // -----------------------------------------------------
        // Step 1: Initialize the accounts with starting balances
        // -----------------------------------------------------

        // Index 0 - My Account has $500
        Accounts.add((float)500);

        // Index 1 - Lazada Account's Account has $1000
        Accounts.add((float)1000);

        // Index 2 - Seller's Account has $0
        Accounts.add((float)0);
                

        // -----------------------------------------------------
        // Step 2: Create a Transaction object
        // -----------------------------------------------------
        // Transaction(0, 2, 100) most likely means:
        //   - Transfer $100 from Account 0 (My Account)
        //   - to Account 2 (Seller's Account)
        //
        // Inside Transaction, it may:
        //   - Debit (withdraw) from My Account
        //   - Credit (deposit) into Seller's Account
        // with proper synchronization on the shared Accounts list.
        Transaction Trans = new Transaction(0, 2, 100);
        
        // -----------------------------------------------------
        // Step 3: Wrap the Transaction inside a Thread
        // -----------------------------------------------------
        // 'Trans' is a Runnable (most likely).
        // We now create a Thread that will execute the Transaction's run() method.
        Thread Trans_Thread = new Thread(Trans);
        
        // -----------------------------------------------------
        // Step 4: Start the Transaction thread
        // -----------------------------------------------------
        // Once started, Trans_Thread will:
        //   - Perform the money transfer
        //   - Use synchronization to protect the critical sections
        Trans_Thread.start();

        // NOTE:
        // In a more complete example, we might:
        //   - Start multiple Transaction threads
        //   - Use join() to wait for them to finish
        //   - Print final balances afterwards.
        // This simple version just starts one transaction.
    }
}
