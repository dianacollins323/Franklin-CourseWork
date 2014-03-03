import java.io.*;

/**
 * An implementation of the inventory system for ABC Rentals.
 * 
 * @author Franklin University
 * @version Winter 2013
 */
public class ABCRentals implements Inventory, Serializable
{
    private Item[] items;
    private int numItems = 0;
    private int nextId = 1;

    /**
     * Default constructor.
     */
    public ABCRentals()
    {
        items = new Item[MAX_ITEMS];
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
        if ((item != null) && (numItems < items.length))
        {
            items[numItems++] = item;
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
        for (int i = 0 ; i < numItems ; i++ )
        {
            Item object = items[i];

            if (object.getId().equals(id))
            {
                items[i] = null;
                for (int j = i ; j < (numItems - 1) ; j++ )
                {
                    items[j] = items[j + 1]; //Copy the next one into this slot
                }
                items[numItems - 1] = null; //Mark the last one null
                
                numItems--; //Decrement numItems to reflect new empty slot
                
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
        return numItems;
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
        int count = 0;
        for (int i = 0 ; i < numItems ; i++ )
        {
            Item object = items[i];
            if (query.matches(object))
            {
                count++;
            }
        }
        
        Item[] result = new Item[count];

        int resultPosition = 0;
        
        for (int i = 0 ; i < numItems ; i++ )
        {
            Item object = items[i];
            if (query.matches(object))
            {
                result[resultPosition++] = object;
            }
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
        for (int i = 0 ; i < numItems ; i++ )
        {
            Item object = items[i];
            if (object.getId().equals(id))
            {
                if (object.isRented())
                {
                    //Already rented
                    return null;
                }
                
                Money fee = object.calculateFee(weeks);
                object.rented();
                return fee;
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
        for (int i = 0 ; i < numItems ; i++ )
        {
            Item object = items[i];
            if (object.getId().equals(id))
            {
                if (object.isRented())
                {
                    object.returned();
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
        File f = new File(fileName);
        ObjectInputStream invIn = null;
        
        try
        {
            if (f.exists())
            {
                invIn = new ObjectInputStream(new FileInputStream(f));
                inventory = (ABCRentals) invIn.readObject();
                invIn.close();
            }
            else
            {
                throw new FileNotFoundException("file does not exist");
            }
        }
        catch (FileNotFoundException e)
        {
            inventory = null;
        }
        catch (IOException e)
        {
            inventory = null;
        }
        catch (ClassNotFoundException e)
        {
            inventory = null;
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
        File f = new File(fileName);
        ObjectOutputStream invOut = null;
        
        try
        {
            if (f.exists())
            {
                invOut = new ObjectOutputStream(new FileOutputStream(f));
                invOut.writeObject(this);
                invOut.close();
            }
            else
            {
                f.createNewFile();
                invOut = new ObjectOutputStream(new FileOutputStream(f));
                invOut.writeObject(this);
                invOut.close();
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.print("the file was not found");
        }
        catch (IOException e)
        {
            System.out.print("an exception was thrown");
        }
        
    }    
}
