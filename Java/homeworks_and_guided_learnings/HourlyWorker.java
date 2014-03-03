
/**
 * Write a description of class HourlyWorker here.
 * 
 * @author Diana Collins 
 * @version 1.0
 */
public class HourlyWorker extends Worker
{
    private double weeklyPay = 0.00;

    public HourlyWorker(String name, double payRate)
    {
        setName(name);
        setHourlyRate(payRate);
    }
    
    public double computePay(int hours)
    {
        int overTimeHours = 0;
        double overTimeRate = 0.00;
        
        if (hours <= 40)
        {
            weeklyPay = hours * getHourlyRate();
        }
        else
        {
            overTimeHours = hours - 40;
            overTimeRate = (getHourlyRate() / 2) + getHourlyRate();
            weeklyPay = (getHourlyRate() * 40) + (overTimeRate * overTimeHours);
        }
        
        return weeklyPay;
    }
    
    public String toString()
    {
        return super.toString() + String.format("%6.2f", this.weeklyPay);
    }
}
