/**
 * The test class PayCheckTest.
 *
 * @author  Todd A. Whittaker
 * @version 2006-01
 */
public class PayCheckTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class PayCheckTest.
     */
    public PayCheckTest()
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
     * Test taht all fields in a check are present and correct.
     */
    public void testPayCheck()
    {
        PayCheck check = new PayCheck("Laura Bertrand", new Dollar(300),
            new Dollar(500), new Dollar(200));
        assertEquals("Laura Bertrand", check.getPayee());
        assertEquals(new Dollar(300), check.getNet());
        assertEquals(new Dollar(500), check.getGross());
        assertEquals(new Dollar(200), check.getTaxes());
        assertTrue(check.toString().indexOf("Laura Bertrand") >= 0);
        assertTrue(check.toString().indexOf(new Dollar(300).toString()) >= 0);
    }
}
