

/**
 * The test class TelevisionTest. 
 *
 * @author  Diana Collins
 * @version 1.0
 */
public class TelevisionTest extends junit.framework.TestCase
{
    Television sony37Plasma;
    Television samsung42LED;
    Money wklyRate;

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
        sony37Plasma = new Television();
        samsung42LED = new Television();
        wklyRate = new Dollar(5.55);
        sony37Plasma.setWeeklyRate(wklyRate);
        samsung42LED.setWeeklyRate(null);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
        // Tear down
    }
    
    /**
     * test set/get description
     */
    public void testDescription()
    {
        sony37Plasma.setDescription("Sony 37in Plasma");
        assertEquals("Sony 37in Plasma", sony37Plasma.getDescription());
    }
    
    /**
     * test weeklyRate !null
     */
    public void testWeeklyRate()
    {
        assertEquals(new Dollar(5.55), sony37Plasma.getWeeklyRate());
    }
    
    /**
     * test weeklyRate null
     */
    public void testWeeklyRateNull()
    {
        assertNull(samsung42LED.getWeeklyRate());
    }
    
    /**
     * test set/get id
     */
    public void testId()
    {
        sony37Plasma.setId("1");
        assertEquals("1", sony37Plasma.getId());
    }
    
    /**
     * test weeks
     */
    public void testCalculateFee()
    {
        assertEquals(new Dollar(11.10), sony37Plasma.calculateFee(2));
    }
    
    /**
     * test 0 weeks
     */
    public void testCalculateFeeZero()
    {
        assertNull(sony37Plasma.calculateFee(0));
    }
    
    /**
     * test >0 weeks
     */
    public void testCalculateFeeLessThanZero()
    {
        assertNull(sony37Plasma.calculateFee(-1));
    }
    
    /**
     * test null rate
     */
    public void testCalculateFeeNull()
    {
        assertNull(samsung42LED.calculateFee(2));
    }
    
    /**
     * test set/get size
     */
    public void testSize()
    {
        sony37Plasma.setSize(37);
        assertEquals(37, sony37Plasma.getSize());
    }
    
    /**
     * test set/get type
     */
    public void testType()
    {
        sony37Plasma.setType("Plasma");
        assertEquals("Plasma", sony37Plasma.getType());
    }
    
    /**
     * test isRented rented item
     */
    public void testIsRented()
    {
        sony37Plasma.rented();
        assertTrue(sony37Plasma.isRented());
    }
    
    /**
     * test isRented !rented item
     */
    public void testIsRentedFalse()
    {
        assertFalse(sony37Plasma.isRented());
    }
    
    /**
     * test isRented returned item
     */
    public void testIsRentedReturned()
    {
        sony37Plasma.rented();
        sony37Plasma.returned();
        assertFalse(sony37Plasma.isRented());
    }
}
