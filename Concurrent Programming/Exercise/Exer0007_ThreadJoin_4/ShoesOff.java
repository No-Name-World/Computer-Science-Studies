package Exer0007_Threadjoin_4;

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
        System.out.println("\tClass " + ((int)((this.StudentNumber-1)/10)+1)+ " - Student " + this.StudentNumber+ ": Taking Shoes Off.");
    }
}
