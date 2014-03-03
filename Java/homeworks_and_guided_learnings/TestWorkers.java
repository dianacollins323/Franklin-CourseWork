
/**
 * Write a description of class TestWorkers here.
 * 
 * @author Diana Collins 
 * @version 1.0
 */
public class TestWorkers
{
    public static void main(String[] args)
    {
        Worker max = new SalaryWorker("Max", 10.50);
        Worker sid = new SalaryWorker("Sid", 12.00);
        Worker rue = new HourlyWorker("Rue", 8.50);
        Worker sophie = new HourlyWorker("Sophie", 7.75);
        
        max.computePay(35);  //420.00
        sid.computePay(58);  //480.00
        rue.computePay(25);  //212.50
        sophie.computePay(49);  //414.63
        
        System.out.println(max);
        System.out.println(sid);
        System.out.println(rue);
        System.out.println(sophie);
    }
}
