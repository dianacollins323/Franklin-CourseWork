
/**
 * Write a description of class Instructor here.
 * 
 * @author Diana Collins 
 * @version 1.0
 */
public class Instructor extends Person
{
    private double salary;

    public Instructor(String name, int year, double pay)
    {
        super(name, year);
        this.setSalary(pay);
    }
    
    public double getSalary()
    {
        return this.salary;
    }
    
    public void setSalary(double pay)
    {
        this.salary = pay;
    }
    
    public String toString()
    {
        return super.toString() + ", Salary: $" + String.format("%6.2f", this.salary);
    }
}
