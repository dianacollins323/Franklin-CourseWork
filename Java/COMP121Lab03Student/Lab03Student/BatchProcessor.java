import java.io.*;
import java.util.Scanner;
/**
 * Processes inventory transactions from a file.
 * 
 * @author Diana Collins
 * @version 1.0
 */
public class BatchProcessor
{
    private Inventory inventory;
    private Item item;
    
    /**
     * Constructor for objects of class BatchProcessor.
     * 
     * @param inv the inventory to which the transactions are applied
     */
    public BatchProcessor(Inventory inv)
    {
        inventory = new ABCRentals();
        inventory = inv;
    }

    /**
     * Reads the transactions from a file and executes them on
     * the inventory.
     * 
     * @param fileName the name of the file from which to read
     * @return the number of successfully executed transactions
     */
    public int process(String fileName)
    {
        FileReader inFile;
        int transNum = 0;
        try
        {
            inFile = new FileReader(fileName);
            transNum = process(inFile);
            inFile.close();
        }
        catch (FileNotFoundException e)
        {
            //blah
        }
        catch (IOException e)
        {
            //blah
        }
        return transNum;
    }
    
    /**
     * Reads the transactions from the given Reader,
     * executing them on the inventory.
     * 
     * @param reader the reader from which to read
     * @return the number of successfully executed transactions
     */
    public int process(Reader reader)
    {
        ABCRentals inv = (ABCRentals) inventory;
        int transNum = 0;
        String id;
        Scanner readFile = new Scanner(reader);
        
        
        while (readFile.hasNextLine())
        {
            String line = readFile.nextLine();
            String [] tokens = line.split(" ", 2);
            String command = tokens[0];
            
            if (command.equalsIgnoreCase("add"))
            {
                this.addCommand(tokens[1]);
                inv.add(item);
                transNum++;
            }
            else if (command.equalsIgnoreCase("remove"))
            {
                inv.remove(tokens[1]);
                transNum++;
            }
            else if (command.equalsIgnoreCase("rent"))
            {
                String [] rentInfo = tokens[1].split(" ");
                id = rentInfo[0];
                int weeks = Integer.parseInt(rentInfo[1]);
                inv.rent(id, weeks);
                transNum++;
            }
            else if (command.equalsIgnoreCase("restock"))
            {
                inv.restock(tokens[1]);
                transNum++;
            }
            else if (command.equalsIgnoreCase("update"))
            {
                String [] updateInfo = tokens[1].split(" ", 2);
                id = updateInfo[0];
                Lookup query = new IdLookup(id);
                item = inv.findItems(query)[0];
                this.fieldValueProcessor(updateInfo[1]);
                transNum++;
            }
            
        }
        
        return transNum;
    }
    
    /**
     * Splits addTokens to find type
     * Creates new item of specified type
     * Passes the rest of the string
     * to the fieldValueProcessor method
     * 
     * @param token the string from which to find type
     */
    public void addCommand(String token)
    {
        String [] addTokens = token.split(" ", 2);
        String type = null;
        String pairs = null;
        
        if (addTokens.length < 2)
        {
            type = addTokens[0];  
            if (type.equals("DVD"))
            {
                item = new DVDPlayer();
            }
            else if (type.equals("TV"))
            {
                item = new Television();
            }
            else if (type.equals("Furniture"))
            {
                item = new Furniture();
            }
        }
        else
        {
            type = addTokens[0];
            pairs = addTokens[1];
            if (type.equals("DVD"))
            {
                item = new DVDPlayer();
                this.fieldValueProcessor(pairs);
            }
            else if (type.equals("TV"))
            {
                item = new Television();
                this.fieldValueProcessor(pairs);
            }
            else if (type.equals("Furniture"))
            {
                item = new Furniture();
                this.fieldValueProcessor(pairs);
            }
        }
    }
    
    /**
     * Splits token into pairTokens
     * Splits pairTokens into field and value variables
     * Processes values according to the type of field
     * 
     * @param token the token from which to find field and value pairs
     */
    public void fieldValueProcessor(String token)
    {
        String [] pairTokens = token.split(",");
        
        for (int i = 0; i < pairTokens.length; i++)
        {
            String [] fieldValue = pairTokens[i].split("=");
            String field = fieldValue[0];
            String value = fieldValue[1];
            
            if (field.equals("desc"))
            {
                item.setDescription(value);
            }
            else if (field.equals("rented"))
            {
                if (value.equalsIgnoreCase("t"))
                {
                    item.rented();
                }
            }
            else if (field.equals("wkRate"))
            {
                double rate = Double.parseDouble(value);
                item.setWeeklyRate(new Dollar(rate));
            }
            else if (field.equals("type") && item instanceof Television)
            {
                Television tv = (Television) item;
                tv.setType(value);
            }
            else if (field.equals("size") && item instanceof Television)
            {
                int size = Integer.parseInt(value);
                Television tv = (Television) item;
                tv.setSize(size);
            }
            else if (field.equals("monRate") && item instanceof Furniture)
            {
                double rate = Double.parseDouble(value);
                Furniture furn = (Furniture) item;
                furn.setMonthlyRate(new Dollar(rate));
            }
            
        }
    }
}
