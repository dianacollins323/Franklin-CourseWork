

/**
 * This class represents a television to be rented.  
 * 
 * @author Franklin University
 * @version Winter 2013
 */
public class Television implements Item
{
    private boolean rented;
    private String description;
    private Money weeklyRate;
    private String itemId;
    private int tvSize;
    private String tvType;
    private Money rentalFee = new Dollar(0.00);

    /**
     * Constructor for objects of class Television.
     */
    public Television()
    {
        this.rented = false;
    }

    /**
     * Return the description of the Television.
     * @return the description.
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     * Change the description of the television to the 
     * given parameter.
     * @param desc the new description.
     */
    public void setDescription(String desc)
    {
        this.description = desc;
    }
    
    /**
     * Return the weekly rental rate for the television.
     * @return the rate.
     */
    public Money getWeeklyRate()
    {
        return this.weeklyRate;
    }

    /**
     * Change the weekly rental rate of the television
     * to the given parameter.
     * @param wklyRate the new weekly rate.
     */
    public void setWeeklyRate(Money wklyRate)
    {
        this.weeklyRate = wklyRate;
    }    
     
    /**
     * Return the ID of the television.
     * @return the ID.
     */
    public String getId()
    {
        return this.itemId;
    }    
    
    /**
     * Change the ID of the television to the given 
     * parameter.
     * @param idNum the new ID.
     */
    public void setId(String idNum)
    {
        this.itemId = idNum;
    }    

    /**
     * Calculate the fees for renting the television for a given
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
            //catches null exception
            this.rentalFee = null;
        }
        
        return this.rentalFee;
    }  
    
    /**
     * Return the size of the television.
     * @return the size.
     */
    public int getSize()
    {
        return this.tvSize;
    }    
    
    /**
     * Change the size of the television to the given 
     * parameter.
     * @param screenSize the new size.
     */
    public void setSize(int screenSize)
    {
        this.tvSize = screenSize;
    }  
    
    /**
     * Return the type of the television.
     * @return the type.
     */
    public String getType()
    {
        return this.tvType;
    }    
    
    /**
     * Change the type of the television to the given 
     * parameter.
     * @param screenType the new type.
     */
    public void setType(String screenType)
    {
        this.tvType = screenType;
    }        
    
    /**
     * Indicate the television has been rented.
     */
    public void rented()
    {
        this.rented = true;
    }
    
    /**
     * Indicate the television has been returned and is no
     * longer rented.
     */
    public void returned()
    {
        this.rented = false;
    }    

    /**
     * Return the rental status of the television.
     * @return true if the television is rented, otherwise false.
     */
    public boolean isRented()
    {
        return this.rented;
    }    
}
