
/**
 * Write a description of class SavingsAccount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SavingsAccount extends BankAccount
{
    private double interestRate;

    public SavingsAccount(double amt, double rate)
    {
        super(amt);
        this.interestRate = rate;
    }
    
    public void addInterest()
    {
        double interest = getBalance() * interestRate / 100;
        deposit(interest);
    }
}
