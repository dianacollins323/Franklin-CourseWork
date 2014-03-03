import java.io.Serializable;
import java.util.List;
import java.util.LinkedList;
//import java.util.ListIterator;
/**
 * A rental order.
 *
 * @author Franklin University
 * @version Winter 2013
 */
public class Order implements Serializable, Comparable
{
    private LinkedList<Item> orderItems;
    private String id;
    private String name;
    private String phoneNumber;
    private int weeks;

    /**
     * Constructor for objects of class Order. The constructor will throw an
     * IllegalArgumentException if any of the arguments are invalid. Any String
     * other than an empty string or null is valid for the String parameters.
     * The number of weeks must be a positive integer value.
     *
     * @param orderId the order id
     * @param custName the name of the customer placing the order
     * @param phone the phone number of the customer placing the order
     * @param wks the number of weeks the items are being rented
     */
    public Order(String orderId, String custName, String phone, int wks)
    {
        //validtate orderId
        if (orderId == null || orderId.equals(""))
        {
            throw new IllegalArgumentException("id cannot be empty or null");
        }
        //validate custName
        else if (custName == null || custName.equals(""))
        {
            throw new IllegalArgumentException("name cannot be empty or null");
        }
        //validate phone
        else if (phone == null || phone.equals(""))
        {
            throw new IllegalArgumentException("phone cannot be empty or null");
        }
        //validate weeks
        else if (wks <= 0)
        {
            throw new IllegalArgumentException("wks must be a pos number");
        }
        //create orderItems LinkedList
        //instantiate member variables
        else
        {
            orderItems = new LinkedList<Item>();
            id = orderId;
            name = custName;
            phoneNumber = phone;
            weeks = wks;
        }
    }

    /**
     * Retrieves the order id.
     *
     * @return the order id
     */
    public String getId()
    {
        return id;
    }

    /**
     * Retrieves the customer name.
     *
     * @return the customer name
     */
    public String getName()
    {
        return name;
    }

    /** Retrieves the customer phone number.
     *
     * @return the customer phone number
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * Retrieves the number of weeks.
     *
     * @return the number of weeks
     */
    public int getWeeks()
    {
        return weeks;
    }

    /**
     * Adds an item to the order. It can be assumed that the item exists in
     * inventory.
     *
     * @param  item the item to be added to the order
     */
    public void add(Item item)
    {
        //validates item !null and adds it to orderItems List
        if (item != null)
        {
            orderItems.add(item);
        }
    }

    /**
     * Retrieves the items from the order.
     *
     * @return a list of the items in the order
     */
    public List<Item> getItems()
    {
        return orderItems;
    }

    /**
     * Retrieves the total rental fee for the order.
     *
     * @return the total rental fee
     */
    public Money getTotalFee()
    {
        Money fee = new Dollar();
        Money totalFee = new Dollar();
        //iterates through OrderItems, calculates rental fee
        //for each item, adds the item fee to the totalFee
        for (Item e : orderItems)
        {
            fee = e.calculateFee(weeks);
            totalFee = totalFee.add(fee);
        }
        return totalFee;
    }
    
    /**
     * Compares the totalFee of order to
     * the backOrders to sort by greater
     * fee.
     * 
     * @param order the order to compare to backOrders
     * @return -1 if order is less than, 0 if order is 
     * equal to, and 1 if order is greater than 
     */
    public int compareTo(Object order)
    {
        Order backOrder = (Order) order;
        return this.getTotalFee().compareTo(backOrder.getTotalFee());
    }
}
