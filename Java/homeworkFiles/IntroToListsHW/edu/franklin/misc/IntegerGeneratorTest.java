package edu.franklin.misc;

/**
 * The test class IntegerGeneratorTest.
 *
 * @author Todd A. Whittaker
 * @version 2005-09
 */
public class IntegerGeneratorTest extends AbstractGeneratorTest
{
    /**
     * Default constructor for test class IntegerGeneratorTest.
     */
    public IntegerGeneratorTest()
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
     *  Get the concrete implementation IntegerGenerator.
     *  @return an IntegerGenerator
     */
    protected DataGenerator<Integer> getGenerator()
    {
        return new IntegerGenerator(COUNT);
    }
    
    /**
     * Tests that the sequence matches the expected.
     */
    public void testRightSequence()
    {
        for (int i = 0; i < COUNT; ++i)
        {
            assertEquals(i, generator.next().intValue());
        }
        assertFalse(generator.hasNext());
    }
}
