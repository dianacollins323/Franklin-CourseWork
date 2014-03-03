/**
 * An abstraction of a paycheck.  Contains data on the payee,
 * the net and gross income, and the taxes withheld.
 * 
 * @author Todd A. Whittaker
 * @version 2006-01
 */
public class PayCheck
{
    private String payee;
    private Money netIncome;
    private Money grossIncome;
    private Money taxesWitheld;
    
    /**
     * Build a pay check.
     * @param name the name on the check.
     * @param net the net pay.
     * @param gross the gross pay.
     * @param tax the tax deducted from gross.
     */
    public PayCheck(String name, Money net, Money gross, Money tax)
    {
        this.payee = name;
        this.netIncome = net;
        this.grossIncome = gross;
        this.taxesWitheld = tax;
    }
    
    /**
     * Return the entity to whom the check is written.
     * @return the payee.
     */
    public String getPayee()
    {
        return this.payee;
    }
    
    /**
     * Return the net pay amount.
     * @return net pay.
     */
    public Money getNet()
    {
        return this.netIncome;
    }
    
    /**
     * Return the gross pay amount.
     * @return gross pay.
     */
    public Money getGross()
    {
        return this.grossIncome;
    }
    
    /**
     * Return the taxes deducted.
     * @return the taxes.
     */
    public Money getTaxes()
    {
        return this.taxesWitheld;
    }
    
    /**
     * Return a string representation of they check.
     * @return a string containing some of the data fields.
     */
    public String toString()
    {
        return "Pay to the order of: " + getPayee() + " " + getNet();
    }
}
