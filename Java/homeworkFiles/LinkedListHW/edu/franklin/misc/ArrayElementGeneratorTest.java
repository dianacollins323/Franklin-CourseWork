package edu.franklin.misc;

/**
 * The test class ArrayElementGeneratorTest.
 *
 * @author Todd A. Whittaker
 * @version 2005-09
 */
public class ArrayElementGeneratorTest extends AbstractGeneratorTest
{
    private Integer [] data = new Integer [] {
        5, 4, 3, 2, 1, 10, 9, 8, 7, 6, 15, 14, 13, 12, 11
    };
    
    /**
     * Default constructor for test class ArrayElementGeneratorTest.
     */
    public ArrayElementGeneratorTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
        super.setUp();
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
     *  Get the concrete implementation ArrayElementGenerator.
     *  @return an ArrayElementGenerator
     */
    protected DataGenerator<Integer> getGenerator()
    {
        return new ArrayElementGenerator<Integer>(data);
    }
    
    /**
     * Tests that the sequence matches the array data.
     */
    public void testRightSequence()
    {
        for (int i = 0; i < data.length; ++i)
        {
            assertEquals(data[i], generator.next());
        }
        assertFalse(generator.hasNext());
    }
}
