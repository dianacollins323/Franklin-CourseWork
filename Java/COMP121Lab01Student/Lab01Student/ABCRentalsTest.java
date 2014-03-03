

/**
 * The test class ABCRentalsTest.
 *
 * @author  Diana Collins
 * @version 1.0
 */
public class ABCRentalsTest extends junit.framework.TestCase
{
    private ABCRentals abcInv;
    private DVDPlayer sonyBlueRay;
    private Television sony37Plasma;
    private Furniture oakSideTable;
    private DVDPlayer samsung;
    private Lookup testQuery;

    /**
     * Default constructor for test class ABCRentalsTest.
     */
    public ABCRentalsTest()
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
        abcInv = new ABCRentals();
        sonyBlueRay = new DVDPlayer();
        sonyBlueRay.setDescription("Sony Blue Ray");
        sony37Plasma = new Television();
        sony37Plasma.setDescription("Sony 37in Plasma");
        oakSideTable = new Furniture();
        oakSideTable.setDescription("Oak Side Table");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
        //Tear down
    }
    
    /**
     * 
     */
    public void testABCRentalConstruction()
    {
        assertEquals(0, abcInv.getItemCount());
    }
    
    /**
     * test adding firt item
     */
    public void testAddFirstItem()
    {
        assertTrue(abcInv.add(sonyBlueRay));
    }
    
    /**
     * test adding more than one item
     */
    public void testAddSecondItem()
    {
        assertTrue(abcInv.add(sonyBlueRay));
        assertTrue(abcInv.add(sony37Plasma));
    }
    
    /**
     * test adding duplicate item description
     */
    public void testAddDuplicateDesc()
    {
        assertTrue(abcInv.add(oakSideTable));  
        assertFalse(abcInv.add(oakSideTable));
    }
    
    /**
     * test adding null item
     */
    public void testAddNullItem()
    {
        assertFalse(abcInv.add(samsung));
    }
    
    /**
     * test adding item to full inventory
     * the for loop fills the inventory array 
     * test adding an item to a full inventory
     */
    public void testAddFullInventory()
    {
        for (int i = 0; i <= 300; i++) {
            DVDPlayer tester = new DVDPlayer();
            tester.setDescription("tester" + i);
            abcInv.add(tester);
        }
        assertFalse(abcInv.add(sonyBlueRay));
    }
    
    /**
     * test removing item from full inventory
     * the for loop fills the inventory array
     * test adding a new item after removal
     */
    public void testRemoveAndAddToFullInv()
    {
        for (int i = 0; i <= 300; i++) {
            DVDPlayer tester = new DVDPlayer();
            tester.setDescription("tester" + i);
            abcInv.add(tester);
        }
        assertTrue(abcInv.remove("3"));
        assertTrue(abcInv.add(sony37Plasma));
    }
    
    /**
     * test removing 1 item
     */
    public void testRemovingOneItem()
    {
        abcInv.add(sonyBlueRay);
        abcInv.add(sony37Plasma);
        assertTrue(abcInv.remove("1"));
        assertEquals(1, abcInv.getItemCount());
    }
    
    /**
     * test removing more than 1 item
     */
    public void testRemovingTwoItems()
    {
        abcInv.add(sonyBlueRay);
        abcInv.add(sony37Plasma);
        abcInv.add(oakSideTable);
        abcInv.remove("1");
        abcInv.remove("3");
        assertEquals(1, abcInv.getItemCount());
    }
    
    /**
     * test removing not found item
     */
    public void testRemovingItemNotFound()
    {
        abcInv.add(sonyBlueRay);
        assertFalse(abcInv.remove("2"));
    }
    
    /**
     * test removing null item
     */
    public void testRemovingNullItem()
    {
        assertFalse(abcInv.remove(null));
    }
    
    /**
     * test getItemCount
     */
    public void testGetItemCount()
    {
        abcInv.add(sonyBlueRay);
        abcInv.add(sony37Plasma);
        abcInv.add(oakSideTable);
        assertEquals(3, abcInv.getItemCount());
    }
    
    /**
     * test findItems with an IdLookup query
     */
    public void testFindItemsId()
    {
        testQuery = new IdLookup("3");
        abcInv.add(sonyBlueRay);
        abcInv.add(sony37Plasma);
        abcInv.add(oakSideTable);
        assertEquals(1, abcInv.findItems(testQuery).length);
    }
    
    /**
     * test findItems with a RentalStatus query
     */
    public void testFindItemsRentalStatus()
    {
        testQuery = new RentalStatusLookup(false);
        abcInv.add(sonyBlueRay);
        abcInv.add(sony37Plasma);
        abcInv.add(oakSideTable);
        sony37Plasma.rented();
        assertEquals(2, abcInv.findItems(testQuery).length);
    }
    
    /**
     * test renting item that is found and !rented
     */
    public void testRent()
    {
        abcInv.add(sonyBlueRay);
        abcInv.add(sony37Plasma);
        sony37Plasma.setWeeklyRate(new Dollar(5.50));
        assertEquals(new Dollar(16.50), abcInv.rent("2", 3));
    }
    
    /**
     * test rent item already rented
     */
    public void testRentRentedItem()
    {
        abcInv.add(sonyBlueRay);
        abcInv.add(sony37Plasma);
        sony37Plasma.rented();
        assertNull(abcInv.rent("2", 3));
    }
    
    /**
     * test rent item !found
     */
    public void testRentRentedItemNotFound()
    {
        abcInv.add(sonyBlueRay);
        sony37Plasma.rented();
        assertNull(abcInv.rent("2", 3));
    }
    
    /**
     * test rent null id
     */
    public void testRentRentedNullId()
    {
        assertNull(abcInv.rent(null, 3));
    }
    
    /**
     * test rent zero weeks
     */
    public void testRentRentedZeroWeeks()
    {
        abcInv.add(sony37Plasma);
        sony37Plasma.rented();
        assertNull(abcInv.rent("1", 0));
    }
    
    /**
     * test rent item no rental rate
     */
    public void testRentItemNoRate()
    {
        abcInv.add(sony37Plasma);
        assertNull(abcInv.rent("1", 2));
    }
    
    /**
     * test restocking item already rented
     */
    public void testRestock()
    {
        abcInv.add(sonyBlueRay);
        abcInv.add(sony37Plasma);
        sony37Plasma.rented();
        assertTrue(abcInv.restock("2"));
    }
    
    /**
     * test restocking item !rented
     */
    public void testRestockUnrentedItem()
    {
        abcInv.add(sonyBlueRay);
        abcInv.add(sony37Plasma);
        assertFalse(abcInv.restock("2"));
    }
    
    /**
     * test restocking !found item
     */
    public void testRestockNotFoundItem()
    {
        abcInv.add(sonyBlueRay);
        assertFalse(abcInv.restock("2"));
    }
    
    /**
     * test restock null id
     */
    public void testRestockNull()
    {
        abcInv.add(sonyBlueRay);
        assertFalse(abcInv.restock(null));
    }
}