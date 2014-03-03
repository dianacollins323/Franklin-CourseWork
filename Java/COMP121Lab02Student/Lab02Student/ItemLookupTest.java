
/**
 * The test class ItemLookupTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ItemLookupTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class ItemLookupTest.
     */
    public ItemLookupTest()
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
     * Test matching nulls
     */
    public void testMatchingNulls()
    {
        Lookup query = new ItemLookup(null);
        assertTrue(query.matches(null));
    }
    
    /**
     * Test null ItemLookup param
     */
    public void testNullItemLookupParam()
    {
        Item a = new Furniture();
        Lookup query = new ItemLookup(null);
        assertFalse(query.matches(a));
    }
    
    /**
     * Test null matches param
     */
    public void testNullMatchesParam()
    {
        Item a = new Furniture();
        Lookup query = new ItemLookup(a);
        assertFalse(query.matches(null));
    }
    
    /**
     * Test DVDPlayer matches
     */
    public void testDvdplayerMatches()
    {
        Item a = new DVDPlayer();
        Item b = new DVDPlayer();
        Lookup query = new ItemLookup(a);
        assertTrue(query.matches(b));
    }
    
    /**
     * Test DVDPlayer no match
     */
    public void testDvdplayerNoMatch()
    {
        Item a = new DVDPlayer();
        Item b = new DVDPlayer();
        a.setDescription("dvd player a");
        Lookup query = new ItemLookup(a);
        assertFalse(query.matches(b));
    }
    
    /**
     * Test Furniture matches
     */
    public void testFurnitureMatches()
    {
        Item a = new Furniture();
        Item b = new Furniture();
        Lookup query = new ItemLookup(a);
        assertTrue(query.matches(b));
    }
    
    /**
     * Test Furniture no match
     */
    public void testFurnitureNoMatch()
    {
        Item a = new Furniture();
        Item b = new Furniture();
        a.setDescription("furniture a");
        Lookup query = new ItemLookup(a);
        assertFalse(query.matches(b));
    }
    
    /**
     * Test Television matches
     */
    public void testTelevisionMatches()
    {
        Item a = new Television();
        Item b = new Television();
        Lookup query = new ItemLookup(a);
        assertTrue(query.matches(b));
    }
    
    /**
     * Test item no match
     */
    public void testFurnitureNoMatches()
    {
        Item a = new Television();
        Item b = new Television();
        a.setDescription("television a");
        Lookup query = new ItemLookup(a);
        assertFalse(query.matches(b));
    }
}
