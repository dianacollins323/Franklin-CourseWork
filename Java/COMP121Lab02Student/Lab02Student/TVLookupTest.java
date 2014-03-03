
/**
 * The test class TVLookupTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TVLookupTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class ItemLookupTest.
     */
    public TVLookupTest()
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
     * Test matching zeros and nulls
     */
    public void testMatchingNulls()
    {
        Lookup query = new TVLookup(0, null);
        assertFalse(query.matches(null));
    }
    
    /**
     * Test null types & equals sizes
     */
    public void testNullTypesEqualSizes()
    {
        Item a = new Television();
        Television tvA = (Television) a;
        Lookup query = new TVLookup(0, null);
        assertEquals(true, query.matches(tvA));
    }
    
    /**
     * Test null types unequal sizes
     */
    public void testNullTypesUnequalSizes()
    {
        Item a = new Television();
        Television tvA = (Television) a;
        tvA.setSize(10);
        Lookup query = new TVLookup(0, null);
        assertFalse(query.matches(tvA));
    }
    
    /**
     * Test equal types and sizes
     */
    public void testEqualTypesAndSizes()
    {
        Item a = new Television();
        Television tvA = (Television) a;
        tvA.setSize(10);
        tvA.setType("LCD");
        Lookup query = new TVLookup(10, "LCD");
        assertEquals(true, query.matches(tvA));
    }
    
    /**
     * Test equal types and unequal sizes
     */
    public void testEqualTypesUnequalSizes()
    {
        Item a = new Television();
        Television tvA = (Television) a;
        tvA.setSize(10);
        tvA.setType("LCD");
        Lookup query = new TVLookup(17, "LCD");
        assertFalse(query.matches(tvA));
    }
    
    /**
     * Test unequal types and equal sizes
     */
    public void testUnequalTypesEqualSizes()
    {
        Item a = new Television();
        Television tvA = (Television) a;
        tvA.setSize(40);
        tvA.setType("plasma");
        Lookup query = new TVLookup(40, "LCD");
        assertFalse(query.matches(tvA));
    }
    
    /**
     * Test unequal types and sizes
     */
    public void testUnequalTypesAndSizes()
    {
        Item a = new Television();
        Television tvA = (Television) a;
        tvA.setSize(10);
        tvA.setType("LCD");
        Lookup query = new TVLookup(40, "plasma");
        assertFalse(query.matches(tvA));
    }
    
    /**
     * Test null items
     */
    public void testNullItems()
    {
        Item a = new Television();
        Television tvA = (Television) a;
        Lookup query = new TVLookup(0, "plasma");
        assertFalse(query.matches(tvA));
    }
}