

/**
 * The test class DVDPlayerTest.
 *
 * @author  Diana Collins
 * @version 1.0 
 */
public class DVDPlayerTest extends junit.framework.TestCase
{
    DVDPlayer sonyBlueRay;
    DVDPlayer samsungDuelDisc;
    Money wklyRate;

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
        sonyBlueRay = new DVDPlayer();
        samsungDuelDisc = new DVDPlayer();
        wklyRate = new Dollar(5.55);
        sonyBlueRay.setWeeklyRate(wklyRate);
        samsungDuelDisc.setWeeklyRate(null);
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
     * test set/get descriiption
     */
    public void testDescription()
    {
        sonyBlueRay.setDescription("Sony Blue Ray Player");
        assertEquals("Sony Blue Ray Player", sonyBlueRay.getDescription());
    }
    
    /**
     * test setget weeklyrate Dollar
     */
    public void testWeeklyRate()
    {
        assertEquals(new Dollar(5.55), sonyBlueRay.getWeeklyRate());
    }
    
    /**
     * test setget weeklyrate null
     */
    public void testWeeklyRateNull()
    {
        assertNull(samsungDuelDisc.getWeeklyRate());
    }
    
    /**
     * test set/get id
     */
    public void testId()
    {
        sonyBlueRay.setId("1");
        assertEquals("1", sonyBlueRay.getId());
    }
    
    /**
     * test calculateFee
     */
    public void testCalculateFee()
    {
        sonyBlueRay.setWeeklyRate(wklyRate);
        assertEquals(new Dollar(11.10), sonyBlueRay.calculateFee(2));
    }
    
    /**
     * test 0 weeks
     */
    public void testCalcualteFeeZeroWeeks()
    {
        sonyBlueRay.setWeeklyRate(wklyRate);
        assertNull(sonyBlueRay.calculateFee(0));
    }
    
    /**
     * test >0 weeks
     */
    public void testCalculateFeeLessThanZero()
    {
        assertNull(sonyBlueRay.calculateFee(-1));
    }
    
    /**
     * test null weeklyRate
     */
    public void testCalculateFeeNullRate()
    {
        samsungDuelDisc.setWeeklyRate(null);
        assertNull(samsungDuelDisc.calculateFee(2));
    }
    
    /**
     * test isRented rented item
     */
    public void testIsRentedTrue()
    {
        sonyBlueRay.rented();
        assertTrue(sonyBlueRay.isRented());
    }
    
    /**
     * test isRented !rented item
     */
    public void testIsRentedFalse()
    {
        assertFalse(sonyBlueRay.isRented());
    }
    
    /**
     * test isRented returned item
     */
    public void testIsRentedReturned()
    {
        sonyBlueRay.rented();
        sonyBlueRay.returned();
        assertFalse(sonyBlueRay.isRented());
    }
}
