package Exer0006_Threadjoin_3;

public class ShoesOff implements Runnable{
    
    // Attributes
    int StudentNumber;
    
    ShoesOff(int StdNum)
    {
        this.StudentNumber = StdNum;
    }
    
        @Override
    public void run()
    {
        System.out.println("\tStudent " + this.StudentNumber+ ": Taking Shoes Off.");
    }
}
