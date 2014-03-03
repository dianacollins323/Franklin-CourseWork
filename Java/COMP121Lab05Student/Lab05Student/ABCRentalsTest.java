
/**
 * The test class ABCRentalsTest.
 *
 * @author  Franklin University
 * @version Winter 2013
 */
public class ABCRentalsTest extends junit.framework.TestCase
{
    private ABCRentals inventory;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FIVE = 5;
    private static final boolean TRUE = true;
    private static final boolean FALSE = false;
    private static final String ID1 = "1";
    private static final String ID2 = "2";
    private static final String ID3 = "3";
    private static final String ID301 = "301";
    private static final Money WKLY_RATE = new Dollar(10.00);
    private static final Money MNTHLY_RATE = new Dollar(35.00);
    private static final String PLASMA = "Plasma";
    private static final String PLASMA2 = "plasma";
    private static final String LCD = "LCD";
    private static final int SIZE_40 = 40;
    private static final int SIZE_45 = 45;
    private static final String DVD_DESC = "Sony Blu-Ray";
    private static final String FURNITURE_DESC = "Diningroom chair";
    private static final String TV_DESC = "Panasonic";
    private DVDPlayer dvdplayer;
    private Furniture furniture;
    private Television television;
    private static final String ID = "12345";
    private static final String NAME = "John Doe";
    private static final String PHONE = "123.456.7890";

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
        inventory = new ABCRentals();
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
     *
     * Test getting the item count when inventory is empty.
     */
    public void testNoItems()
    {
        assertEquals("Hint: getItemCount() should return 0 when " +
                     "inventory is empty. ",
                     ZERO, inventory.getItemCount());
    }

    /**
     * Test adding a null Item to inventory.
     */
    public void testAddNullItem()
    {
        // Test adding a null item to inventory
        assertFalse("Hint: add() should return false if item is null. ",
                    inventory.add(null));
        assertEquals("Hint: Item should not be incremented  if add() " +
                     "failed. ",
                     ZERO, inventory.getItemCount());
    }

    /**
     * Test adding a single Item of each type to inventory.
     */
    public void testAddItem()
    {
        // Test adding a furniture item to inventory
        Furniture f = new Furniture();
        assertTrue("Hint: add() should return true when a Furniture " +
                   "object is added to inventory. ",
                   inventory.add(f));
        assertEquals("Hint: getItemCount() should return 1 after " +
                     "first item is added to inventory. ",
                     ONE, inventory.getItemCount());
        assertEquals("Hint: add() should set the id of the first " +
                     "item added to inventory to 1. ",
                     ID1, f.getId());

        // Test adding a dvd player to inventory
        DVDPlayer dvd = new DVDPlayer();
        assertTrue("Hint: add() should return true when a dvd player " +
                   "is added to inventory. ",
                   inventory.add(dvd));
        assertEquals("Hint: getItemCount() should return 2 after " +
                     "second item is added to inventory. ",
                     TWO, inventory.getItemCount());
        assertEquals("Hint: add() should set the id of the second " +
                     "item added to inventory to 2. ",
                     ID2, dvd.getId());

        // Test adding a television to inventory
        Television tv = new Television();
        assertTrue("Hint: add() should return true when a television " +
                   "is added to inventory. ",
                   inventory.add(tv));
        assertEquals("Hint: getItemCount() should return 3 after " +
                     "second item is added to inventory. ",
                     THREE, inventory.getItemCount());
        assertEquals("Hint: add() should set the id of the third " +
                     "item added to inventory to 3. ",
                     ID3, tv.getId());
    }

    /**
     * Test the inventory limit.
     */
    public void testInventoryLimit()
    {
        // Test adding an item over the limit to inventory
        Furniture f;
        for (int i = 0; i < ABCRentals.MAX_ITEMS; i++)
        {
            f = new Furniture();
            assertTrue("Hint: add() should add up to 300 items " +
                       "to inventory. ",
                       inventory.add(f));
        }
        f = new Furniture();
        assertFalse("Hint: add() should return false if inventory " +
                    "limit is reached. ", inventory.add(f));
    }

