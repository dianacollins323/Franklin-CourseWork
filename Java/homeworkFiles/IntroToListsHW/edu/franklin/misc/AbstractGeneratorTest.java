package edu.franklin.misc;

/**
 * The test class AbstractGeneratorTest.
 *
 * @author Todd A. Whittaker
 * @version 2005-09
 */
public abstract class AbstractGeneratorTest extends junit.framework.TestCase
{
    /** The generator to build. */
    protected DataGenerator<Integer> generator;
    
    /** The count of items to build. */
    protected static final int COUNT = 15;
    
    /**
     *  Get the concrete implementation.
     *  @return the concrete implementation under test
     */
    protected abstract DataGenerator<Integer> getGenerator();
    
    /**
     * Default constructor for test class AbstractGeneratorTest.
     */
    public AbstractGeneratorTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
        generator = getGenerator();
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
     * Tests that the correct count of items is generated.
     */
    public void testGenerationCount()
    {
        int count = 0;
        while (generator.hasNext())
        {
            Integer result = generator.next();
            ++count;
        }
        assertEquals(count, COUNT);
    }
    
    /**
     * Tests that data is generated.
     */
    public void testGeneration()
    {
        while (generator.hasNext())
        {
            Integer result = generator.next();
            assertNotNull(result);
        }
    }
    
    /**
     * Tests that the same sequence is generated after a reset.
     */
    public void testReset()
    {
        int i = 0;
        Integer [] arr = new Integer[COUNT];

        i = 0;
        while (generator.hasNext())
        {
            arr[i++] = generator.next();
        }
        
        generator.reset();
        // should get same data back
        for (i = 0; i < arr.length; ++i)
        {
            assertEquals(arr[i], generator.next());
        }
        assertFalse(generator.hasNext());
    }
}
