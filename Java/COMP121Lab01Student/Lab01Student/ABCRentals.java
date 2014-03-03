
/**
 * An implementation of the inventory system for ABC Rentals.
 * 
 * @author Franklin University
 * @version Winter 2013
 */
public class ABCRentals implements Inventory
{
    private Item[] inventory;
    private int itemId = 0;
    private int invSize = 0;
    
    /**
     * Default constructor.
     */
    public ABCRentals()
    {
        inventory = new Item[300];
    }
    
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
    public boolean add(Item item)
    {
        boolean found = false;
        boolean itemAdded = false;
        
        try {
            //if invSize is 0 add item 
            if (invSize == 0) {
                this.itemId++;
                String id = Integer.toString(itemId);
                item.setId(id);
                inventory[invSize] = item;
                this.invSize++;
                itemAdded = true;
            }
            else {
                //search for matching description
                for (int i = 0; i < invSize; i++) {
                    if (item.getDescription().equalsIgnoreCase(inventory[i]
                        .getDescription())) {
                        found = true;
                    }
                }
                //if description doesn't matcha dd item
                if (invSize < inventory.length && !found) {
                    this.itemId++;
                    String id = Integer.toString(itemId);
                    item.setId(id);
                    inventory[invSize] = item;
                    this.invSize++;
                    itemAdded = true;      
                }
                else {
                    itemAdded = false;
                }
            }
        }
        catch (NullPointerException e) {
            //catch null exception and return false
            itemAdded = false;
        }
        
        return itemAdded;
    }
    
    /**
     * Remove an item.  The id should be the id of
     * an item in the inventory.  The item with the 
     * specified id is located in inventory and removed.
     * @param id the id of the item to remove.
     * @return true if the item was found and removed, false otherwise.
     */
    public boolean remove(String id)
    {
        int index = 0;
        boolean found = false;
        boolean itemDeleted = false;
        
        //iterates through array to find matching item id
        for (int i = 0; i < invSize; i++) {
            if (this.inventory[i].getId().equalsIgnoreCase(id)) {
                index = i;
                found = true;
            }
        }
        
        //if found removes and adjusts inventory array
        if (found) {
            inventory[index] = inventory[invSize - 1];
            invSize--;
            itemDeleted = true;
        }
        else {
            itemDeleted = false;
        }
        
        return itemDeleted;
    }

    /**
     * Return the number of items presently in inventory.
     * @return the number of items.
     */
    public int getItemCount()
    {
        return this.invSize;
    }
    
     /**
     * Create and return an array of items that match the given query
     * criteria.  The query will identify which items match.  The length
     * of the returned array should be equal to the number of items
     * that match, and each element in the array should be a matching
     * item.
     * @param query a query indicating which items to find.
     * @return an array of matching items.
     */
    public Item [] findItems(Lookup query)
    {
        Lookup searchQuery = query;
        int numFound = 0;
        
        //iterates through array to find number of items matching query
        for (int i = 0; i < invSize; i++) {
            if (searchQuery.matches(inventory[i])) {
                numFound++;
            }
        }
        
        Item[] itemsFound = new Item[numFound];
        int index = 0;
        
        //creates itemsFound array of specified size 
        for (int i = 0; i < invSize; i++) {
            if (searchQuery.matches(inventory[i])) {
                itemsFound[index] = inventory[i];
                index++;
            }
        }
        
        return itemsFound;
    }

    /**
     * Rent an item in inventory. The rental fee for the item is
     * calculated, and the item is marked as rented.
     * @param id the id of the item to be rented.
     * @param weeks the number of weeks the item is to be rented.
     * @return the rental fee if the item was available for rent,
     * null otherwise.
     */
    public Money rent(String id, int weeks)
    {
        Money itemRent;
        int index = 0;
        boolean found = false;
        boolean rented = false;
        
        //iterates theough array to find id number
        for (int i = 0; i < this.invSize; i++) {
            if (this.inventory[i].getId().equalsIgnoreCase(id)) {
                index = i;
                found = true;
                if (this.inventory[index].isRented()) {
                    rented = true;
                }
            }
        }
        
        //rents item if id num is found
        if (found && !rented) {
            inventory[index].rented();
            itemRent = inventory[index].calculateFee(weeks);
        }
        else {
            itemRent = null;
        } 
        
        return itemRent;
    }

    /**
     * Return an item to inventory. The item is marked as not 
     * rented. Note that if the item isn't already rented, it cannot
     * be restocked.
     * @param id the id of the restocked item.
     * @return true if the item was sucessfully returned to inventory,
     * false otherwise.
     */
    public boolean restock(String id)
    {
        boolean restocked = false;
        int index = 0;
        boolean found = false;
        boolean rented = false;
        
        //iterates through array to find matching id number
        for (int i = 0; i < invSize; i++) {
            if (inventory[i].getId().equalsIgnoreCase(id)) {
                index = i;
                found = true;
                if (inventory[index].isRented()) {
                    rented = true;
                }
            }
        }
        
        //removes item if id is found
        if (found && rented) {
            inventory[index].returned();
            restocked = true;
        }
        else if (found && !rented) {
            restocked = false;
        }
        else {
            restocked = false;
        }
        
        return restocked;
    }
}
