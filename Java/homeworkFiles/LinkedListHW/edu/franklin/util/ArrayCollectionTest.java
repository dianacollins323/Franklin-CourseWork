package edu.franklin.util;
import edu.franklin.misc.DataGenerator;

/**
 * Test cases specific to the concrete class ArrayCollection.  Most of the
 * test cases for the concrete implementation are inherited from
 * AbstractCollectionTest.
 *
 * @author Todd A. Whittaker
 * @version 2005-09
 */
public class ArrayCollectionTest extends AbstractCollectionTest
{
    /**
     * Default constructor for test class ArrayCollectionTest.
     */
    public ArrayCollectionTest()
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
     * Creates an ArrayCollection containing data from the specified
     * generator.
     * @param <E> the type of data to generate
     * @param generator the object which will provide the data
     * @return an ArrayCollection containing the data
     */
    public <E> Collection<E> getCollection(DataGenerator<E> generator)
    {
        Collection<E> coll = new ArrayCollection<E>();
        while (generator.hasNext())
        {
            coll.add(generator.next());
        }
        return coll;
    }

    /**
     * Try to create an ArrayCollection with a bad initial capacity.
     */
    public void testBadCreating()
    {
        try
        {
            Collection<Integer> coll = new ArrayCollection<Integer>(-10);
            fail("Should have trown an IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            assertTrue(true);
        }
    }
}
