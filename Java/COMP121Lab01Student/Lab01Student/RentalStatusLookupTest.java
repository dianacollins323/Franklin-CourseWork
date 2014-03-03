

/**
 * The test class RentalStatusLookupTest. 
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RentalStatusLookupTest extends junit.framework.TestCase
{
    private ABCRentals abcInv;
    private DVDPlayer sonyBlueRay;
    private DVDPlayer sonyDuelDisc;
    private RentalStatusLookup testLookup;

    /**
     * Default constructor for test class RentalStatusLookupTest.
     */
    public RentalStatusLookupTest()
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
        sonyBlueRay.setDescription("Sony Blue Ray");
        abcInv = new ABCRentals();
        abcInv.add(sonyBlueRay);
        testLookup = new RentalStatusLookup(true);
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
     * test rented item matches !rented items
     */
    public void testMatchesNone()
    {
        assertFalse(testLookup.matches(sonyBlueRay));
    }
    
    /**
     * test tented matches 1 rented item not other !rented item
     */
    public void testMatchesSomething()
    {
        sonyBlueRay.rented();
        assertTrue(testLookup.matches(sonyBlueRay));
        assertFalse(testLookup.matches(sonyDuelDisc));
    }
}
