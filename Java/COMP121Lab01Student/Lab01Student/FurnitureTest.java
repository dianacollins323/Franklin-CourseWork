

/**
 * The test class FurnitureTest. 
 *
 * @author  Diana Collins
 * @version 1.0
 */
public class FurnitureTest extends junit.framework.TestCase
{
    Furniture oakSideTable;
    Furniture oakCoffeeTable;
    Furniture oakBookShelf;
    Money wklyRate;
    Money mnthlyRate;
    
    /**
     * Default constructor for test class FurnitureTest.
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
        oakSideTable = new Furniture();
        oakCoffeeTable = new Furniture();
        oakBookShelf = new Furniture();
        wklyRate = new Dollar(5.55);
        mnthlyRate = new Dollar(15.00);
        oakSideTable.setWeeklyRate(wklyRate);
        oakCoffeeTable.setWeeklyRate(null);
        oakBookShelf.setWeeklyRate(wklyRate);
        oakSideTable.setMonthlyRate(mnthlyRate);
        oakCoffeeTable.setMonthlyRate(mnthlyRate);
        oakBookShelf.setMonthlyRate(null);
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
        oakSideTable.setDescription("Sony 37in Plasma");
        assertEquals("Sony 37in Plasma", oakSideTable.getDescription());
    }
    
    /**
     * test weeklyRate Dollar
     */
    public void testWeeklyRate()
    {
        assertEquals(new Dollar(5.55), oakSideTable.getWeeklyRate());
    }
    
    /**
     * test weeklyRate null
     */
    public void testWeeklyRateNull()
    {
        assertNull(oakCoffeeTable.getWeeklyRate());
    }
    
    /**
     * test set/get id
     */
    public void testId()
    {
        oakSideTable.setId("1");
        assertEquals("1", oakSideTable.getId());
    }
    
    /**
     * test rent weeks only
     */
    public void testWeeklyCalculateFee()
    {
        assertEquals(new Dollar(11.10), oakSideTable.calculateFee(2));
    }
    
    /**
     * test rent 0 weeks
     */
    public void testCalculateFeeZeroWeeks()
    {
        assertNull(oakSideTable.calculateFee(0));
    }
    
    /**
     * test <0 weeks
     */
    public void testCalculateFeeLessThanZero()
    {
        assertNull(oakSideTable.calculateFee(-1));
    }
    
    /**
     * test rent null wklyRate rate
     */
    public void testWeeklyCalculateFeeNull()
    {
        assertNull(oakCoffeeTable.calculateFee(2));
    }
    
    /**
     * test rent months and weeks
     */
    public void testMonthlyCalculateFee()
    {
        assertEquals(new Dollar(20.55), oakSideTable.calculateFee(5));
    }
    
    /**
     * test rent null mnthlyRate
     */
    public void testMonthlyCalculateFeeNull()
    {
        assertNull(oakBookShelf.calculateFee(5));    
    }
    
    /**
     * test monthlyRate Dollar
     */
    public void testMonthlyRate()
    {
        oakSideTable.setWeeklyRate(mnthlyRate);
        assertEquals(new Dollar(15.00), oakSideTable.getWeeklyRate());
    }
    
    /**
     * test monthlyRate null
     */
    public void testMonthlyRateNull()
    {
        oakCoffeeTable.setWeeklyRate(null);
        assertNull(oakCoffeeTable.getWeeklyRate());
    }
    
    /**
     * test isRented rented item
     */
    public void testIsRented()
    {
        oakSideTable.rented();
        assertTrue(oakSideTable.isRented());
    }
    
    /**
     * test isRented !rented item
     */
    public void testIsRentedFalse()
    {
        assertFalse(oakSideTable.isRented());
    }
    
    /**
     * test isRented returned item
     */
    public void testIsRentedReturned()
    {
        oakSideTable.rented();
        oakSideTable.returned();
        assertFalse(oakSideTable.isRented());
    }
}
