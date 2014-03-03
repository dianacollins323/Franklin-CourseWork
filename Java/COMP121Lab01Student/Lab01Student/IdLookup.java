
/**
 * This class is a lookup that matches items whose id matches exactly
 * a specified id, to be passed into the constructor when creating
 * the instance of the lookup.  
 * 
 * @author Franklin University
 * @version Winter 2013
 */
public class IdLookup implements Lookup
{
    private String check;
    private boolean doesMatch;

    /**
     * Constructor for objects of class IdLookup.
     * @param id the id to lookup.
     */
    public IdLookup(String id)
    {
        this.check = id;
    }
    
    /**
     * Indicates whether the item's id exactly matches the id 
     * passed into the contructor.
     * @param item the item being checked for a match.
     * @return true if the id of the item matches, otherwise
     * false.
     */
    public boolean matches (Item item)
    {
        try {
            //checks for an exact match to query
            if (this.check == null && item.getId() == null) {
                this.doesMatch = true;
            }
            else if (this.check.equals(item.getId())) {
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