    /**
     * Test finding items by id.
     */
    public void testQueryById()
    {
        Lookup query;
        Item [] items;
        // Test finding each type of item in inventory
        Furniture f = new Furniture();
        DVDPlayer dvd = new DVDPlayer();
        Television tv = new Television();
        inventory.add(f);
        inventory.add(dvd);
        inventory.add(tv);

        // Find the furniture item
        query = new IdLookup(f.getId());
        items = inventory.findItems(query);
        assertEquals("Hint: findItems() should return an array with length " +
                     "of 1 when doing a lookup by id. ",
                     ONE, items.length);
        assertEquals("Hint: findItems() returned array with incorrect " +
                     "item when doing a lookup by id. ",
                     f, items[0]);

        // Find the dvd player
        query = new IdLookup(dvd.getId());
        items = inventory.findItems(query);
        assertEquals("Hint: findItems() should return an array with length " +
                     "of 1 when doing a lookup by id. ",
                     ONE, items.length);
        assertEquals("Hint: findItems() returned array with incorrect " +
                     "item when doing a lookup by id. ",
                     dvd, items[0]);

        // Find the television
        query = new IdLookup(tv.getId());
        items = inventory.findItems(query);
        assertEquals("Hint: findItems() should return an array with length " +
                     "of 1 when doing a lookup by id. ",
                     ONE, items.length);
        assertEquals("Hint: findItems() returned array with incorrect " +
                     "item when doing a lookup by id. ",
                     tv, items[0]);
    }

    /**
     * Test finding items by rental status.
     */
    public void testQueryByRentalStatus()
    {
        Lookup query;
        Item [] items;

        // Test finding rented and non-rented items
        Furniture f = new Furniture();
        f.rented();
        DVDPlayer dvd = new DVDPlayer();
        dvd.returned();
        Television tv = new Television();
        tv.rented();
        inventory.add(f);
        inventory.add(dvd);

        // Find the furniture item that is rented
        query = new RentalStatusLookup(TRUE);
        items = inventory.findItems(query);
        assertEquals("Hint: findItems() returned an array with the wrong " +
                     "length when doing a lookup by rental status. ",
                     ONE, items.length);
        assertEquals("Hint: findItems() returned array with incorrect " +
                     "item when doing a lookup by rental status. ",
                     f, items[0]);

        // Find the dvd player that is not rented
        query = new RentalStatusLookup(FALSE);
        items = inventory.findItems(query);
        assertEquals("Hint: findItems() returned an array with the wrong " +
                     "length when doing a lookup by rental status. ",
                     ONE, items.length);
        assertEquals("Hint: findItems() returned array with incorrect " +
                     "item when doing a lookup by rental status. ",
                     dvd, items[0]);

        // Find multiple rented items
        inventory.add(tv);
        query = new RentalStatusLookup(TRUE);
        items = inventory.findItems(query);
        assertEquals("Hint: findItems() returned an array with the wrong " +
                     "length when doing a lookup by rental status. ",
                     TWO, items.length);
    }

    /**
     * Test queries that find no matching items.
     */
    public void testFindingNoItems()
    {
        Lookup query;
        Item [] items;

        Furniture f = new Furniture();
        f.rented();
        DVDPlayer dvd = new DVDPlayer();
        dvd.rented();

        // Lookup when inventory is empty
        query = new IdLookup(ID1);
        items = inventory.findItems(query);
        assertEquals("Hint: findItems() should return an array with a " +
                     "length of 0 when searching an empty inventory. ",
                     ZERO, items.length);

        query = new RentalStatusLookup(TRUE);
        items = inventory.findItems(query);
        assertEquals("Hint: findItems() should return an array with a " +
                     "length of 0 when searching an empty inventory. ",
                     ZERO, items.length);

        inventory.add(f);
        inventory.add(dvd);

        // Lookup an item that doesn't exist
        query = new IdLookup(ID3);
        items = inventory.findItems(query);
        assertEquals("Hint: findItems() should return an array with a " +
                     "length of 0 when searching for an item id that " +
                     "doesn't exist in inventory. ",
                     ZERO, items.length);

        // Find the dvd player that is not rented
        query = new RentalStatusLookup(FALSE);
        items = inventory.findItems(query);
        assertEquals("Hint: findItems() should return an array with a " +
                     "length of 0 when searching for a rental status that " +
                     "doesn't exist in inventory. ",
                     ZERO, items.length);
    }

