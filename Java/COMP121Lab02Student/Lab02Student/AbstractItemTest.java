
/**
 * The test class AbstractItemTest.
 *
 * @author  Franklin University
 * @version Winter 2013
 */
public abstract class AbstractItemTest extends junit.framework.TestCase
{
    /**
     * Item used for testing subclasses.
     */
    protected AbstractItem item;     // for use in subclass tests
    private static final String DESC = "Item description";
    private static final String ID = "123";
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int NEGATIVE = -1;
    private static final Money WKLY_RATE = new Dollar(23.99);
    private static final boolean TRUE = true;
    private static final boolean FALSE = false;

    /**
     * Default constructor for test class AbstractItemTest.
     */
    public AbstractItemTest()
    {
        // No code needed here
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
        item = createItem();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
        // No code needed here
    }

    /**
     * Test creating an Item.
     */
    public void testCreateItemMethod()
    {
        assertNotNull("Hint: creatItem() returned null instead of an " +
                      "Item object. ", createItem());
    }

    /**
     * Test Item mutator and accessor methods.
     */
    public void testItemSetAndGetMethods()
    {
        item.setDescription(DESC);
        assertEquals("Hint: getDescription() return value does not match " +
                     "set value. ",
                     DESC, item.getDescription());
        item.setWeeklyRate(WKLY_RATE);
        assertEquals("Hint: getWeeklyRate() return value does not match " +
                     "set value. ",
                     WKLY_RATE, item.getWeeklyRate());
        item.setId(ID);
        assertEquals("Hint: getId() return value does not match " +
                     "set value. ",
                     ID, item.getId());
    }


    /**
     * Test calculating the rental fees.
     */
    public void testCalculateFeeMethod()
    {
        // Test when the weekly rate is null
        assertNull("Hint: calculateFee() should return null " +
                   "if weekly rate is not set. ",
                   item.calculateFee(TWO));

        item.setWeeklyRate(WKLY_RATE);
        // Test when the number of weeks is 1
        assertEquals("Hint: calculateFee() should return a rate " +
                     "equal to the weekly rate when 1 is " +
                     "passed into the method. ",
                     WKLY_RATE, item.calculateFee(ONE));

        // Test when the number of weeks is more than 1
        assertEquals("Hint: calculateFee() should return a fee " +
                     "equal to n times the weekly rate where n " +
                     "is the number of weeks passed into the " +
                     "method. ",
                     WKLY_RATE.mul(TWO), item.calculateFee(TWO));

        // Test when the number of weeks is 0
        assertNull("Hint: calculateFee() should return null " +
                   "if number of weeks is 0. ",
                   item.calculateFee(ZERO));

        // Test when the number of weeks is negative
        assertNull("Hint: calculateFee() should return null " +
                   "if number of weeks is negative. ",
                   item.calculateFee(NEGATIVE));
    }

    /**
     * Test renting and returning an item.
     */
    public void testRentalStatusMethods()
    {
        // Test the default value.
        assertEquals("Hint: isRented() should return false " +
                     "if the item has not been rented. ",
                     FALSE, item.isRented());

        // Test setting the rental status to rented.
        item.rented();
        assertEquals("Hint: isRented() should return true " +
                     "if the item has been rented. ",
                     TRUE, item.isRented());

        // Test setting the rental status to not rented.
        item.returned();
        assertEquals("Hint: isRented() should return false " +
                     "if the item has been returned. ",
                     FALSE, item.isRented());
    }

    /**
     * Test setDescription method validation
     */
    public void testSetDescriptionMethodValidation()
    {
        //test setDescription validation null
        boolean exceptionThrown = false;
        try 
        {
            item.setDescription(null);
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
        
        //test setDescription validation emptyString
        exceptionThrown = false;
        try 
        {
            item.setDescription("");
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
    
    /**
     * Test setWeeklyRate metho validation
     */
    public void testSetWeeklyRateMethodValidation()
    {
        //test setWeeklyRate validation null
        boolean exceptionThrown = false;
        try
        {
            item.setWeeklyRate(null);
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
        
        //test setWeeklyRate validation negative param
        exceptionThrown = false;
        try
        {
            item.setWeeklyRate(new Dollar(-2.00));
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
        
        //test setWeeklyRate validation zero amount
        exceptionThrown = false;
        try
        {
            item.setWeeklyRate(new Dollar(0.00));
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
    
    /**
     * Test setId method validation
     */
    public void testSetIdMethodValidation()
    {
        //test setId validation null
        boolean exceptionThrown = false;
        try
        {
            item.setId(null);
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
        
        //test setId validation emptyString
        exceptionThrown = false;
        try
        {
            item.setId("");
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
        
        //test setId validation negative number
        exceptionThrown = false;
        try
        {
            item.setId("-1");
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
        
        //test setId validation zero
        try
        {
            item.setId("0");
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
    
    /**
     * test equals
     */
    public void testEqualsMethod()
    {
        AbstractItem queryItem = createItem();
        AbstractItem queryItem2 = createItem();

        //Test null items
        assertEquals(true, item.equals(queryItem));
        
        item.setDescription("item");
        //Test null queryItem description
        assertFalse(item.equals(queryItem));
        
        queryItem.setDescription("query item");
        //Test not matching descriptions
        assertFalse(item.equals(queryItem));
        
        queryItem.setDescription("item");
        //Test matching descriptions
        assertEquals(true, item.equals(queryItem));
        
        //Test null weeklyRates
        assertEquals(true, item.equals(queryItem));
        
        queryItem2.setWeeklyRate(new Dollar(2.00));
        //Test null item weeklyRate
        assertFalse(item.equals(queryItem2));
        
        item.setWeeklyRate(new Dollar(2.00));
        //Test null queryItem weeklyRate
        assertFalse(item.equals(queryItem));
        
        queryItem.setWeeklyRate(new Dollar(2.00));
        //Test matching weeklyRates
        assertEquals(true, item.equals(queryItem));
        
        queryItem2.setId("2");
        //Test null item id
        assertFalse(item.equals(queryItem2));
        
        item.setId("1");
        //Test null queryItem id
        assertFalse(item.equals(queryItem));
        
        queryItem.setId("1");
        //Test matching ids
        assertEquals(true, item.equals(queryItem));
        
        item.rented();
        //Test mismatching rentalStatuses
        assertFalse(item.equals(queryItem));
        
        queryItem.rented();
        //Test all instance fields matching
        assertEquals(true, item.equals(queryItem));
    }

    /**
     * Retrieves a concrete item object for testing.
     *
     * @return the item object for testing.
     */
    protected abstract AbstractItem createItem();
    
    /**
     * Work around
    public void testAll()
    {
        testCreateItemMethod();
        testItemSetAndGetMethods();
        testRentalStatusMethods();
        testSetDescriptionMethodValidation();
        testSetWeeklyRateMethodValidation();
        testSetIdMethodValidation();
    }
    */
}
