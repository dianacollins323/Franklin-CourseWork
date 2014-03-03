
/**
 * The test class TelelvisionTest.
 *
 * @author  Franklin University
 * @version Winter 2013
 */
public class TelevisionTest extends AbstractItemTest
{

    /**
     * Default constructor for test class TelevisionTest.
     */
    public TelevisionTest()
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
        return new Television();
    }

    /**
     * test equalsMethod to account for tv size and type
     */
    public void testTelevisionEqualsMethod()
    {
        AbstractItem queryItem = createItem();
        AbstractItem queryItem2 = createItem();
        //casting abstractItems to type television
        Television newItem = (Television) item;
        Television newQueryItem = (Television) queryItem;
        Television newQueryItem2 = (Television) queryItem2;
        
        //Test tvSize and tvType not set
        assertEquals(true, newItem.equals(newQueryItem));
        
        newItem.setSize(37);
        //Test newQueryItem tvSize not set
        assertFalse(newItem.equals(newQueryItem));
        
        newQueryItem.setSize(37);
        //Test matching tvSizes
        assertEquals(true, newItem.equals(newQueryItem));
        
        newQueryItem2.setType("plasma");
        //Test newItem tvType not set
        assertFalse(newItem.equals(newQueryItem2));
        
        newItem.setType("plasma");
        //Test newQueryItem tvType not set
        assertFalse(newItem.equals(newQueryItem));
        
        newQueryItem.setType("plasma");
        //Test matching televisions
        assertEquals(true, newItem.equals(newQueryItem));
    }
    
    /**
     * test setSize method too low
     */
    public void testSetSizeTooLow()
    {
        Television newItem = (Television) item;
        //test size < 5
        boolean exceptionThrown = false;
        try 
        {
            newItem.setSize(2);
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
    
    /**
     * test setSize method too high
     */
    public void testSetTooHigh()
    {
        Television newItem = (Television) item;
        //test size > 60
        boolean exceptionThrown = false;
        try 
        {
            newItem.setSize(62);
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
    
    /**
     * test 
     */
    public void testSetSizeTypeNull()
    {
        Television newItem = (Television) item;
        //test size 5 - 60 type null
        newItem.setSize(55);
    }
    
    /**
     * Test size > 5 and < 50
     */
    public void testSetSizeLCD()
    {
        Television newItem = (Television) item;
        newItem.setType("LCD");
        
        newItem.setSize(45);
        assertEquals(45, newItem.getSize());
        
        boolean exceptionThrown = false;
        try
        {
            newItem.setSize(55);
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
    
    /**
     * Test size > 37 and < 60
     */
    public void testSetSizePlasma()
    {
        Television newItem = (Television) item;
        newItem.setType("plasma");
        
        newItem.setSize(45);
        assertEquals(45, newItem.getSize());
        
        boolean exceptionThrown = false;
        try
        {
            newItem.setSize(25);
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
    
    /**
     * Test size and type mismatch
     */
    public void testSizeAndTypeMismatch()
    {
        Television newItem = (Television) item;
        //test size < 37 and plasma
        boolean exceptionThrown = false;
        newItem.setType("plasma");
        try 
        {
            newItem.setSize(27);
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
        
        //test size > 50 and LCD
        exceptionThrown = false;
        newItem.setType("LCD");
        try 
        {
            newItem.setSize(55);
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
    
    /**
     * Test setType method null param
     */
    public void testSetTypeMethodNullParam()
    {
        Television newItem = (Television) item;
        //test null param
        boolean exceptionThrown = false;
        try
        {
            newItem.setType(null);
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
    
    /**
     * Test setType empty param
     */
    public void testSetTypeMethodEmptyParam()
    {
        Television newItem = (Television) item;
        //test null param
        boolean exceptionThrown = false;
        try
        {
            newItem.setType("");
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
    
    /**
     * Test setType if size is zero
     */
    public void testSetTypeMethodSizeZero()
    {
        Television newItem = (Television) item;
        //test plasma
        newItem.setType("plasma");
        
        //test LCD
        newItem.setType("LCD");
        
        boolean exceptionThrown = false;
        //test other
        try
        {
            newItem.setType("other");
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
    
    /**
     * test setType size 37 - 50
     */
    public void testSetTypeSize37To50()
    {
        Television newItem = (Television) item;
        newItem.setSize(47);
        //test type plasma size 37 - 50
        newItem.setType("plasma");
        
        //test type LCD size 37 - 50
        newItem.setType("LCD");
        
        //test type other size 37 - 50
        boolean exceptionThrown = false;
        try
        {
            newItem.setType("other");
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
    
    /**
     * Test setType size < 37
     */
    public void testSEtTypeSizeLessThan37()
    {
        Television newItem = (Television) item;
        newItem.setSize(27);
        //test type plasma size < 37
        boolean exceptionThrown = false;
        try
        {
            newItem.setType("plasma");
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
        
        //test type LCD size < 37
        newItem.setType("LCD");
        
        //test type other size < 37
        exceptionThrown = false;
        try
        {
            newItem.setType("other");
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
    
    /**
     * Test setType > 50
     */
    public void testSetTypeGreaterThan50()
    {
        Television newItem = (Television) item;
        newItem.setSize(55);
        //test type plasma size > 50
        newItem.setType("plasma");
        
        //test type LCD size > 50
        boolean exceptionThrown = false;
        try
        {
            newItem.setType("LCD");
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
        
        //test type other size > 50
        exceptionThrown = false;
        try
        {
            newItem.setType("other");
        }
        catch (IllegalArgumentException e)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
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
    
    /**
     * Work around
    public void testLameness()
    {
        super.testAll();
    }
    */
}
