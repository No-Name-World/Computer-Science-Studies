package Exer0012_CriticalSections_Full_Syncronization;

import static Exer0012_CriticalSections_Full_Syncronization.CriticalSections.Accounts;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transaction implements Runnable
{
    // ATTRIBUTES
    private Integer Source_Account_Number;
    private Integer Destination_Account_Number;
    private float amount;
    private Integer Lazada_Account_Number = 1;
    
    // CONSTRUCTOR
    public Transaction(Integer src_acc, Integer des_acc, float amount)
    {
        this.Destination_Account_Number = des_acc;
        this.Source_Account_Number = src_acc;
        this.amount = amount;
    }
    
    @Override
    public void run()
    {
        // Does not need to be synchronized as it does NOT access a shared resource.
        Thread Debit_1 = new Thread(new Debit_Process(this.Source_Account_Number,(float)100));
         
        // Transfer from My Account (Number 0) to Lazada's Account (Number 1).
        Thread Credit_1 = new Thread(new Credit_Process(this.Lazada_Account_Number,(float)100));

        // Transfer from Lazada's Account (Number 1) to Seller's Account (Number 2)
        Thread Debit_2 = new Thread(new Debit_Process(this.Lazada_Account_Number,(float)100));

        Thread Credit_2 = new Thread(new Credit_Process(this.Destination_Account_Number,(float)100));
        

        Debit_1.start();
        Credit_1.start();
        try {
            Debit_1.join();
            Credit_1.join();
        } catch (InterruptedException ex) {
        }
        
        Debit_2.start();
        Credit_2.start();
        // Does not need to be synchronized as it does NOT access a shared resource.
        
    }
}
