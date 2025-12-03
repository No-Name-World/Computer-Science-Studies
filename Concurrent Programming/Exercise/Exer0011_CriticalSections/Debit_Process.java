package criticalsections;

import static criticalsections.CriticalSections.Accounts;


public class Debit_Process implements Runnable
{
    // ATTRIBUTES
    private Integer Source_Account_Number;
    private float amount;
    
    // CONSTRUCTOR
    public Debit_Process(Integer src_acc, float amount)
    {
        this.Source_Account_Number = src_acc;
        this.amount = amount;
    }
     
    public void run()
    {
        this.debit();
        synchronized(this)
        {
            System.out.println(Thread.currentThread().getName() +" Debit Process Executed - New Balances are:");
            System.out.println("Father's Account:" + Accounts.get(0));
            System.out.println("Family's Account:" + Accounts.get(1));
            System.out.println("Daughter's Account:" + Accounts.get(2));
        }
    }
    
    // Withdrawl
    public void debit()
    {
        System.out.println("HELLO.. I am outside the Synchronized Section.");

        synchronized(this)
        {
            // CRITICAL SECTION: It MUST be executed altogether at once
            Accounts.set(this.Source_Account_Number, (Accounts.get(this.Source_Account_Number)-this.amount));
        }
    }
}
