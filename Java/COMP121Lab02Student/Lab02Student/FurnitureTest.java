
/**
 * The test class FurnitureTest.
 *
 * @author  Franklin University
 * @version Winter 2013
 */
public class FurnitureTest extends AbstractItemTest
{

    /**
     * Default constructor for test class DVDPlayerTest.
     */
    public FurnitureTest()
    {
        // Default constructor
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
        super.setUp();
    }

    /**
     * Creates a concrete item to test. DO NOT MODIFY THIS METHOD.
     *
     * @return the item
     */
    protected AbstractItem createItem()
    {
        return new Furniture();
    }
    
    /**
     * Test setMonthlyRate method
     */
    public void testSetMonthlyRateMethod()
    {
        //casting abstractItem to type furniture
        Furniture newItem = (Furniture) item;
        
        boolean exceptionThrown = false;
        //Test null
        try
        {
            newItem.setMonthlyRate(null);
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
        
        exceptionThrown = false;
        //Test negative
        try
        {
            newItem.setMonthlyRate(new Dollar(-2.00));
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
        
        exceptionThrown = false;
        //Test zero
        try
        {
            newItem.setMonthlyRate(new Dollar(0.00));
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
        
        //Test setMonthlyRate
        newItem.setMonthlyRate(new Dollar(2.00));
    }
    
    /**
     * Test Furniture calculateFee method
     */
    public void testFurnitureCalculateFeeMethod()
    {
        //casting abstractItems to type furniture
        Furniture newItem = (Furniture) item;
        
        //Test null weeklyRate
        assertNull(newItem.calculateFee(3));
        
        //Test zero weeks
        assertNull(newItem.calculateFee(0));
        
        //Test weeks < 0
        assertNull(newItem.calculateFee(-3));
        
        //Test weeks < 4
        newItem.setWeeklyRate(new Dollar(2.00));
        assertEquals(new Dollar(6.00), newItem.calculateFee(3));
        
        //Test weeks > 4 and null monthlyRate
        assertNull(newItem.calculateFee(6));
        
        //Test > 4
        newItem.setMonthlyRate(new Dollar(6.00));
        assertEquals(new Dollar(10.00), newItem.calculateFee(6));
    }

    /**
     * test equals method to account for furniture monthlyRate
     */
    public void testFurntureEqualsMethod()
    {
        AbstractItem queryItem = createItem();
        AbstractItem queryItem2 = createItem();
        //casting abstractItems to type furniture
        Furniture newItem = (Furniture) item;
        Furniture newQueryItem = (Furniture) queryItem;
        Furniture newQueryItem2 = (Furniture) queryItem2;
        
        //Test null monthlyRates
        assertEquals(true, newItem.equals(newQueryItem));
        
        newQueryItem2.setWeeklyRate(new Dollar(2.00));
        newQueryItem2.setMonthlyRate(new Dollar(4.00));
        //Test null monthlyRate
        assertFalse(newItem.equals(newQueryItem2));
        
        newItem.setMonthlyRate(new Dollar(5.00));
        //Test null newQueryItem monthlyRate
        assertFalse(newItem.equals(newQueryItem));
        
        newQueryItem.setMonthlyRate(new Dollar(5.00));
        //Test matching furniture items
        assertEquals(true, newItem.equals(newQueryItem));
        
        //Test not matching items
        assertFalse(newItem.equals(newQueryItem2));
    }
    
    /**
     * Work Around
     */
    public void testCreatItem()
    {
        super.testCreateItemMethod();
    }
    
    /**
     * Work around
     */
    public void testItemSetAndGet()
    {
        super.testItemSetAndGetMethods();
    }
    
    /**
     * work around
     */
    public void testCalculateFee()
    {
        super.testCalculateFeeMethod();
    }

    /**
     * Work Around
     */
    public void testRentalStatusMethods()
    {
        super.testRentalStatusMethods();
    }
    
    /**
     * Work around
     */
    public void testSetDescriptionMethodValidation()
    {
        super.testSetDescriptionMethodValidation();
    }
    
    /**
     * work around
     */
    public void testSetWeeklyRateMethodValidation()
    {
        super.testSetWeeklyRateMethodValidation();
    }
    
    /**
     * Work around
     */
    public void testSetIdMethodValidation()
    {
        super.testSetIdMethodValidation();
    }
    
    /**
     * work around
     */
    public void testSuperEqualsMethod()
    {
        super.testEqualsMethod();
    }
}