    /**
     * Test removing each type of item from inventory.
     */
    public void testRemoveItem()
    {
        Lookup query;
        Item [] items;

        // Add three items to inventory
        Furniture f = new Furniture();
        DVDPlayer dvd = new DVDPlayer();
        Television tv = new Television();
        inventory.add(tv);
        inventory.add(f);
        inventory.add(dvd);

        // Test removing the middle item from inventory
        assertTrue("Hint: remove() should return true when an existing " +
                   "item is removed from inventory. ",
                   inventory.remove(ID2));
        assertEquals("Hint: The item count should be decremented when " +
                     "an item is removed from inventory. ",
                     TWO, inventory.getItemCount());
        query = new IdLookup(ID2);
        items = inventory.findItems(query);
        assertEquals("Hint: findItems() should return an array with a " +
                     "length of 0 when searching for an item " +
                     "that was removed from inventory",
                     ZERO, items.length);

        // Test removing the first from inventory
        assertTrue("Hint: remove() should return true when an existing " +
                   "item is removed from inventory. ",
                   inventory.remove(ID1));
        assertEquals("Hint: The item count should be decremented when " +
                     "an item is removed from inventory. ",
                     ONE, inventory.getItemCount());

        // Test removing the last from inventory
        assertTrue("Hint: remove() should return true when an existing " +
                   "item is removed from inventory. ",
                   inventory.remove(ID3));
        assertEquals("Hint: The item count should be decremented when " +
                     "an item is removed from inventory. ",
                     ZERO, inventory.getItemCount());
    }

    /**
     * Test removing items that don't exist.
     */
    public void testRemoveNoItem()
    {
        // Test removing from an empty inventory
        assertFalse("Hint: remove() should return false if inventory " +
                    "is empty. ",
                    inventory.remove(ID1));

        // Add an item but remove a different item
        Furniture f = new Furniture();
        inventory.add(f);
        assertFalse("Hint: remove() should return false if item is " +
                    "not in inventory. ",
                    inventory.remove(ID2));
        assertEquals("Hint: Item should not be decremented if remove() " +
                     "failed. ",
                     ONE, inventory.getItemCount());

        // Try to remove an item passing in null
        assertFalse("Hint: remove() should return false if item id " +
                    "is null. ",
                    inventory.remove(null));
        assertEquals("Hint: Item should not be decremented if remove() " +
                     "failed. ",
                     ONE, inventory.getItemCount());
    }

    /**
     * Test that item ids are not reused when removing items from inventory.
     */
    public void testIdsNotReused()
    {
        Furniture f = new Furniture();
        Television tv = new Television();
        DVDPlayer dvd = new DVDPlayer();

        // Test two items, removing one, and adding another
        inventory.add(f);
        inventory.add(tv);
        inventory.remove(f.getId());
        inventory.add(dvd);
        assertEquals("Hint: Items ids should not be reused when items " +
                     "are removed from inventory and then new items " +
                     "are added. ",
                     ID3, dvd.getId());
        assertEquals("Hint: Item count is incorrect when items are " +
                     "removed from inventory and then new items are " +
                     "added. ",
                     TWO, inventory.getItemCount());
    }

    /**
     * Test that an item can be added to inventory after an item was removed
     * from a full inventory.
     */
    public void testFillingInventory()
    {
        // Fill up inventory
        Furniture f;
        for (int i = 0; i < ABCRentals.MAX_ITEMS; i++)
        {
            f = new Furniture();
            inventory.add(f);
        }

        // Try adding an item
        f = new Furniture();
        assertFalse("Hint: add() should return false if inventory " +
                    "limit is reached. ", inventory.add(f));

        // Remove an item and try again
        inventory.remove(ID3);
        assertTrue("Hint: add() should add an item to inventory after an " +
                   "item was removed from a full inventory. ",
                   inventory.add(f));
        assertEquals("Hint: The id number is incorrect on an item added " +
                     "to inventory after an item was removed from a full " +
                     "inventory. ",
                     ID301, f.getId());
    }

