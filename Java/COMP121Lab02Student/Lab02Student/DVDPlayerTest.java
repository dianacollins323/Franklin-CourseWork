
/**
 * The test class DVDPlayerTest.
 *
 * @author  Franklin University
 * @version Winter 2013
 */
public class DVDPlayerTest extends AbstractItemTest
{

    /**
     * Default constructor for test class DVDPlayerTest.
     */
    public DVDPlayerTest()
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
        return new DVDPlayer();
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
    public void testEqualsMethod()
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
