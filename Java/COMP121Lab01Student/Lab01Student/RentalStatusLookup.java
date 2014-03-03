
/**
 * This class is a lookup that matches items whose rental status
 * matches the status passed into the constructor when creating
 * the instance of the lookup.  
 * 
 * @author Franklin University
 * @version Winter 2013
 */
public class RentalStatusLookup implements Lookup
{
    private boolean check;
    private boolean doesMatch;
    
    /**
     * Constructor for objects of class RentalStatusLookup.
     * @param status the rental status passed into the contructor.
     */
    public RentalStatusLookup(boolean status)
    {
        this.check = status;
    }

    /**
     * Indicates whether the item's rental status matches 
     * the rental status passed into the contructor.
     * @param item the item being checked for a match.
     * @return true if the rental status of the item matches, otherwise
     * false.
     */
    public boolean matches (Item item)
    {
        try {
            //checks for exact query match
            if (this.check == item.isRented()) {
                this.doesMatch = true;
            }
            else {
                this.doesMatch = false;
            }
        }
        catch (NullPointerException e) {
            //catches null exception
            this.doesMatch = false;
        }
        return this.doesMatch;
    }
}
