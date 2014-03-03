
/**
 * This class is a lookup that matches items that exactly match
 * a specified item, to be passed into the constructor when creating
 * the instance of the lookup.
 *
 * @author Franklin University
 * @version Winter 2013
 */
public class ItemLookup implements Lookup
{
    AbstractItem queryItem;	

    /**
	 * Constructor for objects of class IdLookup.
	 * @param item the item to lookup.
	 */
	public ItemLookup(Item item)
	{
	    queryItem = (AbstractItem) item;
	}

    /**
     * Indicates whether the item exactly matches the item
     * passed into the contructor.
     * @param item the item being checked for a match.
     * @return true if the item matches, otherwise
     * false.
     */
    public boolean matches (Item item)
    {
        if (item == null && queryItem == null)
        {
            return true;
        }
        else if (item == null || queryItem == null)
        {
            return false;
        }
        else
        {
            return item.equals(queryItem);
        }
    }
}