    /**
     * Test renting items in inventory.
     */
    public void testRentingItems()
    {
        // First add items to inventory
        Furniture f = new Furniture();
        f.setWeeklyRate(WKLY_RATE);
        f.setMonthlyRate(MNTHLY_RATE);
        DVDPlayer dvd = new DVDPlayer();
        dvd.setWeeklyRate(WKLY_RATE);
        Television tv = new Television();
        tv.setWeeklyRate(WKLY_RATE);
        inventory.add(f);
        inventory.add(dvd);
        inventory.add(tv);

        // Test renting a tv for a week
        assertEquals("Hint: rent() should return a rental fee equal to " +
                     "the number of weeks multiplied by the weekly " +
                     "rental rate when renting a television. ",
                     WKLY_RATE,
                     inventory.rent(tv.getId(), ONE));
        assertTrue("Hint: rent() should cause the rental status to be " +
                   "set to true when a television is rented. ",
                   tv.isRented());

        // Test renting a dvd player for two weeks
        assertEquals("Hint: rent() should return a rental fee equal to " +
                     "the number of weeks multiplied by the weekly " +
                     "rental rate when renting a DVD player. ",
                     WKLY_RATE.mul(TWO),
                     inventory.rent(dvd.getId(), TWO));
        assertTrue("Hint: rent() should cause the rental status to be " +
                   "set to true when a DVD player is rented. ",
                   dvd.isRented());

        // Test renting furniture for five weeks
        assertEquals("Hint: rent() should return a rental fee equal to " +
                     "the monthly rate plus the weekly rate when " +
                     "renting furniture for five weeks. ",
                     MNTHLY_RATE.add(WKLY_RATE),
                     inventory.rent(f.getId(), FIVE));
        assertTrue("Hint: rent() should cause the rental status to be " +
                   "set to true when furniture is rented. ",
                   f.isRented());
    }

    /**
     * Test renting items in not in inventory or already rented.
     */
    public void testRentingNoItems()
    {
        // Test renting an item when there is no inventory
        assertNull("Hint: rent() should return null if there " +
                   "are no items in inventory. ",
                   inventory.rent(ID1, ONE));

        // Add an item to inventory
        Television tv = new Television();
        tv.setWeeklyRate(WKLY_RATE);
        tv.rented();
        inventory.add(tv);

        // Test renting an item not in inventory
        assertNull("Hint: rent() should return null if the" +
                   "item is not in inventory. ",
                   inventory.rent(ID2, ONE));

        // Test renting an item that is already rented
        assertNull("Hint: rent() should return null if the" +
                   "item is in inventory but is already rented. ",
                   inventory.rent(tv.getId(), ONE));
    }

    /**
     * Test restocking items in inventory.
     */
    public void testRestockingItems()
    {
        // First add items to inventory with status set to rented
        Furniture f = new Furniture();
        f.setWeeklyRate(WKLY_RATE);
        f.setMonthlyRate(MNTHLY_RATE);
        f.rented();
        DVDPlayer dvd = new DVDPlayer();
        dvd.setWeeklyRate(WKLY_RATE);
        dvd.rented();
        Television tv = new Television();
        tv.setWeeklyRate(WKLY_RATE);
        tv.rented();
        inventory.add(f);
        inventory.add(dvd);
        inventory.add(tv);

        // Test restocking a tv
        assertTrue("Hint: restock() should return true when restocking a " +
                     "television that was rented. ",
                     inventory.restock(tv.getId()));
        assertFalse("Hint: restock() should cause the rental status to be " +
                   "set to false when a television is restocked. ",
                   tv.isRented());

        // Test restocking a dvd player
        assertTrue("Hint: restock() should return true when restocking a " +
                     "DVD player that was rented. ",
                     inventory.restock(dvd.getId()));
        assertFalse("Hint: restock() should cause the rental status to be " +
                   "set to false when a DVD player is restocked. ",
                   dvd.isRented());

        // Test restocking furniture
        assertTrue("Hint: restock() should return true when restocking " +
                     "furniture that was rented. ",
                     inventory.restock(f.getId()));
        assertFalse("Hint: restock() should cause the rental status to be " +
                   "set to false when furniture is restocked. ",
                   f.isRented());
    }

