

/**
 * The test class OrderTest.
 *
 * @author  Diana Collins
 * @version 1.0
 */
public class OrderTest extends junit.framework.TestCase
{
    private static final Money WKLY_RATE = new Dollar(10.00);
    private static final Money MNTHLY_RATE = new Dollar(35.00);

    /**
     * Default constructor for test class OrderTest.
     */
    public OrderTest()
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
        // Sets up the test fixture
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
        // Tears down the test fixture
    }
    
    /**
     * test valid constructor parameters
     */
    public void testConstructOrder()
    {
        //test valid constructor parameters
        Order validOrder = new Order("123", "Diana", "3305556677", 3);
        assertEquals("123", validOrder.getId());
        assertEquals("Diana", validOrder.getName());
        assertEquals("3305556677", validOrder.getPhoneNumber());
        assertEquals(3, validOrder.getWeeks());
    }
    
    /**
     * test invalid id parameters
     */
    public void testInvalidId()
    {
        boolean exThrown = false;
        Order id = null;
        //test empty id
        try
        {
            id = new Order("", "Diana", "3305556677", 3);
        }
        catch (IllegalArgumentException e)
        {
            exThrown = true;
        }
        assertEquals(true, exThrown);
        
        exThrown = false;
        //test null id
        try
        {
            id = new Order(null, "Diana", "3305556677", 3);
        }
        catch (IllegalArgumentException e)
        {
            exThrown = true;
        }
        assertEquals(true, exThrown);
        assertEquals(null, id);
    }
    
    /**
     * test invalid name parameters
     */
    public void testInvalidName()
    {
        boolean exThrown = false;
        Order name = null;
        //test empty name
        try
        {
            name = new Order("123", "", "3305556677", 3);
        }
        catch (IllegalArgumentException e)
        {
            exThrown = true;
        }
        assertEquals(true, exThrown);
        
        exThrown = false;
        //test null name
        try
        {
            name = new Order("123", null, "3305556677", 3);
        }
        catch (IllegalArgumentException e)
        {
            exThrown = true;
        }
        assertEquals(true, exThrown);
        assertEquals(null, name);
    }
    
    /**
     * test invalid phone parameters
     */
    public void testInvalidPhone()
    {
        boolean exThrown = false;
        Order phone = null;
        //test empty phone
        try
        {
            phone = new Order("123", "Diana", "", 3);
        }
        catch (IllegalArgumentException e)
        {
            exThrown = true;
        }
        assertEquals(true, exThrown);
        
        exThrown = false;
        //test null phone
        try
        {
            phone = new Order("123", "Diana", null, 3);
        }
        catch (IllegalArgumentException e)
        {
            exThrown = true;
        }
        assertEquals(true, exThrown);
        assertEquals(null, phone);
    }
    
    /**
     * test invalid weeks parameters
     */
    public void testInvalidWeeks()
    {
        boolean exThrown = false;
        Order weeks = null;
        //test negative weeks
        try
        {
            weeks = new Order("123", "Diana", "3305556677", -3);
        }
        catch (IllegalArgumentException e)
        {
            exThrown = true;
        }
        assertEquals(true, exThrown);
        
        exThrown = false;
        //test zero weeks
        try
        {
            weeks = new Order("123", "Diana", "3305556677", 0);
        }
        catch (IllegalArgumentException e)
        {
            exThrown = true;
        }
        assertEquals(true, exThrown);
        assertEquals(null, weeks);
    }
    
    /**
     * test getItems method
     */
    public void testGetItems()
    {
        Order validOrder = new Order("123", "Diana", "3305556677", 3);
        Furniture f = new Furniture();
        DVDPlayer dvd = new DVDPlayer();
        Television tv = new Television();
        validOrder.add(f);
        validOrder.add(dvd);
        validOrder.add(tv);
        assertEquals(3, validOrder.getItems().size());
    }
    
    /**
     * test getTotalFee method
     */
    public void testGetTotalFee()
    {
        Order weeklyOrder = new Order("123", "Diana", "3305556677", 3);
        Order monthlyOrder = new Order("123", "Diana", "3305556677", 5);
        Furniture f = new Furniture();
        DVDPlayer dvd = new DVDPlayer();
        Television tv = new Television();
        weeklyOrder.add(f);
        weeklyOrder.add(dvd);
        weeklyOrder.add(tv);
        monthlyOrder.add(f);
        monthlyOrder.add(dvd);
        monthlyOrder.add(tv);
        f.setWeeklyRate(WKLY_RATE);
        f.setMonthlyRate(MNTHLY_RATE);
        dvd.setWeeklyRate(WKLY_RATE);
        tv.setWeeklyRate(WKLY_RATE);
        assertEquals(new Dollar(90.00), weeklyOrder.getTotalFee());
        assertEquals(new Dollar(145.00), monthlyOrder.getTotalFee());
    }
    
    /**
     * test compareTo method
     * greater than
     */
    public void testGreaterThan()
    {
        Order weeklyOrder = new Order("123", "Diana", "3305556677", 3);
        Order monthlyOrder = new Order("123", "Diana", "3305556677", 5);
        Furniture f = new Furniture();
        DVDPlayer dvd = new DVDPlayer();
        Television tv = new Television();
        weeklyOrder.add(f);
        weeklyOrder.add(dvd);
        weeklyOrder.add(tv);
        monthlyOrder.add(f);
        monthlyOrder.add(dvd);
        monthlyOrder.add(tv);
        f.setWeeklyRate(WKLY_RATE);
        f.setMonthlyRate(MNTHLY_RATE);
        dvd.setWeeklyRate(WKLY_RATE);
        tv.setWeeklyRate(WKLY_RATE);
        assertEquals(1, monthlyOrder.compareTo(weeklyOrder));
    }
    
    /**
     * test compareTo method
     * less than
     */
    public void testLessThan()
    {
        Order weeklyOrder = new Order("123", "Diana", "3305556677", 3);
        Order monthlyOrder = new Order("123", "Diana", "3305556677", 5);
        Furniture f = new Furniture();
        DVDPlayer dvd = new DVDPlayer();
        Television tv = new Television();
        weeklyOrder.add(f);
        weeklyOrder.add(dvd);
        weeklyOrder.add(tv);
        monthlyOrder.add(f);
        monthlyOrder.add(dvd);
        monthlyOrder.add(tv);
        f.setWeeklyRate(WKLY_RATE);
        f.setMonthlyRate(MNTHLY_RATE);
        dvd.setWeeklyRate(WKLY_RATE);
        tv.setWeeklyRate(WKLY_RATE);
        assertEquals(-1, weeklyOrder.compareTo(monthlyOrder));
    }
    
    /**
     * test compareTo method
     * equal to
     */
    public void testEqualTo()
    {
        Order weeklyOrder = new Order("123", "Diana", "3305556677", 3);
        Order otherOrder = new Order("123", "Diana", "3305556677", 3);
        Furniture f = new Furniture();
        DVDPlayer dvd = new DVDPlayer();
        Television tv = new Television();
        weeklyOrder.add(f);
        weeklyOrder.add(dvd);
        weeklyOrder.add(tv);
        otherOrder.add(f);
        otherOrder.add(dvd);
        otherOrder.add(tv);
        f.setWeeklyRate(WKLY_RATE);
        f.setMonthlyRate(MNTHLY_RATE);
        dvd.setWeeklyRate(WKLY_RATE);
        tv.setWeeklyRate(WKLY_RATE);
        assertEquals(0, weeklyOrder.compareTo(otherOrder));
    }
}
