package Exer0012_CriticalSections_Full_Syncronization;

import static Exer0012_CriticalSections_Full_Syncronization.CriticalSections.Accounts;


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
    
    @Override
    public void run()
    {
        synchronized (Accounts)     //We Are trying to Synchronize the access to the Accounts (Shared Resource).
        {
            // CRITICAL SECTION: It MUST be executed altogether at once
            Accounts.set(this.Source_Account_Number, (Accounts.get(this.Source_Account_Number)-this.amount));

            System.out.println(Thread.currentThread().getName() +" Debit Process Executed - New Balances are:");
            System.out.println("My Account:" + Accounts.get(0));
            System.out.println("Lazada's Account:" + Accounts.get(1));
            System.out.println("Seller's Account:" + Accounts.get(2));}
        }
    }
    