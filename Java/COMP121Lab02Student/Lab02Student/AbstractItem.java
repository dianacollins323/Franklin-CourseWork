
/**
 * This class represents an abstract item to be rented.
 *
 * @author Franklin University
 * @version Winter 2013
 */
public abstract class AbstractItem implements Item
{
    private String description;
    private Money weeklyRate;
    private String id;
    private boolean rentalStatus;

    /**
     * Return the description of the item.
     * @return the description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Change the description of the item to the
     * given parameter.
     * @param desc the new description.
     */
    public void setDescription(String desc)
    {
        if (desc == null)
        {
            throw new IllegalArgumentException("desc is null");
        }
        else if (desc.equals(""))
        {
            throw new IllegalArgumentException("desc is empty");
        }
        else
        {
            description = desc;    
        }
    }

    /**
     * Return the weekly rental rate for the item.
     * @return the rate.
     */
    public Money getWeeklyRate()
    {
        return weeklyRate;
    }

    /**
     * Change the weekly rental rate of the item
     * to the given parameter.
     * @param wklyRate the new weekly rate.
     */
    public void setWeeklyRate(Money wklyRate)
    {
        if (wklyRate == null)
        {
            throw new IllegalArgumentException("amount is null");
        }
        else if (wklyRate.isNegative())
        {
            throw new IllegalArgumentException("not a valid rental rate");
        }
        else if (wklyRate.compareTo(new Dollar(0.00)) == 0)
        {
            throw new IllegalArgumentException("not a valid rental rate");
        }
        else
        {
            weeklyRate = wklyRate;   
        }
    }

    /**
     * Return the ID of the item.
     * @return the ID.
     */
    public String getId()
    {
        return id;
    }

    /**
     * Change the ID of the item to the given
     * parameter.
     * @param idNum the new ID.
     */
    public void setId(String idNum)
    {
        if (idNum == null)
        {
            throw new IllegalArgumentException("idNum is null");    
        }
        else if (idNum.equals(""))
        {
            throw new IllegalArgumentException("idNum is empty");
        }
        else if (idNum.equals("0") || idNum.startsWith("-"))
        {
            throw new IllegalArgumentException("idNum not pos num");
        }
        else
        {
            id = idNum;    
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
        if ((weeklyRate == null) || (weeks <= 0))
        {
            return null;
        }
        else
        {
            return weeklyRate.mul(weeks);
        }
    }

    /**
     * Indicate the item has been rented.
     */
    public void rented()
    {
        rentalStatus = true;
    }

    /**
     * Indicate the item has been returned and is no
     * longer rented.
     */
    public void returned()
    {
        rentalStatus = false;
    }

    /**
     * Return the rental status of the item.
     * @return true if the item is rented, otherwise false.
     */
    public boolean isRented()
    {
        return rentalStatus;
    }
    
    /**
     * @param o reference to item to match
     * @return true if item is exact match 
     * false otherwise
     */
    public boolean equals(Object o)
    {
        AbstractItem temp = (AbstractItem) o;
        boolean matchDesc = false;
        boolean matchWklyRate = false;
        boolean matchId = false;
        boolean matchRentalStatus = false;
        
        
        //compare descripitions
        if (description == null && temp.getDescription() == null)
        {
            matchDesc = true;
        }
        else if (temp.getDescription() == null)
        {
            matchDesc = false;
        }
        else if (description == null)
        {
            matchDesc = false;
        }
        else if (description.equals(temp.getDescription()))
        {
            matchDesc = true;
        }
        else
        {
            matchDesc = false;
        }
        
        //compare weekly rates
        if (weeklyRate == null && temp.getWeeklyRate() == null)
        {
            matchWklyRate = true;
        }
        else if (temp.getWeeklyRate() == null)
        { 
            matchWklyRate = false;
        }
        else if (weeklyRate == null)
        {
            matchWklyRate = false;
        }
        else if (weeklyRate.equals(temp.getWeeklyRate()))
        {
            matchWklyRate = true;
        }
        
        //compare ids
        if (id == null && temp.getId() == null)
        {
            matchId = true;
        }
        else if (temp.getId() == null)
        {
            matchId = false;
        }
        else if (id == null)
        {
            matchId = false;
        }
        else if (id.equals(temp.getId()))
        { 
            matchId = true;
        }
        
        //compare rental statuses
        if (rentalStatus != temp.isRented())
        {
            matchRentalStatus = false;
        }
        else
        {
            matchRentalStatus = true;
        }
        
        //match items
        if (!matchDesc)
        {
            return false;
        }
        else if (!matchWklyRate)
        {
            return false;
        }
        else if (!matchId)
        {
            return false;
        }
        else if (!matchRentalStatus)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}