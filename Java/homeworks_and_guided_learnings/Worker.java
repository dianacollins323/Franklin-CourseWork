
/**
 * Write a description of class Worker here.
 * 
 * @author Diana Collins
 * @version 1.0
 */
public abstract class Worker
{
    private String fullName;
    private double hourlyRate;

    public Worker()
    {
        
    }
    
    public abstract double computePay(int hours);
    
    public String getName()
    {
        return fullName;
    }
    
    public void setName(String name)
    {
        fullName = name;    
    }
    
    public double getHourlyRate()
    {
        return hourlyRate;
    }
    
    public void setHourlyRate(double payRate)
    {
        hourlyRate = payRate;
    }
    
    public String toString()
    {
        return "Name: " + this.fullName + ", Total Weekly Pay: ";
    }
}
