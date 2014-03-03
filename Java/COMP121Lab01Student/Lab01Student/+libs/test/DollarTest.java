/**
 * The test class DollarTest.
 *
 * @author  Todd A. Whittaker
 * @version 2006-01
 */
public class DollarTest extends junit.framework.TestCase
{
    private static final double DELTA = 1.0e-8;
    /**
     * Default constructor for test class DollarTest.
     */
    public DollarTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }
    
    /**
     * Test build a dollar.
     */
    public void testConstructors()
    {
        assertEquals(0.0, (new Dollar()).asDouble(), DELTA);
        assertEquals(1.0, (new Dollar(1, 0)).asDouble(), DELTA);
        assertEquals(1.5, (new Dollar(1, 50)).asDouble(), DELTA);
        assertEquals(1.5, (new Dollar(1.5)).asDouble(), DELTA);
        assertEquals(1.5, (new Dollar(new Dollar(1.5))).asDouble(), DELTA);
    }
    
    /**
     * Test the isNegative method.
     */
    public void testIsNegative()
    {
        assertTrue((new Dollar(-1, 0)).isNegative());
        assertFalse((new Dollar(1, 0)).isNegative());
    }
    
    /**
     * Test the equals method.
     */
    public void testEquals()
    {
        Dollar d1 = new Dollar(1, 50);
        Dollar d2 = new Dollar(1, 50);
        Dollar d3 = new Dollar(2, 50);
        assertTrue(d1.equals(d2));
        assertFalse(d2.equals(d3));
    }
    
    /**
     * Test the negate method.
     */
    public void testNeg()
    {
        Dollar d1 = new Dollar(150);
        Dollar d2 = new Dollar(-150);
        assertEquals(d1, d2.neg());
        assertEquals(d1.neg(), d2);
    }
    
    /**
     * Test the abs method.
     */
    public void testAbs()
    {
        Dollar d1 = new Dollar(150);
        Dollar d2 = new Dollar(-150);
        assertEquals(d1, d2.abs());
        assertEquals(d1, d1.abs());
    }
    
    /**
     * Test dividing money by money (yielding percentage).
     */
    public void testDivByMoney()
    {
        Dollar d1 = new Dollar(450);
        Dollar d2 = new Dollar(150);
        assertEquals(3.0, d1.div(d2), DELTA);
        assertEquals(1 / 3.0, d2.div(d1), DELTA);
    }
    
    /**
     * Test dividing money by double (yielding money).
     */
    public void testDivByDouble()
    {
        assertEquals(new Dollar(50), ((new Dollar(100)).div(2.0)));
        assertEquals(new Dollar(-50), ((new Dollar(100)).div(-2.0)));
    }
    
    /**
     * Test multiplying money by double (yielding money).
     */
    public void testMulByDouble()
    {
        assertEquals(new Dollar(450), ((new Dollar(150)).mul(3.0)));
        assertEquals(new Dollar(-450), ((new Dollar(-150)).mul(3.0)));
    }
    
    /**
     * Test adding money to money yielding money.
     */
    public void testAdd()
    {
        assertEquals(new Dollar(100), ((new Dollar(75)).add(new Dollar(25))));
    }
    
    /**
     * Test subtracting money from money yielding money.
     */
    public void testSub()
    {
        assertEquals(new Dollar(75), ((new Dollar(100)).sub(new Dollar(25))));
    }
}