    /**
     * Test renting items in not in inventory or not rented.
     */
    public void testRestockingNoItems()
    {
        // Test restocking an item when there is no inventory
        assertFalse("Hint: restock() should return false if there " +
                   "are no items in inventory. ",
                   inventory.restock(ID1));

        // Add an item to inventory
        Television tv = new Television();
        tv.setWeeklyRate(WKLY_RATE);
        inventory.add(tv);

        // Test restocking an item not in inventory
        assertFalse("Hint: restock() should return false if the" +
                   "item is not in inventory. ",
                   inventory.restock(ID2));

        // Test restocking an item in inventory that is not rented
        assertFalse("Hint: restock() should return false if the" +
                   "item is in inventory but is not rented. ",
                   inventory.restock(tv.getId()));
    }

    /**
     * Test finding television set by size and type.
     */
    public void testQueryBySizeAndType()
    {
        Lookup query;
        Item [] items;

        Television tv1 = new Television();
        tv1.setSize(SIZE_40);
        tv1.setType(PLASMA);
        Television tv2 = new Television();
        tv2.setSize(SIZE_40);
        tv2.setType(LCD);
        Television tv3 = new Television();
        tv2.setSize(SIZE_45);
        tv2.setType(PLASMA);
        Television tv4 = new Television();
        tv4.setSize(SIZE_40);
        tv4.setType(PLASMA2);
        Furniture f = new Furniture();
        DVDPlayer dvd = new DVDPlayer();
        inventory.add(tv1);
        inventory.add(f);
        inventory.add(tv2);
        inventory.add(dvd);
        inventory.add(tv3);
        inventory.add(tv4);

        // Find the 40 inch plasma televisions
        query = new TVLookup(SIZE_40, PLASMA);
        items = inventory.findItems(query);
        assertEquals("Hint: findItems() returned an array with the wrong " +
                     "length when doing a lookup for televisions. ",
                     TWO, items.length);
        assertEquals("Hint: findItems() returned array with incorrect " +
                     "items when doing a lookup by size and type. ",
                     tv1, items[0]);
        assertEquals("Hint: findItems() returned array with incorrect " +
                     "items when doing a lookup by size and type. ",
                     tv4, items[1]);
    }

    /**
     * Test finding an exact item.
     */
    public void testQueryByItem()
    {
        Lookup query;
        Item [] items;

        Television tv = new Television();
        Furniture f = new Furniture();
        DVDPlayer dvd = new DVDPlayer();
        inventory.add(tv);
        inventory.add(f);
        inventory.add(dvd);

        Furniture f2 = new Furniture();
        f2.setId(f.getId());

        // Find a matching furniture item
        query = new ItemLookup(f2);
        items = inventory.findItems(query);
        assertEquals("Hint: findItems() returned an array with the wrong " +
                     "length when doing an exact item match. ",
                     ONE, items.length);
        assertEquals("Hint: findItems() returned array with incorrect " +
                     "item when doing a lookup item. ",
                     f, items[0]);

    }

    /**
     * Test serialization and deserialization.
     */
    public void testWriteAndRead()
    {
        Item [] items = new Item[ABCRentals.MAX_ITEMS];

        for (int i = 0; i < ABCRentals.MAX_ITEMS; i++)
        {
            if (i % 3 == 0)
            {
                items[i] = new DVDPlayer();
                items[i].setDescription(DVD_DESC);
            }
            else if (i % 2 == 0)
            {
                items[i] = new Furniture();
                items[i].setDescription(FURNITURE_DESC);
                ((Furniture)items[i]).setMonthlyRate(MNTHLY_RATE);
            }
            else
            {
                items[i] = new Television();
                items[i].setDescription(TV_DESC);
            }
            items[i].setWeeklyRate(WKLY_RATE);
            inventory.add(items[i]);
        }
        String fileName = "Inventory.dat";

        ABCRentals duplicate = null;
        boolean caught = false;
        try
        {
            inventory.writeToFile(fileName);
            duplicate = ABCRentals.readFromFile(fileName);
        }
        catch (RuntimeException e)
        {
            caught = true;
        }

        assertFalse("Hint: Serializing and deserializing an ABCRentals " +
                 "object to and from a valid file " +
                 "should not cause an exception. ",
                 caught);
        assertNotNull("Hint: After reading in the inventory " +
                      "from a file, the inventory should not " +
                      "be null. ", duplicate);
        assertEquals("Hint: After reading in the inventory from a file, "
            + "the item count is incorrect.",
            inventory.getItemCount(),
            duplicate.getItemCount());

        RentalStatusLookup allNotRented = new RentalStatusLookup(false);
        Item [] newItems = duplicate.findItems(allNotRented);

        for (int i = 0; i < items.length; i++)
        {
            assertEquals("Hint: After reading in inventory from a file, " +
                "the contents of the inventory does not match " +
                "the original contents.",
                items[i],
                newItems[i]);
        }

    }

