package Exer0005_Threadjoin_2;

public class SubThread implements Runnable
{
    @Override
    public void run()
    {
        System.out.println("\tSubThread is Executed: " + Thread.currentThread().getName());
    }
}
