package edu.franklin.util;
import edu.franklin.misc.DataGenerator;

/**
 * Test cases specific to the concrete class LinkedCollection.  Most of the
 * test cases for the concrete implementation are inherited from
 * AbstractCollectionTest.
 *
 * @author Todd A. Whittaker
 * @version 2005-09
 */
public class LinkedCollectionTest extends AbstractCollectionTest
{
    /**
     * Default constructor for test class LinkedCollectionTest.
     */
    public LinkedCollectionTest()
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
     * Creates a LinkedCollection containing data from the specified
     * generator.
     * @param <E> the type of data to generate
     * @param generator the object which will provide the data
     * @return a LinkedCollection containing the data
     */
    public <E> Collection<E> getCollection(DataGenerator<E> generator)
    {
        Collection<E> coll = new LinkedCollection<E>();
        while (generator.hasNext())
        {
            coll.add(generator.next());
        }
        return coll;
    }
}
