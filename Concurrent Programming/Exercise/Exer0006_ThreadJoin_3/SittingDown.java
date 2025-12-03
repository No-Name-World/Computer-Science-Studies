package Exer0006_Threadjoin_3;

public class SittingDown implements Runnable{
    // Attributes
    int StudentNumber;
    
    SittingDown(int StdNum)
    {
        this.StudentNumber = StdNum;
    }
        @Override
    public void run()
    {
        System.out.println("\tStudent " + this.StudentNumber+ ": Sitting Down.");
    }
}
