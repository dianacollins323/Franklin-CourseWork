

/**
 * The test class IdLookupTest.
 *
 * @author  Franklin University
 * @version Winter 2013
 */
public class IdLookupTest extends junit.framework.TestCase
{
    private static final String IDNUM = "123";
    private static final String NUM = "456";
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
     * Test a matching nulls.
     */
    public void testNullMatch()
    {
        Item f = new Furniture();
        Lookup query = new IdLookup(null);
        assertTrue("Hint: matches() should have returned true " +
                   "when Item id is null and id passed into " +
                   "IdLookup constructor is null. ",
                   query.matches(f));
    }

    /**
     * Test a matching Item.
     */
    public void testMatch()
    {
        Item f = new Furniture();
        f.setId(IDNUM);
        Lookup query = new IdLookup(IDNUM);
        assertTrue("Hint: matches() should have returned true " +
                   "when Item id matches id passed into " +
                   "IdLookup constructor. ",
                   query.matches(f));
    }

    /**
     * Test when lookup id not set.
     */
    public void testNoMatchToNull()
    {
        Item dvd = new DVDPlayer();
        dvd.setId(IDNUM);
        Lookup query = new IdLookup(null);
        assertFalse("Hint: matches() should have returned false " +
                   "when id passed into IdLookup constructor is " +
                   "null and Item id is set. ",
                    query.matches(dvd));
    }

    /**
     * Test when item id not set.
     */
    public void testNoMatchToItemNull()
    {
        Item dvd = new DVDPlayer();
        Lookup query = new IdLookup(IDNUM);
        assertFalse("Hint: matches() should have returned false " +
                   "when Item id is null and id passed into IdLookup " +
                   "constructor is not null. ",
                    query.matches(dvd));
    }

    /**
     * Test non-matching Item with non-null id.
     */
    public void testNoMatchId()
    {
        Item tv = new Television();
        tv.setId(IDNUM);
        Lookup query = new IdLookup(NUM);
        assertFalse("Hint: matches() should have returned false " +
                   "when id passed into IdLookup constructor " +
                   "does not match Item id. ",
                    query.matches(tv));
    }
    
    /**
     * Test null Item
     */
    public void testNullItem()
    {
        Item tv = new Television();
        tv.setId(IDNUM);
        Lookup query = new IdLookup(NUM);
        assertFalse(query.matches(null));
    }
}
