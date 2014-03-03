

/**
 * This class represents a furniture item for rent.
 * 
 * @author Franklin University
 * @version Winter 2013
 */
public class Furniture implements Item
{
    private String description;
    private Money weeklyRate;
    private String itemId;
    private Money monthlyRate;
    private boolean rented;
    private Money rentalFee = new Dollar(0.00);

    /**
     * Constructor for objects of class Furniture.
     */
    public Furniture()
    {
        this.rented = false;
    }

    /**
     * Return the description of the furniture.
     * @return the description.
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     * Change the description of the furniture to the 
     * given parameter.
     * @param desc the new description.
     */
    public void setDescription(String desc)
    {
        this.description = desc;
    }
    
    /**
     * Return the weekly rental rate for the furniture
     * item.
     * @return the rate.
     */
    public Money getWeeklyRate()
    {
        return this.weeklyRate;
    }

    /**
     * Change the weekly rental rate of the furniture item 
     * to the given parameter.
     * @param wklyRate the new weekly rate.
     */
    public void setWeeklyRate(Money wklyRate)
    {
        this.weeklyRate = wklyRate;
    }    
     
    /**
     * Return the ID of the furniture item.
     * @return the ID.
     */
    public String getId()
    {
        return this.itemId;
    }    
    
    /**
     * Change the ID of the furniture item to the given 
     * parameter.
     * @param idNum the new ID.
     */
    public void setId(String idNum)
    {
        this.itemId = idNum;
    }    

    /**
     * Calculate the fees for renting the furniture for a given
     * number of weeks. If the number of weeks is 4 or more, the
     * monthly rate is charged for each 4 week period. For example, if
     * the furniture item is rented for 6 weeks, the rental fee will
     * be the monthly fee plus 2 X the weekly fee.  Both the weekly
     * rate and the monthly rate must be set and the number of rental 
     * weeks must be valid in order for the fee to be calculated.
     * @param weeks the number of rental weeks
     * @return the fee or null if the fee could not be calculated
     */
    public Money calculateFee(int weeks)
    {
        try {
            //calculates rental fee 
            if (weeks >= 4 && getMonthlyRate() != null) {
                int months = weeks / 4;
                int newWeeks = weeks % 4;
                this.rentalFee = getWeeklyRate().mul(newWeeks)
                    .add(this.getMonthlyRate().mul(months));
            }
            else if (weeks >= 4 && getMonthlyRate() == null) {
                this.rentalFee = null;
            }
            else if (weeks <= 0) {
                this.rentalFee = null;
            }
            else {
                this.rentalFee = getWeeklyRate().mul(weeks);
            }
        }
        catch (NullPointerException e) {
            //catches null exception
            this.rentalFee = null;
        }
        return this.rentalFee;
    }    

    /**
     * Return the monthly rental rate for the furniture
     * item.
     * @return the rate.
     */
    public Money getMonthlyRate()
    {
        return this.monthlyRate;
    }

    /**
     * Change the monthly rental rate of the furniture item 
     * to the given parameter.
     * @param mnthlyRate the new weekly rate.
     */
    public void setMonthlyRate(Money mnthlyRate)
    {
        this.monthlyRate = mnthlyRate;
    }    
    
    /**
     * Indicate the furniture item has been rented.
     */
    public void rented()
    {
        this.rented = true;
    }
    
    /**
     * Indicate the furniture item has been returned and is no
     * longer rented.
     */
    public void returned()
    {
        this.rented = false;
    }    

    /**
     * Return the rental status of the furniture item.
     * @return true if the furniture item is rented, otherwise false.
     */
    public boolean isRented()
    {
        return this.rented;
    }    
}
