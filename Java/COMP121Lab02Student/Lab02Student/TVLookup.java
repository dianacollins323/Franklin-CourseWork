
/**
 * This class is a lookup that matches Television items whose size and type
 * match a specified item, to be passed into the constructor when creating
 * the instance of the lookup.
 *
 * @author Franklin University
 * @version Winter 2013
 */
public class TVLookup implements Lookup
{
    private int lookupSize;
    private String lookupType;
    /**
     * Constructor for objects of class IdLookup.
     * @param size the size of the television to lookup.
     * @param type the type of the teleivision to lookup.
     */
    public TVLookup(int size, String type)
    {
        lookupSize = size;
        lookupType = type;
    }

    /**
     * Indicates whether the item matches the size and type
     * passed into the contructor.
     * @param item the item being checked for a match.
     * @return true if the size and type match, otherwise
     * false. Note that case should be ignored when matching
     * the type.
     */
    public boolean matches (Item item)
    {
        if (item == null)
        {
            return false;
        }
        Television newItem = (Television) item;
        int size = newItem.getSize();
        String type = newItem.getType();
        
        if (lookupType == null && type == null)
        {
            if (lookupSize == size)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if (lookupType == null || type == null)
        {
            return false;
        }
        else if (lookupType.equals(type) && lookupSize == size)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
