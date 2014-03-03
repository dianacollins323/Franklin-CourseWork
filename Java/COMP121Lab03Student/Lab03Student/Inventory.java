
/**
 * An abstraction of an inventory system. 
 * 
 * @author Franklin University
 * @version Winter 2013
 */

public interface Inventory
{
    /**
     * Add an item to the inventory.  The parameter item
     * should not be something already in the inventory.  As a
     * result of adding an item, the item will be assigned an ID starting
     * with 1 and increasing by 1 for each item added.  IDs
     * are not reused.  All items are kept
     * in a database internal to the inventory so that they may be searched
     * for using a query.
     * @param item the item to add.
     * @return true when the item is added, false if the item cannot
     * be added for any reason.
     */
    boolean add(Item item);
    
    /**
     * Remove an item.  The parameter item should
     * be an item in the inventory.  The specified item is
     * located in the database and removed.
     * @param id the id of the item to remove.
     * @return true if the item was found and removed, false otherwise.
     */
    boolean remove(String id);
    
    /**
     * Return the number of items presently in inventory.
     * @return the number of items.
     */
    int getItemCount();
    
    /**
     * Create and return an array of items that match the given query
     * criteria.  The query will identify which items match.  The length
     * of the returned array should be equal to the number of items
     * that match, and each element in the array should be a matching
     * item.
     * @param query a query indicating which items to find
     * @return an array of matching items.
     */
    Item [] findItems(Lookup query);
    
    /**
     * The maximum number of items that an inventory can have.
     */
    static final int MAX_ITEMS = 300;
}
