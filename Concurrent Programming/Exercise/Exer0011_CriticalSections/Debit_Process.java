package Exer0011_CriticalSections;

// Import the shared Accounts list from the CriticalSections class
// This gives us direct access to: Accounts.get(), Accounts.set(), etc.
import static Exer0011_CriticalSections.CriticalSections.Accounts;

public class Debit_Process implements Runnable
{
    // ---------------------------------------------------------
    // ATTRIBUTES
    // ---------------------------------------------------------

    // Source_Account_Number:
    // This tells us which account we want to TAKE MONEY FROM.
    // Example:
    //   0 → My/Father Account
    //   1 → Lazada/Family Account
    //   2 → Seller/Daughter Account
    private Integer Source_Account_Number;

    // amount:
    // How much money we want to withdraw from that account.
    private float amount;
    
    // ---------------------------------------------------------
    // CONSTRUCTOR
    // ---------------------------------------------------------
    public Debit_Process(Integer src_acc, float amount)
    {
        // Save the source account index (the account we withdraw from)
        this.Source_Account_Number = src_acc;

        // Save the amount to withdraw
        this.amount = amount;
    }
     

    // ---------------------------------------------------------
    // run() → Called when the thread starts executing
    // ---------------------------------------------------------
    @Override
    public void run()
    {
        // First perform the debit operation
        this.debit();

        // Then print the balances.
        // Putting this inside synchronized(this) protects ONLY
        // this printing block for THIS specific object.
        synchronized(this)
        {
            System.out.println(Thread.currentThread().getName() 
                    + " Debit Process Executed - New Balances are:");
            System.out.println("Father's Account:  " + Accounts.get(0));
            System.out.println("Family's Account:  " + Accounts.get(1));
            System.out.println("Daughter's Account: " + Accounts.get(2));
        }

        // IMPORTANT:
        // If multiple Debit_Process OBJECTS exist,
        // each one has a different "this"
        //
        // So synchronized(this) does NOT protect the shared Accounts list
        // from OTHER Debit_Process or Credit_Process objects.
    }
    
    // ---------------------------------------------------------
    // debit() → Withdraw money from an account
    // ---------------------------------------------------------
    public void debit()
    {
        // This line is purposely OUTSIDE the synchronized block.
        // It shows that some code can run freely without locking.
        System.out.println("HELLO.. I am outside the Synchronized Section.");

        // CRITICAL SECTION:
        // This is where we actually UPDATE the shared account balance.
        // It MUST be protected, because multiple threads may try to update
        // the SAME account at the SAME time.
        synchronized(this)
        {
            // Read the current balance and subtract the amount
            // Then immediately write (set) the new balance.
            //
            // Without locking, two threads might read the same balance
            // then both write new balances → WRONG results (race condition).
            Accounts.set(
                this.Source_Account_Number, 
                (Accounts.get(this.Source_Account_Number) - this.amount)
            );
        }
        // When synchronized block ends, the lock on "this" is released.
    }
}



/*
SUMMARY
- Debit_Process WITHDRAWS money from one account.

- The critical section is updating the shared Account list.

- synchronized(this) means:
    ✔ Only ONE thread using THIS object may enter the block
    ❌ But other Debit/Credit objects are NOT blocked

- So if you have multiple Debit_Process objects, each object has its own lock → race conditions can still happen.

*/