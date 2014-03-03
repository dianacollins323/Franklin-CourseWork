import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * An implementation of the inventory system for ABC Rentals.
 *
 * @author Franklin University
 * @version Winter 2013
 */
public class ABCRentals implements Inventory, Serializable
{
    private LinkedList<Item> items;
    private LinkedList<Order> backOrders = new LinkedList<Order>();
    private int nextId = 1;

    /**
     * Default constructor.
     */
    public ABCRentals()
    {
        items = new LinkedList<Item>();
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
        //check item is not null and there is room in invetory
        if ((item != null) && (items.size() < 300))
        {
            //adding item to inventory
            items.add(item);
            //setting id number and increasing id for next item
            item.setId("" + nextId++);
            return true;
        }
        
        return false;
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
        //Iterate through LinkedList
        for (Item e : items)
        {
            //matching id to item id to remove
            if (e.getId().equals(id))
            {
                //removing item that matches id
                items.remove(e);
                return true;
            }
        }
        
        return false;
    }

    /**
     * Return the number of items presently in inventory.
     * @return the number of items.
     */
    public int getItemCount()
    {
        return items.size();
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
        //LinkedList to temporarily store foundItems in
        LinkedList<Item> foundItems = new LinkedList<Item>();
        
        //finding items and adding them to temporary LinkedList
        for (Item e : items)
        {
            if (query.matches(e))
            {
                foundItems.add(e);
            }
        }
        
        //Array to return and ListIterator to iterate 
        //through foundItems LinkedList
        Item[] result = new Item[foundItems.size()];
        ListIterator itr = foundItems.listIterator();
        
        //adding foundItems to result array to be returned
        for (int i = 0 ; i < result.length ; i++)
        {
            result[i] = (Item) itr.next();
        }

        return result;
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
        //iterate through LinkedList
        for (Item e : items)
        {
            //matching item id
            if (e.getId().equals(id))
            {
                //checking rental status
                if (!e.isRented())
                {
                    //calculating fee and marking as rented
                    Money fee = e.calculateFee(weeks);
                    e.rented();
                    return fee;
                }
                else
                {
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * Return a rented item to inventory. The item is marked as not
     * rented. Note that if the item isn't already rented, it cannot
     * be restocked.
     * @param id the id of the restocked item.
     * @return true if the item was sucessfully returned to inventory,
     * false otherwise.
     */
    public boolean restock(String id)
    {
        //iterate through LinkedList
        for (Item e : items)
        {
            //matching item id
            if (e.getId().equals(id) && e.isRented())
            {
                //checking rental status
                if (e.isRented())
                {
                    //marking as not rented
                    e.returned();
                    processBackOrder(checkBackOrders());
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Recreates the inventory from a persisted object file.  The file should
     * have been created by serialization of a prior inventory object.
     *
     * @param fileName the name of the file containing the inventory object
     * @return the ABCRentals object read from the file
     */
    public static ABCRentals readFromFile(String fileName)
    {
        ABCRentals inventory = null;

        try
        {
            ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(fileName));
            try
            {
                inventory = (ABCRentals) in.readObject();
            }
            finally
            {
                in.close();
            }
        }
        // Convert any exception to an unchecked exception
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }

        return inventory;
    }

    /**
     * Writes the current inventory to the given file name using
     * object serialization.
     *
     * @param fileName the name of the file to write.
     */
    public void writeToFile(String fileName)
    {
        try
        {
            ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(fileName));
            try
            {
                out.writeObject(this);
            }
            finally
            {
                out.close();
            }
        }
        // Convert any exception to an unchecked exception
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Processes a rental order. The items in the order are marked as rented
     * only if all the items are available for rent. If the entire order
     * cannot be processed, it is saved and processed when the items have
     * been restocked.
     *
     * @param order The order to process.
     * @return true if and only if the order can be processed.
     */
    public boolean processOrder(Order order)
    {
        if (orderInStock(order))
        {
            handleOrder(order);
            return true;
        }
        else
        {
            backOrders.add(sortBackOrders(order), order);
            return false;
        }
    }
    
    /**
     * checks if all items in order are in stock
     * 
     * @param order to check
     * @return true if all items in order 
     * are in stock, false if not
     */
    public boolean orderInStock(Order order)
    {
        ListIterator itr = order.getItems().listIterator();
        Item item = null;
        int count = 0;
        int itemsInStock = 0;
        if (itr.hasNext())
        {
            item = (Item) itr.next();
            //checking rental status of each item in order
            while (count < order.getItems().size())
            {
                if (!item.isRented())
                {
                    itemsInStock++;
                }
                if (itr.hasNext())
                {
                    item = (Item) itr.next();
                }
                count++;
            }
        }
        //check if all items in order are in stock
        if (itemsInStock == order.getItems().size())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * if isOrderInStock returns true items are marked
     * as rented for customer pickup, else order is saved
     * 
     * @param order to be rented
     */
    public void handleOrder(Order order)
    {
        ListIterator itr = order.getItems().listIterator();
        Item item = null;
        int count = 0;
        if (itr.hasNext())
        {
            item = (Item) itr.next();
            while (count < order.getItems().size())
            {
                item.rented();
                if (itr.hasNext())
                {
                    item = (Item) itr.next();
                }
                count++;
            }
        }
    }
    
    /**
     * Processes a back order. The items in the order are marked as rented
     * only if all the items are available for rent. If the entire order
     * cannot be processed, it is saved and processed when the items have
     * been restocked.
     *
     * @param order The back order to process.
     * @return true if and only if the order can be processed.
     */
    public boolean processBackOrder(Order order)
    {
        Order thisOrder = order;
        if (thisOrder != null)
        {
            handleOrder(thisOrder);
            getBackOrders().remove(thisOrder);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * @return backOrder list
     */
    public LinkedList<Order> getBackOrders()
    {
        return backOrders;
    }
    
    /**
     * Sorts backOrders list
     * putting highest totalFee at beginning
     * 
     * @param order to add to list
     * @return index for place in 
     * backOrders to add order
     */
    public int sortBackOrders(Order order)
    {
        ListIterator itr = getBackOrders().listIterator();
        Order backOrder = null;
        int count = 0;
        int index = 0;
        if (getBackOrders().size() == 0)
        {
            index = 0;
            //backOrders.add(order);
        }
        else
        {
            backOrder = (Order) itr.next();
            while (count < getBackOrders().size())
            {
                if (order.compareTo(backOrder) > 0)
                {
                    index = backOrders.indexOf(backOrder);
                    //backOrders.add(count, order);
                }
                else if (order.compareTo(backOrder) < 0 && !itr.hasNext())
                {
                    index = backOrders.size();
                    //backOrders.add(order);
                }
            
                if (itr.hasNext())
                {
                    backOrder = (Order) itr.next();
                }
                count++;
            }
        }
        return index;
    }
    
    /**
     * Check backOrders for orderInStock
     * 
     * @return foundOrder order to be filled
     * or null if no backOrders can be filled
     */
    public Order checkBackOrders()
    {
        ListIterator itr = getBackOrders().listIterator();
        Order backOrder = null;
        int count = 0;
        Order foundOrder = null;
        if (getBackOrders().size() == 0)
        {
            return foundOrder;
        }
        else
        {
            backOrder = (Order) itr.next();
            while (count < getBackOrders().size())
            {
                if (foundOrder == null)
                {
                    if (orderInStock(backOrder))
                    {
                        foundOrder = backOrder;
                    }
             
                    if (itr.hasNext())
                    {
                        backOrder = (Order) itr.next();
                    }
                }
                count++;
            }
        }
        return foundOrder;
    }
}
