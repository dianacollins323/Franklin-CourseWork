

/**
 * The test class FedTaxCalculatorTest.
 *
 * @author  Todd A. Whittaker
 * @version 2006-01
 */
public class FedTaxCalculatorTest extends junit.framework.TestCase
{
    private FedTaxCalculator calc = new FedTaxCalculator();
    
    /**
     * Default constructor for test class FedTaxCalculatorTest.
     */
    public FedTaxCalculatorTest()
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
     * Test the first tax bracket.
     */
    public void testBracketOne()
    {
        assertEquals(new Dollar(0),
            calc.calculateTaxes(new Dollar(200000), 0, 1));
    }
    
    /**
     * Test the second tax bracket.
     */
    public void testBracketTwo()
    {
        assertEquals(new Dollar(23500),
            calc.calculateTaxes(new Dollar(500000), 0, 1));
    }

    /**
     * Test the third tax bracket.
     */
    public void testBracketThree()
    {
        assertEquals(new Dollar(224500),
            calc.calculateTaxes(new Dollar(2000000), 0, 1));
    }

    /**
     * Test the fourth tax bracket.
     */
    public void testBracketFour()
    {
        assertEquals(new Dollar(859500),
            calc.calculateTaxes(new Dollar(5000000), 0, 1));
    }

    /**
     * Test the fifth tax bracket.
     */
    public void testBracketFive()
    {
        assertEquals(new Dollar(2200250),
            calc.calculateTaxes(new Dollar(10000000), 0, 1));
    }

    /**
     * Test the sixth tax bracket.
     */
    public void testBracketSix()
    {
        assertEquals(new Dollar(5240500),
            calc.calculateTaxes(new Dollar(20000000), 0, 1));
    }

    /**
     * Test the seventh tax bracket.
     */
    public void testBracketSeven()
    {
        assertEquals(new Dollar(15484000),
            calc.calculateTaxes(new Dollar(50000000), 0, 1));
    }
    
    /**
     * Test that dependents factor in to the calculation.
     */
    public void testAllowances()
    {
        assertEquals(new Dollar(305500),
            calc.calculateTaxes(new Dollar(3500000), 3, 1));
    }
}
