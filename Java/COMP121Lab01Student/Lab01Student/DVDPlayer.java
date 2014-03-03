

/**
 * This class represents a DVD player to be rented.  
 * 
 * @author Franklin University
 * @version Winter 2013
 */
public class DVDPlayer implements Item
{
    private String description;
    private Money weeklyRate;
    private String itemId;
    private boolean rented;
    private Money rentalFee = new Dollar(0.00);
    
    /**
     * Constructor for objects of class DVDPlayer.
     */
    public DVDPlayer()
    {
        this.rented = false;
    }

    /**
     * Return the description of the DVD player.
     * @return the description.
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     * Change the description of the DVD player to the 
     * given parameter.
     * @param desc the new description.
     */
    public void setDescription(String desc)
    {
        this.description = desc;
    }
    
    /**
     * Return the weekly rental rate for the DVD player.
     * @return the rate.
     */
    public Money getWeeklyRate()
    {
        return this.weeklyRate;
    }

    /**
     * Change the weekly rental rate of the DVD player
     * to the given parameter.
     * @param wklyRate the new weekly rate.
     */
    public void setWeeklyRate(Money wklyRate)
    {
        this.weeklyRate = wklyRate;
    }    
     
    /**
     * Return the ID of the DVD player.
     * @return the ID.
     */
    public String getId()
    {
        return this.itemId;
    }    
    
    /**
     * Change the ID of the DVD player to the given 
     * parameter.
     * @param idNum the new ID.
     */
    public void setId(String idNum)
    {
        this.itemId = idNum;
    }    

    /**
     * Calculate the fees for renting the DVD player for a given
     * number of weeks. The weekly rate must be set and the 
     * number of rental weeks must be valid in order for the fee 
     * to be calculated.
     * @param weeks the number of rental weeks
     * @return the fee or null if the fee could not be calculated
     */
    public Money calculateFee(int weeks)
    {
        try {
            //calculates rental fee
            if (weeks > 0) {
                this.rentalFee = getWeeklyRate().mul(weeks);
            }
            else {
                this.rentalFee = null;
            }
        }
        catch (NullPointerException e) {
            //catche null exception
            this.rentalFee = null;
        }
        
        return this.rentalFee;
    }  
    
    /**
     * Indicate the DVD player has been rented.
     */
    public void rented()
    {
        this.rented = true;
    }
    
    /**
     * Indicate the DVD player has been returned and is no
     * longer rented.
     */
    public void returned()
    {
        this.rented = false;
    }    

    /**
     * Return the rental status of the DVD player.
     * @return true if the DVD player is rented, otherwise false.
     */
    public boolean isRented()
    {
        return this.rented;
    }    
}
