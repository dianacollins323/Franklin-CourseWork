
/**
 * Write a description of class SalaryWorker here.
 * 
 * @author Diana Collins
 * @version 
 */
public class SalaryWorker extends Worker
{
    private double weeklyPay = 0.00;

    public SalaryWorker(String name, double payRate)
    {
        setName(name);
        setHourlyRate(payRate);
    }
    
    public double computePay(int hours)
    {
        weeklyPay = 40 * getHourlyRate();
        
        return weeklyPay;
    }
    
    public String toString()
    {
        return super.toString() + String.format("%6.2f", this.weeklyPay);
    }
}