    /**
     * Test trying to write/read from bad file.
     */
    public void testBadFileName()
    {
        boolean caught = false;
        try {
            ABCRentals.readFromFile("foofoo.dat");
        }
        catch (RuntimeException ex) {
            caught = true;
        }
        assertTrue("Hint: readFromFile should throw unchecked " +
                   "exception when file does not exist. ", caught);

        caught = false;
        try {
            inventory.writeToFile(null);
        }
        catch (RuntimeException ex) {
            caught = true;
        }
        assertTrue("Hint: writeToFile should throw unchecked " +
                   "exception when file name is null. ", caught);

        caught = false;
        try {
            ABCRentals.readFromFile(null);
        }
        catch (RuntimeException ex) {
            caught = true;
        }
        assertTrue("Hint: readFromFile should throw unchecked " +
                   "exception when file name is null. ", caught);
    }

    /**
     * Test processOrder with no restocks.
     */
    public void testOrderNoRestock()
    {
        createAndAddItems();
        // Test processing an order with no items
        Order order = new Order(ID, NAME, PHONE, 1);
        assertTrue("Hint: processOrder() should return " +
                   "true when processing an order that " +
                   "has no items. ",
                   inventory.processOrder(order));

        // Test processing an order with items available
        order.add(furniture);
        order.add(dvdplayer);
        assertTrue("Hint: processOrder() should return " +
                   "true when processing an order with " +
                   "all items available. ",
                   inventory.processOrder(order));
        assertTrue("Hint: All items in a processed order " +
                   "should be marked as rented. ",
                   furniture.isRented());
        assertTrue("Hint: All items in a processed order " +
                   "should be marked as rented. ",
                   dvdplayer.isRented());

        // Test processing an order with no items available
        Order order2 = new Order(ID, NAME, PHONE, 2);
        order2.add(furniture);
        assertFalse("Hint: processOrder() should return " +
                   "false when processing an order that " +
                   "cannot be filled. ",
                   inventory.processOrder(order2));

        // Test procesing an order with some items available
        Order order3 = new Order(ID, NAME, PHONE, 3);
        order3.add(television);
        order3.add(dvdplayer);
        assertFalse("Hint: processOrder() should return " +
                   "false when processing an order that " +
                   "cannot be filled. ",
                   inventory.processOrder(order3));
        assertFalse("Hint: No items should be marked rented " +
                    "when processing an order that " +
                    "cannot be filled. ",
                    television.isRented());
    }

    /**
     * Test processOrder with restocks.
     */
    public void testOrderWithRestock()
    {
        createAndAddItems();
        // Test restocking an item that does not fill an order
        Order order = new Order(ID, NAME, PHONE, 1);
        order.add(dvdplayer);
        inventory.processOrder(order);
        Order order2 = new Order(ID, NAME, PHONE, 2);
        order2.add(furniture);
        order2.add(dvdplayer);
        inventory.processOrder(order2);
        assertFalse("Hint: processOrder() should return " +
                   "false when processing an order that " +
                   "cannot be filled. ",
                   inventory.processOrder(order2));
        television.rented();
        assertTrue("Hint: Restocking an item when there are " +
                   "saved orders should still work properly. ",
                   inventory.restock(television.getId()));
        assertFalse("Hint: Restocking an item not in a  " +
                     "saved order should have no effect. ",
                     furniture.isRented());

        assertTrue("Hint: Restocking an item when there are " +
                   "saved orders should still work properly. ",
                   inventory.restock(dvdplayer.getId()));
        assertTrue("Hint: Restocking an item that fills a  " +
                     "saved order should cause all items " +
                     "in the order to be rented. ",
                     furniture.isRented());
        assertTrue("Hint: Restocking an item that fills a  " +
                     "saved order should cause all items " +
                     "in the order to be rented. ",
                     dvdplayer.isRented());
    }

