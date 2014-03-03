
/**
 * This interface represents an item lookup.
 *
 * @author Franklin University
 * @version Winter 2013
 */

public interface Lookup
{
    /**
     * Indicates whether the item meets the criterion
     * passed into the contructor.
     * @param item the item being checked for a match.
     * @return true if the item matches, otherwise
     * false.
     */
    boolean matches (Item item);

}
