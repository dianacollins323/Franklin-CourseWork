
/**
 * Write a description of class NewAccountB here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NewAccountB
{
    public static void main(String[] args)
    {
        SavingsAccount b = new SavingsAccount(0, 10);
        System.out.println(b.getBalance());
        
        b.deposit(5000);
        System.out.println(b.getBalance());
        
        b.withdraw(b.getBalance() / 2);
        System.out.println(b.getBalance());
        
        b.addInterest();
        System.out.println(b.getBalance());
    }
}
