package Exer0008_Threadstate;
public class ShortRunnableProcess implements Runnable{
    
    public void run()
    {
        System.out.println(Thread.currentThread().getName() +
                ": Short Runnable Process been Fully Executed.");
    }
}
