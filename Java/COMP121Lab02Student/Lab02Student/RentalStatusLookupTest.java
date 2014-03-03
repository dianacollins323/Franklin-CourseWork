

/**
 * The test class RentalStatusLookupTest.
 *
 * @author  Franklin University
 * @version Winter 2013
 */
public class RentalStatusLookupTest extends junit.framework.TestCase
{
    private static final boolean TRUE = true;
    private static final boolean FALSE = false;
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
        // Set up
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
     * Test a matching rented Item.
     */
    public void testMatchRented()
    {
        Item f = new Furniture();
        f.rented();
        Lookup query = new RentalStatusLookup(TRUE);
        assertTrue("Hint: matches() should have returned true " +
                   "when Item was rented and true was passed into " +
                   "RentalStatusLookup constructor. ",
                   query.matches(f));
    }

    /**
     * Test a matching Item that is not rented.
     */
    public void testMatchNotRented()
    {
        Item dvd = new DVDPlayer();
        dvd.returned();
        Lookup query = new RentalStatusLookup(FALSE);
        assertTrue("Hint: matches() should have returned true " +
                   "when Item was not rented and false was passed " +
                   "into RentalStatusLookup constructor. ",
                   query.matches(dvd));

    }

    /**
     * Test non-matching rented Item.
     */
    public void testNoMatchRented()
    {
        Item tv = new Television();
        tv.rented();
        Lookup query = new RentalStatusLookup(FALSE);
        assertFalse("Hint: matches() should have returned false " +
                   "when Item was rented and false was passed " +
                   "into RentalStatusLookup constructor. ",
                    query.matches(tv));
    }

    /**
     * Test non-matching Item that is not rented.
     */
    public void testNoMatchNotRented()
    {
        Item tv = new Television();
        tv.returned();
        Lookup query = new RentalStatusLookup(TRUE);
        assertFalse("Hint: matches() should have returned false " +
                   "when Item was not rented and true was passed " +
                   "into RentalStatusLookup constructor. ",
                    query.matches(tv));
    }
    
    /**
     * Test null Item
     */
    public void testNullItem()
    {
        Item tv = new Television();
        tv.returned();
        Lookup query = new RentalStatusLookup(TRUE);
        assertFalse(query.matches(null));
    }
}
