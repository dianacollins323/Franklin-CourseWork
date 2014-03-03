package edu.franklin.util;
import edu.franklin.misc.DataGenerator;

/**
 * Test cases specific to the concrete class LinkedList.  Most of the
 * test cases for the concrete implementation are inherited from
 * AbstractListTest.
 *
 * @author  Todd A. Whittaker
 * @version 2005-09
 */
public class LinkedListTest extends AbstractListTest
{
    /**
     * Default constructor for test class LinkedListTest.
     */
    public LinkedListTest()
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
        return getLinkedList(generator);
    }
    
    /**
     * Creates an ArrayList containing data from the specified
     * generator.
     * @param <E> the type of data to generate
     * @param generator the object which will provide the data
     * @return an ArrayList containing the data
     */
    public <E> LinkedList<E> getLinkedList(DataGenerator<E> generator)
    {
        LinkedList<E> list = new LinkedList<E>();
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
     * Test adding to a list at the front.
     */
    public void testAddFirst()
    {
        LinkedList<Integer> list = getLinkedList(emptyGenerator);
        LinkedList<Integer> control = getLinkedList(sequenceGenerator);
        ListIterator<Integer> badIterator = list.listIterator();
        
        for (ListIterator<Integer> itr = control.listIterator(control.size());
            itr.hasPrevious(); )
        {
            list.addFirst(itr.previous());
        }
        assertEquals(control, list);

        try
        {
            badIterator.next();
            fail("Should have thrown ConcurrentModificationException");
        }
        catch (java.util.ConcurrentModificationException e)
        {
            assertTrue(true);
        }
    }
    
    /**
     * Test adding to the list at the end.
     */
    public void testAddLast()
    {
        LinkedList<Integer> list = getLinkedList(emptyGenerator);
        LinkedList<Integer> control = getLinkedList(sequenceGenerator);
        ListIterator<Integer> badIterator = list.listIterator();
        
        for (Integer element : control)
        {
            list.addLast(element);
        }
        assertEquals(control, list);
        
        try
        {
            badIterator.next();
            fail("Should have thrown ConcurrentModificationException");
        }
        catch (java.util.ConcurrentModificationException e)
        {
            assertTrue(true);
        }
    }
    
    /**
     * Test the linked list getFirst and getLast methods.
     */
    public void testGetFirstandLast()
    {
        LinkedList<Integer> list = getLinkedList(sequenceGenerator);
        assertEquals(0, list.getFirst().intValue());
        assertEquals(SEQUENCE_SIZE - 1, list.getLast().intValue());
        list.clear();

        try
        {
            list.getFirst();
            fail("Should have thrown NoSuchElementException");
        }
        catch (java.util.NoSuchElementException e)
        {
            assertTrue(true);
        }

        try
        {
            list.getLast();
            fail("Should have thrown NoSuchElementException");
        }
        catch (java.util.NoSuchElementException e)
        {
            assertTrue(true);
        }
    }
    
    /**
     * Tests removing the first and last elements in a linked list.
     */
    public void testRemoveFirstAndLast()
    {
        LinkedList<Integer> list = getLinkedList(sequenceGenerator);
        assertEquals(0, list.removeFirst().intValue());
        assertFalse(list.contains(new Integer(0)));
        assertEquals(SEQUENCE_SIZE - 1, list.size());

        assertEquals(SEQUENCE_SIZE - 1, list.removeLast().intValue());
        assertFalse(list.contains(new Integer(SEQUENCE_SIZE - 1)));
        assertEquals(SEQUENCE_SIZE - 2, list.size());
    }
}
