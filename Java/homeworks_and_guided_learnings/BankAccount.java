
/**
 * Write a description of class BankAccount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BankAccount
{
    private double balance;
    
    public BankAccount(double amt)
    {
       this.balance = amt;
    }
    
    public double getBalance()
    {
       return this.balance; 
    }
    
    public void deposit(double amt)
    {
        this.balance = balance + amt;
    }
    
    public void withdraw(double amt)
    {
        this.balance = balance - amt;
    }
}
