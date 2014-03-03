

/**
 * This class represents a furniture item for rent.
 *
 * @author Franklin University
 * @version Winter 2013
 */
public class Furniture extends AbstractItem
{
    private Money fee;
    private Money monthlyRate;

    /**
     * Constructor for objects of class Furniture.
     */
    public Furniture()
    {
        //Furniture constructor
    }
    
    /**
     * Return the monthly rental rate for the furniture
     * item.
     * @return the rate.
     */
    public Money getMonthlyRate()
    {
        return monthlyRate;
    }

    /**
     * Change the monthly rental rate of the furniture item
     * to the given parameter.
     * @param mnthlyRate the new weekly rate.
     */
    public void setMonthlyRate(Money mnthlyRate)
    {
        if (mnthlyRate == null)
        {
            throw new IllegalArgumentException("mnthlyRate is null");
        }
        else if (mnthlyRate.isNegative())
        {
            throw new IllegalArgumentException("mnthlyRate is negative");
        }
        else if (mnthlyRate.compareTo(new Dollar(0.00)) == 0)
        {
            throw new IllegalArgumentException("mnthlyRate is zero");
        }
        else
        {
            monthlyRate = mnthlyRate;    
        }
    }

    /**
     * Calculate the fees for renting the item for a given
     * number of weeks. The weekly rate must be set and the
     * number of rental weeks must be valid in order for the fee
     * to be calculated.
     * @param weeks the number of rental weeks
     * @return the fee or null if the fee could not be calculated
     */
    public Money calculateFee(int weeks)
    {
        if (super.getWeeklyRate() == null)
        {
            return null;
        }
        else if (weeks <= 0)
        {
            return null;
        }
        else if ((weeks > 4) && (this.getMonthlyRate() == null))
        {
            return null;
        }
        else if (weeks < 4)
        {
            fee = super.getWeeklyRate().mul(weeks);
        }
        else
        {
            int months = weeks / 4;
            int newWeeks = weeks % 4;
            Money weeklyFee = super.getWeeklyRate().mul(newWeeks);
            fee = (monthlyRate.mul(months)).add(weeklyFee);
        }
        return fee;
    }
    
    /**
     * overriding equals method to account
     * for furniture monthlyRate
     * @param o reference to item to match
     * @return true if item is exact match
     * false otherwise
     */
    public boolean equals(Object o)
    {
        Furniture temp = (Furniture) o;
        
        if (!super.equals(temp))
        {
            return false;
        }
        else if (monthlyRate == null && temp.getMonthlyRate() == null)
        {
            return true;
        }
        else if (temp.getMonthlyRate() == null)
        {
            return false;
        }
        else if (monthlyRate == null)
        {
            return false;
        }
        else if (!monthlyRate.equals(temp.getMonthlyRate()))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
