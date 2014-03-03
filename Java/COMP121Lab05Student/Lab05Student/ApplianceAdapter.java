import java.io.Serializable;
import java.util.Calendar;
/**
 * This class adapts an Appliance object 
 * so it can interact with ABCRentals as 
 * an Item object.
 * 
 * @author Diana Collins 
 * @version 1.0
 */
public class ApplianceAdapter implements Item, Serializable
{
    private Appliance appliance;
    private String id;

    /**
     * Constructor for objects of class ApplianceAdapter.
     * @param app appliance to adapt
     */
    public ApplianceAdapter(Appliance app)
    {
        if (app == null)
        {
            throw new IllegalArgumentException("null appliance");
        }
        else
        {
            this.appliance = app;
        }
    }
    
    /**
     * Return the description of the appliance item
     * by concatinating the appliance brand and model
     * @return the description
     */
    public String getDescription()
    {
        if (this.appliance.getBrand() == null && 
            this.appliance.getModel() == null)
        {
            return null;
        }
        else if (this.appliance.getBrand() == null || 
            this.appliance.getModel() == null)
        {
            if (this.appliance.getBrand() == null)
            {
                return ":" + this.appliance.getModel();
            }
            else
            {
                return this.appliance.getBrand() + ":";
            }
        }
        else if (this.appliance.getBrand().equals("") && 
            this.appliance.getModel().equals(""))
        {
            return ":";
        }
        else if (this.appliance.getBrand().equals(""))
        {
            return ":" + this.appliance.getModel();
        }
        else if (this.appliance.getModel().equals(""))
        {
            return this.appliance.getBrand() + ":";
        }
        else
        {
            return this.appliance.getBrand() + ":" + this.appliance.getModel();
        }
    }
    
    /**
     * Change the description of the appliance 
     * to the appliance brand and model
     * @param desc the description to split 
     */
    public void setDescription(String desc)
    {
        if (desc == null)
        {
            throw new IllegalArgumentException("desc is null");
        }
        else if (desc.indexOf(":") == -1)
        {
            throw new IllegalArgumentException("desc does not contain a :");
        }
        else
        {
            String [] tokens = desc.split(":", 2);
            String brand = tokens[0];
            String model = tokens[1];
            if (tokens[0].equals("") && tokens[1].equals(""))
            {
                this.appliance.setBrand(null);
                this.appliance.setModel(null);
            }
            else if (tokens[0].equals(""))
            {
                this.appliance.setBrand(null);
                this.appliance.setModel(model);
            }
            else if (tokens[1].equals(""))
            {
                this.appliance.setBrand(brand);
                this.appliance.setModel(null);
            }
            else
            {
                this.appliance.setBrand(brand);
                this.appliance.setModel(model);
            }
        }
    }
    
    /**
     * Return the ID of the appliance.
     * @return the ID.
     */
    public String getId()
    {
        return id;
    }
    
    /**
     * Change the ID of the appliance
     * to the given parameter.
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
        else if (Integer.parseInt(idNum) <= 0)
        {
            throw new IllegalArgumentException("idNum not pos num");
        }
        else
        {
            id = idNum;    
        }
    }
    
    /**
     * Return the appliance rentalFee
     * divided by 4
     * @return weeklyRate
     */
    public Money getWeeklyRate()
    {
        double fee = 0.00;
        if (this.appliance.getRentalFee() < 0)
        {
            throw new IllegalArgumentException("appliance" +
                " rentalFee is less than zero");
        }
        else if (this.appliance.getRentalFee() == 0)
        {
            fee = this.appliance.getRentalFee() / 4; 
        }
        else
        {
            fee = this.appliance.getRentalFee() / 4;   
        }
        return new Dollar(fee);
    }
    
    /**
     * Set appliance rentalFee by
     * converting item weeklyRate
     * @param wklyRt to convert
     */
    public void setWeeklyRate(Money wklyRt)
    {
        double fee = 0.00;
        if (wklyRt == null)
        {
            throw new IllegalArgumentException("rental rate " +
                "cannot be null");
        }
        else if (wklyRt.isNegative())
        {
            throw new IllegalArgumentException("rental rate " +
                "cannot be negative");
        }
        else if (wklyRt.compareTo(new Dollar(0.00)) == 0)
        {
            throw new IllegalArgumentException("rental rate " +
                "cannot be zero");
        }
        else
        {
            fee = wklyRt.asDouble() * 4;
        }
        this.appliance.setRentalFee(fee);
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
        if ((this.getWeeklyRate() == null) || (weeks <= 0))
        {
            return null;
        }
        else
        {
            return this.getWeeklyRate().mul(weeks);
        }
    }
    
    /**
     * Return the rental status of the appliance.
     * @return true if the appliance is rented, otherwise false.
     */
    public boolean isRented()
    {
        if (this.appliance.getDateRented() == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * Indicate the appliance has been returned and is no
     * longer rented by setting dateRented to null.
     */
    public void returned()
    {
        this.appliance.setDateRented(null);
    }
    
    /**
     * Indicate the item has been rented by setting
     * dateRented to the current calendar date and time.
     */
    public void rented()
    {
        this.appliance.setDateRented(Calendar.getInstance());
    }
}