    /**
     * Test processOrder with priority based on fee.
     */
    public void testOrderPriority()
    {
        createAndAddItems();
        Order order = new Order(ID, NAME, PHONE, 1);
        order.add(dvdplayer);
        Order order2 = new Order(ID, NAME, PHONE, 2);
        order2.add(furniture);
        order2.add(dvdplayer);
        Order order3 = new Order(ID, NAME, PHONE, 3);
        order3.add(dvdplayer);
        order3.add(television);

        // Insure the orders can't be processed
        dvdplayer.rented();
        assertFalse("Hint: processOrder() should return " +
                   "false when processing an order that " +
                   "cannot be filled. ",
                   inventory.processOrder(order));
        assertFalse("Hint: processOrder() should return " +
                   "false when processing an order that " +
                   "cannot be filled. ",
                   inventory.processOrder(order3));
        assertFalse("Hint: processOrder() should return " +
                   "false when processing an order that " +
                   "cannot be filled. ",
                   inventory.processOrder(order2));

        inventory.restock(dvdplayer.getId());
        // The order with the television should be filled first
        assertTrue("Hint: Restocking an item that fills " +
                     "more than one save order " +
                     "should cause the order with the " +
                     "highest rental fee to be filled. ",
                     dvdplayer.isRented());
        assertTrue("Hint: Restocking an item that fills " +
                     "more than one save order " +
                     "should cause the order with the " +
                     "highest rental fee to be filled. ",
                     television.isRented());

        inventory.restock(dvdplayer.getId());
        // The order with the furniture should be filled next
        assertTrue("Hint: Restocking an item that fills " +
                     "more than one save order " +
                     "should cause the order with the " +
                     "highest rental fee to be filled. ",
                     dvdplayer.isRented());
        assertTrue("Hint: Restocking an item that fills " +
                     "more than one save order " +
                     "should cause the order with the " +
                     "highest rental fee to be filled. ",
                     furniture.isRented());

        inventory.restock(dvdplayer.getId());
        // The order with only the dvdplayer should be filled last
        assertTrue("Hint: Restocking an item that fills " +
                     "more than one save order " +
                     "should cause the order with the " +
                     "highest rental fee to be filled. ",
                     dvdplayer.isRented());
    }

    /**
     * A helper method that creates some items and adds them to inventory
     * to help test processing orders.
     */
    private void createAndAddItems()
    {
        dvdplayer = new DVDPlayer();
        dvdplayer.setDescription(DVD_DESC);
        dvdplayer.setWeeklyRate(WKLY_RATE);
        inventory.add(dvdplayer);
        furniture = new Furniture();
        furniture.setDescription(FURNITURE_DESC);
        furniture.setWeeklyRate(WKLY_RATE);
        furniture.setMonthlyRate(MNTHLY_RATE);
        inventory.add(furniture);
        television = new Television();
        television.setDescription(TV_DESC);
        television.setWeeklyRate(WKLY_RATE);
        television.setType(PLASMA);
        television.setSize(SIZE_40);
        inventory.add(television);
    }

    /**
     * Test using ApplianceAdapter
     */
    public void testUsingApplianceAdapter()
    {
        Appliance appliance = new Appliance();
        ApplianceAdapter adapter = new ApplianceAdapter(appliance);
        appliance.setBrand("brand");
        appliance.setModel("model");
        assertEquals("brand:model", adapter.getDescription());
        //test can be added
        assertEquals(true, inventory.add(adapter));
        //test inventory item count correct
        assertEquals(1, inventory.getItemCount());
        //test can be rented
        adapter.setWeeklyRate(new Dollar(1.00));
        assertEquals(new Dollar(1.00), inventory.rent("1", 1));
        assertEquals(true, adapter.isRented());
        //test can be restocked
        assertEquals(true, inventory.restock("1"));
        assertEquals(false, adapter.isRented());
        //test it can be removed
        assertEquals(true, inventory.remove("1"));
        assertEquals(0, inventory.getItemCount());
    }
    
    
}