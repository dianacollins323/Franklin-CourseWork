package edu.franklin.util;
import edu.franklin.misc.DataGenerator;

/**
 * Test cases specific to the concrete class ArrayList.  Most of the
 * test cases for the concrete implementation are inherited from
 * AbstractListTest.
 *
 * @author  Todd A. Whittaker
 * @version 2005-09
 */
public class ArrayListTest extends AbstractListTest
{
    /**
     * Default constructor for test class ArrayListTest.
     */
    public ArrayListTest()
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
     * Creates a List containing data from the specified
     * generator.
     * @param <E> the type of data to generate
     * @param generator the object which will provide the data
     * @return an ArrayList containing the data
     */
    public <E> List<E> getList(DataGenerator<E> generator)
    {
        return getArrayList(generator);
    }
    
    /**
     * Creates an ArrayList containing data from the specified
     * generator.
     * @param <E> the type of data to generate
     * @param generator the object which will provide the data
     * @return an ArrayList containing the data
     */
    public <E> ArrayList<E> getArrayList(DataGenerator<E> generator)
    {
        ArrayList<E> list = new ArrayList<E>();
        while (generator.hasNext())
        {
            list.add(generator.next());
        }
        return list;
    }

    /**
     * Test our broken sub-list capabilities.
     */
    public void testSubList()
    {
        // note, right now our sublist doesn't work like the java.util.List
        List<Integer> list = getList(sequenceGenerator);
        List<Integer> front = list.subList(0, 3);
        List<Integer> middle = list.subList(3, 7);
        List<Integer> tail = list.subList(7, list.size());
        
        List<Integer> result = getList(emptyGenerator);
        result.addAll(front);
        result.addAll(middle);
        result.addAll(tail);
        
        assertEquals(list, result);
    }
    
    /**
     * Test cutting down the capacity of the backing array to just
     * the size of the list.
     */
    public void testTrimToSize()
    {
        ArrayList<Integer> list = new ArrayList<Integer>(100);
        for (int i = 0; i < 10; ++i)
        {
            list.add(new Integer(i));
        }
        
        list.trimToSize();
        assertEquals(getList(sequenceGenerator), list);
    }

    /**
     * Tests that ensureCapacity increments mod counts.
     */
    public void testForCoMods5()
    {
        ArrayList<Integer> list = getArrayList(sequenceGenerator);
        ListIterator<Integer> itr = list.listIterator();
        
        list.ensureCapacity(list.size());
        try
        {
            itr.remove();
            fail("Should have thrown ConcurrentModificationException");
        }
        catch (java.util.ConcurrentModificationException e)
        {
            assertTrue(true);
        }
    }
}
