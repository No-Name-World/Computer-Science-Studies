package criticalsections;

import static Exer0012_CriticalSections_Full_Syncronization.CriticalSections.Accounts;
import java.util.HashSet;

public class Credit_Process implements Runnable
{
    // ATTRIBUTES
    private Integer Destination_Account_Number;
    private float amount;
    
    // CONSTRUCTOR
    public Credit_Process(Integer des_acc, float amount)
    {
        this.Destination_Account_Number = des_acc;
        this.amount = amount;
    }
    
    public void run()
    {
        this.credit();
        synchronized(this)
        {
            System.out.println(Thread.currentThread().getName() +" Credit PRocess Executed - New Balances are:");
            System.out.println("My's Account:" + Accounts.get(0));
            System.out.println("Lazada's Account:" + Accounts.get(1));
            System.out.println("Seller's Account:" + Accounts.get(2));
        }
    }
        
    // Deposit
    private void credit()
    {
        synchronized(this)
        {
            float New_Amount = Accounts.get(this.Destination_Account_Number)+this.amount;
            // CRITICAL SECTION: It MUST be executed altogether at once
            Accounts.set(this.Destination_Account_Number, New_Amount);  
        }
    }
}
