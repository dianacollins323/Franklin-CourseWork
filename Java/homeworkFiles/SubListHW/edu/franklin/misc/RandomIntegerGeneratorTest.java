package edu.franklin.misc;

/**
 * The test class RandomIntegerGeneratorTest.
 *
 * @author Todd A. Whittaker
 * @version 2005-09
 */
public class RandomIntegerGeneratorTest extends AbstractGeneratorTest
{
    /**
     * Default constructor for test class RandomIntegerGeneratorTest.
     */
    public RandomIntegerGeneratorTest()
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
     *  Get the concrete implementation RandomIntegerGenerator.
     *  @return a RandomIntegerGenerator
     */
    protected DataGenerator<Integer> getGenerator()
    {
        return new RandomIntegerGenerator(COUNT);
    }
    
    /**
     * Tests that the sequence matches the expected.
     */
    public void testRightSequence()
    {
        long seed = 1234L;
        java.util.Random random = new java.util.Random(seed);
        generator = new RandomIntegerGenerator(seed, COUNT);
        
        for (int i = 0; i < COUNT; ++i)
        {
            assertEquals(random.nextInt(), generator.next().intValue());
        }
        assertFalse(generator.hasNext());
    }
}
