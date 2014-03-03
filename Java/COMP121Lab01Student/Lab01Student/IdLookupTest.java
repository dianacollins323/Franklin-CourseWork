

/**
 * The test class IdLookupTest.
 *
 * @author  Diana Collins   
 * @version 1.0
 */
public class IdLookupTest extends junit.framework.TestCase
{
    private ABCRentals abcInv;
    private DVDPlayer sonyBlueRay;
    private DVDPlayer sonyDuelDisc;
    private IdLookup testLookup;
    private IdLookup testLookupNull;
    
    /**
     * Default constructor for test class IdLookupTest.
     */
    public IdLookupTest()
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
        sonyDuelDisc = new DVDPlayer();
        sonyBlueRay.setDescription("Sony Blue Ray");
        sonyDuelDisc.setDescription("Sony Duel Disc");
        abcInv = new ABCRentals();
        abcInv.add(sonyBlueRay);
        abcInv.add(sonyDuelDisc);
        sonyDuelDisc.setId(null);
        testLookup = new IdLookup("1");
        testLookupNull = new IdLookup(null);
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
     * test item id matches
     */
    public void testMatchesIdMatch()
    {
        assertTrue(testLookup.matches(sonyBlueRay));
    }
    
    /**
     * test item id !match
     */
    public void testMatchesNoMatch()
    {
        assertFalse(testLookup.matches(sonyDuelDisc));
    }
    
    /**
     * test item id !match null id
     */
    public void testMatchesNullItem()
    {
        assertFalse(testLookupNull.matches(sonyBlueRay));    
    }
    
    /**
     * test null item id matches null id
     */
    public void testMatchesNullItemNullId()
    {
        assertTrue(testLookupNull.matches(sonyDuelDisc));
    }
}
